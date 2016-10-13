package com.arx.android.salesapp.ui.base

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter
import com.hannesdorfmann.mosby.mvp.MvpView
import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Created by esafirm on 6/28/16.
 */
open class BasePresenter<V : MvpView> : MvpNullObjectBasePresenter<V>(){
  protected operator fun CompositeSubscription.plusAssign(subscription: Subscription) = this.add(subscription)
}