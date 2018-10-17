package com.xina.doctor.Models;

import com.google.gson.annotations.SerializedName;

public class ForgetModel {

	@SerializedName("success")
	private int success;

	@SerializedName("forgotDetail")
	private ForgotDetail forgotDetail;

	@SerializedName("message")
	private String message;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setForgotDetail(ForgotDetail forgotDetail){
		this.forgotDetail = forgotDetail;
	}

	public ForgotDetail getForgotDetail(){
		return forgotDetail;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}