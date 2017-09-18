package com.p2pmoney.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Unbinder;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;
import com.p2pmoney.ui.LoadingPage;
import com.p2pmoney.util.UIUtils;

/**
 * Created by Administrator on 2015/12/14.
 */
public abstract class BaseFragment extends Fragment {

    private LoadingPage loadingPage;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadingPage = new LoadingPage(container.getContext()) {
            @Override
            public int LayoutId() {
                return getLayoutId();
            }

            @Override
            protected void OnSuccess(ResultState resultState, View successView) {
                ButterKnife.bind(BaseFragment.this, successView);
                initTitle();
                unbinder = ButterKnife.bind(this, successView);
                initData(resultState.getContent());
            }

            @Override
            protected RequestParams params() {
                return getParams();
            }

            @Override
            protected String url() {
                return getUrl();
            }
        };
        return loadingPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                show();
            }
        }, 1000);
    }

    protected abstract RequestParams getParams();

    protected abstract String getUrl();

    protected abstract void initData(String content);

    protected abstract void initTitle();

    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!=null)
            unbinder.unbind();
    }

    public void show() {
        loadingPage.show();
    }
}
