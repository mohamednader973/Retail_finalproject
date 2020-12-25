package com.MOHAMEDNADER.retail.asyncTask;

import android.os.AsyncTask;

import com.MOHAMEDNADER.retail.room.ProductDAO;

public class DeleteAsyncTask extends AsyncTask<Void,Void,Void> {

    private ProductDAO productDAO;

    public DeleteAsyncTask(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        productDAO.deleteAllProducts();
        return null;
    }
}
