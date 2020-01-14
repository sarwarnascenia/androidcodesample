package co.mirach.kounsel.redesign.category.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import co.mirach.kounsel.redesign.R;
import co.mirach.kounsel.redesign.home.model.CounselorModel;

public class CategoryCounselorAdapter extends RecyclerView.Adapter<CategoryCounselorAdapter.CounselorViewHolder> {

    private ArrayList<CounselorModel> mCounselorModels;
    private Context mContext;
    public CategoryCounselorAdapter(ArrayList<CounselorModel> counselorModels_local, Context context){
        mCounselorModels = counselorModels_local;
        mContext = context;
    }
    @NonNull
    @Override
    public CategoryCounselorAdapter.CounselorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_category_counselor_details, viewGroup, false);
        return new CounselorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryCounselorAdapter.CounselorViewHolder counselorViewHolder, final int i) {
        counselorViewHolder.counselorNameTextView.setText(mCounselorModels.get(i).getmCounselorName());
        counselorViewHolder.CounselorTypeTextView.setText(mCounselorModels.get(i).getmCounselorType());
        counselorViewHolder.CounselorRateTextView.setText("600 usd / h ");
        counselorViewHolder.CounselorRatingTextView.setText(mCounselorModels.get(i).getmCounselorRating());
        counselorViewHolder.mainImageVIew.setBackground(mCounselorModels.get(i).getmCounselorDrawable());

    }

    @Override
    public int getItemCount() {
        return mCounselorModels.size();
    }

    public class CounselorViewHolder extends RecyclerView.ViewHolder {
        private ImageView mainImageVIew;
        private TextView counselorNameTextView;
        private TextView CounselorTypeTextView;
        private TextView CounselorRateTextView;
        private TextView CounselorRatingTextView;
        private RelativeLayout counselorDetailsRelativeLayout;
        private RelativeLayout counselorRateRatingRelativeLayout;

        public CounselorViewHolder(View view) {
            super(view);
            mainImageVIew = view.findViewById(R.id.mainImageVIew);
            counselorNameTextView = view.findViewById(R.id.counselorNameTextView);
            CounselorTypeTextView = view.findViewById(R.id.CounselorTypeTextView);
            CounselorRateTextView = view.findViewById(R.id.CounselorRateTextView);
            CounselorRatingTextView = view.findViewById(R.id.CounselorRatingTextView);
            counselorRateRatingRelativeLayout = view.findViewById(R.id.counselorRateRatingRelativeLayout);

            counselorDetailsRelativeLayout = view.findViewById(R.id.counselorDetailsRelativeLayout);
        }
    }
}
