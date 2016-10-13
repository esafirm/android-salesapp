package com.arx.android.salesapp.utils.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import rx.Observable

open class RecyclerItemClickListener(context: Context, private val mListener: OnItemClickListener)
: RecyclerView.OnItemTouchListener {

	internal var mGestureDetector: GestureDetector

	init {
		mGestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
			override fun onSingleTapUp(e: MotionEvent): Boolean {
				return true
			}
		})
	}

	override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
		val childView = view.findChildViewUnder(e.x, e.y)
		if (childView != null && mGestureDetector.onTouchEvent(e)) {
			mListener.onItemClick(view.getChildAdapterPosition(childView), childView)
		}
		return false
	}

	override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {
	}

	override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
	}
}

fun RecyclerView.itemClicks(): Observable<Pair<Int, View>> {
	return Observable.create<Pair<Int, View>> {
		val onItemClick = object : OnItemClickListener {
			override fun onItemClick(position: Int, view: View) {
				it.onNext(Pair(position, view))
			}
		}
		addOnItemTouchListener(RecyclerItemClickListener(context, onItemClick))
	}
}


