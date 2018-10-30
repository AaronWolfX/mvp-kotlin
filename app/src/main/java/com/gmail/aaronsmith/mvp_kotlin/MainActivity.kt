package com.gmail.aaronsmith.mvp_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.gmail.aaronsmith.mvp_kotlin.mvp.contract.HomeContract
import com.gmail.aaronsmith.mvp_kotlin.mvp.model.bean.HomeBean
import com.gmail.aaronsmith.mvp_kotlin.mvp.presenter.HomePresenter
import com.gmail.aaronsmith.mvp_kotlin.ui.adapter.HomeAdapter
import com.hazz.kotlinmvp.base.BaseActivity
import com.scwang.smartrefresh.header.MaterialHeader
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity(), HomeContract.View {

    private val mPresenter by lazy { HomePresenter() }

    private var mTitle = "标题"

    private var num = 1

    private var mHomeAdapter: HomeAdapter? = null

    private var loadingMore = false

    private var isRefresh = false

    private var mMaterialHeader: MaterialHeader? = null

    private val linearLayoutmanager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private val simpleDateFormat by lazy {
        SimpleDateFormat("- MM. dd, 'Brunch' -", Locale.ENGLISH)
    }


    override fun showError(msg: String, errorCode: Int) {

    }

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
