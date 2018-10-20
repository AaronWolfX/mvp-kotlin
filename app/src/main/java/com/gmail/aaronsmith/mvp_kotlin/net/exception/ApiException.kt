package com.hazz.kotlinmvp.net.exception

/**
 * @author Aaron Smith
 * @date 2018/10/10.
 * desc:
 */
class ApiException : RuntimeException {

    private var code: Int? = null


    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}