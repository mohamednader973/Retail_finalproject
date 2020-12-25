package com.MOHAMEDNADER.retail.asyncTask;

import android.os.AsyncTask;

import com.MOHAMEDNADER.retail.ProductModel;
import com.MOHAMEDNADER.retail.room.ProductDAO;

public class InsertAsyncTask extends AsyncTask<ProductModel, Void, Void> {

    private ProductDAO productDAO;

    public InsertAsyncTask(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    protected Void doInBackground(ProductModel... productModels) {
        productDAO.insertProduct(productModels[0]);
        return null;
    }
}
