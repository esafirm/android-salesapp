package com.arx.android.salesapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import org.jetbrains.anko.forEachChild

/**
 * Created by esafirm on 6/29/16.
 */

fun ImageView.loadImage(url: String?): Unit {
	url?.let { Glide.with(context).load(url).into(this) }
}

fun View.isVisible(visible: Boolean): Unit {
	visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.inflater(): LayoutInflater {
	return LayoutInflater.from(this.context)
}

fun ViewGroup.children(): List<View> {
	val list = mutableListOf<View>()
	forEachChild { list.add(it) }
	return list
}
