package com.shasa.registrationsystem.PHP_API;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    @SerializedName("status")
    private  String status;
    @SerializedName("role")
    private String role;

    @SerializedName("email")
    private String email;
    @SerializedName("resultCode")
    private  int resultCode;

    @SerializedName("department")
   private String department;

    @SerializedName("userExist")
    private  String userExist;

    public String getStatus() {
        return status;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getDepartment() {
        return department;
    }

    public String getUserExist() {
        return userExist;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
