package com.MOHAMEDNADER.retail.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.MOHAMEDNADER.retail.ProductModel;
import com.MOHAMEDNADER.retail.R;
import com.MOHAMEDNADER.retail.adapters.CartAdapter;
import com.MOHAMEDNADER.retail.asyncTask.DeleteAsyncTask;
import com.MOHAMEDNADER.retail.asyncTask.GetProductAsyncTask;
import com.MOHAMEDNADER.retail.asyncTask.UpdateAsyncTask;
import com.MOHAMEDNADER.retail.room.RoomFactory;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class CartFragment extends Fragment {

    private RecyclerView cartRv;
    private CartAdapter cartAdapter;

    ArrayList<ProductModel> productList = new ArrayList<>();

    private Button clearBtn;
    private Button checkOutBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRv = view.findViewById(R.id.cart_rv);
        clearBtn = view.findViewById(R.id.clear_btn);
        checkOutBtn = view.findViewById(R.id.checkout_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpRecyclerView();
        setUpClickListeners();
        getAllProductsFromDB();
    }

    private void getAllProductsFromDB() {

        try {
            productList.addAll(new GetProductAsyncTask(RoomFactory.getRoomDatabase(requireContext()).getProductDao()).execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cartAdapter.notifyDataSetChanged();

    }

    private void setUpClickListeners() {

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DeleteAsyncTask(RoomFactory.getRoomDatabase(requireContext()).getProductDao()).execute();
                productList.clear();
                cartAdapter.notifyDataSetChanged();

            }
        });
    }

    private void setUpRecyclerView() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        cartRv.setLayoutManager(layoutManager);
        cartRv.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));

        cartAdapter = new CartAdapter(requireContext(), productList, new CartAdapter.OnIncClickListener() {
            @Override
            public void onIncClick(View view, int position) {

                productList.get(position).setQuantity(productList.get(position).getQuantity() + 1 );

                new UpdateAsyncTask(RoomFactory.getRoomDatabase(requireContext()).getProductDao()).execute(productList.get(position));

                cartAdapter.notifyDataSetChanged();

            }
        }, new CartAdapter.OnDecClickListener() {
            @Override
            public void onDecClick(View view, int position) {


                productList.get(position).setQuantity(productList.get(position).getQuantity() - 1 );

                new UpdateAsyncTask(RoomFactory.getRoomDatabase(requireContext()).getProductDao()).execute(productList.get(position));

                cartAdapter.notifyDataSetChanged();
            }
        });

        cartRv.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

    }
}
