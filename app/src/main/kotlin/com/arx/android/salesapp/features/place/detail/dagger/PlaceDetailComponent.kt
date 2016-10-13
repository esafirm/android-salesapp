package com.arx.android.salesapp.features.place.detail.dagger

import com.arx.android.salesapp.dagger.ControllerScope
import com.arx.android.salesapp.dagger.component
import com.arx.android.salesapp.features.place.detail.PlaceDetailController
import dagger.Subcomponent

/**
 * Created by esafirm on 6/29/16.
 */
@ControllerScope
@Subcomponent(modules = arrayOf(PlaceDetailModule::class))
interface PlaceDetailComponent {
	fun inject(controller: PlaceDetailController)
}

fun PlaceDetailController.daggerComponent(): PlaceDetailComponent {
	return component().plus(PlaceDetailModule(view))
}
