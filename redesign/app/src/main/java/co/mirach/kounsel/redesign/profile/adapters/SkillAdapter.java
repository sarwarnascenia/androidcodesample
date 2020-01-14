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

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder>{

    private List<String> mSkillList;

    private Context mContext;
    public SkillAdapter(List<String> specialDayList, Context context){
        mSkillList = specialDayList;
        mContext = context;
    }
    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_profile_skill, parent, false);
        SkillViewHolder categoryItemViewHolder = new SkillViewHolder(itemView);


        return categoryItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        holder.profileSkillTextView.setText(mSkillList.get(position));
    }

    @Override
    public int getItemCount() {
        return mSkillList.size();
    }

    public class SkillViewHolder extends RecyclerView.ViewHolder {
        private TextView profileSkillTextView;

        public SkillViewHolder(View view) {
            super(view);
            profileSkillTextView = view.findViewById(R.id.profileSkillTextView);
        }
    }
}
