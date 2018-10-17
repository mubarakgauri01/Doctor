package com.xina.doctor.Models;


import com.google.gson.annotations.SerializedName;


public class ChangePasswordDetail {

	@SerializedName("speciality")
	private String speciality;

	@SerializedName("image")
	private String image;

	@SerializedName("password")
	private String password;

	@SerializedName("token_id")
	private String tokenId;

	@SerializedName("last_login")
	private String lastLogin;

	@SerializedName("name")
	private String name;

	@SerializedName("degree")
	private String degree;

	@SerializedName("id")
	private String id;

	@SerializedName("userid")
	private String userid;

	@SerializedName("email")
	private String email;

	public void setSpeciality(String speciality){
		this.speciality = speciality;
	}

	public String getSpeciality(){
		return speciality;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
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

	public void setLastLogin(String lastLogin){
		this.lastLogin = lastLogin;
	}

	public String getLastLogin(){
		return lastLogin;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDegree(String degree){
		this.degree = degree;
	}

	public String getDegree(){
		return degree;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getUserid(){
		return userid;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}