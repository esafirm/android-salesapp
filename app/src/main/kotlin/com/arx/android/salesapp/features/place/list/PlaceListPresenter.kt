package com.arx.android.salesapp.features.place.list

import com.arx.android.salesapp.data.repository.PlaceRepository
import com.arx.android.salesapp.ui.base.BasePresenter
import com.arx.android.salesapp.utils.rx.asyncTask
import com.arx.android.salesapp.utils.rx.bind
import com.arx.android.salesapp.utils.rx.bindToLifecycle
import com.arx.android.salesapp.utils.rx.onErrorNotify
import rx.functions.Action1

/**
 * Created by esafirm on 6/28/16.
 */
class PlaceListPresenter(val placeDataSource: PlaceRepository) : BasePresenter<PlaceListView>() {

	fun bind(controller: PlaceListController) {

		val addClick = view.addClick.share()
		val itemClick = view.itemClick.share()
		val pullToRefresh = view.pullToRefresh.share()

		pullToRefresh.startWith(Unit)
				.doOnNext { view.showLoading(true) }
				.bindToLifecycle(controller)
				.switchMap {
					placeDataSource.getPlaces()
							.asyncTask()
							.doOnTerminate { view.showLoading(false) }
							.onErrorNotify(Action1 { view.showError(null) })
				}
				.bind(view.showContent)

		addClick.bindToLifecycle(controller).subscribe()
		itemClick.bindToLifecycle(controller).subscribe()
	}

}