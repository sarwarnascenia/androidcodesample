package co.mirach.kounsel.redesign.profile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import co.mirach.kounsel.redesign.R;

public class SpecialDayAdapter extends RecyclerView.Adapter<SpecialDayAdapter.SpecialDayViewHolder>{

    private List<String> mSpecialDayList;

    private Context mContext;
    public SpecialDayAdapter(List<String> specialDayList,Context context){
        mSpecialDayList = specialDayList;
        mContext = context;
    }
    @NonNull
    @Override
    public SpecialDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_profile_special_day, parent, false);
        SpecialDayViewHolder categoryItemViewHolder = new SpecialDayViewHolder(itemView);


        return categoryItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialDayViewHolder holder, int position) {
        holder.specialDayTextView.setText(mSpecialDayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mSpecialDayList.size();
    }

    public class SpecialDayViewHolder extends RecyclerView.ViewHolder {
        private TextView specialDayTextView;

        public SpecialDayViewHolder(View view) {
            super(view);
            specialDayTextView = view.findViewById(R.id.specialDayTextView);
        }
    }
}
