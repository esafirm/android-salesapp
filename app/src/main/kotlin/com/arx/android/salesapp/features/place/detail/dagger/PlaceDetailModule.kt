package com.arx.android.salesapp.features.place.detail.dagger

import android.view.View
import com.arx.android.salesapp.dagger.ControllerScope
import com.arx.android.salesapp.features.place.detail.PlaceDetailPresenter
import com.arx.android.salesapp.features.place.detail.PlaceDetailView
import com.arx.android.salesapp.features.place.detail.PlaceDetailViewImpl
import dagger.Module
import dagger.Provides

/**
 * Created by esafirm on 6/29/16.
 */
@Module
class PlaceDetailModule(val view: View) {
	@ControllerScope @Provides fun presenter(): PlaceDetailPresenter = PlaceDetailPresenter()
	@ControllerScope @Provides fun view(): PlaceDetailView = PlaceDetailViewImpl(view)
}