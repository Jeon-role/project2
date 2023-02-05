package org.techtown.project;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

/**
 * Created by philipp on 02/06/16.
 */
public class CountAxisValueFormatter2 extends ValueFormatter
{

    private final String[] mMonths = new String[]{
            "월", "화", "수", "목", "금", "토", "일"
    };

    private final BarLineChartBase<?> chart;

    public CountAxisValueFormatter2(BarLineChartBase<?> chart) {
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value) {

        int days = (int) value;

        int year = determineYear(days);

        int month = determineMonth(days);
        String monthName = mMonths[month % mMonths.length];
        String yearName = String.valueOf(year);


            int dayOfMonth = determineDayOfMonth(days, month + 12 * (year - 2016));

            String appendix = "";

            switch (dayOfMonth) {
                case 1:
                    appendix = "월";
                    break;
                case 2:
                    appendix = "화";
                    break;
                case 3:
                    appendix = "수";
                    break;
                case 4:
                    appendix = "목";
                    break;
                case 5:
                    appendix = "금";
                break;
                case 6:
                    appendix = "토";
                    break;
                case 7:
                    appendix = "일";
                    break;
            }

            return dayOfMonth == 0 ? "" : appendix ;
    }

    private int getDaysForMonth(int month, int year) {

        // month is 0-based

        if (month == 1) {
            boolean is29Feb = false;

            if (year < 1582)
                is29Feb = (year < 1 ? year + 1 : year) % 4 == 0;
            else if (year > 1582)
                is29Feb = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);

            return is29Feb ? 29 : 28;
        }

        if (month == 3 || month == 5 || month == 8 || month == 10)
            return 30;
        else
            return 31;
    }

    private int determineMonth(int dayOfYear) {

        int month = -1;
        int days = 0;

        while (days < dayOfYear) {
            month = month + 1;

            if (month >= 12)
                month = 0;

            int year = determineYear(days);
            days += getDaysForMonth(month, year);
        }

        return Math.max(month, 0);
    }

    private int determineDayOfMonth(int days, int month) {

        int count = 0;
        int daysForMonths = 0;

        while (count < month) {

            int year = determineYear(daysForMonths);
            daysForMonths += getDaysForMonth(count % 12, year);
            count++;
        }

        return days - daysForMonths;
    }

    private int determineYear(int days) {

        if (days <= 366)
            return 2016;
        else if (days <= 730)
            return 2017;
        else if (days <= 1094)
            return 2018;
        else if (days <= 1458)
            return 2019;
        else
            return 2020;

    }
}
