package ru.zaar2.kingdom;

import android.content.res.Resources;
import android.text.format.DateFormat;
import android.widget.TextView;

import java.util.Date;

public class RecordTable {

    private final TextView recordTable_tv;

    public RecordTable(TextView recordTableTv) {
        recordTable_tv = recordTableTv;
    }

    public void init_recordTable(Resources resources, String[][] inputTable) {
        StringBuilder string = new StringBuilder(resources.getString(R.string.btn_RecordTable) + ":\n");
        if (inputTable != null) {
            for (String[] item : inputTable) {
                String dateString, add;
                String longV = item[2];
                long millisecond = Long.parseLong(longV);
                if (millisecond > 0) {
                    dateString = DateFormat.format("MM/dd/yyyy  hh.mm", new Date(millisecond)).toString();
                    add ="Правил, лет: " + item[1] +  "  ( " + dateString + " )\n";
                } else {
                    add = "";
                }
                string.append(add);
            }
        } else {
            string.append("_______________________\n");
            string.append("Таблица рекордов пустая!");
        }
        recordTable_tv.setText(string);
    }
}