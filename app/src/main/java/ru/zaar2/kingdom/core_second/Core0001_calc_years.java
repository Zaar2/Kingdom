package ru.zaar2.kingdom.core_second;

import android.content.Context;

import java.util.ArrayList;

public class Core0001_calc_years {

    public Core0001_calc_years() {
    }

    public int[] calcDB_years(Context context) {
        Core0001_calc_years_calcResInd calcResInd = new Core0001_calc_years_calcResInd(context);

        calcResInd.start(context);

        calcResInd.calc_indicators_born_dead(context);
        calcResInd.calc_indicator_migratory(context);

        int[] result = calc_fromYear_repeatSteps(calcResInd, context);

        calcResInd.calc_indicator_landValue(context);
//        calcResInd.calc_indicator_personCanHandle(context);
        calcResInd.postCalc_resources_grain_budget(context);

        calcResInd.end(context);

        calcResInd.updateDB(context);

        return result;
    }

    private int[] calc_fromYear_repeatSteps(Core0001_calc_years_calcResInd calcResInd, Context context) {
        ArrayList<Integer> eventsList = new ArrayList<>();
        //starvation
        calcResInd.calc_defeat_dueTo_starvation(context, eventsList);
        //crop yields improved, landDepletion
        calcResInd.calc_event_9_10_cropYieldsImproved_landDepletion(context, eventsList);
        //resources grain (budget)
        calcResInd.preCalc_resources_grain(context);
        //fire
        calcResInd.calc_event_0_fire_diversion(context, eventsList);
        //rats
        calcResInd.calc_event_1_rats(context, eventsList);
        //migratory
        calcResInd.calc_event_2_migratory(context, eventsList);
        //rebellion
        calcResInd.calc_event_3_rebellion(context, eventsList);
        //epidemic, demographic
        calcResInd.calc_event_4_5_epidemic_demographic(context, eventsList);
        //diversion
        calcResInd.calc_event_6_diversion(context, eventsList);
        //saboteur
        calcResInd.calc_event_20_saboteur_makeHisWay(context,eventsList);
        //personCanHandle - increased, decline
        calcResInd.calc_event_7_8_personCanHandle_increased_decline(context, eventsList);
        //plunder
        calcResInd.calc_event_11_plunder(context, eventsList);
        //aggressor
        calcResInd.calc_event_12_aggressor(context, eventsList);
        //raid
        calcResInd.calc_raid(context, eventsList);
        //resources population
        calcResInd.calc_resources_population(context, eventsList);

        int[] result = new int[eventsList.size()];
        for (int i = 0; i < eventsList.size(); i++) {
            result[i] = (eventsList.get(i));
        }
        return result;
    }
}