package com.zkys.yun.ihome.base;

public class BasePresenter <T extends IView> implements IPresenter<T>{

    protected  T mView;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
