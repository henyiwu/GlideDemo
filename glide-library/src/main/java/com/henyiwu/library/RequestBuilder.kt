package com.henyiwu.library

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import com.henyiwu.library.listener.RequestListener
import com.henyiwu.library.manager.RequestManager
import com.henyiwu.library.utils.Md5FileNameGenerator
import java.lang.ref.SoftReference

class RequestBuilder(val context: Context?) {

    private lateinit var url: String
    private lateinit var md5FileName: String
    private var placeHolderId: Int = 0
    private var imageView: SoftReference<ImageView>? = null
    private var listener: RequestListener? = null

    public fun load(url: String) : RequestBuilder {
        this.url = url
        // 写到本地的文件名需要统一格式
        this.md5FileName = Md5FileNameGenerator.generate(url)
        return this
    }

    public fun placeHolder(placeholderId: Int) : RequestBuilder {
        this.placeHolderId = placeholderId
        return this
    }

    /**
     * 拼装参数
     */
    public fun into(imageView: ImageView) {
        imageView.tag = md5FileName
        this.imageView = SoftReference(imageView)
        if (TextUtils.isEmpty(url)) {
            // 抛出异常
        }
        if (placeHolderId <= 0) {
            // 自由发挥
        }

        // 将包装好的请求对象体，添加到请求队列中
        RequestManager.addRequestQueue(this).dispatcher()
    }

    fun getPlaceHolderId(): Int {
        return this.placeHolderId
    }

    fun getImageView(): ImageView? {
        return this.imageView?.get()
    }

    fun getUrl() : String {
        return this.url
    }

    fun getMd5Name(): String {
        return this.md5FileName
    }

    fun setRequestListener(listener: RequestListener): RequestBuilder {
        this.listener = listener
        return this
    }

    fun getRequestListener(): RequestListener? {
        return listener
    }
}