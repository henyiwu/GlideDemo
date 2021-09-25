package com.henyiwu.library.listener

import android.graphics.Bitmap
import com.henyiwu.library.GlideException

interface RequestListener {

    fun onResourceReady(bitmap: Bitmap)

    fun onLoadFailed(exception: GlideException)
}