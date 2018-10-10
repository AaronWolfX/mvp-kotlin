package com.gmail.aaronsmith.mvp_kotlin

import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃   神兽保佑
//    ┃　　　┃   代码无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
// 
/**
 * @author Aaron Smith
 * @date 2018/10/10.
 */
class MyApplication : Application() {
    private var refWatcher: RefWatcher? = null

    companion object {
        private val TAG = "MyApplication"

        var context: Context by Delegates.notNull()
            private set

        fun getRefWatch(context: Context): RefWatcher? {
            val myApplication = context.applicationContext as MyApplication
            return myApplication.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }


    private fun initConfig(){
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(7)
                .tag("hao_zz")
                .build()
        Logger.addLogAdapter(object :AndroidLogAdapter(formatStrategy){
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}