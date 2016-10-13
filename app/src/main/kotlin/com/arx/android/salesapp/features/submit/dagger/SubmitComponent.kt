package com.arx.android.salesapp.features.submit.dagger

import com.arx.android.salesapp.dagger.ControllerScope
import com.arx.android.salesapp.dagger.component
import com.arx.android.salesapp.features.submit.SubmitController
import dagger.Subcomponent

/**
 * Created by esafirm on 6/30/16.
 */
@ControllerScope
@Subcomponent(modules = arrayOf(SubmitModule::class))
interface SubmitComponent {
	fun inject(controller: SubmitController)
}

fun SubmitController.daggerComponent(): SubmitComponent{
	return component().plus(SubmitModule(view))
}