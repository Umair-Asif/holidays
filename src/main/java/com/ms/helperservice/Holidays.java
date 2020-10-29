package com.ms.helperservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class Holidays {

    public static void main(String args[]) {

        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        List<String> listOne = new ArrayList<>(Arrays.asList("2020-07-01", "2020-07-02", "2020-07-03", "2020-07-04", "2020-07-06"));


        System.out.println("Comparing TWO LISTS");
        System.out.println(isListValid(currentYear, listOne));
        System.out.println();
        System.out.println("VERIFYING IF GIVEN DATE IS VALID FROM LIST ");
        System.out.println(isDateValid(currentYear, "2020-12-21"));
        System.out.println();
        System.out.println("GET CURRENT YEAR HOLIDAYS");
        System.out.println(getList());
        System.out.println();
        System.out.println("GET HOLIDAYS BY GIVEN YEAR");
        System.out.println(getListByYear(2022));
        System.out.println();
        System.out.println("VERIFYING AND RETURNING IT BACK, IF GIVEN DATE IS PRESENT IN HOLIDAYS");
        System.out.println(getDate("2020-12-21"));
    }

    public static List<String> range(String starts, String ends) {
        LocalDate start = LocalDate.parse(starts);
        LocalDate end = LocalDate.parse(ends);
        ArrayList<String> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start.toString());
            start = start.plusDays(1);
        }
        return totalDates;
    }

    //Thanks Giving: The Saturday before the fourth Thursday of November
    public static Date getSaturday(int year) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 3);
        c.set(Calendar.MONTH, Calendar.NOVEMBER);
        c.set(Calendar.YEAR, year);
        c.get(Calendar.DATE);

        return c.getTime();
    }

    //Thanks Giving: The Monday after the fourth Thursday of November
    public static Date getMondayAfterThursday(int year) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 3);
        c.set(Calendar.MONTH, Calendar.NOVEMBER);
        c.set(Calendar.YEAR, year);
        c.get(Calendar.DATE);

        c.add(Calendar.DATE, 9);
        return c.getTime();
    }

    //Christmas & New Year's: The Monday before Christmas
    public static Date getMonday(int year, int month) {
        Calendar c = Calendar.getInstance();
        Calendar cd = Calendar.getInstance();

        c.set(year, month, 23);
        cd.setTime(c.getTime());
        cd.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return cd.getTime();
    }

    //Christmas & New Year's: The first Monday in January
    //Labor Day: The first Monday in September
    //Labor day if condition (Add 1 day in Monday to get Tuesday)
    public static Date getMondayFirst(int year, int month, String event) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, year);
        c.get(Calendar.DATE);

        if (event == "labor") {
            c.add(Calendar.DATE, 1);
        }
        return c.getTime();
    }

    //Labor Day: The Thursday before the first Monday in the month of September
    public static Date getThursday(int year, int month) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, year);
        c.get(Calendar.DATE);

        DateFormat df = new SimpleDateFormat("EE");

        for (int i = 0; i < 10; i++) {
            c.add(Calendar.DATE, -1);
            if (df.format(c.getTime()).equals("Thu")) {
                return c.getTime();
            }
        }
        return c.getTime();
    }

    //Independence Day: July 1st - 5th
    public static Date getIndependence(int year, int month, int day) {
        Calendar c = Calendar.getInstance();   // this takes current date

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        if (day == 5) {
            c.set(Calendar.DAY_OF_MONTH, 5);
        } else {
            c.set(Calendar.DAY_OF_MONTH, 1);
        }

        return c.getTime();
    }

    //Memorial Day: The Thursday before the last Monday in the month of May
    public static Date getLastMondayOfMonth(int year) {
        Calendar c = Calendar.getInstance();
        Calendar cd = Calendar.getInstance();

        c.set(year, 4, 30);

        cd.setTime(c.getTime());
        cd.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        DateFormat df = new SimpleDateFormat("EE");

        for (int i = 0; i < 10; i++) {
            cd.add(Calendar.DATE, -1);
            if (df.format(cd.getTime()).equals("Thu")) {
                return cd.getTime();
            }
        }

        return cd.getTime();
    }

    //Memorial Day: The Tuesday after the last Monday in  the month of May
    public static Date getMemorialDay(int year) {
        Calendar c = Calendar.getInstance();
        Calendar cd = Calendar.getInstance();

        c.set(year, 4, 30);

        cd.setTime(c.getTime());
        cd.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        cd.add(Calendar.DATE, 1);

        return cd.getTime();
    }

    //Getting all the holidays(lists) and pushing them into Array List
    public static List<String> getAll(int getYear) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        List<String> christmasEve = range(df.format(getMonday(getYear, Calendar.DECEMBER).getTime()), df.format(getMondayFirst(getYear + 1, Calendar.JANUARY, "").getTime()));
        List<String> thanksGiving = range(df.format(getSaturday(getYear)), df.format(getMondayAfterThursday(getYear)));
        List<String> laborDay = range(df.format(getThursday(getYear, Calendar.SEPTEMBER).getTime()), df.format(getMondayFirst(getYear, Calendar.SEPTEMBER, "labor").getTime()));
        List<String> IndependenceDay = range(df.format(getIndependence(getYear, Calendar.JULY, 1).getTime()), df.format(getIndependence(getYear, Calendar.JULY, 5).getTime()));
        List<String> MemorialDay = range(df.format(getLastMondayOfMonth(getYear).getTime()), df.format(getMemorialDay(getYear).getTime()));

        List<String> combinedList = new ArrayList<>();
        if (christmasEve != null)
            combinedList.addAll(christmasEve);
        if (thanksGiving != null)
            combinedList.addAll(thanksGiving);
        if (laborDay != null)
            combinedList.addAll(laborDay);
        if (IndependenceDay != null)
            combinedList.addAll(IndependenceDay);
        if (MemorialDay != null)
            combinedList.addAll(MemorialDay);

        return combinedList;
    }

    //Verifying single date from list
    //(year: int, date: String)
    //e.g
    //(year: 2020: date: "2020-21-12")
    public static boolean isDateValid(int year, String date) {

        List<String> listOfString = getAll(year);
        boolean result = listOfString.stream().anyMatch(v -> v.contains(date));

        return result;
    }

    //Comparing lists
    //List1: Pre-defined
    //List2: "User Entry ArrayList<String>"
    public static boolean isListValid(int year, List date) {
        List<String> listOfString = getAll(year);

        Collections.sort(listOfString);
        Collections.sort(date);

        Boolean result = listOfString.equals(date);

        return result;
    }

    //Getting current year holidays
    public static List<String> getList() {
        Calendar c = Calendar.getInstance();
        List<String> list = getAll(c.get(Calendar.YEAR));

        return list;
    }

    //Getting all holidays by User Defined Year
    public static List<String> getListByYear(int year) {
        Calendar c = Calendar.getInstance();
        List<String> list = getAll(year);

        return list;
    }

    //Verifying date and returning it back
    public static Object getDate(String date) {
        Calendar c = Calendar.getInstance();
        List<String> list = getAll(c.get(Calendar.YEAR));

        Object result = null;

        if(list.contains(date)) {
            result = date;
        } else {
            result = false;
        }

        return result;
    }

}

