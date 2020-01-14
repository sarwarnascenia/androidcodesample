package co.mirach.kounsel.redesign.profile.models;

import android.graphics.drawable.Drawable;

public class PortfolioModel {

    private String mPortfolioName;
    private String mPortfolioLink;

    public PortfolioModel(String mPortfolioName_local,
                          String mPortfolioLink_local){
        mPortfolioName = mPortfolioName_local;
        mPortfolioLink = mPortfolioLink_local;
    }

    public String getmPortfolioName() {
        return mPortfolioName;
    }

    public void setmPortfolioName(String mPortfolioName) {
        this.mPortfolioName = mPortfolioName;
    }

    public String getmPortfolioLink() {
        return mPortfolioLink;
    }

    public void setmPortfolioLink(String mPortfolioLink) {
        this.mPortfolioLink = mPortfolioLink;
    }


}
