package com.android.javaeemongodb.helper;

import android.content.Context;

import com.android.javaeemongodb.R;

public class Colors {
    public static int getRandomMaterialColor(final Context context, String typeColor) {
        int[] colorArray = new int[18];
        colorArray[0] = R.color.red_500;
        colorArray[1] = R.color.pink_500;
        colorArray[2] = R.color.purple_500;
        colorArray[3] = R.color.deep_purple_500;
        colorArray[4] = R.color.indigo_500;
        colorArray[5] = R.color.blue_500;
        colorArray[6] = R.color.light_blue_500;
        colorArray[7] = R.color.cyan_500;
        colorArray[8] = R.color.teal_500;
        colorArray[9] = R.color.green_500;
        colorArray[10] = R.color.light_green_500;
        colorArray[11] = R.color.lime_500;
        colorArray[12] = R.color.yellow_500;
        colorArray[13] = R.color.orange_500;
        colorArray[14] = R.color.deep_orange_500;
        colorArray[15] = R.color.brown_500;
        colorArray[16] = R.color.grey_500;
        colorArray[17] = R.color.blue_grey_500;

        int index = (int) (Math.random() * colorArray.length);
        return colorArray[index];
    }
}
