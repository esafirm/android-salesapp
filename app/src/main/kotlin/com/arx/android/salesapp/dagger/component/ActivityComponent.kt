package com.arx.android.salesapp.dagger.component

import android.app.Activity
import com.arx.android.salesapp.dagger.ActivityScope
import com.arx.android.salesapp.dagger.module.ActivityModule
import com.arx.android.salesapp.dagger.module.ControllerModule
import dagger.Component

/**
 * Created by esafirm on 6/30/16.
 */
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent{
	fun plus(module: ControllerModule): ControllerComponent
	fun activity(): Activity
}