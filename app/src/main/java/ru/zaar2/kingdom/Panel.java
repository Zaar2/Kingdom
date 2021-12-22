package ru.zaar2.kingdom;

import android.content.Context;
import android.os.Bundle;

import ru.zaar2.kingdom.core_second.EntryToCore;

public class Panel {

    public Panel() {
    }

    public Bundle dataForDisplay(String table, Context context) {
        return (new EntryToCore()).dataForDisplay(table, context, 1);
    }

    public void updateParameter(int value, String nameParameter, String nameTable, Context context) {
        new EntryToCore().updateParameter(value, nameParameter, nameTable, context, 1);
    }
}
