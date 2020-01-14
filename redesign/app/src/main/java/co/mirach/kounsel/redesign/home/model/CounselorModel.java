package co.mirach.kounsel.redesign.home.model;

import android.graphics.drawable.Drawable;

public class CounselorModel {


    private String mCounselorName;
    private String mCounselorType;
    private String mCounselorHourlyRate;
    private String mCounselorRating;
    private Drawable mCounselorDrawable;

    public CounselorModel(String counselor_name_local,
                          String counselor_type_local,
                          String hourly_rate_local,
                          String rating_local,
                          Drawable counselorDrawable_local){
       mCounselorName = counselor_name_local;
       mCounselorType = counselor_type_local;
       mCounselorHourlyRate = hourly_rate_local;
       mCounselorRating = rating_local;
       mCounselorDrawable = counselorDrawable_local;
    }
    public String getmCounselorName() {
        return mCounselorName;
    }

    public void setmCounselorName(String mCounselorName) {
        this.mCounselorName = mCounselorName;
    }

    public String getmCounselorType() {
        return mCounselorType;
    }

    public void setmCounselorType(String mCounselorType) {
        this.mCounselorType = mCounselorType;
    }

    public String getmCounselorHourlyRate() {
        return mCounselorHourlyRate;
    }

    public void setmCounselorHourlyRate(String mCounselorHourlyRate) {
        this.mCounselorHourlyRate = mCounselorHourlyRate;
    }

    public String getmCounselorRating() {
        return mCounselorRating;
    }

    public void setmCounselorRating(String mCounselorRating) {
        this.mCounselorRating = mCounselorRating;
    }
    public Drawable getmCounselorDrawable() {
        return mCounselorDrawable;
    }

    public void setmCounselorDrawable(Drawable mCounselorDrawable) {
        this.mCounselorDrawable = mCounselorDrawable;
    }

}
