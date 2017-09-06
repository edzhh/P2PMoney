package com.p2pmoney.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.p2pmoney.R;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class TouziFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view=inflater.inflate( R.layout.fragment_touzi,container,false);
    return view;
  }
}
