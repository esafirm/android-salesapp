package com.arx.android.salesapp.features.place.list

import android.view.View
import com.arx.android.salesapp.data.model.place.Place
import com.arx.android.salesapp.ui.navigator.Navigator
import com.arx.android.salesapp.utils.rx.RxUi
import com.bluelinelabs.conductor.Router
import com.incendiary.androidcommon.android.Toasts
import com.jakewharton.rxbinding.support.v4.widget.refreshes
import com.jakewharton.rxbinding.view.clicks
import kotlinx.android.synthetic.main.controller_place_list.view.*
import rx.Observable
import rx.Subscription
import rx.functions.Func1

class PlaceListViewImpl(val view: View,
                        val adapter: PlaceListAdapter,
                        val navigator: Navigator,
                        val childRouter: Router) : PlaceListView {

	override val addClick: Observable<Unit> = view.placeListAddButton.clicks().doOnNext {
		navigator.showSubmitForm(childRouter)
	}

	override val itemClick: Observable<PlaceListAdapter.PlaceViewHolder> = adapter.itemClicks().doOnNext {
		val place = adapter.getItem(it.adapterPosition)
		navigator.showDetailPlace(place)
	}

	override val showContent: Func1<Observable<List<Place>>, Subscription>
			= RxUi.ui<List<Place>> { adapter.pushData(it) }

	override val pullToRefresh = view.swipeRefreshLayout.refreshes()

	override fun showError(throwable: Throwable?) {
		Toasts.show("Oops! This shouldn't be happened")
	}

	override fun showLoading(isLoading: Boolean) {
		view.swipeRefreshLayout.post {
			view.swipeRefreshLayout.isRefreshing = isLoading
		}
	}
}