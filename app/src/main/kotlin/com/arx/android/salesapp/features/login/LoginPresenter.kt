package com.arx.android.salesapp.features.login

import com.arx.android.salesapp.data.DataStore
import com.arx.android.salesapp.data.common.BaseResponse
import com.arx.android.salesapp.network.ApiService
import com.arx.android.salesapp.ui.base.BasePresenter
import com.arx.android.salesapp.utils.rx.bind
import com.arx.android.salesapp.utils.rx.onErrorNotify
import com.bluelinelabs.conductor.rxlifecycle.ControllerLifecycleProvider
import rx.Subscription
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by esafirm on 6/28/16.
 */

class LoginPresenter(private val apiService: ApiService) : BasePresenter<LoginView>() {

	fun bind(controller: ControllerLifecycleProvider): Subscription {
		val subscription = CompositeSubscription()

		val login = view.login.share()
		val password = view.password.share()

		val credentials = rx.Observable
				.combineLatest(login, password,
						{ login, pass -> Triple(login, pass, login.isNotEmpty() && pass.isNotEmpty()) })
				.publish()

		subscription += credentials
				.map { it.third }
				.startWith(false)
				.compose(controller.bindToLifecycle<Boolean>())
				.bind(view.signInEnable)

		val signInResult = view.signInClick
				.withLatestFrom(credentials, { t1, t2 -> t2.first to t2.second })
				.switchMap {
					apiService.login(it.first, it.second)
							.subscribeOn(Schedulers.io())
				}
				.doOnNext { DataStore.user = it.data }
				.share()

		subscription += credentials.connect()
		subscription += signInResult
				.compose(controller.bindToLifecycle<BaseResponse>())
				.onErrorNotify(view.onError)
				.bind(view.signInResult)

		return subscription
	}
}