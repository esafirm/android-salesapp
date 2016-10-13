package com.arx.android.salesapp.features.place.detail

import com.arx.android.salesapp.ui.base.BasePresenter
import com.arx.android.salesapp.data.model.place.Place
import com.arx.android.salesapp.utils.parcelable
import com.arx.android.salesapp.utils.rx.bind
import com.arx.android.salesapp.utils.rx.bindToLifecycle

/**
 * Created by esafirm on 6/29/16.
 */
class PlaceDetailPresenter : BasePresenter<PlaceDetailView>() {

	fun bind(controller: PlaceDetailController){
		val place = controller.args.parcelable<Place>()

    rx.Observable.just(place)
				.bindToLifecycle(controller)
				.bind(view.showPlace)
	}
}