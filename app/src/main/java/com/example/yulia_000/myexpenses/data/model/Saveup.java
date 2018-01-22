package com.example.yulia_000.myexpenses.data.model;

/**
 * Created by Yulia_000 on 22.01.2018.
 */

public class Saveup {
    public static final String TAG = Entry.class.getSimpleName();
    public static final String TABLE = "Saveup";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_UserID = "SaveupId";
    public static final String KEY_Date = "Date";
    public static final String KEY_Description = "Description";
    public static final String KEY_Amount = "Amount";

    private Integer ID;
    private Integer UserID ;
    private String SaveupDate;
    private String SaveupDescription ;
    private String SaveupAmount ;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getSaveupDate() {
        return SaveupDate;
    }

    public void setSaveupDate(String saveupDate) {
        SaveupDate = saveupDate;
    }

    public String getSaveupDescription() {
        return SaveupDescription;
    }

    public void setSaveupDescription(String saveupDescription) {
        SaveupDescription = saveupDescription;
    }

    public String getSaveupAmount() {
        return SaveupAmount;
    }

    public void setSaveupAmount(String saveupAmount) {
        SaveupAmount = saveupAmount;
    }
}
