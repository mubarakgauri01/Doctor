package com.xina.doctor.Models;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class PatientListDetails {

	@SerializedName("disease")
	private String disease;

	@SerializedName("address")
	private String address;

	@SerializedName("gender")
	private String gender;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private String id;

	@SerializedName("create_at")
	private String createAt;

	@SerializedName("age")
	private String age;

	@SerializedName("email")
	private String email;

	public void setDisease(String disease){
		this.disease = disease;
	}

	public String getDisease(){
		return disease;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
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

	public void setAge(String age){
		this.age = age;
	}

	public String getAge(){
		return age;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}