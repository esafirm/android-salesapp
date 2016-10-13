package com.arx.android.salesapp.utils.rx

import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by esafirm on 6/30/16.
 */

fun <T> Observable<T>.asyncTask(scheduler: Scheduler = Schedulers.io()): Observable<T> {
	return compose {
		it.subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())
	}
}