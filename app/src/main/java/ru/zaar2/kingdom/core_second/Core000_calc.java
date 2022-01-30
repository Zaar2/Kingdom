package ru.zaar2.kingdom.core_second;

import android.content.Context;
import android.os.Bundle;

import ru.zaar2.kingdom.core_second.Core005_DB.Core005_DB;

public class Core000_calc {

    public final Randomized randomized;
    public Bundle resources_data_bundle;
    public Bundle indicators_data_bundle;
    public Bundle accessory_data_bundle;

    public Core000_calc() {
        randomized = new Randomized();
    }

    public void initBundle(
            String nameTable_accessory,
            String nameTable_resources,
            String nameTable_indicators, Context context
    ) {
        Core005_DB db = new Core005_DB(context, 1);

        resources_data_bundle = db.dataForDisplay(nameTable_resources);
        indicators_data_bundle = db.dataForDisplay(nameTable_indicators);
        accessory_data_bundle = db.dataForDisplay(nameTable_accessory);
    }
    /**
     *!!!Вернет false в том числе если таблицы базы не инициализированны.
     */
    public boolean updateDB(Context context) {
        return new Core005_DB(context, 1).updateDB(
                resources_data_bundle,
                indicators_data_bundle,
                accessory_data_bundle,
                context
        );
    }
}