package com.example.endline_v1;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endline_v1.ui.beauty.BeautyFragment;
import com.example.endline_v1.ui.food.FoodFragment;
import com.example.endline_v1.ui.health.HealthFragment;
import com.example.endline_v1.ui.home.HomeFragment;
import com.example.endline_v1.ui.login.ProfileFragment;
import com.example.endline_v1.ui.medical.MedicalFragment;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    List<String> titles;
    List<Integer> images;
    Context context;
    LayoutInflater inflater;


    public MainAdapter(Context context, List<String> titles, List<Integer> images){
        this.titles = titles;
        this.images = images;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.gridIcon.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_main_menu);
            gridIcon = itemView.findViewById(R.id.iv_main_icon);
        }
    }
}
