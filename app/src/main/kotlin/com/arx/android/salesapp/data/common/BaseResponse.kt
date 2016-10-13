package com.arx.android.salesapp.data.common

import com.google.gson.annotations.SerializedName

/**
 * Created by esafirm on 6/24/16.
 */
open class BaseResponse : Response {

	/**
	 * status : 0001
	 * mssg : Valid account
	 */

	@SerializedName("status") var status: String? = null
	@SerializedName("mssg") var message: String? = null
}

fun BaseResponse.isSuccess(): Boolean = status?.equals("0001") ?: false
