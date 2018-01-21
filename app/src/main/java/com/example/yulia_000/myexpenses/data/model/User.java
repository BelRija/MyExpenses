package com.example.yulia_000.myexpenses.data.model;

/**
 * Created by Yulia_000 on 15.01.2018.
 */

public class User {
        public static final String TAG = User.class.getSimpleName();
        public static final String TABLE = "User";

        // Labels Table Columns names
        public static final String KEY_UserID = "id";
        public static final String KEY_Name = "Name";
        public static final String KEY_Password = "Password";
        public static final String KEY_Credit = "Credit";

        private Integer ID ;
        private String name;
        private String password ;
        private String credit ;

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public Integer getUserId() {
            return ID;
        }

        public void setUserId(Integer ID) {
            this.ID = ID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }
