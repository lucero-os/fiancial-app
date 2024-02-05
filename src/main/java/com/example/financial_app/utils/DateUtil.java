package com.example.financial_app.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

    public Date now(){
        return this.convertToLocalDateTimeToDate(LocalDateTime.now());
    }

    public Date applyTimeDifference(Date datetime, Long miliseconds){
        long currentTime = datetime.getTime();
        datetime.setTime(currentTime + miliseconds);

        return datetime;
    }

    private Date convertToLocalDateTimeToDate(LocalDateTime localDateTime) {
        // Convert LocalDateTime to Date
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
