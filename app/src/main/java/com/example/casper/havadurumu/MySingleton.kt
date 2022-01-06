package com.example.casper.havadurumu

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley


class MySingleton private constructor(context: Context) {

    private var mRequestQueue: RequestQueue? = null

    val requestQueue: RequestQueue?
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(mCtx?.applicationContext)
            }
            return mRequestQueue
        }

    init {
        mCtx = context
        mRequestQueue = requestQueue
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue?.add(req)
    }

    companion object {
        private var mInstance: MySingleton? = null
        private var mCtx: Context? = null

        @Synchronized
        fun getInstance(context: Context?): MySingleton? {
            if (mInstance == null) {
                mInstance = MySingleton(context!!)
            }
            return mInstance
        }
    }
}