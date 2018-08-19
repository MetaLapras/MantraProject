package com.pasistence.mantrafingerprint.Remote;

import com.pasistence.mantrafingerprint.Models.APIResponseModels.ApiProjectResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IMyAPI {
    @FormUrlEncoded
    @POST("project_login_details.php")
    Call<ApiProjectResponse> loginUser(@Field("employee_id") String employeeId, @Field("project_name") String projectName, @Field("password") String password);

}
