package co.mirach.kounsel.redesign.profile.models;

import android.graphics.drawable.Drawable;

public class AttachmentModel {
    private String mAttachmentName;
    private Drawable mAttachmentImage;

    public AttachmentModel(String mAttachmentName_local,
                           Drawable mAttachmentImage_local){
        mAttachmentName = mAttachmentName_local;
        mAttachmentImage = mAttachmentImage_local;
    }


    public String getmAttachmentName() {
        return mAttachmentName;
    }

    public void setmAttachmentName(String mAttachmentName) {
        this.mAttachmentName = mAttachmentName;
    }

    public Drawable getmAttachmentImage() {
        return mAttachmentImage;
    }

    public void setmAttachmentImage(Drawable mAttachmentImage) {
        this.mAttachmentImage = mAttachmentImage;
    }

}
