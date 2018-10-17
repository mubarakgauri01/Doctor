package com.xina.doctor.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileModel{

	@SerializedName("success")
	private int success;

	@SerializedName("detail")
	private List<ProfileDetail> detail;

	@SerializedName("message")
	private String message;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setDetail(List<ProfileDetail> detail){
		this.detail = detail;
	}

	public List<ProfileDetail> getDetail(){
		return detail;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}