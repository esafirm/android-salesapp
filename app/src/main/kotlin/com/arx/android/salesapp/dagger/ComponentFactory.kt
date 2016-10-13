package com.arx.android.salesapp.dagger

import android.app.Activity
import com.arx.android.salesapp.MainActivity
import com.arx.android.salesapp.SalesApp
import com.arx.android.salesapp.dagger.component.*
import com.arx.android.salesapp.dagger.module.ActivityModule
import com.arx.android.salesapp.dagger.module.AppModule
import com.arx.android.salesapp.dagger.module.ControllerModule
import com.arx.android.salesapp.navigator.PhoneNavigator
import com.bluelinelabs.conductor.Controller

/**
 * Created by esafirm on 6/22/16.
 */

fun SalesApp.makeComponent(): AppComponent {
	val component = DaggerAppComponent.builder()
			.appModule(AppModule(this))
			.build();
	component.inject(this)
	return component;
}

fun Activity.makeComponent(): ActivityComponent {
	return DaggerActivityComponent.builder()
			.appComponent(SalesApp.component)
			.activityModule(ActivityModule(this))
			.build()
}

fun Controller.component(): ControllerComponent {
	return MainActivity.component.plus(ControllerModule(PhoneNavigator(router)))
}