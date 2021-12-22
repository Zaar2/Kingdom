package ru.zaar2.kingdom;

import java.text.DecimalFormatSymbols;

public class DecimalFormat extends java.text.DecimalFormat {

    public DecimalFormat() {
        super();

        DecimalFormatSymbols separatorGroups = new DecimalFormatSymbols();
        separatorGroups.setGroupingSeparator(' ');

        setDecimalFormatSymbols(separatorGroups);
        setGroupingSize(3);
    }
}