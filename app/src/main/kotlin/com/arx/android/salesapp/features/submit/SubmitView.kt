package com.arx.android.salesapp.features.submit

import com.arx.android.salesapp.data.common.BaseResponse
import com.arx.android.salesapp.data.model.place.PlacePostParameter
import com.hannesdorfmann.mosby.mvp.MvpView
import retrofit2.Response
import rx.Observable
import rx.Subscription
import rx.functions.Action1
import rx.functions.Func1

/**
 * Created by esafirm on 6/29/16.
 */
interface SubmitView : MvpView {

	val addPhotoClick: rx.Observable<Boolean>
	val submitClick: rx.Observable<PlacePostParameter>

	val showSubmitted: Func1<Observable<Response<BaseResponse>>, Subscription>
	val showPhotoAdded: Func1<Observable<String>, Subscription>

	val showError: Action1<Throwable>
	fun showLoading(isShow: Boolean)
}