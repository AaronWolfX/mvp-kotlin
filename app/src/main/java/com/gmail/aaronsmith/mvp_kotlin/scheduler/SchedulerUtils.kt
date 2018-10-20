package com.hazz.kotlinmvp.rx.scheduler

/**
 * @author Aaron Smith
 * @date 2018/10/10.
 * desc:
 */

object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}
