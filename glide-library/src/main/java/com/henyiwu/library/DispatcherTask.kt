package com.henyiwu.library

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import java.io.InputStream
import java.net.URL
import java.util.concurrent.LinkedBlockingQueue

/**
 * 加载任务器
 */
class DispatcherTask(val requestQueue: LinkedBlockingQueue<RequestBuilder>) : Thread(){

    var handler: Handler = Handler(Looper.getMainLooper())

    override fun run() {
        super.run()

        // 只要线程没中断就一直轮询
        while (!isInterrupted) {
            val requestBuilder = requestQueue.take()
            placeHolder(requestBuilder)
            val bitmap = loadFromNet(requestBuilder)
            finalShow(requestBuilder, bitmap)
        }
    }

    private fun finalShow(requestBuilder: RequestBuilder?, bitmap: Bitmap?) {
        if (requestBuilder?.getImageView() != null
            && bitmap != null
            && requestBuilder.getImageView()?.tag == requestBuilder.getMd5Name()
        ) {
            handler.post {
                requestBuilder.getImageView()?.setImageBitmap(bitmap)
                if (requestBuilder.getRequestListener() != null) {
                    requestBuilder.getRequestListener()?.onResourceReady(bitmap)
                }
            }
        }
    }

    private fun loadFromNet(requestBuilder: RequestBuilder?): Bitmap? {
        val inputStream : InputStream
        var bitmap: Bitmap? = null
        try {
            val url = URL(requestBuilder?.getUrl())
            val conn = url.openConnection()
            inputStream = conn.getInputStream()
            bitmap = BitmapFactory.decodeStream(inputStream)

        } catch (e: Exception) {

        }
        return bitmap
    }

    private fun placeHolder(requestBuilder: RequestBuilder) {
        // 设置了占位图
        if (requestBuilder.getPlaceHolderId() > 0 && requestBuilder.getImageView() != null) {
            // 在主线程设置
            handler.post {
                requestBuilder.getImageView()?.setImageResource(requestBuilder.getPlaceHolderId())
            }
        }
    }
}