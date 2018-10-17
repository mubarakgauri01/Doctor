package com.xina.doctor.Models;

import com.google.gson.annotations.SerializedName;

public class ForgotDetail {

	@SerializedName("speciality")
	private Object speciality;

	@SerializedName("image")
	private Object image;

	@SerializedName("password")
	private Object password;

	@SerializedName("token_id")
	private Object tokenId;

	@SerializedName("last_login")
	private Object lastLogin;

	@SerializedName("name")
	private Object name;

	@SerializedName("degree")
	private Object degree;

	@SerializedName("id")
	private Object id;

	@SerializedName("userid")
	private Object userid;

	@SerializedName("email")
	private Object email;

	public void setSpeciality(Object speciality){
		this.speciality = speciality;
	}

	public Object getSpeciality(){
		return speciality;
	}

	public void setImage(Object image){
		this.image = image;
	}

	public Object getImage(){
		return image;
	}

	public void setPassword(Object password){
		this.password = password;
	}

	public Object getPassword(){
		return password;
	}

	public void setTokenId(Object tokenId){
		this.tokenId = tokenId;
	}

	public Object getTokenId(){
		return tokenId;
	}

	public void setLastLogin(Object lastLogin){
		this.lastLogin = lastLogin;
	}

	public Object getLastLogin(){
		return lastLogin;
	}

	public void setName(Object name){
		this.name = name;
	}

	public Object getName(){
		return name;
	}

	public void setDegree(Object degree){
		this.degree = degree;
	}

	public Object getDegree(){
		return degree;
	}

	public void setId(Object id){
		this.id = id;
	}

	public Object getId(){
		return id;
	}

	public void setUserid(Object userid){
		this.userid = userid;
	}

	public Object getUserid(){
		return userid;
	}

	public void setEmail(Object email){
		this.email = email;
	}

	public Object getEmail(){
		return email;
	}
}