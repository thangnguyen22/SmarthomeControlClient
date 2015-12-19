package com.uit.smarthomecontrol.util;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by tensh on 12/15/2015.
 */
public class ChangeFont {
    public static void changeDefaultFont(Context context, String nameOfFontBeingChanged, String nameOfFontInAsset){
        Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), nameOfFontInAsset);
        changeFont(nameOfFontBeingChanged, customFontTypeface);
    }

    private static void changeFont(String nameOfFontBeingChanged, Typeface customFontTypeface) {
        try {
            Field _myField = Typeface.class.getDeclaredField(nameOfFontBeingChanged);
            _myField.setAccessible(true);
            _myField.set(null, customFontTypeface);
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }
}

