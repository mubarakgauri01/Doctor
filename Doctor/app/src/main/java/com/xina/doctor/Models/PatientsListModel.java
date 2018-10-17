package com.xina.doctor.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//import javax.annotation.Generated;

//@Generated("com.robohorse.robopojogenerator")
public class PatientsListModel{

	@SerializedName(" details")
	private List<PatientListDetails> details;

	public void setDetails(List<PatientListDetails> details){
		this.details = details;
	}

	public List<PatientListDetails> getDetails(){
		return details;
	}
}