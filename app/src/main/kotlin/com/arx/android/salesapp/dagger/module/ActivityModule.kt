package com.arx.android.salesapp.dagger.module

import android.app.Activity
import com.arx.android.salesapp.dagger.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by esafirm on 6/30/16.
 */
@Module
class ActivityModule(val activity: Activity) {
	@ActivityScope @Provides fun provideActivity() = activity;
}