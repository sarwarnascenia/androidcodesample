package co.mirach.kounsel.redesign.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;

import co.mirach.kounsel.redesign.R;
import co.mirach.kounsel.redesign.category.activity.CategoryActivity;
import co.mirach.kounsel.redesign.home.Utility;
import co.mirach.kounsel.redesign.home.adapter.CategoryItemAdapter;
import co.mirach.kounsel.redesign.home.adapter.CategoryPopularItemAdapter;
import co.mirach.kounsel.redesign.home.adapter.CategotyAdapter;
import co.mirach.kounsel.redesign.home.adapter.CounselorAdapter;
import co.mirach.kounsel.redesign.home.model.CategoryItemDetailsModel;
import co.mirach.kounsel.redesign.home.model.CounselorModel;
import co.mirach.kounsel.android.icon.KounselIcon;
import co.mirach.kounsel.redesign.home.viewmodel.CategoryViewModel;
import co.mirach.kounsel.redesign.home.viewmodel.CounselorViewModel;
import co.mirach.kounsel.redesign.home.viewmodel.MostPopularViewModel;

import java.util.ArrayList;
import java.util.Map;


/* This is Home Page activity */
public class HomeActivity extends AppCompatActivity {

    private  RecyclerView mCategoryView;
    private  RecyclerView mMostPopularRecycleView;
    private  RecyclerView mCounselorsRecycleView;

    private CategoryPopularItemAdapter categoryPopularItemAdapter;


    private static final String TAG = HomeActivity.class.getSimpleName();

    private static final String CATEGORY_NAME = "category_name";
    private static final String SUB_CATEGORY_NAMES = "sub_category_names";


    private Map<String, Object> mCounselorMap;
    private Map<String, Object> mCategoriesMap;
    private ArrayList<String> mCategoriesList;


    private CategoryViewModel mCategoryViewModel;
    private MostPopularViewModel mMostPopularViewModel;
    private CounselorViewModel mCounselorViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseApp.initializeApp(this);

        /* This is done for getting most popular item from firestore */
        mMostPopularViewModel = ViewModelProviders.of(this).get(MostPopularViewModel.class);
        final Observer<ArrayList<CategoryItemDetailsModel>> popularObserver = new Observer<ArrayList<CategoryItemDetailsModel>>() {
            @Override
            public void onChanged(ArrayList<CategoryItemDetailsModel> categoryItemDetailsModels) {
                if(categoryItemDetailsModels != null)
                    constructMostPopular(categoryItemDetailsModels);
                else
                    findViewById(R.id.mostPopulerRelativeLayout).setVisibility(View.GONE);
            }

        };
        mMostPopularViewModel.getCategories(this).observe(this, popularObserver);


        /* For loading counselor map from firestore */
        mCounselorViewModel = ViewModelProviders.of(this).get(CounselorViewModel.class);
        final Observer<Map<String,Object>> counselorObserver = new Observer<Map<String,Object>>() {
            @Override
            public void onChanged(Map<String, Object> categoryObjectMap) {
                if(categoryObjectMap != null) {
                    // For loading the category from fire store
                    mCounselorMap = categoryObjectMap;
                    mCategoryViewModel = ViewModelProviders.of(HomeActivity.this).get(CategoryViewModel.class);
                    mCategoryViewModel.setCounselorMap(mCounselorMap);
                    final Observer<ArrayList<String>> categoryObserver = new Observer<ArrayList<String>>() {
                        @Override
                        public void onChanged(ArrayList<String> categoryObjectMap) {
                            if (categoryObjectMap != null) {
                                mCategoriesList = categoryObjectMap;
                                mCategoriesMap = mCategoryViewModel.getCategoriesMap();
                                constructMainCategoriesMap();
                            }
                        }
                    };
                    mCategoryViewModel.getCategories().observe(HomeActivity.this, categoryObserver);
                }
            }

        };
        mCounselorViewModel.getCategories().observe(this, counselorObserver);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        toolbar.setContentInsetsAbsolute(0,0);
        float density = getResources().getDisplayMetrics().density;
        int toolbarOffset = (int)(getResources().getDimension(R.dimen.toolbar_offset)/density);
        int topPadding = (int)density*toolbarOffset;
        toolbar.setPadding(0, -topPadding, 0, 0);
        int kounsetTextWidth = (int)(getResources().getDimension(R.dimen.kounsel_text_width)/density);
        int kounsetTexHeight = (int)(getResources().getDimension(R.dimen.kounsel_text_height)/density);
        ImageView kounselImageLogo = findViewById(R.id.kounselImageLogoImageView);
        kounselImageLogo.setImageDrawable(new co.mirach.kounsel.android.icon.IconicsDrawable(this,
                KounselIcon.Icon.logo_text).color(getResources().getColor(R.color.navy_blue)).
                sizeDpX(kounsetTextWidth).sizeDpY(kounsetTexHeight));



        // currently showing static data updated once data received from firestore
        mCounselorsRecycleView = findViewById(R.id.counselorRecyclerView);
        ArrayList<CounselorModel> counselorModels = Utility.getCounselorCategories(HomeActivity.this);
        CounselorAdapter counselorAdapter = new CounselorAdapter(counselorModels,this);
        RecyclerView.LayoutManager mLayoutManagerVounselor = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,false);
        mCounselorsRecycleView.setLayoutManager(mLayoutManagerVounselor);
        mCounselorsRecycleView.setItemAnimator(new DefaultItemAnimator());
        mCounselorsRecycleView.setAdapter(counselorAdapter);

    }

    private void constructMainCategoriesMap(){

        mCategoryView = findViewById(R.id.categoryRecyclerView);
        ArrayList<String> categoryArrayList = new ArrayList<>();
        categoryArrayList.add(getResources().getString(R.string.all));
        categoryArrayList.addAll(mCategoriesList);
        CategotyAdapter categotyAdapter = new CategotyAdapter(categoryArrayList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        mCategoryView.setLayoutManager(mLayoutManager);
        mCategoryView.setItemAnimator(new DefaultItemAnimator());
        mCategoryView.setAdapter(categotyAdapter);

        RecyclerView categoryDetailsRecyclerView = findViewById(R.id.categoryDetailsRecyclerView);
        CategoryItemAdapter categoryItemAdapter = new CategoryItemAdapter(mCategoriesList, this, mCategoriesMap, mCounselorMap) {
            @Override
            public void onCLickMore(int position,ArrayList<String> finalCategoryItem) {
                Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
                intent.putExtra(CATEGORY_NAME,mCategoriesList.get(position));
                intent.putStringArrayListExtra(SUB_CATEGORY_NAMES,finalCategoryItem);
                startActivity(intent);
            }
        };
        RecyclerView.LayoutManager mLayoutManagerEducation = new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL,false);
        categoryDetailsRecyclerView.setLayoutManager(mLayoutManagerEducation);
        categoryDetailsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        categoryDetailsRecyclerView.setAdapter(categoryItemAdapter);
        categoryDetailsRecyclerView.setNestedScrollingEnabled(false);
    }

    private void constructMostPopular(ArrayList<CategoryItemDetailsModel> categoryItemDetailsModels){
        mMostPopularRecycleView = findViewById(R.id.mostPopularRecyclerView);
        categoryPopularItemAdapter = new CategoryPopularItemAdapter(categoryItemDetailsModels, HomeActivity.this);
        RecyclerView.LayoutManager mLayoutManagerMostPopular = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,false);
        mMostPopularRecycleView.setLayoutManager(mLayoutManagerMostPopular);
        mMostPopularRecycleView.setItemAnimator(new DefaultItemAnimator());
        mMostPopularRecycleView.setAdapter(categoryPopularItemAdapter);

    }
}
