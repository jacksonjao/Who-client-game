package com.jacksonjao.jhon.who;

import java.util.Comparator;

import envio.UserRanking;

/**
 * Created by Jhon on 22/05/16.
 */
public class CompararRanking implements Comparator<UserRanking> {
    @Override
    public int compare(UserRanking lhs, UserRanking rhs) {
        return (int)(Integer.parseInt(lhs.getTiempoTotal()))-(Integer.parseInt(rhs.getTiempoTotal()));
    }
}
