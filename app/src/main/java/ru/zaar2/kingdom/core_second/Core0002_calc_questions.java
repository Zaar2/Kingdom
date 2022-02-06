package ru.zaar2.kingdom.core_second;

import android.content.Context;

import ru.zaar2.kingdom.R;

import static ru.zaar2.kingdom.core_second.bcc.bcc001_value.COST_PER_WARRIOR;
import static ru.zaar2.kingdom.core_second.bcc.bcc001_value.GRAIN_FOR_SOWING;

public class Core0002_calc_questions extends Core000_calc {

    int grain, price, land, population;

    public Core0002_calc_questions() {
    }

    public void calcDB_forQuestions(Context context, int value) {
        initBundle(
                context.getResources().getString(R.string.strDB_accessory),
                context.getResources().getString(R.string.strDB_resources),
                context.getResources().getString(R.string.strDB_indicators),
                context
        );

        grain = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_grainReserve_indicator));
        population = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_unemployed_person_indicator));
        price = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_landValue_indicator));
        land = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_landInOwnership_indicator));

        calc_forQuestions(context, value);
        updateDB(context);
    }

    private void calc_forQuestions(Context context, int value) {
        int question = accessory_data_bundle.getInt(context.getResources().getString(R.string.strDB_currently_question_accessory));
        switch (question) {
            case 0: //купля
                calc_question_0_purchase(context, value);
                break;
            case 1: //продажа
                calc_question_1_sale(context, value);
                break;
            case 2: //рекрутинг
                calc_question_2_recruiting(context, value);
                break;
            case 3: //потребление жителей
                calc_question_3_consumption(context, value);
                break;
            case 4: //посевная
                calc_question_4_sowing(context, value);
                break;
            case 5: //набег
                calc_question_5_raid(context, value);
                break;
        }
    }

    private void calc_question_0_purchase(Context context, int value) {
        boolean test;
        int calculation_result;
        calculation_result = value * price;

        //test: grain
        if (calculation_result <= grain) test = true;
        else test = false;

        //calc, upgDB
        if (test) {
            grain -= calculation_result;
            land += value;

            indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_grainReserve_indicator), grain);
            indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_landInOwnership_indicator), land);
        }
    }

    private void calc_question_1_sale(Context context, int value) {
        boolean test;
        int calculation_result;
        calculation_result = value * price;

        //test: land
        if (value >= 0 && value <= indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_landInOwnership_indicator)))
            test = true;
        else test = false;

        //calc, upgDB
        if (test) {
            grain += calculation_result;
            land -= value;

            indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_grainReserve_indicator), grain);
            indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_landInOwnership_indicator), land);
        }
    }

    private void calc_question_2_recruiting(Context context, int value) {
        boolean test;
        int army = 0;

        if (value <= (grain / COST_PER_WARRIOR)) test = true;
        else test = false;
        if (value <= population && value > 0) test = true;
        else test = false;

        if (test) {
            army += value;
            population -= army;
            grain -= army * COST_PER_WARRIOR;

            indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_army_indicator), army);
            indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_unemployed_person_indicator), population);
        }
    }

    private void calc_question_3_consumption(Context context, int value) {
        boolean test;
        int calculation_result;

        calculation_result = population * value;

        if (calculation_result <= grain && calculation_result > 0) test = true;
        else test = false;

        //calc, upgDB: grain--
        if (test) {
            accessory_data_bundle.putInt(context.getResources().getString(R.string.strDB_grain_per_citizen_accessory), value);
            indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_grainReserve_indicator), grain - calculation_result);
        }
    }

    private void calc_question_4_sowing(Context context, int value) {
        int personProductivity;

        personProductivity = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_personCanHandle_indicator));

        int appointmentPerson = value / personProductivity;
        int decreaseGrain = (int) (value * GRAIN_FOR_SOWING);

        indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_grainReserve_indicator), grain - decreaseGrain);
        indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_unemployed_person_indicator), population - appointmentPerson);
        accessory_data_bundle.putInt(context.getResources().getString(R.string.strDB_sown_land_accessory), value);
    }

    private void calc_question_5_raid(Context context, int value) {
        boolean startRaid = false;
        int
                yearOfReturn = accessory_data_bundle.getInt(context.getResources().getString(R.string.strDB_raid_year_of_return_accessory)),
                warriors = indicators_data_bundle.getInt(context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator)),
                year = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_years_resources));
        //test: population, grain, value
        if (population > 0 && grain > 0 && value > 0) {
            if (value <= population && (value * COST_PER_WARRIOR) < grain) {
                if (yearOfReturn <= 0) {
                    yearOfReturn = year + (int) randomized.random(1, 5);
                    startRaid = true;
                }
                //calc, upgDB
                warriors += value;
                population -= value;
                grain -= value * COST_PER_WARRIOR;
                int population0 = resources_data_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources));
                population0 -= value;
                indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator), warriors);
                indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_unemployed_person_indicator), population);
                indicators_data_bundle.putInt(context.getResources().getString(R.string.strDB_grainReserve_indicator), grain);
                resources_data_bundle.putInt(context.getResources().getString(R.string.strDB_population_resources), population0);
                if (startRaid)
                    accessory_data_bundle.putInt(context.getResources().getString(R.string.strDB_raid_year_of_return_accessory), yearOfReturn);
            }
        }
    }
}