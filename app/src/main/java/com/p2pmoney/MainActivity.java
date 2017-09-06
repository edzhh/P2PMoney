package com.p2pmoney;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.p2pmoney.fragment.HomeFragment;
import com.p2pmoney.fragment.MeFragment;
import com.p2pmoney.fragment.MoreFragment;
import com.p2pmoney.fragment.TouziFragment;

public class MainActivity extends Activity {


  @BindView(R.id.contentor)
  FrameLayout contentor;
  @BindView(R.id.iv_home)
  ImageView ivHome;
  @BindView(R.id.tv_home)
  TextView tvHome;
  @BindView(R.id.ll_home)
  LinearLayout llHome;
  @BindView(R.id.iv_touzi)
  ImageView ivTouzi;
  @BindView(R.id.tv_touzi)
  TextView tvTouzi;
  @BindView(R.id.ll_touzi)
  LinearLayout llTouzi;
  @BindView(R.id.iv_me)
  ImageView ivMe;
  @BindView(R.id.tv_me)
  TextView tvMe;
  @BindView(R.id.ll_me)
  LinearLayout llMe;
  @BindView(R.id.iv_more)
  ImageView ivMore;
  @BindView(R.id.tv_more)
  TextView tvMore;
  @BindView(R.id.ll_more)
  LinearLayout llMore;
  private HomeFragment homeFragment;
  private TouziFragment touziFaragment;
  private MeFragment meFragment;
  private MoreFragment moreFragment;
  private FragmentTransaction ft;

  SQLiteDatabase db;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    db = SQLiteDatabase.openOrCreateDatabase(
        this.getFilesDir().toString()
            + "/my.db3", null); // 
    Log.i(this.getFilesDir().toString(), "onCreate: ");
  }

  @OnClick({R.id.ll_home, R.id.ll_me, R.id.ll_more, R.id.ll_touzi})
  public void changTab(View view) {
    switch (view.getId()) {
      case R.id.ll_home:
        setSelect(0);
        break;
      case R.id.ll_touzi:
        setSelect(1);
        break;
      case R.id.ll_me:
        setSelect(2);
        break;
      case R.id.ll_more:
        setSelect(3);
        break;
    }

  }

  private void setSelect(int i) {

    FragmentManager fm = getFragmentManager();
    ft = fm.beginTransaction();
    reSetTab(i);
    switch (i) {
      case 0:
        if (homeFragment == null) {
          homeFragment = new HomeFragment();
        }

        ivHome.setImageResource(R.drawable.bid01);
        ft.replace(R.id.contentor, homeFragment).commit();
        break;
      case 1:
        if (touziFaragment == null) {
          touziFaragment = new TouziFragment();
        }

        ivTouzi.setImageResource(R.drawable.bid03);
        ft.replace(R.id.contentor, touziFaragment).commit();
        break;
      case 2:
        if (meFragment == null) {
          meFragment = new MeFragment();
        }

        ivMe.setImageResource(R.drawable.bid05);
        ft.replace(R.id.contentor, meFragment).commit();
        break;
      case 3:
        if (moreFragment == null) {
          moreFragment = new MoreFragment();
        }

        ivMore.setImageResource(R.drawable.bid07);
        ft.replace(R.id.contentor, moreFragment).commit();
        break;


    }
  }

  private void reSetTab(int i) {

    ivHome.setImageResource(R.drawable.bid02);
    ivTouzi.setImageResource(R.drawable.bid04);
    ivMe.setImageResource(R.drawable.bid06);
    ivMore.setImageResource(R.drawable.bid08);
  }

}
