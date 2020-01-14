package co.mirach.kounsel.redesign.category.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import co.mirach.kounsel.redesign.R;
import co.mirach.kounsel.redesign.category.adapter.CategoryCounselorAdapter;
import co.mirach.kounsel.redesign.category.adapter.CategotyAdapter;
import co.mirach.kounsel.redesign.home.Utility;
import co.mirach.kounsel.redesign.home.model.CounselorModel;

// This activity is showing category wise counselor
public class CategoryActivity extends AppCompatActivity {

    private  RecyclerView mCategoryView;
    private  RecyclerView mCounselorsRecycleView;

    private static final int DEFAULT_TOTAL_IMAGE = 2;
    private static final int DEFAULT_TOTAL_IMAGE_OFFSET = 1;

    private TextView mTittleNameTextView;
    private LinearLayout mBackLinearLayout;

    private static final String TAG =  CategoryActivity.class.getSimpleName();
    private String mTittleName;

    private static final String CATEGORY_NAME = "category_name";
    private static final String SUB_CATEGORY_NAMES = "sub_category_names";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //get Category as tittle name from
        mTittleName = getIntent().getStringExtra(CATEGORY_NAME);
        mTittleNameTextView = findViewById(R.id.tittleNameTextView);
        mTittleNameTextView.setText(mTittleName);



        //creating item sub category from category
        ArrayList<String> subCategory = new ArrayList<>();
        subCategory.add(getResources().getString(R.string.all));
        subCategory.addAll(getIntent().getStringArrayListExtra(SUB_CATEGORY_NAMES));
        mCategoryView = findViewById(R.id.categoryRecyclerView);
        CategotyAdapter categotyAdapter = new CategotyAdapter(subCategory, CategoryActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mCategoryView.setLayoutManager(mLayoutManager);
        mCategoryView.setItemAnimator(new DefaultItemAnimator());
        mCategoryView.setAdapter(categotyAdapter);
//        getCounselorMap();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        toolbar.setContentInsetsAbsolute(0,0);
        toolbar.setPadding(0, 0, 0, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.darkish_blue));
        }

        mBackLinearLayout = findViewById(R.id.backLinearLayout);
        mBackLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        float density = getResources().getDisplayMetrics().density;
        int itemCategoryWidth = (int)(getResources().getDimension(R.dimen.recyclerview_item_width)/density);
        int minimumOffset = (int)(getResources().getDimension(R.dimen.minimum_offset_for_grid_view)/density);

        mCounselorsRecycleView = findViewById(R.id.counselorRecyclerView);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int totalItem = DEFAULT_TOTAL_IMAGE;
        int layoutParam = 0;
        int resultWidthDP = (int) (width/density);
        if(resultWidthDP>(itemCategoryWidth*3)){
            if((resultWidthDP%itemCategoryWidth) > minimumOffset) {
                totalItem = (int) (resultWidthDP / itemCategoryWidth);
            }else{
                totalItem = (int) ((resultWidthDP-itemCategoryWidth) / itemCategoryWidth);
            }
        }
        layoutParam = (resultWidthDP - (itemCategoryWidth*totalItem))/(totalItem+DEFAULT_TOTAL_IMAGE_OFFSET);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mCounselorsRecycleView.getLayoutParams();
        params.setMargins(layoutParam, 0, layoutParam, 0); //substitute parameters for left, top, right, bottom
        mCounselorsRecycleView.setLayoutParams(params);

        // currently showing static data updated once data received from firestore
        ArrayList<CounselorModel> counselorModels = Utility.getCounselorCategories(CategoryActivity.this);
        CategoryCounselorAdapter counselorAdapter = new CategoryCounselorAdapter(counselorModels,this);
        mCounselorsRecycleView.setLayoutManager(new GridLayoutManager(this, totalItem));
        mCounselorsRecycleView.setItemAnimator(new DefaultItemAnimator());
        mCounselorsRecycleView.setAdapter(counselorAdapter);

    }

}
