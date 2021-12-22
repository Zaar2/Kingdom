package ru.zaar2.kingdom.core_second;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.Objects;

import ru.zaar2.kingdom.R;
import ru.zaar2.kingdom.core_second.bcc.*;

public class Core005_DB extends SQLiteOpenHelper {

    private String[][] ROWS_and_VALUES_TABLE_changeableParameters;
    private String[][] ROWS_and_VALUES_TABLE_indicators;
    private String[][] ROWS_and_VALUES_TABLE_resources;
    private String[][] ROWS_and_VALUES_TABLE_accessory;

    private String NAME_TABLE_CHANGEABLE_PARAMETERS;// = "changeableParameters";
    private String NAME_TABLE_INDICATORS;// = "indicators";
    private String NAME_TABLE_RESOURCES;// = "resources";
    private String NAME_TABLE_ACCESSORY;//="accessory"

    public Core005_DB(@Nullable Context context, int version) {
        super(
                Objects.requireNonNull(context, "context - must not be null"),
                Objects.requireNonNull(context, "context.getResources() - must not be null").getResources().getString(R.string.name_database),
                null,
                version
        );

        fillStringForQuery(context);
        fillNameTables(context);
    }

    private void fillNameTables(Context context) {
        NAME_TABLE_CHANGEABLE_PARAMETERS = context.getResources().getString(R.string.strDB_changeableParameters);
        NAME_TABLE_INDICATORS = context.getResources().getString(R.string.strDB_indicators);
        NAME_TABLE_RESOURCES = context.getResources().getString(R.string.strDB_resources);
        NAME_TABLE_ACCESSORY = context.getResources().getString(R.string.strDB_accessory);
    }

    private void fillStringForQuery(Bundle res_bundle, Bundle indic_bundle, Bundle accessory_bundle, Context context) {
        ROWS_and_VALUES_TABLE_changeableParameters = null;
        ROWS_and_VALUES_TABLE_indicators = null;
        ROWS_and_VALUES_TABLE_resources = null;
        ROWS_and_VALUES_TABLE_accessory = null;

        ROWS_and_VALUES_TABLE_indicators = new String[][]{
                {context.getResources().getString(R.string.strDB_increaseInPopulation_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_increaseInPopulation_indicator)))},
                {context.getResources().getString(R.string.strDB_mortality_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_mortality_indicator)))},
                {context.getResources().getString(R.string.strDB_migratoryIncreaseInPopulation_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_migratoryIncreaseInPopulation_indicator)))},
                {context.getResources().getString(R.string.strDB_landInOwnership_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_landInOwnership_indicator)))},
                {context.getResources().getString(R.string.strDB_cropYields_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_cropYields_indicator)))},
                {context.getResources().getString(R.string.strDB_grainReserve_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_grainReserve_indicator)))},
                {context.getResources().getString(R.string.strDB_landValue_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_landValue_indicator)))},
                {context.getResources().getString(R.string.strDB_personCanHandle_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_personCanHandle_indicator)))},
                {context.getResources().getString(R.string.strDB_army_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_army_indicator)))},
                {context.getResources().getString(R.string.strDB_unemployed_person_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_unemployed_person_indicator)))},
                {context.getResources().getString(R.string.strDB_grainLoss_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_grainLoss_indicator)))},
                {context.getResources().getString(R.string.strDB_aggressor_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_aggressor_indicator)))},
                {context.getResources().getString(R.string.strDB_distance_to_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_distance_to_indicator)))},
                {context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator), String.valueOf(indic_bundle.getInt(context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator)))}
        };

        ROWS_and_VALUES_TABLE_resources = new String[][]{
                {context.getResources().getString(R.string.strDB_population_resources), String.valueOf(res_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources)))},
                {context.getResources().getString(R.string.strDB_budget_resources), String.valueOf(res_bundle.getInt(context.getResources().getString(R.string.strDB_budget_resources)))},
                {context.getResources().getString(R.string.strDB_years_resources), String.valueOf(res_bundle.getInt(context.getResources().getString(R.string.strDB_years_resources)))},
                {context.getResources().getString(R.string.strDB_acreage_resources), String.valueOf(res_bundle.getInt(context.getResources().getString(R.string.strDB_acreage_resources)))}
        };

        ROWS_and_VALUES_TABLE_accessory = new String[][]{
                {context.getResources().getString(R.string.strDB_currently_question_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_currently_question_accessory)))},
                {context.getResources().getString(R.string.strDB_sown_land_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_sown_land_accessory)))},
                {context.getResources().getString(R.string.strDB_accumulation_saboteur_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_accumulation_saboteur_accessory)))},
                {context.getResources().getString(R.string.strDB_accumulation_land_productivity_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_accumulation_land_productivity_accessory)))},
                {context.getResources().getString(R.string.strDB_grain_per_citizen_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_grain_per_citizen_accessory)))},
                {context.getResources().getString(R.string.strDB_dead_in_battles_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_dead_in_battles_accessory)))},
                {context.getResources().getString(R.string.strDB_born_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_born_accessory)))},
                {context.getResources().getString(R.string.strDB_dead_epidemic_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_dead_epidemic_accessory)))},
                {context.getResources().getString(R.string.strDB_dead_in_fire_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_dead_in_fire_accessory)))},
                {context.getResources().getString(R.string.strDB_dead_in_rebellion_accessory),String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_dead_in_rebellion_accessory)))},
                {context.getResources().getString(R.string.strDB_dead_in_starvation_accessory),String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_dead_in_starvation_accessory)))},
                {context.getResources().getString(R.string.strDB_plunder_grain_accessory),String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_plunder_grain_accessory)))},
                {context.getResources().getString(R.string.strDB_loss_grain_in_diversion_accessory),String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_loss_grain_in_diversion_accessory)))},
                {context.getResources().getString(R.string.strDB_grain_burnt_down_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_grain_burnt_down_accessory)))},
                {context.getResources().getString(R.string.strDB_raid_capture_grain_accessory),String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_raid_capture_grain_accessory)))},
                {context.getResources().getString(R.string.strDB_raid_capture_land_accessory),String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_raid_capture_land_accessory)))},
                {context.getResources().getString(R.string.strDB_raid_capture_people_accessory),String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_raid_capture_people_accessory)))},
                {context.getResources().getString(R.string.strDB_raid_dead_accessory),String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_raid_dead_accessory)))},
                {context.getResources().getString(R.string.strDB_raid_year_of_return_accessory),String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_raid_year_of_return_accessory)))}
        };
    }

    private void fillStringForQuery(Context context) {
        bcc004_initializingStartingValueDB StartingValue = new bcc004_initializingStartingValueDB();
        ROWS_and_VALUES_TABLE_resources = new String[][]{
                {context.getResources().getString(R.string.strDB_population_resources), String.valueOf(StartingValue.def_POPULATION_RESOURCES)},
                {context.getResources().getString(R.string.strDB_budget_resources), String.valueOf(StartingValue.def_BUDGET_RESOURCES)},
                {context.getResources().getString(R.string.strDB_years_resources), String.valueOf(StartingValue.def_YEARS_RESOURCES)},
                {context.getResources().getString(R.string.strDB_acreage_resources), String.valueOf(StartingValue.def_ACREAGE_RESOURCES)}
        };
        ROWS_and_VALUES_TABLE_indicators = new String[][]{
                {context.getResources().getString(R.string.strDB_increaseInPopulation_indicator), String.valueOf(StartingValue.def_INCREASE_IN_POPULATION_INDICATORS)},
                {context.getResources().getString(R.string.strDB_mortality_indicator), String.valueOf(StartingValue.def_MORTALITY)},
                {context.getResources().getString(R.string.strDB_migratoryIncreaseInPopulation_indicator), String.valueOf(StartingValue.def_MIGRATORY_INCREASE_IN_POPULATION)},
                {context.getResources().getString(R.string.strDB_landInOwnership_indicator), String.valueOf(StartingValue.def_LAND_IN_OWNERSHIP)},
                {context.getResources().getString(R.string.strDB_cropYields_indicator), String.valueOf(StartingValue.def_PRODUCTIVITY)},
                {context.getResources().getString(R.string.strDB_grainReserve_indicator), String.valueOf(StartingValue.def_GRAIN_RESERVE)},
                {context.getResources().getString(R.string.strDB_landValue_indicator), String.valueOf(StartingValue.def_LAND_VALUE)},
                {context.getResources().getString(R.string.strDB_personCanHandle_indicator), String.valueOf(StartingValue.def_PERSON_CAN_HANDLE)},
                {context.getResources().getString(R.string.strDB_army_indicator), String.valueOf(StartingValue.def_ARMY_RESOURCES)},
                {context.getResources().getString(R.string.strDB_unemployed_person_indicator), String.valueOf(StartingValue.def_UNEMPLOYED_PERSON)},
                {context.getResources().getString(R.string.strDB_grainLoss_indicator), String.valueOf(StartingValue.def_GRAIN_LOSS)},
                {context.getResources().getString(R.string.strDB_aggressor_indicator), String.valueOf(StartingValue.def_AGGRESSOR)},
                {context.getResources().getString(R.string.strDB_distance_to_indicator), String.valueOf(StartingValue.def_DISTANCE_TO)},
                {context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator), String.valueOf(StartingValue.def_STR_WARRIORS_IN_A_RAID)}
        };
        ROWS_and_VALUES_TABLE_accessory = new String[][]{
                {context.getResources().getString(R.string.strDB_currently_question_accessory), String.valueOf(StartingValue.def_CURRENTLY_QUESTION_ACCESSORY)},
                {context.getResources().getString(R.string.strDB_sown_land_accessory), String.valueOf(StartingValue.def_SOWN_LAND)},
                {context.getResources().getString(R.string.strDB_accumulation_saboteur_accessory), String.valueOf(StartingValue.def_ACCUMULATION_SABOTEUR_ACCESSORY)},
                {context.getResources().getString(R.string.strDB_accumulation_land_productivity_accessory), String.valueOf(StartingValue.def_ACCUMULATION_LAND_PRODUCTIVITY_ACCESSORY)},
                {context.getResources().getString(R.string.strDB_grain_per_citizen_accessory), String.valueOf(StartingValue.def_GRAIN_PER_CITIZEN_ACCESSORY)},
                {context.getResources().getString(R.string.strDB_dead_in_battles_accessory), String.valueOf(StartingValue.def_DEAD_IN_BATTLES_ACCESSORY)},
                {context.getResources().getString(R.string.strDB_born_accessory), String.valueOf(StartingValue.def_BORN_ACCESSORY)},
                {context.getResources().getString(R.string.strDB_dead_epidemic_accessory), String.valueOf(StartingValue.def_DEAD_EPIDEMIC_ACCESSORY)},
                {context.getResources().getString(R.string.strDB_dead_in_fire_accessory), String.valueOf(StartingValue.def_DEAD_IN_FIRE_ACCESSORY)},
                {context.getResources().getString(R.string.strDB_dead_in_rebellion_accessory),String.valueOf(StartingValue.def_DEAD_IN_REBELLION)},
                {context.getResources().getString(R.string.strDB_dead_in_starvation_accessory),String.valueOf(StartingValue.def_DEAD_IN_STARVATION)},
                {context.getResources().getString(R.string.strDB_plunder_grain_accessory),String.valueOf(StartingValue.def_PLUNDER_GRAIN)},
                {context.getResources().getString(R.string.strDB_loss_grain_in_diversion_accessory),String.valueOf(StartingValue.def_LOSS_GRAIN_IN_DIVERSION)},
                {context.getResources().getString(R.string.strDB_grain_burnt_down_accessory), String.valueOf(StartingValue.def_GRAIN_BURNT_DOWN_ACCESSORY)},
                {context.getResources().getString(R.string.strDB_raid_capture_grain_accessory),String.valueOf(StartingValue.def_RAID_CAPTURE_GRAIN)},
                {context.getResources().getString(R.string.strDB_raid_capture_land_accessory),String.valueOf(StartingValue.def_RAID_CAPTURE_LAND)},
                {context.getResources().getString(R.string.strDB_raid_capture_people_accessory),String.valueOf(StartingValue.def_RAID_CAPTURE_PEOPLE)},
                {context.getResources().getString(R.string.strDB_raid_dead_accessory),String.valueOf(StartingValue.def_RAID_DEAD)},
                {context.getResources().getString(R.string.strDB_raid_year_of_return_accessory),String.valueOf(StartingValue.def_RAID_YEAR_OF_RETURN)}
        };
    }

    private void createTable(SQLiteDatabase sqLiteDatabase, String nameTable) {
        sqLiteDatabase.execSQL(
                "create table " + nameTable + " ("
                        + "_id int primary key, "
                        + "Name text, "
                        + "Value_default int, "
                        + "Value_currently int"
                        + ");"
        );
    }

    private void dropTables() {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(
                "drop table if exists " + NAME_TABLE_CHANGEABLE_PARAMETERS
        );
        database.execSQL(
                "drop table if exists " + NAME_TABLE_INDICATORS
        );
        database.execSQL(
                "drop table if exists " + NAME_TABLE_RESOURCES
        );
        database.execSQL(
                "drop table if exists " + NAME_TABLE_ACCESSORY
        );
    }

    private void insertValues() {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        for (int i = 0; i < ROWS_and_VALUES_TABLE_indicators.length; i++) {
            values.put("_id", i);
            values.put("Name", (ROWS_and_VALUES_TABLE_indicators[i][0]));
            values.put("Value_default", Integer.parseInt(ROWS_and_VALUES_TABLE_indicators[i][1]));
            values.put("Value_currently", Integer.parseInt(ROWS_and_VALUES_TABLE_indicators[i][1]));
            database.insert(NAME_TABLE_INDICATORS, null, values);
        }
        for (int i = 0; i < ROWS_and_VALUES_TABLE_resources.length; i++) {
            values.put("_id", i);
            values.put("Name", (ROWS_and_VALUES_TABLE_resources[i][0]));
            values.put("Value_default", Integer.parseInt((ROWS_and_VALUES_TABLE_resources[i][1])));
            values.put("Value_currently", Integer.parseInt((ROWS_and_VALUES_TABLE_resources[i][1])));
            database.insert(NAME_TABLE_RESOURCES, null, values);
        }
        for (int i = 0; i < ROWS_and_VALUES_TABLE_accessory.length; i++) {
            values.put("_id", i);
            values.put("Name", (ROWS_and_VALUES_TABLE_accessory[i][0]));
            values.put("Value_default", Integer.parseInt(ROWS_and_VALUES_TABLE_accessory[i][1]));
            values.put("Value_currently", Integer.parseInt(ROWS_and_VALUES_TABLE_accessory[i][1]));
            database.insert(NAME_TABLE_ACCESSORY, null, values);
        }
    }

    public void restartDB() {
        dropTables();
        onCreate(this.getWritableDatabase());
        insertValues();
    }

    public Bundle dataForDisplay(String table) {
        Bundle dataContainer = new Bundle();

        SQLiteDatabase database = getWritableDatabase();

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
        return dataContainer;
    }

    public int findCurrentlyValue_ofSpecifiedParameter(String nameParameter, String nameTable) {
        int result;

        SQLiteDatabase database = this.getWritableDatabase();
//        String query = "SELECT Name, Value_currently FROM " + nameTable + " WHERE Name = '" + nameParameter + "';";
//        Cursor cursor = database.rawQuery(query, null);
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
        database.close();
        return result;
    }

    public boolean updateParameter(int value, String nameParameter, String nameTable) {
        int id = -1;
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Value_currently", value);
        id = database.update(nameTable, values, "Name = ?", new String[]{nameParameter});

        database.close();
        return (id >= 0);
    }

    public void updateDB(Bundle resource_date, Bundle indicators_date, Bundle bundle_accessory, Context context) {
        dropTables();
        onCreate(this.getWritableDatabase());
        fillStringForQuery(resource_date, indicators_date, bundle_accessory, context);
        insertValues();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase, NAME_TABLE_CHANGEABLE_PARAMETERS);
        createTable(sqLiteDatabase, NAME_TABLE_INDICATORS);
        createTable(sqLiteDatabase, NAME_TABLE_RESOURCES);
        createTable(sqLiteDatabase, NAME_TABLE_ACCESSORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}