package com.henyiwu.library

import android.content.Context
import com.henyiwu.library.manager.RequestManager

object Glide {

    /**
     * 初始化了一个请求管理类
     */
    fun with(context: Context) : RequestManager {
        return RequestManager.get(context)
    }

}