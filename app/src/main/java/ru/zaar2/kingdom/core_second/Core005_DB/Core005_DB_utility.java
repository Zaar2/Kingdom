package ru.zaar2.kingdom.core_second.Core005_DB;

import static ru.zaar2.kingdom.core_second.bcc.bcc001_value.MAX_COUNT_RECORD_TABLE;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Date;

public class Core005_DB_utility {

    private static final String TAG = "Core005_DB_utility";

    protected static void insertValues(SQLiteDatabase database) {
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

    /**
     * <p>!!! Вернет контейнер без значений, в случае если таблицы базы не были инициализированы.</p>
     * <p>!!!В этом случае Bundle будет содержать одно значение boolean:name->"isFilled_utility",value->false </p>
     * <p>context.getResources().getString(R.string.str_isFilled_utility</p>
     *
     * @return Bundle dataContainer
     */
    protected static Bundle dataForDisplay(String table, SQLiteDatabase database) {
        Bundle dataContainer = new Bundle();
        if (get_isFilled(database)) {
            dataContainer.putBoolean(Core005_DB_value.name_isFilled, true);

            String query = "SELECT * FROM " + table;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
//            int id_ColIndex = cursor.getColumnIndex("_id");
                int name_ColIndex = cursor.getColumnIndex(Core005_DB_value.NAME);
//            int value_default_ColIndex = cursor.getColumnIndex("Value_default");
                int value_currently_ColIndex = cursor.getColumnIndex(Core005_DB_value.VALUE_CURRENTLY);

                dataContainer.putInt(
                        cursor.getString(name_ColIndex),
                        Integer.parseInt(cursor.getString(value_currently_ColIndex))
                );

                cursor.moveToNext();
            }
            cursor.close();
        } else {
            dataContainer.putBoolean(Core005_DB_value.name_isFilled, false);
        }
        return dataContainer;
    }

    /**
     * Проверяет выполнялся-ли уже метод для заполнения таблиц или таблицы в базе пустые.
     * <p>При первом запуске приложения, в таблицу базы данных ("utility" -> context.getResources().getString(R.string.strDB_utility))</p>
     * <p>вносится значение true в соответствующее поле ("isFilled_utility" -> context.getResources().getString(R.string.str_isFilled_utility)),</p>
     * <p>В дальнейшем значение для этого поля не модифицируется</p>
     **/
    protected static boolean get_isFilled(SQLiteDatabase database) {
        boolean isFilled;
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
        if (get_isFilled(database)) {
            Cursor cursor = database.query(
                    nameTable,
                    new String[]{Core005_DB_value.ID, Core005_DB_value.NAME, Core005_DB_value.VALUE_CURRENTLY},
                    Core005_DB_value.NAME + " = ?",
                    new String[]{nameParameter},
                    null,
                    null,
                    null
            );
            cursor.moveToFirst();

            int value_currently_ColIndex = cursor.getColumnIndex(Core005_DB_value.VALUE_CURRENTLY);
            result = cursor.getInt(value_currently_ColIndex);

            cursor.close();
        } else {
            result = -100;
        }
        return result;
    }

    /**
     * !!!Вернет false в том числе если таблицы базы не инициализированны.
     */
    protected static boolean updateParameter(int value, String nameParameter, String nameTable, SQLiteDatabase database) {
        if (get_isFilled(database)) {
            int id;
            ContentValues values = new ContentValues();
            values.put(Core005_DB_value.VALUE_CURRENTLY, value);
            id = database.update(
                    nameTable,
                    values,
                    Core005_DB_value.NAME + " = ?",
                    new String[]{nameParameter}
            );
            return (id >= 0);
        } else {
            return false;
        }
    }

    /**
     * <p>INSERT RECORD VALUE:</p>
     *     <p>1.read all rows from table and copy to array:</p>
     *     <ul>
     *         <p>String[_id][years][date] arr_recordTable;
     *     </ul>
     *     <p>2.checking and find new place in recordValue</p>
     *     <p>3.trueChecked->insert new value with sort array</p>
     *     <p>3.falseChecked->finish</p>
     *     <p>4.clear table</p>
     *     <p>5.copy all value pairs of array to rows of the table</p>
     *
     * @return true / false
     */
    protected static boolean insertValue_recordTable(int value, Date date, SQLiteDatabase database) {
        String[][] arr_recordTable = readFromRecordTable(database); //1
//        printTableRecord(arr_recordTable);
        int placeForNewValue = -1;
//        if (arr_recordTable != null) {
        placeForNewValue = findNewPlace(value, arr_recordTable); //2
//        }else {
//            placeForNewValue=0;
//        }
        if (placeForNewValue >= 0) { //true
            arr_recordTable = insert_value_to_recordTable(value, date, arr_recordTable, placeForNewValue);
            if (arr_recordTable == null) { //3
                //...error processing
                return false;
            }
        } else { //false
            return false; //3
        }
        clearTable(database, Core005_DB_value.NAME_TABLE_RECORD); //4
        copy_arrToTable(arr_recordTable, database, Core005_DB_value.NAME_TABLE_RECORD); //5
//        printTableRecord(arr_recordTable);
        return true;
    }

    /**
     * <p>Вернёт null если в таблице нет записей</p>
     * @param database SQLiteDatabase
     * @return String[][] or null
     */
    protected static String[][] readFromRecordTable(SQLiteDatabase database) {
        Cursor cursor;
        String query;
//        long countRows__=DatabaseUtils.queryNumEntries(database, Core005_DB_value.NAME_TABLE_RECORD);
        if (DatabaseUtils.queryNumEntries(database, Core005_DB_value.NAME_TABLE_RECORD) <= MAX_COUNT_RECORD_TABLE) {
            query =
                    "SELECT * " +
                            "FROM " + Core005_DB_value.NAME_TABLE_RECORD + " " +
                            "ORDER BY " + Core005_DB_value.VALUE_CURRENTLY + " DESC";
        } else {
            query =
                    "SELECT TOP " + MAX_COUNT_RECORD_TABLE + " * " +
                            "FROM " + Core005_DB_value.NAME_TABLE_RECORD + " " +
                            "ORDER BY " + Core005_DB_value.VALUE_CURRENTLY + " DESC";

        }
        cursor = database.rawQuery(query, null);
        int countRows = cursor.getCount();
        String[][] resultArray = getNewRecordArray_String(MAX_COUNT_RECORD_TABLE, 3);
        if (countRows > 0) {
            if (cursor.moveToFirst()) {
                for (
                        int i = 0;
                        i < countRows && i < MAX_COUNT_RECORD_TABLE && !cursor.isAfterLast();
                        i++, cursor.moveToNext()
                ) {
                    int _id_ColIndex = cursor.getColumnIndex(Core005_DB_value.ID);
                    int date_ColIndex = cursor.getColumnIndex(Core005_DB_value.NAME);
                    int years_ColIndex = cursor.getColumnIndex(Core005_DB_value.VALUE_CURRENTLY);

                    resultArray[i][0] = cursor.getString(_id_ColIndex); //_id
                    resultArray[i][1] = cursor.getString(years_ColIndex); //years
                    resultArray[i][2] = cursor.getString(date_ColIndex); //date
                }
            }
        } else {
            cursor.close();
            return null;
        }
        cursor.close();
        return resultArray;
    }

    private static int findNewPlace(int value, String[][] arr_recordTable) {
        int result = -1;
        if (arr_recordTable == null) {
            result = 0;
        } else {
            for (
                    int i = 0;
                    i < arr_recordTable.length;
                    i++
            ) {
                if (value >= Integer.parseInt(arr_recordTable[i][1])) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

//    private static boolean checkRecordValue(int value, String[][] arr_recordTable) {
//        int min;
//        int[] outputArr = new int[arr_recordTable.length];
//        for (int i = 0; i < arr_recordTable.length; i++) {
//            outputArr[i] = Integer.parseInt(arr_recordTable[i][1]);
//        }
//        min = findMin(outputArr);
//        if (value > min)
//            return true;
//        else return false;
//    }
//
//    private static int findMin(int[] inputArr) {
//        int min = inputArr[0];
//        for (int i = 0; i < inputArr.length; i++)
//            min = Math.min(min, inputArr[i]);
//        return min;
//    }

    private static String[][] insert_value_to_recordTable(int value, Date date, String[][] arr_recordTable, int newPlace) {
        try {
            if (arr_recordTable == null) {
                arr_recordTable = getNewRecordArray_String(MAX_COUNT_RECORD_TABLE, 3);
            }
            if (arr_recordTable.length - 1 - newPlace >= 0) {
                System.arraycopy(
                        arr_recordTable,
                        newPlace,
                        arr_recordTable,
                        newPlace + 1,
                        arr_recordTable.length - 1 - newPlace
                );
            }
            arr_recordTable[newPlace] = new String[]{
                    String.valueOf(newPlace),
                    String.valueOf(value),
                    String.valueOf(date.getTime())
            };
            return arr_recordTable;
        } catch (Exception exception) {
            return null;
        }
    }

//    /**
//     * <ul>
//     * <p><b>String[][]  arr_recordTable</b> - входящий массив строк, состоит из одномерных массивов строк, где в каждом элементе:
//     *<p>[0]->_id (int->String)
//     * <p>[1]->years (int->String)</p>
//     * <p>[2]->date (long->String)</p>
//     * </ul>
//     * <ul>
//     *     <p><b>List<_long[]>  arr_recordTable_intermediate</b> - промежуточная коллекция одномерных массивов чисел
//     *     (в дальнейшем отсортированная по убыванию), где в каждом элементе:</p>
//     *     <p>[0]->_id (String->long)</p>
//     *     <p>[1]->years (String->long)</p>
//     *     <p>[2]->date (String->long)</p>
//     * </ul>
//     * <ul>
//     *     <p><b>List<_long[]>  outputListArray_intermediate</b> - отсортированная по убыванию, промежуточная коллекция одномерных массивов чисел,
//     *     с размещенным текущим достижением, где в каждом элементе:</p>
//     *     <p>[0]->_id</p>
//     *     <p>[1]->years</p>
//     *     <p>[2]->date</p>
//     * </ul>
//     * <ul>
//     *     <p><b>String[][]  outputArray</b> - готовый массив строк, где в каждом элементе:</p>
//     *     <p>[0]->years (long->String)</p>
//     *     <p>[1]->date (long->String)</p>
//     * </ul>
//     * @return String[][] outputArray = new String [arr_recordTable.length] [2];
//     */
    //-------------------------!!!! не сделана СОРТИРОВКА И ВСТАВКА ТЕКУЩИХ ДОСТИЖЕНИЙ !!!!!----------------
//    private static String[][] insert_and_sort_value_to_recordTable(int value, Date date, String[][] arr_recordTable) {
//        List<long[]> arr_recordTable_intermediate = getIntermediateArray_recordTable(arr_recordTable);
//        arr_recordTable_intermediate = sortArray(arr_recordTable_intermediate);
//        List<long[]> outputListArray_intermediate = insertRecord(arr_recordTable_intermediate, value, date);
//        String[][] outputArray = new String[arr_recordTable.length][2];
//        for (int i = 0; i < arr_recordTable.length; i++) {
//            outputArray[i][0]=String.valueOf(outputListArray_intermediate.get(i)[1]);
//            outputArray[i][1]=String.valueOf(outputListArray_intermediate.get(i)[2]);
//        }
//        return outputArray;
//    }
//    private static List<long[]> getIntermediateArray_recordTable(String[][] arr_recordTable) {
//        List<long[]> arr_recordTable_intermediate = new ArrayList<>();
//        for (String[] item : arr_recordTable) {
//            arr_recordTable_intermediate.add(
//                    new long[]{
//                            Long.parseLong(item[0]), //_id
//                            Long.parseLong(item[1]), //years
//                            Long.parseLong(item[2]) //date
//                    }
//            );
//        }
//        return arr_recordTable_intermediate;
//    }
//    /**
//     * НЕ СДЕЛАНО !!!!!!
//     * @param arr_recordTable_intermediate
//     * @return
//     */
//    private static List<long[]> sortArray(List<long[]> arr_recordTable_intermediate) {
//        List<long[]> outputArr = new ArrayList<>();
//
//        return outputArr;
//    }
//    /**
//     * НЕ СДЕЛАНО !!!!!!!
//     * @param arr_recordTable_intermediate
//     * @param value
//     * @param date
//     * @return
//     */
//    private static List<long[]> insertRecord(List<long[]> arr_recordTable_intermediate, int value, Date date){
//        List<long[]> outputArray=new ArrayList<>();
//
//        return outputArray;
//    }

    protected static void clearTable(SQLiteDatabase database, String nameTable) {
        database.delete(nameTable, null, null);
    }

    private static void copy_arrToTable(String[][] inputArray, SQLiteDatabase database, String nameTable) {
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < inputArray.length; i++) {
            contentValues.put(Core005_DB_value.ID,i);
            contentValues.put(Core005_DB_value.NAME,inputArray[i][2]);
            contentValues.put(Core005_DB_value.VALUE_CURRENTLY,inputArray[i][1]);
            database.insert(nameTable,null,contentValues);
        }
    }

    /**
     * <p>Создание и инициализация двухуровневого строкового массива</p>
     * <p>все элементы инициализируются значением "0"</p>
     * <ul>
     *     <p>Назначение подэлементов:</p>
     *     <p>[0] -> id</p>
     *     <p>[1] -> years</p>
     *     <p>[2] -> date</p>
     * </ul>
     * @param countLevel1 кол-во элементов массива
     * @param countLevel2 кол-во подэлементов
     */
    private static String[][] getNewRecordArray_String(int countLevel1, int countLevel2) {
        String[][] array = new String[countLevel1][countLevel2];
        for (int i=0;i<countLevel1;i++){
            for (int j=0;j<countLevel2;j++){
                array[i][j]="0";
            }
        }
        return array;
    }

//    private static void printTableRecord(String[][] inputArray) {
//        StringBuilder msg =
//                new StringBuilder("\nTABLE RECORD:\n" +
//                        "id // years // date\n");
//        for (int i = 0; i < inputArray.length; i++) {
//
//            String longV = inputArray[i][2];
//            long millisecond = Long.parseLong(longV);
//            String dateString = DateFormat.format("MM/dd/yyyy  hh/mm/ss", new Date(millisecond)).toString();
//
//            String add = inputArray[i][0] + " // " + inputArray[i][1] + " // " + dateString + "\n";
//            msg.append(add);
//        }
//        Log.i(TAG, msg.toString());
//    }
}