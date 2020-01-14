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
import co.mirach.kounsel.redesign.profile.models.PortfolioModel;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.PortfolioViewHolder>{

    private List<PortfolioModel> mPortfolioModel;

    private Context mContext;
    public PortfolioAdapter(List<PortfolioModel> portfolioModel, Context context){
        mPortfolioModel = portfolioModel;
        mContext = context;
    }
    @NonNull
    @Override
    public PortfolioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_profile_portfolio, parent, false);
        PortfolioViewHolder categoryItemViewHolder = new PortfolioViewHolder(itemView);


        return categoryItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioViewHolder holder, int position) {
        holder.porfolioNameTextView.setText(mPortfolioModel.get(position).getmPortfolioName());
        holder.porfolioLinkTextView.setText(mPortfolioModel.get(position).getmPortfolioLink());
    }

    @Override
    public int getItemCount() {
        return mPortfolioModel.size();
    }

    public class PortfolioViewHolder extends RecyclerView.ViewHolder {
        private TextView porfolioNameTextView;
        private TextView porfolioLinkTextView;

        public PortfolioViewHolder(View view) {
            super(view);
            porfolioNameTextView = view.findViewById(R.id.porfolioNameTextView);
            porfolioLinkTextView = view.findViewById(R.id.porfolioLinkTextView);
        }
    }
}
