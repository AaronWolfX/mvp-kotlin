package com.gmail.aaronsmith.mvp_kotlin.net

import com.gmail.aaronsmith.mvp_kotlin.api.ApiService
import com.gmail.aaronsmith.mvp_kotlin.api.UrlConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.File

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
object RetrofitManager {
    val service: ApiService by lazy { }


    private fun getRetrofit(): Retrofit {
        // 获取retrofit 实例
        return Retrofit.Builder()
                .baseUrl(UrlConstant.BASE_URL)
                .client()
    }

    private fun getOkHttpCient(): OkHttpClient {
        //添加一个log拦截器，打印所有的log
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        //可以设置请求过滤的水平，bogy，basic，headers
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        //设置 请求的缓存的大小跟位置
        val cacheFile = File()
    }
}