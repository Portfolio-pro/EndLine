package com.example.myapplication3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Products> productsArrayList;

    public MyAdapter(Context context, ArrayList<Products> productsArrayList) {
        this.context = context;
        this.productsArrayList = productsArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Products products = productsArrayList.get(position);

        Glide.with(holder.itemView).load(productsArrayList.get(position).getImg()).into(holder.img);

        holder.name.setText(products.getName());
        holder.endDate.setText(products.getEndDate());
        holder.addDate.setText(products.getAddDate());
        holder.kind.setText(products.getKind());

    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, endDate, addDate, kind;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName);
            endDate = itemView.findViewById(R.id.tvEndDate);
            addDate = itemView.findViewById(R.id.tvAddDate);
            kind = itemView.findViewById(R.id.tvKind);
            img = itemView.findViewById(R.id.imgView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 항목을 클릭하면 물품 정보화면으로 넘어가게 하는 코드
                }
            });


        }
    }
}