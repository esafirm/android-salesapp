package com.arx.android.salesapp.dagger.module

import com.arx.android.salesapp.dagger.ControllerScope
import com.arx.android.salesapp.ui.navigator.Navigator
import dagger.Module
import dagger.Provides

/**
 * Created by esafirm on 6/29/16.
 */
@Module
class ControllerModule(private val navigator: Navigator) {
  @ControllerScope @Provides fun navigator(): Navigator = navigator
}
