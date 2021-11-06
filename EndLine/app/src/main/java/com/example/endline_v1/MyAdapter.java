package com.example.endline_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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

        Glide.with(holder.itemView).load(productsArrayList.get(position).getPhoto_url()).into(holder.photo_url);

        holder.name.setText(products.getName());
        holder.end_date.setText(products.getEnd_date()); //setText 괄호 안에를 바꾸면 내용이 바뀌나봄?
        holder.buy_date.setText(products.getBuy_date());
        holder.category.setText(products.getCategory());

    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, end_date, buy_date, category;
        ImageView photo_url;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_rv_product_name);
            end_date = itemView.findViewById(R.id.tv_rv_end_date);
            buy_date = itemView.findViewById(R.id.tv_rv_buy_date);
            category = itemView.findViewById(R.id.tv_rv_category);
            photo_url = itemView.findViewById(R.id.iv_rv_product_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 항목을 클릭하면 물품 정보화면으로 넘어가게 하는 코드
                }
            });


        }
    }
}