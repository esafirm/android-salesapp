package com.arx.android.salesapp.features.place.detail

import android.os.Bundle
import android.view.View
import com.arx.android.salesapp.R
import com.arx.android.salesapp.data.model.place.Place
import com.arx.android.salesapp.features.place.detail.dagger.daggerComponent
import com.arx.android.salesapp.ui.base.BaseController
import com.arx.android.salesapp.utils.toBundle
import javax.inject.Inject

/**
 * Created by esafirm on 6/23/16.
 */
class PlaceDetailController(args: Bundle) : BaseController<PlaceDetailView, PlaceDetailPresenter>(args) {

	constructor(place: Place) : this(place.toBundle())

	@Inject lateinit var placeView: PlaceDetailView
	@Inject lateinit var placeResenter: PlaceDetailPresenter

	override fun getLayoutResId() = R.layout.controller_place_detail
	override fun createPresenter() = placeResenter
	override fun getMvpView() = placeView

	override fun onInject() {
		daggerComponent().inject(this)
	}

	override fun onAttach(view: View) {
		super.onAttach(view)
		presenter.bind(this)
	}
}