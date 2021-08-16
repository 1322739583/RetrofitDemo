package com.example.retrofitdemo.base;

public interface IView {
    /**
     * 显示加载，可选
     */
  default void showLoading(){

  }

    /**
     * 隐藏加载，可选
     */
   default void hideLoading(){

   }

    /**
     * 显示数据
     */
   void showData();
}
