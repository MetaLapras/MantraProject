package com.pasistence.mantrafingerprint.Remote;

import com.pasistence.mantrafingerprint.Models.APIResponseModels.APIWorkerPersonalResponse;
import com.pasistence.mantrafingerprint.Models.APIResponseModels.ApiProjectResponse;
import com.pasistence.mantrafingerprint.Models.WorkerModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IMyAPI {
    @FormUrlEncoded
    @POST("project_login_details.php")
    Call<ApiProjectResponse> loginUser(@Field("employee_id") String employeeId, @Field("project_name") String projectName, @Field("password") String password);

  /*  @FormUrlEncoded
    @POST("workerRegistration.php")
    Call<WorkerModel> workerRegistration(
            @Field("name") String name,
            @Field("gender") String gender,
            @Field("dob") String dob,
            @Field("fingerprint1") String fingerprint1,
            @Field("fingerprint2") String fingerprint2,
            @Field("email") String email,
            @Field("project_id") String project_id,
            @Field("salary") String salary,
            @Field("employee_id") String employee_id,
            @Field("adharcard_id") String adharcard_id
    );*/

   /* @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("workerRegistration.php")
    Call<APIWorkerPersonalResponse> workerRegistration(
            @Field("name") String name,
            @Field("gender") String gender,
            @Field("dob") String dob,
            @Field("fingerprint1") String fingerprint1,
            @Field("fingerprint2") String fingerprint2,
            @Field("email") String email,
            @Field("project_id") String project_id,
            @Field("salary") String salary,
            @Field("employee_id") String employee_id,
            @Field("adharcard_id") String adharcard_id
    );*/

    @FormUrlEncoded
    //@Headers("Content-Type: application/json")
    @POST("workerRegistration.php")
    Call<APIWorkerPersonalResponse> workerRegistration(
            @Field("name") String name,
            @Field("gender") String gender,
            @Field("dob") String dob,
            @Field("fingerprint1") String fingerprint1,
            @Field("fingerprint2") String fingerprint2,
            @Field("email") String email,
            @Field("project_id") String project_id,
            @Field("salary") String salary,
            @Field("employee_id") String employee_id,
            @Field("adharcard_id") String adharcard_id
    );


    @FormUrlEncoded
    @POST("insert_contact_details.php")
    Call<WorkerModel> insertcontactdetails(
            @Field("contact1") String contact1,
            @Field("contact2") String contact2,
            @Field("address_line_1") String address_line_1,
            @Field("address_line_2") String address_line_2,
            @Field("city") String city,
            @Field("pincode") String pincode,
            @Field("state") String state,
            @Field("country") String country,
            @Field("worker_id") String worker_id,
            @Field("type") String type,
            @Field("employee_id") String employee_id
    );

    @FormUrlEncoded
    @POST("insert_bank_details.php")
    Call<WorkerModel> insertbankdetails(
            @Field("account_holder_name") String account_holder_name,
            @Field("ifsc_code") String ifsc_code,
            @Field("account_no") String account_no,
            @Field("bank_name") String bank_name,
            @Field("worker_id") String worker_id,
            @Field("activation") String activation,
            @Field("employee_id") String employee_id
    );

    @FormUrlEncoded
    @POST("image_upload.php")
    Call<WorkerModel> imageupload(
            //@Part("uploadfile") String uploadfile,
            @Part MultipartBody.Part file, @Part("uploadfile") RequestBody name,
            @Field("worker_id") String worker_id,
            @Field("employee_id") String employee_id
    );
}
