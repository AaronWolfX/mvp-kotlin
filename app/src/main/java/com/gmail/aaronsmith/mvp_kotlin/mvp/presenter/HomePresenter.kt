package com.gmail.aaronsmith.mvp_kotlin.mvp.presenter

import com.gmail.aaronsmith.mvp_kotlin.mvp.contract.HomeContract
import com.gmail.aaronsmith.mvp_kotlin.mvp.model.HomeModel
import com.gmail.aaronsmith.mvp_kotlin.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.net.exception.ExceptionHandle

class HomePresenter :BasePresenter<HomeContract.View>(),HomeContract.Presenter {

    private var bannerHomeBean: HomeBean? = null

    private val homeModel:HomeModel by lazy {
        HomeModel()
    }

    override fun requestHomeData(num: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = homeModel.requestHomeData(num)
                .subscribe({homeBean ->
                   mRootView?.apply {
                       dismissLoading()
                       setHomeData(homeBean!!)
                   }
                },{ t->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                    }
                })
    }
}