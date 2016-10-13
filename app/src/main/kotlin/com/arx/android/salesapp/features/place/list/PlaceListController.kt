package com.arx.android.salesapp.features.place.list

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arx.android.salesapp.R
import com.arx.android.salesapp.features.place.list.dagger.component
import com.arx.android.salesapp.ui.base.BaseController
import kotlinx.android.synthetic.main.controller_place_list.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import javax.inject.Inject


/**
 * Created by esafirm on 6/22/16.
 */
class PlaceListController : BaseController<PlaceListView, PlaceListPresenter>() {

	@Inject lateinit var listAdapter: PlaceListAdapter
	@Inject lateinit var placePresenter: PlaceListPresenter
	@Inject lateinit var placeView: PlaceListView

	init {
		retainViewMode = RetainViewMode.RETAIN_DETACH
	}

	override fun getLayoutResId(): Int = R.layout.controller_place_list
	override fun createPresenter() = placePresenter
	override fun getMvpView() = placeView

	override fun onInject() {
		component().inject(this)
	}

	override fun onViewCreated(view: View) {
		view.toolbar.title = "Toko"
		setSupportActionBar(view.toolbar)

		view.swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent)
		view.recyclerView.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = listAdapter
		}

		presenter.bind(this)
	}
}