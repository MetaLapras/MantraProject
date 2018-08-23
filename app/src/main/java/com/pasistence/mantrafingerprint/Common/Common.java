package com.pasistence.mantrafingerprint.Common;

import com.pasistence.mantrafingerprint.Remote.IMyAPI;
import com.pasistence.mantrafingerprint.Remote.RetrofitClient;

public class Common {

    public static final String BASE_URL = "http://192.168.0.150/mantra/";
    // public static final String BASE_URL = "http://localhost/mantra/";

    public static IMyAPI getApi(){
        return RetrofitClient.getClient(BASE_URL).create(IMyAPI.class);
    }

// for checking the value is null or not
    public String isNull(String parma,String dafualtStr){
     String value;
     if(!parma.equals(null)){
         value =  dafualtStr;
     }else
     {
         value = parma;
     }
     return value;
    }

}
