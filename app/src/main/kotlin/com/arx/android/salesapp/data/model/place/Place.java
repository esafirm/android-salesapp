package com.arx.android.salesapp.data.model.place;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by esafirm on 6/29/16.
 */
public class Place implements Parcelable {

  @SerializedName("id") private String id;
  @SerializedName("nama_toko") private String namaToko;
  @SerializedName("alamat") private String alamat;
  @SerializedName("kelurahan") private String kelurahan;
  @SerializedName("kecamatan") private String kecamatan;
  @SerializedName("kode_pos") private String kodePos;
  @SerializedName("nama_pemilik_toko") private String namaPemilikToko;
  @SerializedName("no_telp") private String noTelp;
  @SerializedName("email") private String email;
  @SerializedName("image") private List<String> images; //TODO should change JSON name to images
  @SerializedName("latlng") private String latlng;
  @SerializedName("created_on") private String createdOn;
  @SerializedName("created_by") private String createdBy;
  @SerializedName("updated_on") private String updatedOn;
  @SerializedName("updated_by") private String updatedBy;

  public String getId() {
    return id;
  }

  public String getNamaToko() {
    return namaToko;
  }

  public String getAlamat() {
    return alamat;
  }

  public String getKelurahan() {
    return kelurahan;
  }

  public String getKecamatan() {
    return kecamatan;
  }

  public String getKodePos() {
    return kodePos;
  }

  public String getNamaPemilikToko() {
    return namaPemilikToko;
  }

  public String getNoTelp() {
    return noTelp;
  }

  public String getEmail() {
    return email;
  }

  @Nullable public List<String> getImages() {
    return images;
  }

  public String getLatlng() {
    return latlng;
  }

  public String getCreatedOn() {
    return createdOn;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public String getUpdatedOn() {
    return updatedOn;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public Place() {
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.id);
    dest.writeString(this.namaToko);
    dest.writeString(this.alamat);
    dest.writeString(this.kelurahan);
    dest.writeString(this.kecamatan);
    dest.writeString(this.kodePos);
    dest.writeString(this.namaPemilikToko);
    dest.writeString(this.noTelp);
    dest.writeString(this.email);
    dest.writeStringList(this.images);
    dest.writeString(this.latlng);
    dest.writeString(this.createdOn);
    dest.writeString(this.createdBy);
    dest.writeString(this.updatedOn);
    dest.writeString(this.updatedBy);
  }

  protected Place(Parcel in) {
    this.id = in.readString();
    this.namaToko = in.readString();
    this.alamat = in.readString();
    this.kelurahan = in.readString();
    this.kecamatan = in.readString();
    this.kodePos = in.readString();
    this.namaPemilikToko = in.readString();
    this.noTelp = in.readString();
    this.email = in.readString();
    this.images = in.createStringArrayList();
    this.latlng = in.readString();
    this.createdOn = in.readString();
    this.createdBy = in.readString();
    this.updatedOn = in.readString();
    this.updatedBy = in.readString();
  }

  public static final Creator<Place> CREATOR = new Creator<Place>() {
    @Override public Place createFromParcel(Parcel source) {
      return new Place(source);
    }

    @Override public Place[] newArray(int size) {
      return new Place[size];
    }
  };
}
