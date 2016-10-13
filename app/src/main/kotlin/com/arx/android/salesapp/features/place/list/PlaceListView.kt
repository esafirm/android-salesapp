package com.arx.android.salesapp.features.place.list

import com.arx.android.salesapp.data.model.place.Place
import com.hannesdorfmann.mosby.mvp.MvpView
import rx.Observable
import rx.Subscription
import rx.functions.Func1

/**
 * Created by esafirm on 6/28/16.
 */
interface PlaceListView : MvpView {

	val addClick: Observable<Unit>
	val itemClick: Observable<PlaceListAdapter.PlaceViewHolder>
	val pullToRefresh: Observable<Unit>

	val showContent: Func1<Observable<List<Place>>, Subscription>

	fun showError(throwable: Throwable?)
	fun showLoading(isLoading: Boolean)
}