package com.p2pmoney.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.p2pmoney.R;
import com.p2pmoney.bean.Image;
import com.p2pmoney.bean.Index;
import com.p2pmoney.bean.Product;
import com.p2pmoney.common.AppNetConfig;
import com.p2pmoney.common.BaseFragment;
import com.p2pmoney.ui.MyScrollview;
import com.p2pmoney.util.UIUtils;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.List;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class HomeFragment extends BaseFragment {

  @BindView(R.id.title_left)
  ImageView titleLeft;
  @BindView(R.id.title_tv)
  TextView titleTv;
  @BindView(R.id.title_right)
  ImageView titleRight;
  @BindView(R.id.vp_barner)
  ViewPager vpBarner;
  @BindView(R.id.circle_barner)
  CirclePageIndicator circleBarner;
  @BindView(R.id.myscrollview)
  MyScrollview myscrollview;
  @BindView(R.id.p_progresss)
  com.p2pmoney.ui.RoundProgress pProgresss;

  private Index index;

  private int totalProgress;

  private Runnable runnable = new Runnable() {
    @Override
    public void run() {
      int tempProgress = 0;
      try {
        while (tempProgress <= totalProgress) {
          pProgresss.setProgress(tempProgress);
          tempProgress++;
          Thread.sleep(100);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  };


  @Override
  protected RequestParams getParams() {
    return new RequestParams();
  }

  @Override
  protected String getUrl() {
    return AppNetConfig.INDEX;
  }

  protected void initData(String content) {
    if (!TextUtils.isEmpty(content)) {
      index = new Index();
      JSONObject jsonObject = JSON.parseObject(content);
      String proInfo = jsonObject.getString("proInfo");
      Product product = JSON.parseObject(proInfo, Product.class);
      String imageArr = jsonObject.getString("imageArr");
      List<Image> imageList = JSON.parseArray(imageArr, Image.class);
      index.product = product;
      index.imageList = imageList;
      //适配数据
      vpBarner.setAdapter(new MyAdapter());
      //viewpager交给指示器
      circleBarner.setViewPager(vpBarner);
      totalProgress = Integer.parseInt(index.product.progress);
      new Thread(runnable).start();
    }
  }


  public void initTitle() {
    titleLeft.setVisibility(View.INVISIBLE);
    titleRight.setVisibility(View.INVISIBLE);
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_home;
  }


  private class MyAdapter extends PagerAdapter {

    @Override
    public int getCount() {
      return index.imageList == null ? 0 : index.imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      String imageUrl = index.imageList.get(position).IMAURL;
      ImageView imageView = new ImageView(getActivity());
      imageView.setScaleType(ScaleType.CENTER_CROP);
      Picasso.with(getActivity()).load(imageUrl).into(imageView);
      container.addView(imageView);
      return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }
  }

}