package com.example.retrofitdemo.mvp.base;

public interface MvpPresenter<V extends MvpView> {
      void onAttach(V mvpView);
      void onDetach();
}