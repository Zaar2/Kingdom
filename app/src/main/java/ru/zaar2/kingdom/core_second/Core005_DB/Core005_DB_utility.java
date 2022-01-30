package ru.zaar2.kingdom.core_second.Core005_DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.Date;

import ru.zaar2.kingdom.R;
import ru.zaar2.kingdom.core_second.bcc.bcc004_initializingStartingValueDB;
import ru.zaar2.kingdom.core_second.Core005_DB.Core005_DB_value;

public class Core005_DB_utility {

    protected static void insertValues(SQLiteDatabase database) {

//        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        for (int i = 0; i < Core005_DB_value.ROWS_and_VALUES_TABLE_utility.length; i++) {
            values.put(Core005_DB_value.ID, i);
            values.put(Core005_DB_value.NAME, Core005_DB_value.ROWS_and_VALUES_TABLE_utility[i][0]);
            values.put(Core005_DB_value.VALUE_DEFAULT, Core005_DB_value.ROWS_and_VALUES_TABLE_utility[i][1]);
            values.put(Core005_DB_value.VALUE_CURRENTLY, Core005_DB_value.ROWS_and_VALUES_TABLE_utility[i][1]);
            database.insert(Core005_DB_value.NAME_TABLE_UTILITY, null, values);
        }
        for (int i = 0; i < Core005_DB_value.ROWS_and_VALUES_TABLE_indicators.length; i++) {
            values.put(Core005_DB_value.ID, i);
            values.put(Core005_DB_value.NAME, (Core005_DB_value.ROWS_and_VALUES_TABLE_indicators[i][0]));
            values.put(Core005_DB_value.VALUE_DEFAULT, Integer.parseInt(Core005_DB_value.ROWS_and_VALUES_TABLE_indicators[i][1]));
            values.put(Core005_DB_value.VALUE_CURRENTLY, Integer.parseInt(Core005_DB_value.ROWS_and_VALUES_TABLE_indicators[i][1]));
            database.insert(Core005_DB_value.NAME_TABLE_INDICATORS, null, values);
        }
        for (int i = 0; i < Core005_DB_value.ROWS_and_VALUES_TABLE_resources.length; i++) {
            values.put(Core005_DB_value.ID, i);
            values.put(Core005_DB_value.NAME, (Core005_DB_value.ROWS_and_VALUES_TABLE_resources[i][0]));
            values.put(Core005_DB_value.VALUE_DEFAULT, Integer.parseInt((Core005_DB_value.ROWS_and_VALUES_TABLE_resources[i][1])));
            values.put(Core005_DB_value.VALUE_CURRENTLY, Integer.parseInt((Core005_DB_value.ROWS_and_VALUES_TABLE_resources[i][1])));
            database.insert(Core005_DB_value.NAME_TABLE_RESOURCES, null, values);
        }
        for (int i = 0; i < Core005_DB_value.ROWS_and_VALUES_TABLE_accessory.length; i++) {
            values.put(Core005_DB_value.ID, i);
            values.put(Core005_DB_value.NAME, (Core005_DB_value.ROWS_and_VALUES_TABLE_accessory[i][0]));
            values.put(Core005_DB_value.VALUE_DEFAULT, Integer.parseInt(Core005_DB_value.ROWS_and_VALUES_TABLE_accessory[i][1]));
            values.put(Core005_DB_value.VALUE_CURRENTLY, Integer.parseInt(Core005_DB_value.ROWS_and_VALUES_TABLE_accessory[i][1]));
            database.insert(Core005_DB_value.NAME_TABLE_ACCESSORY, null, values);
        }
    }

//    public static void dropTable(SQLiteDatabase database, String nameTable){
//        database.execSQL(
//                "drop table if exists " + nameTable
//        );
//    }

    /**
     * <p>!!! Вернет контейнер без значений, в случае если таблицы базы не были инициализированы.</p>
     * <p>!!!В этом случае Bundle будет содержать одно значение boolean:name->"isFilled_utility",value->false </p>
     * <p>context.getResources().getString(R.string.str_isFilled_utility</p>
     *
     * @return Bundle dataContainer
     */
    protected static Bundle dataForDisplay(String table, SQLiteDatabase database) {
        Bundle dataContainer = new Bundle();
        if (!get_isFilled(database)) {
            dataContainer.putBoolean(Core005_DB_value.name_isFilled, false);
        } else {
            dataContainer.putBoolean(Core005_DB_value.name_isFilled, true);

//            SQLiteDatabase database = getWritableDatabase();

            String query = "SELECT * FROM " + table;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
//            int id_ColIndex = cursor.getColumnIndex("_id");
                int name_ColIndex = cursor.getColumnIndex("Name");
//            int value_default_ColIndex = cursor.getColumnIndex("Value_default");
                int value_currently_ColIndex = cursor.getColumnIndex("Value_currently");

                dataContainer.putInt(
                        cursor.getString(name_ColIndex),
                        Integer.parseInt(cursor.getString(value_currently_ColIndex))
                );

                cursor.moveToNext();
            }
            cursor.close();
        }
        return dataContainer;
    }

    protected static boolean get_isFilled(SQLiteDatabase database) {
        boolean isFilled;

//        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(
                Core005_DB_value.NAME_TABLE_UTILITY,
                new String[]{
                        Core005_DB_value.ID,
                        Core005_DB_value.NAME,
                        Core005_DB_value.VALUE_CURRENTLY
                },
                Core005_DB_value.NAME + " = ?",
                new String[]{Core005_DB_value.name_isFilled},
                null,
                null,
                null
        );
        cursor.moveToFirst();
        if (cursor.getCount() <= 0) {
            isFilled = false;
        } else {
            int value_currently_ColIndex = cursor.getColumnIndex(Core005_DB_value.VALUE_CURRENTLY);
            String result = cursor.getString(value_currently_ColIndex);
            isFilled = result.equals("true");
        }
        cursor.close();
        return isFilled;
    }

    /**
     * <p>!!! вернет "-100", в случае если таблицы базы не были инициализированы.</p>
     */
    protected static int findCurrentlyValue_ofSpecifiedParameter(String nameParameter, String nameTable, SQLiteDatabase database) {
        int result;

        if (!get_isFilled(database)) {
            result = -100;
        } else {
//            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.query(
                    nameTable,
                    new String[]{"_id", "Name", "Value_currently"},
                    "Name = ?",
                    new String[]{nameParameter},
                    null,
                    null,
                    null
            );
            cursor.moveToFirst();

            int value_currently_ColIndex = cursor.getColumnIndex("Value_currently");
            result = cursor.getInt(value_currently_ColIndex);

            cursor.close();
        }
        return result;
    }

    /**
     * !!!Вернет false в том числе если таблицы базы не инициализированны.
     */
    protected static boolean updateParameter(int value, String nameParameter, String nameTable, SQLiteDatabase database) {
        if (!get_isFilled(database)) {
            return false;
        } else {

            int id = -1;
//            SQLiteDatabase database = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Value_currently", value);
            id = database.update(
                    nameTable,
                    values,
                    "Name = ?",
                    new String[]{nameParameter}
            );

            return (id >= 0);
        }
    }

    public static int clearTable(SQLiteDatabase database, String nameTable){
        return database.delete(
                nameTable,
                null,
                null
        );
    }

    /**
     * <p>INSERT RECORD VALUE:</p>
     *     <p>1.read all rows from table and copy to array</p>
     *     <p>2.checking recordValue</p>
     *     <p>3.trueChecked->insert new value with sort array</p>
     *     <p>3.falseChecked->finish</p>
     *     <p>4.clear table</p>
     *     <p>5.copy all value pairs of array to rows of the table</p>
     *
     * @return true / false
     */
//    protected static boolean insertValue_recordTable(int value, Date date, SQLiteDatabase database) {
//        String[][] arr_recordTable = readFromRecordTable(database); //1
//        boolean checkValue = checkRecordValue(value, arr_recordTable); //2
//        if (checkValue) { //true
//            insert_and_sort_value_to_recordTable(value, date, arr_recordTable);
//        } else { //false
//            return false;
//        }
//        clearTable(database, Core005_DB_value.NAME_TABLE_RECORD);
//        copy_arrToTable(arr_recordTable, database, Core005_DB_value.NAME_TABLE_RECORD);
//        return true;
//    }
//
//    private static String[][] readFromRecordTable(SQLiteDatabase database) {
//        String query = "SELECT * FROM " + Core005_DB_value.NAME_TABLE_RECORD + " ORDER BY Value_currently ASC";
//        Cursor cursor = database.rawQuery(query, null);
//        int countRows = cursor.getCount();
//        String[][] resultArray = new String[countRows][2];
//        if (countRows > 0) {
//            if (cursor.moveToFirst()) {
//                while (!cursor.isAfterLast()) {
//                    for (int i = 0; i < countRows; i++) {
//                        int name_ColIndex = cursor.getColumnIndex("Name");
//                        int value_currently_ColIndex = cursor.getColumnIndex("Value_currently");
//
//                        resultArray[i][0] = cursor.getString(value_currently_ColIndex);
//                        resultArray[i][1] = cursor.getString(name_ColIndex);
//
//                        cursor.moveToNext();
//                    }
//                }
//            }
//        }
//        cursor.close();
//        return resultArray;
//    }

}