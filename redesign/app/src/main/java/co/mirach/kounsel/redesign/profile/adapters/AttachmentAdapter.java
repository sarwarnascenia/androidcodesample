package co.mirach.kounsel.redesign.profile.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import co.mirach.kounsel.redesign.R;
import co.mirach.kounsel.redesign.profile.models.AttachmentModel;

public class AttachmentAdapter extends RecyclerView.Adapter<AttachmentAdapter.PortfolioViewHolder>{

    private List<AttachmentModel> mAttachmentList;

    private Context mContext;
    public AttachmentAdapter(List<AttachmentModel> portfolioModel, Context context){
        mAttachmentList = portfolioModel;
        mContext = context;
    }
    @NonNull
    @Override
    public PortfolioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_profile_attachment, parent, false);
        PortfolioViewHolder categoryItemViewHolder = new PortfolioViewHolder(itemView);


        return categoryItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioViewHolder holder, int position) {
        holder.attachmentsTextView.setText(mAttachmentList.get(position).getmAttachmentName());
        Drawable imgResource = mAttachmentList.get(position).getmAttachmentImage();
        holder.attachmentsTextView.setCompoundDrawablesWithIntrinsicBounds(imgResource, null, null, null);
    }

    @Override
    public int getItemCount() {
        return mAttachmentList.size();
    }

    public class PortfolioViewHolder extends RecyclerView.ViewHolder {
        private TextView attachmentsTextView;

        public PortfolioViewHolder(View view) {
            super(view);
            attachmentsTextView = view.findViewById(R.id.attachmentsTextView);
        }
    }
}
