package com.arx.android.salesapp.data.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by esafirm on 6/24/16.
 */
public class User {

  /**
   * id : 1
   * username : ahmad
   * password : 202cb962ac59075b964b07152d234b70
   * created_on : 2016-06-20 13:44:31
   * updated_on : 2016-06-20 13:46:52
   */

  @SerializedName("id") private String id;
  @SerializedName("username") private String username;
  @SerializedName("password") private String password;
  @SerializedName("created_on") private String createdOn;
  @SerializedName("updated_on") private String updatedOn;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(String createdOn) {
    this.createdOn = createdOn;
  }

  public String getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(String updatedOn) {
    this.updatedOn = updatedOn;
  }
}
