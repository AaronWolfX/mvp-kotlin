package com.gmail.aaronsmith.mvp_kotlin.mvp.contract

import com.gmail.aaronsmith.mvp_kotlin.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter

interface HomeContract {

    interface View:IBaseView{
        fun setHomeData(homeBean: HomeBean)

        fun showError(msg: String,errorCode:Int)
    }

    interface Presenter:IPresenter<View>{
        fun requestHomeData(num:Int)
    }
}