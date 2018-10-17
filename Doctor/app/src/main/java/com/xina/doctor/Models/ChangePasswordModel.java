package com.xina.doctor.Models;


import com.google.gson.annotations.SerializedName;


public class ChangePasswordModel{

	@SerializedName("success")
	private int success;

	@SerializedName("detail")
	private ChangePasswordDetail detail;

	@SerializedName("message")
	private String message;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setDetail(ChangePasswordDetail detail){
		this.detail = detail;
	}

	public ChangePasswordDetail getDetail(){
		return detail;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}