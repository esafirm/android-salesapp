package com.arx.android.salesapp.utils

import android.os.Bundle
import android.os.Parcelable

/**
 * Created by esafirm on 6/29/16.
 */

fun Parcelable.toBundle(): Bundle {
	return BundleBuilder(Bundle()).putParcelable(javaClass.simpleName, this).build()
}

inline fun <reified T: Parcelable> Bundle.parcelable(): T{
	return getParcelable(T::class.java.simpleName)
}
