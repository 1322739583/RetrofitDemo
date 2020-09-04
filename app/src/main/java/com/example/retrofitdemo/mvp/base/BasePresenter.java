package com.example.retrofitdemo.mvp.base;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V>{

    @Override
    public void onAttach(V mvpView) {

    }

    @Override
    public void onDetach() {

    }
}
