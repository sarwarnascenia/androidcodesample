package co.mirach.kounsel.redesign.home.model;

import android.graphics.drawable.Drawable;

public class CategoryItemDetailsModel {
    public static final int ACTIVE_TYPE = 1;
    public static final int INACTIVE_TYPE = 2;
    private String mCategoryItemName;
    private Drawable mCategotyDrawable;
    private int mColor;
    private int mType;


    public String getmCategoryItemName() {
        return mCategoryItemName;
    }

    public void setmCategoryItemName(String mCategoryItemName) {
        this.mCategoryItemName = mCategoryItemName;
    }

    public Drawable getmCategotyDrawable() {
        return mCategotyDrawable;
    }

    public void setmCategotyDrawable(Drawable mCategotyDrawable) {
        this.mCategotyDrawable = mCategotyDrawable;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }


    public CategoryItemDetailsModel(String categoryItemName_local,
                                    Drawable categotyDrawable_local,
                                    int color_local,
                                    int type_local){
        mCategoryItemName = categoryItemName_local;
        mCategotyDrawable = categotyDrawable_local;
        mColor = color_local;
        mType = INACTIVE_TYPE;
    }
}
