package ru.zaar2.kingdom.core_second.Core005_DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.Date;
import java.util.Objects;

import ru.zaar2.kingdom.R;

public class Core005_DB extends SQLiteOpenHelper {

    public Core005_DB(@Nullable Context context, int version) {
        super(
                Objects.requireNonNull(context, "context - must not be null"),
                Objects.requireNonNull(context, "context.getResources() - must not be null").getResources().getString(R.string.name_database),
                null,
                version
        );

        Core005_DB_value.name_isFilled = context.getResources().getString(R.string.strDB_isFilled_utility);
        Core005_DB_value.fillStringForQuery(context);
        Core005_DB_value.fillNameTables(context);
    }

    public void restartDB() {
        SQLiteDatabase database = this.getWritableDatabase();
        dropTables(database);
        onCreate(database);
        Core005_DB_utility.insertValues(database);
        database.close();
    }

    private void dropTables(SQLiteDatabase database) {
        dropTable(database, Core005_DB_value.NAME_TABLE_CHANGEABLE_PARAMETERS);
        dropTable(database, Core005_DB_value.NAME_TABLE_INDICATORS);
        dropTable(database, Core005_DB_value.NAME_TABLE_RESOURCES);
        dropTable(database, Core005_DB_value.NAME_TABLE_ACCESSORY);
        dropTable(database, Core005_DB_value.NAME_TABLE_UTILITY);
    }

    private void dropTable(SQLiteDatabase database, String nameTable) {
        database.execSQL(
                "drop table if exists " + nameTable
        );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase, Core005_DB_value.NAME_TABLE_CHANGEABLE_PARAMETERS);
        createTable(sqLiteDatabase, Core005_DB_value.NAME_TABLE_INDICATORS);
        createTable(sqLiteDatabase, Core005_DB_value.NAME_TABLE_RESOURCES);
        createTable(sqLiteDatabase, Core005_DB_value.NAME_TABLE_ACCESSORY);
        createTable(sqLiteDatabase, Core005_DB_value.NAME_TABLE_UTILITY);
        createTable(sqLiteDatabase, Core005_DB_value.NAME_TABLE_RECORD);
    }

    private void createTable(SQLiteDatabase sqLiteDatabase, String nameTable) {
        sqLiteDatabase.execSQL(
                "create table if not exists " + nameTable + " ("
                        + Core005_DB_value.ID + " INTEGER primary key, "
                        + Core005_DB_value.NAME + " text, "
                        + Core005_DB_value.VALUE_DEFAULT + " INTEGER, "
                        + Core005_DB_value.VALUE_CURRENTLY + " INTEGER"
                        + ");"
        );
    }

    /**
     * <p>!!! Вернет контейнер без значений, в случае если таблицы базы не были инициализированы.</p>
     * <p>!!!В этом случае Bundle будет содержать одно значение boolean:name->"isFilled_utility",value->false </p>
     * <p>context.getResources().getString(R.string.str_isFilled_utility</p>
     *
     * @return Bundle dataContainer
     */
    public Bundle dataForDisplay(String table) {
        SQLiteDatabase database = this.getWritableDatabase();
        Bundle result = Core005_DB_utility.dataForDisplay(table, database);
        database.close();
        return result;
    }

    /**
     * <p>!!! вернет "-100", в случае если таблицы базы не были инициализированы.</p>
     */
    public int findCurrentlyValue_ofSpecifiedParameter(String nameParameter, String nameTable) {
        SQLiteDatabase database = this.getWritableDatabase();
        int result = Core005_DB_utility.findCurrentlyValue_ofSpecifiedParameter(nameParameter, nameTable, database);
        database.close();
        return result;
    }

    /**
     * !!!Вернет false в том числе если таблицы базы не инициализированны.
     */
    public boolean updateDB(Bundle resource_date, Bundle indicators_date, Bundle bundle_accessory, Context context) {
        SQLiteDatabase database = this.getWritableDatabase();
        if (Core005_DB_utility.get_isFilled(database)) {
            dropTables(database);
            onCreate(database);
            Core005_DB_value.fillStringForQuery(resource_date, indicators_date, bundle_accessory, context);
            Core005_DB_utility.insertValues(database);
            database.close();
            return true;
        } else {
            database.close();
            return false;
        }
    }

    /**
     * !!!Вернет false в том числе если таблицы базы не инициализированны.
     */
    public boolean updateParameter(int value, String nameParameter, String nameTable) {
        SQLiteDatabase database = this.getWritableDatabase();
        boolean result = Core005_DB_utility.updateParameter(value, nameParameter, nameTable, database);
        database.close();
        return result;
    }

    public boolean insertValue_recordTable(int value, Date date) {
        SQLiteDatabase database = this.getWritableDatabase();
        boolean result = Core005_DB_utility.insertValue_recordTable(value, date, this.getWritableDatabase());
        database.close();
        return result;
    }

    public void clearTable(String nameTable) {
        SQLiteDatabase database = this.getWritableDatabase();
        Core005_DB_utility.clearTable(database,nameTable);
        database.close();
    }
    /**
     * <p>Вернёт null если в таблице нет записей</p>
     * @return String[][] or null
     */
    public String[][] readFromRecordTable() {
        SQLiteDatabase database = this.getWritableDatabase();
        String[][] inputTable= Core005_DB_utility.readFromRecordTable(database);
        database.close();
        return inputTable;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }


}