package ru.zaar2.kingdom.core_second;

import android.content.res.Resources;
import android.util.TypedValue;

public class getResourcesDimen {
    public static float getFloat(int dimenID, Resources resources) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(dimenID, typedValue, true);
        float val = typedValue.getFloat();
        typedValue = null;
        return val;
    }

    public static int getInt(int dimenID, Resources resources) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(dimenID, typedValue, true);
        int val = (int) typedValue.getFloat();
        typedValue = null;
        return val;
    }
}
