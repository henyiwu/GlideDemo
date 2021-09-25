package com.henyiwu.demoglide

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.henyiwu.library.Glide
import com.henyiwu.library.GlideException
import com.henyiwu.library.listener.RequestListener

class MainActivity : AppCompatActivity() {

    val url = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20191218%2Fd46eb65b6bf44a3ba07991996c6ae15e.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1635155018&t=625ae2fbb53049d8d9915db7fa587753"
    lateinit var imageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        Glide
            .with(this)
            .load(url)
            .placeHolder(R.mipmap.placeholder)
            .setRequestListener(object : RequestListener {
                override fun onResourceReady(bitmap: Bitmap) {

                }

                override fun onLoadFailed(exception: GlideException) {

                }
            })
            .into(imageView)
    }
}