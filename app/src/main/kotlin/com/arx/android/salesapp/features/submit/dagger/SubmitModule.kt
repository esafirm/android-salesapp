package com.arx.android.salesapp.features.submit.dagger

import android.app.Activity
import android.view.View
import com.arx.android.salesapp.dagger.ControllerScope
import com.arx.android.salesapp.data.repository.PlaceRemoteRepository
import com.arx.android.salesapp.features.submit.SubmitPresenter
import com.arx.android.salesapp.features.submit.SubmitView
import com.arx.android.salesapp.features.submit.SubmitViewImpl
import com.arx.android.salesapp.network.ApiService
import dagger.Module
import dagger.Provides
import pl.charmas.android.reactivelocation.ReactiveLocationProvider

/**
 * Created by esafirm on 6/30/16.
 */
@Module
class SubmitModule(val view: View) {

	@ControllerScope @Provides fun presenter(api: ApiService,
	                                         activity: Activity,
	                                         locaitonProvider: ReactiveLocationProvider): SubmitPresenter {
		return SubmitPresenter(PlaceRemoteRepository(api), locaitonProvider, activity);
	}

	@ControllerScope @Provides fun view(): SubmitView = SubmitViewImpl(view)
}