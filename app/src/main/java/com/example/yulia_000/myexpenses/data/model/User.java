package com.example.yulia_000.myexpenses.data.model;

/**
 * Created by Yulia_000 on 15.01.2018.
 */

public class User {
        public static final String TAG = User.class.getSimpleName();
        public static final String TABLE = "User";

        // Labels Table Columns names
        public static final String KEY_UserID = "UserId";
        public static final String KEY_Name = "Name";
        public static final String KEY_Password = "Password";

        private String ID ;
        private String name;
        private String password ;

        public String getUserId() {
            return ID;
        }

        public void setUserId(String ID) {
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
