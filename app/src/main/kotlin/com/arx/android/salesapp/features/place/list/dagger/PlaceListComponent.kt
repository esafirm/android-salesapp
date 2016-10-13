package com.arx.android.salesapp.features.place.list.dagger

import com.arx.android.salesapp.dagger.ControllerScope
import com.arx.android.salesapp.dagger.component
import com.arx.android.salesapp.features.place.list.PlaceListController
import dagger.Subcomponent
import kotlinx.android.synthetic.main.controller_place_list.view.*

/**
 * Created by esafirm on 6/28/16.
 */
@ControllerScope
@Subcomponent(modules = arrayOf(PlaceListModule::class))
interface PlaceListComponent {
	fun inject(controller: PlaceListController)
}

fun PlaceListController.component(): PlaceListComponent {
	return component().plus(PlaceListModule(activity, view,
			getChildRouter(view.placeListSubmitContainer, null)
	))
}