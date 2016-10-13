package com.arx.android.salesapp.dagger.module

import android.content.Context
import com.arx.android.salesapp.dagger.ApplicationScope
import dagger.Module
import dagger.Provides
import pl.charmas.android.reactivelocation.ReactiveLocationProvider

/**
 * Created by esafirm on 6/22/16.
 */

@Module
class AppModule(val appContext: Context) {
	@ApplicationScope @Provides fun appContext(): Context = appContext
	@ApplicationScope @Provides fun reactiveLocationProvider(): ReactiveLocationProvider {
		return ReactiveLocationProvider(appContext);
	}
}