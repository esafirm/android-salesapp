package com.arx.android.salesapp.utils.rx

import com.bluelinelabs.conductor.rxlifecycle.ControllerLifecycleProvider
import rx.Observable

/**
 * Created by esafirm on 6/29/16.
 */


fun <T> Observable<T>.bindToLifecycle(controller: ControllerLifecycleProvider)
		: Observable<T> = this.compose<T>(controller.bindToLifecycle<T>())