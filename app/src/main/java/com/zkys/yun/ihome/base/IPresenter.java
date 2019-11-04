package com.zkys.yun.ihome.base;

public interface IPresenter <T extends IView> {

    void attachView(T view);
    void detachView();

}
