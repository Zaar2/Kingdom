package ru.zaar2.kingdom.core_second.Core005_DB;

import android.content.Context;
import android.os.Bundle;

import ru.zaar2.kingdom.R;
import ru.zaar2.kingdom.core_second.bcc.bcc004_initializingStartingValueDB;

public class Core005_DB_value {

    protected static String[][] ROWS_and_VALUES_TABLE_changeableParameters;
    protected static String[][] ROWS_and_VALUES_TABLE_indicators;
    protected static String[][] ROWS_and_VALUES_TABLE_resources;
    protected static String[][] ROWS_and_VALUES_TABLE_accessory;
    protected static String[][] ROWS_and_VALUES_TABLE_utility;
    protected static String[][] ROWS_and_VALUES_TABLE_record;

    protected static String NAME_TABLE_CHANGEABLE_PARAMETERS;// = "changeableParameters";
    protected static String NAME_TABLE_INDICATORS;// = "indicators";
    protected static String NAME_TABLE_RESOURCES;// = "resources";
    protected static String NAME_TABLE_ACCESSORY;//="accessory"
    protected static String NAME_TABLE_UTILITY;
    protected static String NAME_TABLE_RECORD;

    protected static String NAME = "Name";
    protected static String ID = "_id";
    protected static String VALUE_DEFAULT = "Value_default";
    protected static String VALUE_CURRENTLY = "Value_currently";

    protected static String name_isFilled;

    protected static void fillNameTables(Context context) {
        NAME_TABLE_CHANGEABLE_PARAMETERS = context.getResources().getString(R.string.strDB_changeableParameters);
        NAME_TABLE_INDICATORS = context.getResources().getString(R.string.strDB_indicators);
        NAME_TABLE_RESOURCES = context.getResources().getString(R.string.strDB_resources);
        NAME_TABLE_ACCESSORY = context.getResources().getString(R.string.strDB_accessory);
        NAME_TABLE_UTILITY = context.getResources().getString(R.string.strDB_utility);
        NAME_TABLE_RECORD = context.getResources().getString(R.string.strDB_record);
    }

    protected static void fillStringForQuery(Bundle res_bundle, Bundle indic_bundle, Bundle accessory_bundle, Context context) {
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
                {context.getResources().getString(R.string.strDB_dead_in_rebellion_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_dead_in_rebellion_accessory)))},
                {context.getResources().getString(R.string.strDB_dead_in_starvation_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_dead_in_starvation_accessory)))},
                {context.getResources().getString(R.string.strDB_plunder_grain_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_plunder_grain_accessory)))},
                {context.getResources().getString(R.string.strDB_loss_grain_in_diversion_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_loss_grain_in_diversion_accessory)))},
                {context.getResources().getString(R.string.strDB_loss_grain_to_rats_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_loss_grain_to_rats_accessory)))},
                {context.getResources().getString(R.string.strDB_loss_grain_dueTo_natural_phenomena_accessory),String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_loss_grain_dueTo_natural_phenomena_accessory)))},
                {context.getResources().getString(R.string.strDB_grain_burnt_down_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_grain_burnt_down_accessory)))},
                {context.getResources().getString(R.string.strDB_raid_capture_grain_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_raid_capture_grain_accessory)))},
                {context.getResources().getString(R.string.strDB_raid_capture_land_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_raid_capture_land_accessory)))},
                {context.getResources().getString(R.string.strDB_raid_capture_people_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_raid_capture_people_accessory)))},
                {context.getResources().getString(R.string.strDB_raid_dead_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_raid_dead_accessory)))},
                {context.getResources().getString(R.string.strDB_raid_year_of_return_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_raid_year_of_return_accessory)))},
                {context.getResources().getString(R.string.strDB_cropYields_cumulativeEffectDepletion_accessory), String.valueOf(accessory_bundle.getInt(context.getResources().getString(R.string.strDB_cropYields_cumulativeEffectDepletion_accessory)))}
        };
    }

    protected static void fillStringForQuery(Context context) {
        bcc004_initializingStartingValueDB StartingValue = new bcc004_initializingStartingValueDB();
        ROWS_and_VALUES_TABLE_utility = new String[][]{
                {Core005_DB_value.name_isFilled, "true"}
        };
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
                {context.getResources().getString(R.string.strDB_cropYields_indicator), String.valueOf(StartingValue.def_CROP_YIELDS)},
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
                {context.getResources().getString(R.string.strDB_dead_in_rebellion_accessory), String.valueOf(StartingValue.def_DEAD_IN_REBELLION)},
                {context.getResources().getString(R.string.strDB_dead_in_starvation_accessory), String.valueOf(StartingValue.def_DEAD_IN_STARVATION)},
                {context.getResources().getString(R.string.strDB_plunder_grain_accessory), String.valueOf(StartingValue.def_PLUNDER_GRAIN)},
                {context.getResources().getString(R.string.strDB_loss_grain_in_diversion_accessory), String.valueOf(StartingValue.def_LOSS_GRAIN_IN_DIVERSION)},
                {context.getResources().getString(R.string.strDB_loss_grain_to_rats_accessory), String.valueOf(StartingValue.def_LOSS_GRAIN_TO_RATS)},
                {context.getResources().getString(R.string.strDB_loss_grain_dueTo_natural_phenomena_accessory),String.valueOf(StartingValue.def_LOSS_GRAIN_DUE_TO_NATURAL_PHENOMENA)},
                {context.getResources().getString(R.string.strDB_grain_burnt_down_accessory), String.valueOf(StartingValue.def_GRAIN_BURNT_DOWN_ACCESSORY)},
                {context.getResources().getString(R.string.strDB_raid_capture_grain_accessory), String.valueOf(StartingValue.def_RAID_CAPTURE_GRAIN)},
                {context.getResources().getString(R.string.strDB_raid_capture_land_accessory), String.valueOf(StartingValue.def_RAID_CAPTURE_LAND)},
                {context.getResources().getString(R.string.strDB_raid_capture_people_accessory), String.valueOf(StartingValue.def_RAID_CAPTURE_PEOPLE)},
                {context.getResources().getString(R.string.strDB_raid_dead_accessory), String.valueOf(StartingValue.def_RAID_DEAD)},
                {context.getResources().getString(R.string.strDB_raid_year_of_return_accessory), String.valueOf(StartingValue.def_RAID_YEAR_OF_RETURN)},
                {context.getResources().getString(R.string.strDB_cropYields_cumulativeEffectDepletion_accessory), String.valueOf(StartingValue.def_CROP_YIELDS_CUMULATIVE_EFFECT_ACCESSORY)}
        };
    }
}