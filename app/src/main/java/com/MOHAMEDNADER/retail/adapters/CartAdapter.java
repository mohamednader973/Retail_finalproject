package com.MOHAMEDNADER.retail.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.MOHAMEDNADER.retail.ProductModel;
import com.MOHAMEDNADER.retail.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<ProductModel> productList;

    private OnIncClickListener onIncClickListener;
    private OnDecClickListener onDecClickListener;

    public interface OnIncClickListener {

        void onIncClick(View view, int position);
    }

    public interface OnDecClickListener {

        void onDecClick(View view, int position);
    }


    public CartAdapter(Context context, List<ProductModel> productList, OnIncClickListener onIncClickListener, OnDecClickListener onDecClickListener) {
        this.context = context;
        this.productList = productList;
        this.onIncClickListener = onIncClickListener;
        this.onDecClickListener = onDecClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cart_rv_item, parent, false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, int position) {

        ProductModel productModel = productList.get(position);
        Glide.with(context).load(productModel.getImage()).into(holder.productIv);
        holder.titleTv.setText(productModel.getTitle());
        holder.detailsTv.setText(productModel.getDetails());
        holder.priceTv.setText(productModel.getPrice());

        holder.quantityTv.setText(productModel.getQuantity() + "");

        holder.incIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onIncClickListener.onIncClick(v, holder.getAdapterPosition());
            }
        });


        holder.decIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onDecClickListener.onDecClick(v, holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView productIv;
        TextView titleTv;
        TextView detailsTv;
        TextView priceTv;
        TextView quantityTv;
        ImageButton incIb;
        ImageButton decIb;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            productIv = itemView.findViewById(R.id.product_cart_iv);
            titleTv = itemView.findViewById(R.id.product_cart_title_tv);
            detailsTv = itemView.findViewById(R.id.product_cart_details_tv);
            priceTv = itemView.findViewById(R.id.product_cart_price_tv);
            quantityTv = itemView.findViewById(R.id.quantity_tv);
            incIb = itemView.findViewById(R.id.inc_ib);
            decIb = itemView.findViewById(R.id.dec_ib);
        }
    }
}
