package by.bsuir.webproj.util;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * Created by Brotorias on 11.06.2016.
 */
public class CurrentDate {
    private static long curTime;

    public static String getCurrentDate(){
        curTime = System.currentTimeMillis();
        return new SimpleDateFormat("yyyy-MM-dd").format(curTime);
    }

    public static String getCurrentTime(){
        curTime = System.currentTimeMillis();
        return new SimpleDateFormat("HH:mm:ss").format(curTime);
    }

    public static String getStringDate(Date date){
        if(date != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        }
        else{
            return new String("");
        }
    }

    public static String getStringTime(Time time){
        if(time != null) {
            return new SimpleDateFormat("HH:mm:ss").format(time);
        }
        else{
            return new String("");
        }
    }
}
