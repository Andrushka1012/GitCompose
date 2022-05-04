package com.example.gitcompose.utils

import android.os.Bundle
import android.os.Parcelable
import javax.inject.Singleton

class ArgsKeeper {
    val args: Bundle = Bundle()

    fun putParcelable(key: String, arg: Parcelable) {
        args.putParcelable(key, arg)
    }

    fun <T> getParcelable(key: String): T? {
        return args.getParcelable(key)
    }
}