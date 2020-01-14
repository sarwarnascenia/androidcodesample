package co.mirach.kounsel.redesign.profile.activity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import co.mirach.kounsel.redesign.R;
import co.mirach.kounsel.redesign.home.Utility;
import co.mirach.kounsel.redesign.home.adapter.CounselorAdapter;
import co.mirach.kounsel.redesign.home.model.CounselorModel;
import co.mirach.kounsel.redesign.profile.adapters.AttachmentAdapter;
import co.mirach.kounsel.redesign.profile.adapters.PortfolioAdapter;
import co.mirach.kounsel.redesign.profile.adapters.SkillAdapter;
import co.mirach.kounsel.redesign.profile.adapters.SpecialDayAdapter;
import co.mirach.kounsel.redesign.profile.models.AttachmentModel;
import co.mirach.kounsel.redesign.profile.models.PortfolioModel;


/* This is Profile Page activity */
public class ProfileActivity extends AppCompatActivity {

    RecyclerView mCounselorsRecycleView;
    RecyclerView mSpecialDayRecyclerView;
    RecyclerView mPortfolioRecyclerView;
    RecyclerView mAttachmentsRecyclerView;
    RecyclerView mSkillRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_profile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mSkillRecyclerView = findViewById(R.id.skillRecyclerView);
        List<String> skilledList = new ArrayList<>();
        skilledList.add("Social Worker");
        skilledList.add("Clinical Doctor");
        skilledList.add("Engineer");
        skilledList.add("Auto Repear");
        skilledList.add("Clinical Staff");
        SkillAdapter skillAdapter = new SkillAdapter(skilledList,this);

        mSkillRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mSkillRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSkillRecyclerView.setAdapter(skillAdapter);



        mSpecialDayRecyclerView = findViewById(R.id.specialDayValueTextView);
        List<String> specialDayList = new ArrayList<>();
        specialDayList.add("Christmas");
        specialDayList.add("Thanksgiving");
        SpecialDayAdapter specialDayAdapter = new SpecialDayAdapter(specialDayList,this);
        mSpecialDayRecyclerView.setLayoutManager( new LinearLayoutManager(this));
        mSpecialDayRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSpecialDayRecyclerView.setAdapter(specialDayAdapter);


        mPortfolioRecyclerView = findViewById(R.id.portfolioRecyclerView);
        List<PortfolioModel> portfolioModelArrayList = new ArrayList<>();
        PortfolioModel portfolioModel = new PortfolioModel("Linkedin","http://www.linkedin.com/myprofile");
        portfolioModelArrayList.add(portfolioModel);
        portfolioModel = new PortfolioModel("Facebook","http://www.facebook.com/myprofile");
        portfolioModelArrayList.add(portfolioModel);
        PortfolioAdapter portfolioAdapter = new PortfolioAdapter(portfolioModelArrayList,this);
        mPortfolioRecyclerView.setLayoutManager( new LinearLayoutManager(this));
        mPortfolioRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mPortfolioRecyclerView.setAdapter(portfolioAdapter);


        mAttachmentsRecyclerView = findViewById(R.id.attachmentsRecyclerView);
        List<AttachmentModel> attachmentModelArrayList = new ArrayList<>();
        AttachmentModel attachmentModel = new AttachmentModel("Audio file.mp3",
                getResources().getDrawable(R.drawable.ic_audio));
        attachmentModelArrayList.add(attachmentModel);
        attachmentModel = new AttachmentModel("Image file.png",
                getResources().getDrawable(R.drawable.ic_image));
        attachmentModelArrayList.add(attachmentModel);
        attachmentModel = new AttachmentModel("Video file.mp4",
                getResources().getDrawable(R.drawable.ic_video));
        attachmentModelArrayList.add(attachmentModel);
        AttachmentAdapter attachmentAdapter = new AttachmentAdapter(attachmentModelArrayList,this);
        mAttachmentsRecyclerView.setLayoutManager( new LinearLayoutManager(this));
        mAttachmentsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAttachmentsRecyclerView.setAdapter(attachmentAdapter);

        // currently showing static data updated once data received from firestore
        mCounselorsRecycleView = findViewById(R.id.counselorRecyclerView);
        ArrayList<CounselorModel> counselorModels = Utility.getCounselorCategories(ProfileActivity.this);
        CounselorAdapter counselorAdapter = new CounselorAdapter(counselorModels,this);
        RecyclerView.LayoutManager mLayoutManagerVounselor = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,false);
        mCounselorsRecycleView.setLayoutManager(mLayoutManagerVounselor);
        mCounselorsRecycleView.setItemAnimator(new DefaultItemAnimator());
        mCounselorsRecycleView.setAdapter(counselorAdapter);

        ImageView reviewerImageView = findViewById(R.id.reviewerImageView);
        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        Uri imageURI = Utility.getUriToResource(this,R.drawable.eye_care);
//        imageLoader.displayImage(imageURI.toString(), reviewerImageView);
        reviewerImageView.setBackground(getResources().getDrawable(R.drawable.small_user_photo));
    }
}
