package com.arx.android.salesapp.ui.base

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arx.android.salesapp.utils.PageHelper
import rx.Observable
import rx.android.MainThreadSubscription
import java.util.*

abstract class BaseListRecyclerAdapter<T, VH : RecyclerView.ViewHolder>(protected var mContext: Context) : RecyclerView.Adapter<VH>() {

	private val mListData = ArrayList<T>()
	protected var mInflater: LayoutInflater

	private var mOnItemClickListener: OnItemClickListener? = null

	init {
		this.mInflater = LayoutInflater.from(mContext)
	}

	abstract fun onCreate(view: View, viewType: Int): VH

	@LayoutRes abstract fun getLayoutResId(viewType: Int): Int

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
		return onCreate(mInflater.inflate(getLayoutResId(viewType), parent, false), viewType)
	}

	override fun onBindViewHolder(holder: VH, position: Int) {
		holder.itemView.setOnClickListener {
			if (mOnItemClickListener != null) mOnItemClickListener!!.onItemClick(holder)
		}
		onBind(holder, mListData[position], position)
	}

	protected abstract fun onBind(viewHolder: VH, place: T, position: Int)

	override fun getItemCount(): Int {
		return mListData.size
	}

	fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
		mOnItemClickListener = onItemClickListener
	}

	val listData: List<T>
		get() = mListData

	fun getItem(position: Int): T {
		return mListData[position]
	}

	@JvmOverloads fun pushData(listData: List<T>, pageHelper: PageHelper? = null) {
		val isClearData = pageHelper == null || pageHelper.isFirstPage
		if (isClearData) {
			if (!mListData.isEmpty()) {
				mListData.clear()
			}
		}

		mListData.addAll(listData)
		if (isClearData) {
			notifyDataSetChanged()
		} else {
			notifyItemRangeInserted(
					Math.max(0, mListData.size - listData.size),
					listData.size)
		}
	}

	fun clear() {
		mListData.clear()
		notifyDataSetChanged()
	}

	@Suppress("UNCHECKED_CAST")
	fun itemClicks(): rx.Observable<VH> {
		return Observable.create {
			val subscriber = it
			setOnItemClickListener(OnItemClickListener {
				subscriber.onNext(it as VH)
			})

			subscriber.add(object : MainThreadSubscription(){
				override fun onUnsubscribe() {
					setOnItemClickListener(null)
				}
			})
		}
	}
}