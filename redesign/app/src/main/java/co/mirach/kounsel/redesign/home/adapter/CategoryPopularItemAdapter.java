package co.mirach.kounsel.redesign.home.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import co.mirach.kounsel.redesign.R;
import co.mirach.kounsel.redesign.home.model.CategoryItemDetailsModel;

public class CategoryPopularItemAdapter extends RecyclerView.Adapter<CategoryPopularItemAdapter.CategoryItemViewHolder> {

    private ArrayList<CategoryItemDetailsModel> mCategoryItems;
    private Context mContext;
    public CategoryPopularItemAdapter(ArrayList<CategoryItemDetailsModel> categories_local, Context context){
        mCategoryItems = categories_local;
        mContext = context;
    }
    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_home_category_most_popular_item, viewGroup, false);
        return new CategoryItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryItemViewHolder categoryItemViewHolder, final int i) {
        if(mCategoryItems.get(i).getmType() == CategoryItemDetailsModel.INACTIVE_TYPE) {
            categoryItemViewHolder.inactiveRoundCurveImageView.setBackground(mContext.getResources().getDrawable(R.drawable.home_categoty_item_round_curve));
            categoryItemViewHolder.mainImageVIew.setBackground(mCategoryItems.get(i).getmCategotyDrawable());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                categoryItemViewHolder.inactiveRoundCurveImageView.setBackgroundTintList(
                        ContextCompat.getColorStateList(mContext, mCategoryItems.get(i).getmColor()));
            }else {
                ViewCompat.setBackgroundTintList(categoryItemViewHolder.inactiveRoundCurveImageView,
                        ContextCompat.getColorStateList(mContext, mCategoryItems.get(i).getmColor()));
            }

            categoryItemViewHolder.categoryNameInactiveTextView.setText(mCategoryItems.get(i).getmCategoryItemName());

            categoryItemViewHolder.activeRelativeLayout.setVisibility(View.GONE);
            categoryItemViewHolder.inactiveRelativeLayout.setVisibility(View.VISIBLE);
        }
        if(mCategoryItems.get(i).getmType() == CategoryItemDetailsModel.ACTIVE_TYPE) {
            categoryItemViewHolder.activeRoundCurveImageView.setBackground(mContext.getResources().getDrawable(R.drawable.home_categoty_item_bottom_curve));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                categoryItemViewHolder.activeRoundCurveImageView.setBackgroundTintList(
                        ContextCompat.getColorStateList(mContext, mCategoryItems.get(i).getmColor()));
            }
            else {
                ViewCompat.setBackgroundTintList(categoryItemViewHolder.activeRoundCurveImageView,
                        ContextCompat.getColorStateList(mContext, mCategoryItems.get(i).getmColor()));
            }
            categoryItemViewHolder.mainImageVIew.setBackground(mCategoryItems.get(i).getmCategotyDrawable());
            categoryItemViewHolder.categoryNameActiveTextView.setText(mCategoryItems.get(i).getmCategoryItemName());

            categoryItemViewHolder.categoryNameInactiveTextView.setVisibility(View.GONE);
            categoryItemViewHolder.activeRelativeLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mCategoryItems.size();
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mainImageVIew;
        private ImageView inactiveRoundCurveImageView;
        private ImageView activeRoundCurveImageView;
        private RelativeLayout inactiveRelativeLayout;
        private RelativeLayout activeRelativeLayout;
        private RelativeLayout mainRelativeLayout;
        private TextView categoryNameInactiveTextView;
        private TextView categoryNameActiveTextView;

        public CategoryItemViewHolder(View view) {
            super(view);
            mainImageVIew = view.findViewById(R.id.mainImageVIew);
            inactiveRoundCurveImageView = view.findViewById(R.id.inactiveRoundCurveImageView);
            activeRoundCurveImageView = view.findViewById(R.id.activeRoundCurveImageView);
            inactiveRelativeLayout = view.findViewById(R.id.inactiveRelativeLayout);
            activeRelativeLayout = view.findViewById(R.id.activeRelativeLayout);
            mainRelativeLayout = view.findViewById(R.id.mainRelativeLayout);
            categoryNameInactiveTextView = view.findViewById(R.id.categoryNameInactiveTextView);
            categoryNameActiveTextView = view.findViewById(R.id.categoryNameActiveTextView);
        }
    }
}
