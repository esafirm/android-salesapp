package com.arx.android.salesapp.utils.rx

import rx.Observable
import rx.Scheduler
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.functions.Func1

/**
 * Created by esafirm on 6/28/16.
 */


/**
 * Binds some UI function to {@link Observable}. Usually used in Presenter/ViewModel/etc classes.
 *
 * @param uiFunc     not-null function that actually performs binding of the {@link Observable} to something, for example UI.
 * @param <T>        type of {@link Observable} emission.
 * @return {@link Subscription} that can be used to unsubscribe and stop bound action.
 */
fun <T> Observable<T>.bind(uiFunc: Func1<Observable<T>, Subscription>): Subscription
		= RxUi.bind(this, uiFunc)

/** !! Block error **/
fun <T> Observable<T>.onErrorNotify(action: Action1<Throwable>, scheduler: Scheduler = AndroidSchedulers.mainThread()): Observable<T> =
		observeOn(AndroidSchedulers.mainThread())
				.doOnError(action)
				.onErrorReturn { null }
				.filter { it != null }
				.observeOn(scheduler)


