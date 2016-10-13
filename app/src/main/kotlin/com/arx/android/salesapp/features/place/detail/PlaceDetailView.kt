package com.arx.android.salesapp.features.place.detail

import com.arx.android.salesapp.data.model.place.Place
import com.hannesdorfmann.mosby.mvp.MvpView
import rx.Observable
import rx.Subscription
import rx.functions.Func1

/**
 * Created by esafirm on 6/29/16.
 */
interface PlaceDetailView : MvpView{
	val showPlace: Func1<Observable<Place>, Subscription>
}