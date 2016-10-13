package com.arx.android.salesapp.features.submit

import android.app.Activity
import android.content.Intent
import android.text.format.DateUtils
import com.arx.android.salesapp.data.repository.PlaceRepository
import com.arx.android.salesapp.ui.base.BasePresenter
import com.arx.android.salesapp.utils.rx.asyncTask
import com.arx.android.salesapp.utils.rx.bind
import com.arx.android.salesapp.utils.rx.bindToLifecycle
import com.arx.android.salesapp.utils.rx.onErrorNotify
import com.fuck_boilerplate.rx_paparazzo.RxPaparazzo
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import pl.charmas.android.reactivelocation.ReactiveLocationProvider
import rx.Observable
import rx_activity_result.RxActivityResult

/**
 * Created by esafirm on 6/29/16.
 */
class SubmitPresenter(val placeDataSource: PlaceRepository,
                      val locationProvider: ReactiveLocationProvider,
                      val activity: Activity) : BasePresenter<SubmitView>() {

	fun bind(controller: SubmitController) {

		val checkLocation = locationProvider
				.checkLocationSettings(getLocationSettingRequest())
				.flatMap { handleResolution(it.status) }
				.flatMap { locationProvider.lastKnownLocation }

		/* Check GPS status */
		checkLocation
				.switchIfEmpty(Observable.error(IllegalStateException("Location must be not null")))
				.bindToLifecycle(controller)
				.onErrorNotify(view.showError)
				.subscribe()

		view.addPhotoClick
				.bindToLifecycle(controller)
				.flatMap { takeImage(it) }
				.filter { it != null }
				.map { it as String }
				.bind(view.showPhotoAdded)

		val locationString = checkLocation
				.map { "[${it.latitude}, ${it.longitude}]" }

		Observable.combineLatest(locationString, view.submitClick, { t1, t2 -> t2.apply { latlng = t1 } })
				.doOnNext { view.showLoading(true) }
				.switchMap {
					placeDataSource.postPlace(it)
							.asyncTask()
							.doOnTerminate { view.showLoading(false) }
							.onErrorNotify(view.showError)
				}
				.doOnNext { view.showLoading(false) }
				.bind(view.showSubmitted)
	}

	fun takeImage(usingCamera: Boolean): Observable<String?> {
		val builder = RxPaparazzo.takeImage(activity)
		return when {
			usingCamera -> builder.usingCamera()
			else -> builder.usingGallery()
		}.map { it.data() }
	}

	fun handleResolution(status: Status): Observable<Boolean> {
		return when (status.statusCode) {
			LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> RxActivityResult.on(activity)
					.startIntentSender(status.resolution.intentSender, Intent(), 0, 0, 0)
					.map { it.resultCode() == Activity.RESULT_OK }
			else -> Observable.just(true)
		}
	}

	fun getLocationSettingRequest(): LocationSettingsRequest {

		val locationRequest = LocationRequest.create()
				.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
				.setInterval(DateUtils.SECOND_IN_MILLIS)

		return LocationSettingsRequest.Builder()
				.addLocationRequest(locationRequest)
				.setAlwaysShow(true)
				.build()

	}
}