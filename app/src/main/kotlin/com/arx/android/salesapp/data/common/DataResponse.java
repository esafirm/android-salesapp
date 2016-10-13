package com.arx.android.salesapp.data.common;

import com.google.gson.annotations.SerializedName;

/**
 * Created by esafirm on 6/24/16.
 */
public class DataResponse<T> extends BaseResponse {

  @SerializedName("data") T data;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
