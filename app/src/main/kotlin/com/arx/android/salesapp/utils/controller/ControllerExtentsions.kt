package com.arx.android.salesapp.utils.controller

import com.arx.android.salesapp.SalesApp
import com.arx.android.salesapp.dagger.component.AppComponent
import com.bluelinelabs.conductor.Controller

/**
 * Created by esafirm on 6/28/16.
 */

fun Controller.appComponent(): AppComponent = SalesApp.component;