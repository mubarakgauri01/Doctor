package com.xina.doctor.Models;

import com.google.gson.annotations.SerializedName;

public class ProfileDetail {

	@SerializedName("image")
	private String image;

	@SerializedName("last_login")
	private String lastLogin;

	@SerializedName("degree")
	private String degree;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("userid")
	private String userid;

	@SerializedName("speciality")
	private String speciality;

	@SerializedName("password")
	private String password;

	@SerializedName("token_id")
	private String tokenId;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("create_at")
	private String createAt;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setLastLogin(String lastLogin){
		this.lastLogin = lastLogin;
	}

	public String getLastLogin(){
		return lastLogin;
	}

	public void setDegree(String degree){
		this.degree = degree;
	}

	public String getDegree(){
		return degree;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getUserid(){
		return userid;
	}

	public void setSpeciality(String speciality){
		this.speciality = speciality;
	}

	public String getSpeciality(){
		return speciality;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setTokenId(String tokenId){
		this.tokenId = tokenId;
	}

	public String getTokenId(){
		return tokenId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCreateAt(String createAt){
		this.createAt = createAt;
	}

	public String getCreateAt(){
		return createAt;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}