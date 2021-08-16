package com.example.retrofitdemo.base;

public interface IPresenter<V extends IView> {
      void onAttach(V mvpView);
      void onDetach();
}