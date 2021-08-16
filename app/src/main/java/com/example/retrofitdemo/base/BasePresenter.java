package com.example.retrofitdemo.base;

public class BasePresenter<V extends IView> implements IPresenter<V> {

    @Override
    public void onAttach(V mvpView) {

    }

    @Override
    public void onDetach() {

    }
}
