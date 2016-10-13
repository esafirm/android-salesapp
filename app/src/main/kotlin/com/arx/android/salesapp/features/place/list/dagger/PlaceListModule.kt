package com.arx.android.salesapp.features.place.list.dagger

import android.content.Context
import android.view.View
import com.arx.android.salesapp.dagger.ControllerScope
import com.arx.android.salesapp.data.repository.PlaceRemoteRepository
import com.arx.android.salesapp.features.place.list.PlaceListAdapter
import com.arx.android.salesapp.features.place.list.PlaceListPresenter
import com.arx.android.salesapp.features.place.list.PlaceListView
import com.arx.android.salesapp.features.place.list.PlaceListViewImpl
import com.arx.android.salesapp.network.ApiService
import com.arx.android.salesapp.ui.navigator.Navigator
import com.bluelinelabs.conductor.Router
import dagger.Module
import dagger.Provides

/**
 * Created by esafirm on 6/28/16.
 */
@Module
class PlaceListModule(private val context: Context,
                      private val view: View,
                      private val router: Router) {

	@ControllerScope @Provides fun providePlaceListAdapter(): PlaceListAdapter = PlaceListAdapter(context)
	@ControllerScope @Provides fun providePlaceListPresenter(dataSource: PlaceRemoteRepository): PlaceListPresenter = PlaceListPresenter(dataSource)

	@ControllerScope @Provides fun placeRemoteDataSource(api: ApiService)
			: PlaceRemoteRepository = PlaceRemoteRepository(api)

	@ControllerScope @Provides fun placeListView(adapter: PlaceListAdapter, navigator: Navigator)
			: PlaceListView = PlaceListViewImpl(view, adapter, navigator, router)

}
