package com.pasistence.mantrafingerprint.Models;

import java.io.Serializable;

public class BankAccountModel {

    public static abstract class NewUserInfo
    {
        public static final String BANK_NAME = "bank_name";
        public static final String ACCOUNT_HOLDER = "account_holder";
        public static final String ACCOUNT_NUMBER = "account_number";
        public static final String IFSC_CODE = "ifsc_code";
        public static final String TABLE_NAME = "user_info";
    }


}
