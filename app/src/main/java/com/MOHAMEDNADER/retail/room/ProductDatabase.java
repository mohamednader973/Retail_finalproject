package com.MOHAMEDNADER.retail.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.MOHAMEDNADER.retail.ProductModel;

@Database(entities = {ProductModel.class} , version = 1 , exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDAO getProductDao();

}
