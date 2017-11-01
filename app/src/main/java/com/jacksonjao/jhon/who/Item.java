package com.jacksonjao.jhon.who;

import android.graphics.Bitmap;

/**
 * Created by Jhon on 16/05/16.
 */
public class Item {
    Integer imagen;
    int id;


    public Item(int id, Integer imagen) {
        this.imagen = imagen;
        this.id = id;
    }

    public Integer getImagen() {
        return imagen;
    }

    public int getId() {
        return id;
    }
}
