package co.mirach.kounsel.redesign.home.adapter;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

import co.mirach.kounsel.redesign.R;

public class CategotyAdapter extends RecyclerView.Adapter<CategotyAdapter.CategoryViewHolder> {

    private CategoryViewHolder mLastViewHolder;
    private ArrayList<String> mServiceCategories;
    private Context mContext;
    public CategotyAdapter(ArrayList<String> categories_local, Context context){
        mServiceCategories = categories_local;
        mContext = context;
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_home_category, viewGroup, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder categoryViewHolder, int i) {
        if(i == 0) {
            categoryViewHolder.linearlayout.setBackground(mContext.getResources().getDrawable(R.drawable.home_category_selected));
            categoryViewHolder.textView.setTextColor(mContext.getResources().getColor(R.color.white_two));
            mLastViewHolder = categoryViewHolder;
        }
        categoryViewHolder.textView.setText(mServiceCategories.get(i));
        categoryViewHolder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(categoryViewHolder != mLastViewHolder) {
                    categoryViewHolder.linearlayout.setBackground(mContext.getResources().getDrawable(R.drawable.home_category_selected));
                    categoryViewHolder.textView.setTextColor(mContext.getResources().getColor(R.color.white_two));
                    if (mLastViewHolder != null) {
                        mLastViewHolder.linearlayout.setBackgroundColor(Color.TRANSPARENT);
                        mLastViewHolder.textView.setTextColor(mContext.getResources().getColor(R.color.navy_blue));
                    }
                    mLastViewHolder = categoryViewHolder;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mServiceCategories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private LinearLayout linearlayout;

        public CategoryViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
            linearlayout = view.findViewById(R.id.linearLayout);
        }
    }
}
