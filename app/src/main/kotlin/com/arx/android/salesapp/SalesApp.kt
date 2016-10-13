package com.arx.android.salesapp

import android.app.Application
import com.arx.android.salesapp.dagger.component.AppComponent
import com.arx.android.salesapp.dagger.makeComponent
import com.facebook.stetho.Stetho
import com.fuck_boilerplate.rx_paparazzo.RxPaparazzo
import com.incendiary.androidcommon.AndroidCommon
import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.HawkBuilder
import com.orhanobut.hawk.LogLevel
import rx_activity_result.RxActivityResult

/**
 * Created by esafirm on 6/22/16.
 */
class SalesApp : Application() {

	companion object {
		@JvmStatic lateinit var component: AppComponent
	}

	override fun onCreate() {
		super.onCreate()

		setupHawk()
		setupDagger()
		setupStetho()
		setupAndroidCommon()

		RxPaparazzo.register(this)
		RxActivityResult.register(this)
	}

	fun setupHawk() {
		Hawk.init(this)
				.setEncryptionMethod(HawkBuilder.EncryptionMethod.NO_ENCRYPTION)
				.setLogLevel(LogLevel.NONE)
				.setStorage(HawkBuilder.newSharedPrefStorage(this))
				.buildRx()
				.subscribe();
	}

	fun setupDagger() {
		component = makeComponent()
	}

	fun setupAndroidCommon() {
		AndroidCommon.with(this)
				.enableLogger(BuildConfig.DEBUG)
				.enableStricMode(BuildConfig.DEBUG)
				.logTag(SalesApp::class.java.simpleName)
				.install()
	}

	fun setupStetho() {
		if (BuildConfig.DEBUG) {
			Stetho.initializeWithDefaults(this)
		}
	}

}