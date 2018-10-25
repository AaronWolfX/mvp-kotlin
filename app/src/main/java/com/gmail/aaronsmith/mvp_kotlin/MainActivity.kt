package com.gmail.aaronsmith.mvp_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gmail.aaronsmith.mvp_kotlin.mvp.contract.HomeContract
import com.gmail.aaronsmith.mvp_kotlin.mvp.model.bean.HomeBean
import com.gmail.aaronsmith.mvp_kotlin.mvp.presenter.HomePresenter
import com.hazz.kotlinmvp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), HomeContract.View {

    private var mTitle = "标题"

    private var num = 1


    override fun showError(msg: String, errorCode: Int) {

    }


    private val mPresenter by lazy { HomePresenter() }

    init {
        mPresenter.attachView(this)
    }

    override fun setHomeData(homeBean: HomeBean) {

    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun layoutId(): Int = R.layout.activity_main

    override fun initData() {

    }

    override fun initView() {
//        mPresenter.requestHomeData(1)
    }

    override fun start() {
        mPresenter.requestHomeData(1)
    }


}
