package com.arx.android.salesapp.data.model.place

import android.webkit.MimeTypeMap
import com.arx.android.salesapp.data.DataStore
import com.google.gson.annotations.SerializedName
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by esafirm on 6/24/16.
 */
class PlacePostParameter {

	@SerializedName("nama_toko") var namaToko: String? = null
	@SerializedName("alamat") var alamat: String? = null
	@SerializedName("kelurahan") var kelurahan: String? = null
	@SerializedName("kecamatan") var kecamatan: String? = null
	@SerializedName("kode_pos") var kodePos: String? = null
	@SerializedName("nama_pemilik_toko") var namaPemilikToko: String? = null
	@SerializedName("no_telp") var noTelp: String? = null
	@SerializedName("email") var email: String? = null
	@SerializedName("latlng") var latlng: String? = null
	@SerializedName("foto[]") var foto: List<String>? = null

	fun toMultipartBody(): MultipartBody {
		val builder = MultipartBody.Builder()
				.setType(MultipartBody.FORM)

		foto?.forEach {
			builder.addFormDataPart(
					"foto[]", "image.jpg", RequestBody.create(MediaType.parse(getMimeType(it)), File(it))
			)
		}

		return builder.addFormDataPart("nama_toko", namaToko)
				.addFormDataPart("alamat", alamat)
				.addFormDataPart("kelurahan", kelurahan)
				.addFormDataPart("kecamatan", kecamatan)
				.addFormDataPart("kode_pos", kodePos)
				.addFormDataPart("nama_pemilik_toko", namaPemilikToko)
				.addFormDataPart("no_telp", noTelp)
				.addFormDataPart("email", email)
				.addFormDataPart("latlng", latlng)
				.addFormDataPart("user_id", DataStore.user?.id)
				.build()
	}

	fun getMimeType(url: String): String {
		var type: String? = null
		val extension = MimeTypeMap.getFileExtensionFromUrl(url)
		if (extension != null) {
			type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
		}
		return type ?: "image/*"
	}
}
