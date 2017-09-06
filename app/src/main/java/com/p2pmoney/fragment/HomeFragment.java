package com.p2pmoney.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.p2pmoney.R;
import com.p2pmoney.util.UIUtils;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class HomeFragment extends Fragment {

  @BindView(R.id.title_left)
  ImageView titleLeft;
  @BindView(R.id.title_tv)
  TextView titleTv;
  @BindView(R.id.title_right)
  ImageView titleRight;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    Log.i("HomeFragment", ":-------------------------------------------------");
    View view = UIUtils.getXmlView(R.layout.fragment_home);
    ButterKnife.bind(this, view);
    initTitle();
    return view;
  }

  public void initTitle() {
    titleLeft.setVisibility(View.INVISIBLE);
    titleRight.setVisibility(View.INVISIBLE);
  }

}