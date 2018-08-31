package com.pasistence.mantrafingerprint.Common;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    public static final String employee_id = "employee_id";
    public static final String project_id = "project_id";
    public static final String worker_id = "worker_id";
    public static final String PREFERENCE_KEY = "MANTRA";
    public static final String PREFERENCE_KEY_SIGN_IN="signIn";

    //prevent instantiation
    private PreferenceUtils(){}


    public static void setSignIn(Context context,boolean signIn){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(PREFERENCE_KEY_SIGN_IN,signIn).apply();
    }

    public static boolean getSignIn(Context context){
        return getSharedPreferences(context).getBoolean(PREFERENCE_KEY_SIGN_IN,false);
    }

    public static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(PREFERENCE_KEY,Context.MODE_PRIVATE);
    }

    public static String getEmployee_id(Context context){
        return getSharedPreferences(context).getString(employee_id,"");
    }
    public static void setEmployee_id(Context context,String getAllMemberDetails){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(employee_id,getAllMemberDetails).apply();
    }
    public static String getWorker_id(Context context){
        return getSharedPreferences(context).getString(worker_id,"");
    }
    public static void setWorker_id(Context context,String getAllMemberDetails){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(worker_id,getAllMemberDetails).apply();
    }
    public static String getProject_id(Context context){
        return getSharedPreferences(context).getString(project_id,"");
    }
    public static void setProject_id(Context context,String getAllMemberDetails){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(project_id,getAllMemberDetails).apply();
    }
}
