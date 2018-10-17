package com.xina.doctor.Interfaces;

import com.xina.doctor.Models.ChangePasswordModel;
import com.xina.doctor.Models.ForgetModel;
import com.xina.doctor.Models.LoginModel;
import com.xina.doctor.Models.PatientsListModel;
import com.xina.doctor.Models.ProfileModel;
import com.xina.doctor.Models.UpdateModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by S K on 02-07-2018.
 */

public interface ApiInterface {

   // ---------------For login----------
    @POST("login.php")
    @FormUrlEncoded
    Call<LoginModel> getLogin(@Field("user_id") String user_id,
                             @Field("password") String password,@Field("user") String user,@Field("tokenid") String tokenid);


    // ---------------For Forgot Password----------
    @POST("forgot_password.php")
    @FormUrlEncoded
    Call<ForgetModel> getForgotPassword(@Field("email") String email, @Field("user") String user);

 // ---------------For Update Doctor DoctorProfile----------
 @POST("update_profile.php")
 @FormUrlEncoded
 Call<UpdateModel> getUpdateDetails(@Field("userid") String userid,
                                    @Field("name") String name,
                                    @Field("email") String email,
                                    @Field("mobile") String mobile,
                                    @Field("image") String image,
                                    @Field("user") String user,
                                    @Field("tokenid") String token_id,
                                    @Field("speciality") String speciality,
                                    @Field("degree") String degree);

 @POST("dt_change_password.php")
 @FormUrlEncoded
 Call<ChangePasswordModel> changePassword(@Field("userid") String userid,
                                          @Field("old_password") String old_password,
                                          @Field("new_password") String new_password);

 @GET("patient_list.php")
 Call<PatientsListModel> getList();

 @FormUrlEncoded
 @POST("profile.php")
 Call<ProfileModel> getProfile(@Field("userid")
                                                 String userid,
                                     @Field("user")
                                                  String user);
}
