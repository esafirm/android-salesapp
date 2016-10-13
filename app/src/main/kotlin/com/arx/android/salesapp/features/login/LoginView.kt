package com.arx.android.salesapp.features.login

import com.arx.android.salesapp.data.common.BaseResponse
import com.hannesdorfmann.mosby.mvp.MvpView
import rx.Observable
import rx.Subscription
import rx.functions.Action1
import rx.functions.Func1

/**
 * Created by esafirm on 6/28/16.
 */
interface LoginView : MvpView {

  val login: Observable<String>
  val password: Observable<String>
  val signInClick: Observable<Unit>

  val signInEnable: Func1<Observable<Boolean>, Subscription>
  val signInResult: Func1<Observable<BaseResponse>, Subscription>

  val onError: Action1<Throwable>
}