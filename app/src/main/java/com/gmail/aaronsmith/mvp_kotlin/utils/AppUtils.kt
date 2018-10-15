package com.gmail.aaronsmith.mvp_kotlin.utils

import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

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
 * @date 2018/10/15.
 */
class AppUtils private constructor() {

    /**
     * 构造函数 private constructor() 只能传入参数
     * 其他初始化语句就在init中写
     */
    init {
        throw Error("Do not need instantiate!")
    }

    /**
     * 静态变量和方法
     */
    companion object {

        private val DEBUG = true
        private val TAG = "AppUtils"

        /**
         * 得到软件版本号
         *
         * @param context 上下文
         * @return 当前版本Code
         */
        fun getVerCode(context: Context): Int {
            var verCode = -1
            //找不到包名，需要捕获异常？？？
            try {
                val packageName = context.packageName
                verCode = context.packageManager
                        .getPackageInfo(packageName, 0).versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return verCode
        }

        /**
         * 获取应用运行的最大内存
         *
         * @return 最大内存
         */
        val maxMemory: Long
            get() = Runtime.getRuntime().maxMemory() / 1024

        /**
         * 得到软件显示版本信息
         *
         * @param context 上下文
         * @return 当前版本信息
         */
        fun getVerName(context: Context): String {
            var verName = ""
            try {
                val packageName = context.packageName
                verName = context.packageManager
                        .getPackageInfo(packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return verName
        }

        /**
         * 获取设备的可用内存大小
         *
         * @param context 应用上下文对象
         * @return 当前内存大小
         */
        fun getDeviceUsableMemory(context: Context): Int {
            val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val mi = ActivityManager.MemoryInfo()
            am.getMemoryInfo(mi)
            //返回当前系统可用内存
            return (mi.availMem / (1024 * 1024)).toInt()
        }

        fun getMobileModel(): String {
            var model: String? = Build.MODEL
            model = model?.trim { it <= ' ' } ?: ""
            return model
        }

        val sdkVersion: Int
            get() = android.os.Build.VERSION.SDK_INT
    }


}