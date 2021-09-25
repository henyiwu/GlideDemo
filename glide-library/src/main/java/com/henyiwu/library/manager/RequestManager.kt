package com.henyiwu.library.manager

import android.content.Context
import com.henyiwu.library.DispatcherTask
import com.henyiwu.library.RequestBuilder
import java.util.concurrent.LinkedBlockingQueue

/**
 * 请求管理类
 */
object RequestManager {

    private var url: String? = null
    private var context: Context? = null
    // 并发时保障线程安全
    private val requestQueue: LinkedBlockingQueue<RequestBuilder> = LinkedBlockingQueue()
    private lateinit var tasks: Array<DispatcherTask?>

    fun get(context: Context): RequestManager {
        RequestManager.context = context
        return this
    }

    // Glide 源码4.9.0中，
    // 构造方法实现了：error显示错误图，placeholder展位图初始化
    // 源码中请求管理类是通过Factory创建出来的

    fun load(url: String): RequestBuilder {
        return RequestBuilder(context).load(url)
    }

    fun addRequestQueue(requestBuilder: RequestBuilder) : RequestManager{
        if (!requestQueue.contains(requestBuilder)) {
            requestQueue.add(requestBuilder)
        }
        return this
    }

    fun dispatcher() {
        // 获取虚拟机可用的最大处理器数，但不小于一个
        val threadCount = Runtime.getRuntime().availableProcessors()
        tasks = arrayOfNulls(threadCount)
        for (item in tasks.withIndex()) {
            val task = DispatcherTask(requestQueue)
            task.start()
            tasks[item.index] = task
        }
    }

    fun stop() {
        if (tasks.isNotEmpty()) {
            for (item in tasks) {
                if (false == item?.isInterrupted) {
                    item.interrupt()
                }
            }
        }
    }
}