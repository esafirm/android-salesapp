package com.arx.android.salesapp.dagger.component

import com.arx.android.salesapp.SalesApp
import com.arx.android.salesapp.dagger.ApplicationScope
import com.arx.android.salesapp.dagger.module.AppModule
import com.arx.android.salesapp.network.ApiService
import com.arx.android.salesapp.network.NetworkModule
import dagger.Component
import pl.charmas.android.reactivelocation.ReactiveLocationProvider

/**
 * Created by esafirm on 6/22/16.
 */
@ApplicationScope
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {
	fun inject(app: SalesApp)

	/* --------------------------------------------------- */
	/* > Getter */
	/* --------------------------------------------------- */
	fun apiService(): ApiService
	fun reactiveLocationProvider(): ReactiveLocationProvider
}