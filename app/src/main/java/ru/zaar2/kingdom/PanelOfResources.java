package ru.zaar2.kingdom;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;


public class PanelOfResources extends Panel {

    private final LinearLayout linearLayout_resources;
    private final TextView textView_yearsOfGame;
    private final DecimalFormat decimalFormat;


    public PanelOfResources(LinearLayout linearLayout_res, TextView textView_years) {
        super();
        linearLayout_resources = linearLayout_res;
        textView_yearsOfGame = textView_years;
        decimalFormat = new DecimalFormat();
    }

    public boolean updateView_Resources(Context context) {
        String table = context.getResources().getString(R.string.strDB_resources);
        Bundle incoming_bundle = dataForDisplay(table, context);

        if (!incoming_bundle.getBoolean(context.getResources().getString(R.string.strDB_isFilled_utility))){
            return false;
        }

        ((TextView) linearLayout_resources.findViewById(R.id.num_of_people_tv_resource)).setText(
                decimalFormat.format(incoming_bundle.getInt(context.getResources().getString(R.string.strDB_population_resources)))
        );
        ((TextView) linearLayout_resources.findViewById(R.id.num_of_money_tv_resource)).setText(
                decimalFormat.format(incoming_bundle.getInt(context.getResources().getString(R.string.strDB_budget_resources)))
        );
        ((TextView) linearLayout_resources.findViewById(R.id.num_of_acreage_tv_resource)).setText(
                decimalFormat.format(incoming_bundle.getInt(context.getResources().getString(R.string.strDB_acreage_resources)))
        );
        textView_yearsOfGame.setText(
                decimalFormat.format(incoming_bundle.getInt(context.getResources().getString(R.string.strDB_years_resources)))
        );
        return true;
    }

    public void incrementAndUpdateDB_Years(int incoming_years, Context context) {
//        (textView_yearsOfGame).setText(String.valueOf(++incoming_years));
        updateParameter(
                ++incoming_years,
                context.getResources().getString(R.string.strDB_years_resources),
                context.getResources().getString(R.string.strDB_resources),
                context
        );
    }
}