package in.co.ikai.retrofitrecyclerviewandruntimepermissions.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import in.co.ikai.retrofitrecyclerviewandruntimepermissions.R;
import in.co.ikai.retrofitrecyclerviewandruntimepermissions.dataModel.NationalFlag;

public class FlagDataAdapter extends RecyclerView.Adapter<FlagDataAdapter.ViewHolder> {

    private ArrayList<NationalFlag> mFlagList;
    private Context mContext;
    private OnCardClickListener mClickListener;

    public FlagDataAdapter(ArrayList<NationalFlag> mFlagList
            , Context context, OnCardClickListener mClickListener) {
        this.mFlagList = mFlagList;
        mContext = context;
        this.mClickListener = mClickListener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.single_image_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlagDataAdapter.ViewHolder holder, int position) {
        NationalFlag flag = mFlagList.get(position);
        Log.e("Sonu", "onBindViewHolder: " + flag.getFlag() );
        Glide.with(mContext)
                .load(flag.getFlag())
                .into(holder.flagImageView);
        holder.rootCardView
                .setOnClickListener(v -> mClickListener.imageClicked(flag.getFlag()));
    }

    @Override
    public int getItemCount() {
        return mFlagList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView flagImageView;
        CardView rootCardView;
        ViewHolder(View itemView) {
            super(itemView);
            flagImageView = itemView.findViewById(R.id.img_single_image);
            rootCardView = itemView.findViewById(R.id.cv_root_card);
        }
    }

    public interface OnCardClickListener{
        void imageClicked(String url);
    }
}
