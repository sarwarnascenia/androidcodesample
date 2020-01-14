package co.mirach.kounsel.redesign.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import co.mirach.kounsel.redesign.R;
import co.mirach.kounsel.redesign.home.model.CategoryItemDetailsModel;

public abstract class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder> {

    public abstract void onCLickMore(int position, ArrayList<String> finalCategoryItem);
    private Map<String, Object> mCounselorMap;
    private Map<String, Object> mCategoriesMap;
    private ArrayList<String> mCategoryItems;
    private ArrayList<ArrayList<String>> mFinalCategoryItems;
    private Context mContext;
    public CategoryItemAdapter(ArrayList<String> categories_local, Context context,
                               Map<String, Object> mCategoriesMap_local,Map<String, Object> mCounselorMap_local){
        mCategoryItems = categories_local;
        mContext = context;
        mCategoriesMap = mCategoriesMap_local;
        mCounselorMap = mCounselorMap_local;
        mFinalCategoryItems = new ArrayList<>();
    }
    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapte_home_category_item, viewGroup, false);
        CategoryItemViewHolder categoryItemViewHolder = new CategoryItemViewHolder(itemView);


        return categoryItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryItemViewHolder categoryItemViewHolder, final int position) {

        ArrayList<String> mainSubList = new ArrayList<>();
        ArrayList<String> mainList = new ArrayList<>();
        ArrayList<CategoryItemDetailsModel>  categoryItems = new ArrayList<>();
        mainList = (ArrayList<String>) mCategoriesMap.get(mCategoryItems.get(position));
        //Compare if the sub category counselor exist if root category in the list show all sub category
        //makeing a sublist to show in category in the category pages
        if(mCounselorMap.get(mCategoryItems.get(position))!= null){
            for(int i =0;i<mainList.size();i++){
                CategoryItemDetailsModel categoryItemDetailsModel = new CategoryItemDetailsModel(
                        mainList.get(i),
                        mContext.getResources().getDrawable(R.drawable.dental_care),
                        R.color.nice_blue, 2);
                categoryItems.add(categoryItemDetailsModel);
                mainSubList.add(mainList.get(i));
            }
        }
        else {
            for(int i =0;i<mainList.size();i++){
                if(mCounselorMap.get(mainList.get(i)) != null ) {
                    CategoryItemDetailsModel categoryItemDetailsModel = new CategoryItemDetailsModel(
                            mainList.get(i),
                            mContext.getResources().getDrawable(R.drawable.dental_care),
                            R.color.nice_blue, 2);
                    categoryItems.add(categoryItemDetailsModel);
                    mainSubList.add(mainList.get(i));
                }
            }
        }

        mFinalCategoryItems.add(mainSubList);

        // The nested recyclerview is used for showing sub category
        CategoryItemDetailsAdapter categoryItemDetailsAdapter = new
                CategoryItemDetailsAdapter(categoryItems,mContext);
        RecyclerView.LayoutManager mLayoutManagerLegal = new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL,false);
        categoryItemViewHolder.categoryRecyclerView.setLayoutManager(mLayoutManagerLegal);
        categoryItemViewHolder.categoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        categoryItemViewHolder.categoryRecyclerView.setAdapter(categoryItemDetailsAdapter);

        categoryItemViewHolder.categoryNameTextView.setText(mCategoryItems.get(position));
//        if(categoryItems.size()<=1)
//            categoryItemViewHolder.categoryMoreTextView.setVisibility(View.INVISIBLE);
        categoryItemViewHolder.categoryMoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCLickMore(position,mFinalCategoryItems.get(position));
            }
        });
        categoryItemViewHolder.categoryRecyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    public int getItemCount() {
        return mCategoryItems.size();
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout categoryRelativeLayout;
        private TextView categoryNameTextView;
        private TextView categoryMoreTextView;
        private RecyclerView categoryRecyclerView;

        public CategoryItemViewHolder(View view) {
            super(view);
            categoryRelativeLayout = view.findViewById(R.id.categoryRelativeLayout);
            categoryNameTextView = view.findViewById(R.id.categoryNameTextView);
            categoryMoreTextView = view.findViewById(R.id.categoryMoreTextView);
            categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        }
    }
}
