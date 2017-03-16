package com.lagosdevelopers.lagdevs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lagosdevelopers.lagdevs.R;
import com.lagosdevelopers.lagdevs.entities.Developer;
import com.lagosdevelopers.lagdevs.utils.ImageUtils;
import com.lagosdevelopers.lagdevs.utils.TextUtils;

import java.util.List;

/**
 * Created by blessochampion on 1/8/17.
 */
public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.ViewHolder> {
    public interface  OnDeveloperSelectedListener{
        void selectedDeveloperPosition(int position);
    }

    private List<Developer> developers;
    private OnDeveloperSelectedListener listener;
    Context context;

    public DeveloperAdapter(Context c, List<Developer> developers, OnDeveloperSelectedListener listener){
        this.developers = developers;
        this.listener = listener;
        context = c;
    }

    @Override
    public DeveloperAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.developer_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(developers.get(position), position);
    }

    @Override
    public int getItemCount() {
        return developers.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView profileImage;
        TextView username;
        View view;

        public ViewHolder(View v) {
            super(v);
            profileImage = (ImageView) v.findViewById(R.id.profile_image);
            username = (TextView) v.findViewById(R.id.username);
            view = v;
            v.setOnClickListener(this);
        }
         void bind(Developer developer, int position) {
             ImageUtils.loadImageInto(context, profileImage, R.drawable.avatar, developer.getProfilePhotoUrl());
             username.setText(TextUtils.capitalizeFirsCharacterIn(developer.getUsername()));
             view.setTag(position);
         }

        @Override
        public void onClick(View v) {
            listener.selectedDeveloperPosition(getIntVersion(v.getTag().toString()));
        }

        private int getIntVersion(String pos){
            return Integer.parseInt(pos);
        }
    }

}
