package ru.zaar2.kingdom;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.zaar2.kingdom.core_second.EntryToCore;
import ru.zaar2.kingdom.core_second.bcc.bcc001_value;


public class PanelOfIndicators extends Panel {

    private final LinearLayout linearLayout_panel;
    private final DecimalFormat decimalFormat;

    public PanelOfIndicators(LinearLayout linearLayout) {
        super();
        linearLayout_panel = linearLayout;
        decimalFormat = new DecimalFormat();
    }

    public void initViews_panel(Context context){
        initViews_panelGroup(
                linearLayout_panel.findViewById(R.id.group_of_panel_people),
                context.getResources().getStringArray(R.array.panelGroup_people)
                );
        initViews_panelGroup(
                linearLayout_panel.findViewById(R.id.group_of_panel_grain),
                context.getResources().getStringArray(R.array.panelGroup_grain)
        );
        initViews_panelGroup(
                linearLayout_panel.findViewById(R.id.group_of_panel_land),
                context.getResources().getStringArray(R.array.panelGroup_land)
        );
        initViews_panelGroup(
                linearLayout_panel.findViewById(R.id.group_of_panel_warriors),
                context.getResources().getStringArray(R.array.panelGroup_warriors)
        );
        initViews_panelGroup(
                linearLayout_panel.findViewById(R.id.group_of_panel_aggressor),
                context.getResources().getStringArray(R.array.panelGroup_aggressor)
        );
    }

    private void initViews_panelGroup(LinearLayout layoutPanelGroup, String[] stringArray_panelGroup) {
        int maxItems = stringArray_panelGroup.length;
        for (int i = 0; i < maxItems; i++) {
            layoutPanelGroup.getChildAt(i).setVisibility(View.VISIBLE);
            ((TextView)((LinearLayout)layoutPanelGroup.getChildAt(i)).getChildAt(0)).setText(stringArray_panelGroup[i]);
        }
        layoutPanelGroup.findViewById(R.id.panelGroup_divider).setVisibility(View.VISIBLE);
    }

    public boolean updateView_Indicators(Context context){
        String table = context.getResources().getString(R.string.strDB_indicators);
        Bundle incoming_bundle = dataForDisplay(table, context);

        if (!incoming_bundle.getBoolean(context.getResources().getString(R.string.str_isFilled_utility))){
            return false;
        }
        updateViews_panelGroup(
                linearLayout_panel.findViewById(R.id.group_of_panel_people),
                context.getResources().getStringArray(R.array.panelGroup_people).length,
                incoming_bundle,
                context
        );
        updateViews_panelGroup(
                linearLayout_panel.findViewById(R.id.group_of_panel_grain),
                context.getResources().getStringArray(R.array.panelGroup_grain).length,
                incoming_bundle,
                context
        );
        updateViews_panelGroup(
                linearLayout_panel.findViewById(R.id.group_of_panel_land),
                context.getResources().getStringArray(R.array.panelGroup_land).length,
                incoming_bundle,
                context
        );
        updateViews_panelGroup(
                linearLayout_panel.findViewById(R.id.group_of_panel_warriors),
                context.getResources().getStringArray(R.array.panelGroup_warriors).length,
                incoming_bundle,
                context
        );
        updateViews_panelGroup(
                linearLayout_panel.findViewById(R.id.group_of_panel_aggressor),
                context.getResources().getStringArray(R.array.panelGroup_aggressor).length,
                incoming_bundle,
                context
        );
        return true;
    }

    private void updateViews_panelGroup(LinearLayout layoutPanelGroup, int countItemOf_panelGroup, Bundle incoming_bundle_fromBD, Context context){
        for (int i=0;i<countItemOf_panelGroup;i++) {
            String nameItem_groupOfPanel = (String) (((TextView) ((LinearLayout) layoutPanelGroup.getChildAt(i)).getChildAt(0)).getText());
            String nameItem_inBD = getString_forDB(nameItem_groupOfPanel, context);
            int value = incoming_bundle_fromBD.getInt(nameItem_inBD);
            ((TextView) ((LinearLayout) layoutPanelGroup.getChildAt(i)).getChildAt(1)).setText(
                    decimalFormat.format(value)
            );
        }
    }

    public void setVisibility(boolean vol) {
        if (vol) {
            linearLayout_panel.setVisibility(View.VISIBLE);
        } else {
            linearLayout_panel.setVisibility(View.GONE);
        }
    }

    public int getMaxValue_seekBarProgress_byFragmentTools(int idQuestion, Context context) {
        int idParameter = 0, idTable = 0;
        int result = -3;
        EntryToCore entryToCore = new EntryToCore();
        int population;
        int
                grainReserve = entryToCore.findCurrentlyValue_ofSpecifiedParameter(
                context.getResources().getString(R.string.strDB_grainReserve_indicator),
                context.getResources().getString(R.string.strDB_indicators),
                context,
                1
        );
        switch (idQuestion) {
            case 0: //покупка земли
                int landValue = entryToCore.findCurrentlyValue_ofSpecifiedParameter(
                        context.getResources().getString(R.string.strDB_landValue_indicator),
                        context.getResources().getString(R.string.strDB_indicators),
                        context,
                        1
                );
                result = grainReserve / landValue;
                break;
            case 1: //продажа земли
                idParameter = R.string.strDB_landInOwnership_indicator;
                idTable = R.string.strDB_indicators;
                result = -1;
                break;
            case 2: //городовой полк
                idParameter = R.string.strDB_unemployed_person_indicator;
                idTable = R.string.strDB_indicators;
                result = -1;
                break;
            case 3: //мера прокорма на человека
                //grain/(population-army)
                population = entryToCore.findCurrentlyValue_ofSpecifiedParameter(
                        context.getResources().getString(R.string.strDB_unemployed_person_indicator),
                        context.getResources().getString(R.string.strDB_indicators),
                        context,
                        1
                );
//                int army = entryToCore.findCurrentlyValue_ofSpecifiedParameter(
//                        context.getResources().getString(R.string.strDB_army_indicator),
//                        context.getResources().getString(R.string.strDB_indicators),
//                        context,
//                        1
//                );
                result = grainReserve / (population);
                break;
            case 4: //посевная

                int
                        person_productivity = entryToCore.findCurrentlyValue_ofSpecifiedParameter(
                        context.getResources().getString(R.string.strDB_personCanHandle_indicator),
                        context.getResources().getString(R.string.strDB_indicators),
                        context,
                        1
                ),
                        max_by_land = entryToCore.findCurrentlyValue_ofSpecifiedParameter(
                                context.getResources().getString(R.string.strDB_landInOwnership_indicator),
                                context.getResources().getString(R.string.strDB_indicators),
                                context,
                                1
                        );
                population = entryToCore.findCurrentlyValue_ofSpecifiedParameter(
                        context.getResources().getString(R.string.strDB_unemployed_person_indicator),
                        context.getResources().getString(R.string.strDB_indicators),
                        context,
                        1
                );
                int max_by_productivity = population * person_productivity;
                int max_by_grain = (int) (grainReserve / bcc001_value.GRAIN_FOR_SOWING);

                if (max_by_land < max_by_grain && max_by_land < max_by_productivity) {
                    result = max_by_land;
                } else {
                    if (max_by_grain < max_by_land && max_by_grain < max_by_productivity)
                        result = max_by_grain;
                    else {
                        if (max_by_productivity < max_by_grain && max_by_productivity < max_by_land)
                            result = max_by_productivity;
                    }
                }
                break;
            case 5: //набег
                result = grainReserve / bcc001_value.COST_PER_WARRIOR;

                population = entryToCore.findCurrentlyValue_ofSpecifiedParameter(
                        context.getResources().getString(R.string.strDB_unemployed_person_indicator),
                        context.getResources().getString(R.string.strDB_indicators),
                        context,
                        1
                );
                if (result > population) result = population;
                break;
            default:
                result = -2;
                break;
        }
        if (result == -1) {
            return
                    new EntryToCore().findCurrentlyValue_ofSpecifiedParameter(
                            context.getResources().getString(idParameter),
                            context.getResources().getString(idTable),
                            context,
                            1
                    );
        } else return result;
    }

    public String getTitle_for_fragment(int idQuestion, Context context){
        int idString;
        switch (idQuestion) {
            case 0:
                idString = R.string.name_question1;
                break;
            case 1:
                idString = R.string.name_question2;
                break;
            case 2:
                idString = R.string.name_question3;
                break;
            case 3:
                idString = R.string.name_question4;
                break;
            case 4:
                idString = R.string.name_question5;
                break;
            case 5:
                idString=R.string.name_question6;
                break;
            default:
                return "-1";
        }
        return context.getResources().getString(idString);
    }

    public String getText_for_fragment(int idQuestion, Context context) {
        int idString;
        switch (idQuestion) {
            case 0:
                idString = R.string.text_question1;
                break;
            case 1:
                idString = R.string.text_question2;
                break;
            case 2:
                idString = R.string.text_question3;
                break;
            case 3:
                idString = R.string.text_question4;
                break;
            case 4:
                idString = R.string.text_question5;
                break;
            case 5:
                idString=R.string.text_question6;
                break;
            default:
                return "-1";
        }
        return context.getResources().getString(idString);
    }

    private String getString_forDB(String nameView, Context context){
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_increaseInPopulation_1))){
            return context.getResources().getString(R.string.strDB_increaseInPopulation_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_mortality_2))){
            return context.getResources().getString(R.string.strDB_mortality_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_migratoryIncreaseInPopulation_3))){
            return context.getResources().getString(R.string.strDB_migratoryIncreaseInPopulation_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_army_9))){
            return context.getResources().getString(R.string.strDB_army_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_unemployed_person_11))){
            return context.getResources().getString(R.string.strDB_unemployed_person_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_grainReserve_6))){
            return context.getResources().getString(R.string.strDB_grainReserve_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_loss_14))){
            return context.getResources().getString(R.string.strDB_grainLoss_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_productivity_5))){
            return context.getResources().getString(R.string.strDB_cropYields_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_landInOwnership_4))){
            return context.getResources().getString(R.string.strDB_landInOwnership_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_landValue_7))){
            return context.getResources().getString(R.string.strDB_landValue_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_personCanHandle_8))){
            return context.getResources().getString(R.string.strDB_personCanHandle_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_warriors_in_a_raid_10))){
            return context.getResources().getString(R.string.strDB_warriors_in_a_Raid_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_aggressor_12))) {
            return context.getResources().getString(R.string.strDB_aggressor_indicator);
        }
        if (nameView.equals(context.getResources().getString(R.string.name_indicators_distance_to_13))){
            return context.getResources().getString(R.string.strDB_distance_to_indicator);
        }
        return "-1";
    }
}