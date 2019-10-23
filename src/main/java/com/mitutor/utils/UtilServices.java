package com.mitutor.utils;

import java.util.Date;
import java.util.List;

public class UtilServices {

    static public Date getMaxDate(List<Date> dates){
        Date maxDate = dates.get(0);
        for (Date d: dates) {
            if(maxDate.before(d)){
                maxDate = d;
            }
        }
        return  maxDate;
    }

    static public Date getMinDate(List<Date> dates){
        Date minDate = dates.get(0);
        for (Date d: dates) {
            if(minDate.after(d)){
                minDate = d;
            }
        }
        return  minDate;
    }




}
