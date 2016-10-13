package com.arx.android.salesapp.ui.navigator

import com.arx.android.salesapp.data.model.place.Place
import com.arx.android.salesapp.features.place.detail.PlaceDetailController
import com.arx.android.salesapp.features.place.list.PlaceListController
import com.arx.android.salesapp.features.submit.SubmitController
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler

/**
 * Created by esafirm on 6/29/16.
 */
interface Navigator {

	val router: Router

	fun showPlacesList() = router.setRoot(RouterTransaction.with(PlaceListController()))

	fun showSubmitForm(childRouter: Router) {
		childRouter.setPopsLastView(true).setRoot(
				RouterTransaction.with(SubmitController())
						.popChangeHandler(VerticalChangeHandler())
						.pushChangeHandler(VerticalChangeHandler())
		)
	}

	fun showDetailPlace(place: Place) {
		router.pushController(
				RouterTransaction.with(PlaceDetailController(place))
						.pushChangeHandler(HorizontalChangeHandler())
						.popChangeHandler(HorizontalChangeHandler())
		)
	}

}