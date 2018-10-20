package com.hazz.kotlinmvp.base



/**
 * @author Aaron Smith
 * @date 2018/10/10.
 * desc: Presenter 基类
 */


interface IPresenter<in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}
