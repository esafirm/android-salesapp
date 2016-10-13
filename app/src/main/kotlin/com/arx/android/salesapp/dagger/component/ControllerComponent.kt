package com.arx.android.salesapp.dagger.component

import com.arx.android.salesapp.dagger.ControllerScope
import com.arx.android.salesapp.dagger.module.ControllerModule
import com.arx.android.salesapp.features.place.detail.dagger.PlaceDetailComponent
import com.arx.android.salesapp.features.place.detail.dagger.PlaceDetailModule
import com.arx.android.salesapp.features.place.list.dagger.PlaceListComponent
import com.arx.android.salesapp.features.place.list.dagger.PlaceListModule
import com.arx.android.salesapp.features.submit.dagger.SubmitComponent
import com.arx.android.salesapp.features.submit.dagger.SubmitModule
import com.arx.android.salesapp.ui.navigator.Navigator
import dagger.Subcomponent

/**
 * Created by esafirm on 6/29/16.
 */
@ControllerScope
@Subcomponent(modules = arrayOf(ControllerModule::class))
interface ControllerComponent {
	fun navigator(): Navigator

	/* --------------------------------------------------- */
	/* > Sub Module */
	/* --------------------------------------------------- */

	fun plus(module: PlaceListModule): PlaceListComponent
	fun plus(module: PlaceDetailModule): PlaceDetailComponent
	fun plus(module: SubmitModule): SubmitComponent
}