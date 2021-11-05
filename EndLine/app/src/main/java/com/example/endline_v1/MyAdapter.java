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

        Glide.with(holder.itemView).load(productsArrayList.get(position).getImg()).into(holder.img);

        holder.productName.setText(products.getProductName());
        holder.endline.setText(products.getEndline()); //setText 괄호 안에를 바꾸면 내용이 바뀌나봄?
        holder.addDay.setText(products.getAddDay());
        holder.category.setText(products.getCategory());

    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView productName, endline, addDay, category;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.tvProductName);
            endline = itemView.findViewById(R.id.tvEndline);
            addDay = itemView.findViewById(R.id.tvAddDay);
            category = itemView.findViewById(R.id.tvCategory);
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