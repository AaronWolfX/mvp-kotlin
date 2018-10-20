package com.gmail.aaronsmith.mvp_kotlin.mvp.model

import com.gmail.aaronsmith.mvp_kotlin.mvp.model.bean.HomeBean
import com.gmail.aaronsmith.mvp_kotlin.net.RetrofitManager
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

class HomeModel {

    fun requestHomeData(num:Int): Observable<HomeBean> {
        return RetrofitManager.service.getFirstHomeData(num)
                .compose(SchedulerUtils.ioToMain())
    }
}