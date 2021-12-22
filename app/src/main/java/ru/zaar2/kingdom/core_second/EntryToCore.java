package ru.zaar2.kingdom.core_second;

import android.content.Context;
import android.os.Bundle;

public class EntryToCore {

    public EntryToCore() {
    }

    public void calcDB_forQuestions(Context context, int value) {
        new Core0002_calc_questions().calcDB_forQuestions(context, value);
    }

    public int[] calcDB_forYear(Context context){
        return new Core0001_calc_years().calcDB_years(context);
    }

    public void restartDB(Context context, int version) {
        new Core005_DB(context, version).restartDB();
    }

    public int findCurrentlyValue_ofSpecifiedParameter(String nameParameter, String nameTable, Context context, int versionDB) {
        return new Core005_DB(context, versionDB).findCurrentlyValue_ofSpecifiedParameter(nameParameter, nameTable);
    }

    public boolean updateParameter(int value, String nameParameter, String nameTable, Context context, int versionDB) {
        return new Core005_DB(context, versionDB).updateParameter(value, nameParameter, nameTable);
    }

    public Bundle dataForDisplay(String table, Context context, int versionDB) {
        return new Core005_DB(context, versionDB).dataForDisplay(table);
    }
}