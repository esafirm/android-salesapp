package com.arx.android.salesapp.network

import com.arx.android.salesapp.data.common.BaseResponse
import com.arx.android.salesapp.data.common.DataResponse
import com.arx.android.salesapp.data.constant.URLs
import com.arx.android.salesapp.data.model.place.Place
import com.arx.android.salesapp.data.model.user.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import rx.Observable

/**
 * Created by esafirm on 6/22/16.
 */
interface ApiService {

	@FormUrlEncoded
	@POST(URLs.LOGIN)
	fun login(@Field("username") username: String,
	          @Field("password") password: String): Observable<DataResponse<User>>

	@GET(URLs.USER)
	fun getCurrentUser(): Observable<DataResponse<User>>

	@FormUrlEncoded
	@POST(URLs.USER)
	fun postUser(@Field("username") username: String,
	             @Field("password") password: String): Observable<BaseResponse>

	/* --------------------------------------------------- */
	/* > Place */
	/* --------------------------------------------------- */

	@GET(URLs.TOKO)
	fun getPlaces(): Observable<DataResponse<List<Place>>>

	@Multipart @POST(URLs.TOKO)
	fun postPlace(@Part("nama_toko") plaeName: String?,
	              @Part("alamat") address: String?,
	              @Part("kelurahan") kelurahan: String?,
	              @Part("kecamatan") kecamatan: String?,
	              @Part("kode_pos") kodePos: String?,
	              @Part("nama_pemilik_toko") pemilikToko: String?,
	              @Part("no_telp") noTelp: String?,
	              @Part("email") email: String?,
	              @Part("latlng") latlng: String?,
	              @Part("user_id") userId: String?,
	              @Part("foto[]") images: RequestBody?)
			: Observable<Response<BaseResponse>>

	@POST(URLs.TOKO)
	fun postPlace(@Body body: MultipartBody)
			: Observable<Response<BaseResponse>>
}