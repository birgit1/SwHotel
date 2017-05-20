
package com.birgit.swhotel.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


public class DateUtils 
{
    public static Date stringToDate(int day, int month, int year)
    {
        String dateString = day+"."+month+"."+year;
        Date date;
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        try 
        {
            date = format.parse(dateString);
        } 
        catch (ParseException ex) 
        {
            return null;
        }
        return date;
    }
    
    public static List<Date> getTimeList(Date arrival, int nights)
    {
        Calendar calendar = new GregorianCalendar();
        
        calendar.setTime(arrival);
        List<Date> dateList = new ArrayList<>();
        for(int i=0; i<nights; i++)
        {
            dateList.add(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }
            
        return dateList;
    }
    
    public static String dateToString(Date date)
    {
        String str = "";
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        str += calendar.get(Calendar.DAY_OF_MONTH);
        str+=".";
        str+=calendar.get(Calendar.MONTH) + 1;
        str+=".";
        str+= calendar.get(Calendar.YEAR);
        return str;
    }
    
    
}
