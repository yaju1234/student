package com.mdgroup.parents;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public final class DateDisplayUtils {

    private static final String MONTH_YEAR_DISPLAY_PATTERN = "MMMM  yyyy";

    private DateDisplayUtils() {
    }
    public static String formatMonthYear(int year, int monthOfYear) {
        Locale locale = Locale.getDefault();
        Calendar calendar = Calendar.getInstance(locale);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        SimpleDateFormat format = new SimpleDateFormat(
                MONTH_YEAR_DISPLAY_PATTERN, Locale.getDefault());
        return format.format(calendar.getTime());
    }
}
