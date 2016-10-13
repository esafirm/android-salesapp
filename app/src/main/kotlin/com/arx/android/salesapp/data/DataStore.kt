package com.arx.android.salesapp.data

import com.arx.android.salesapp.data.model.user.User
import com.orhanobut.hawk.Hawk

/**
 * Created by esafirm on 6/24/16.
 */
object DataStore {

	private val LOGGED_IN_USER = "Key.LoggedInUser";

	/* --------------------------------------------------- */
	/* > Logged In User */
	/* --------------------------------------------------- */

	var user: User?
		get() = get(LOGGED_IN_USER)
		set(user: User?) {
			user?.let { put(LOGGED_IN_USER, user) }
		}

	/* --------------------------------------------------- */
	/* > Convenience Methods */
	/* --------------------------------------------------- */

	fun <T> get(key: String): T = Hawk.get<T>(key)

	fun put(key: String, obj: Any): Unit {
		Hawk.put(key, obj)
	}
}
