package com.pasistence.mantrafingerprint.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.pasistence.mantrafingerprint.Models.APIResponseModels.EmployeeDetails;
import com.pasistence.mantrafingerprint.Remote.IMyAPI;
import com.pasistence.mantrafingerprint.Remote.RetrofitClient;

public class Common {

    public static final String BASE_URL = "http://192.168.0.150/mantra/";
    //public static final String BASE_URL = "https://projectmantra.000webhostapp.com/";
    //public static final String BASE_URL = "http://localhost/mantra/";
    //public static final String BASE_URL = "http://52.172.221.235:8985/mantra/";


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

    //Check for Internet Connection
    public static boolean isConnectedToInterNet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null)
        {
            NetworkInfo[] infos = connectivityManager.getAllNetworkInfo();
            if(infos != null)
            {
                for(int i = 0;i<infos.length;i++)
                {
                    if(infos[i].getState()==NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }

}
