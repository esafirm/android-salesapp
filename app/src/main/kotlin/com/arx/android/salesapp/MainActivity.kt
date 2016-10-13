package com.arx.android.salesapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arx.android.salesapp.dagger.component.ActivityComponent
import com.arx.android.salesapp.dagger.makeComponent
import com.arx.android.salesapp.data.DataStore
import com.arx.android.salesapp.features.login.LoginController
import com.arx.android.salesapp.features.place.list.PlaceListController
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	companion object {
		@JvmStatic lateinit var component: ActivityComponent
	}

	lateinit var router: Router;

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		component = makeComponent()

		router = Conductor.attachRouter(this, controllerContainer, savedInstanceState)
		if (!router.hasRootController()) {
			router.setRoot(when (DataStore.user == null) {
				true -> RouterTransaction.with(LoginController())
				else -> RouterTransaction.with(PlaceListController())
			})
		}
	}

	override fun onBackPressed() {
		if (!router.handleBack()) {
			super.onBackPressed()
		}
	}
}
