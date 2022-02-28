package utils;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.zip.DataFormatException;

public class GetData {
    public static String getShipDate(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSSZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("+0000"));
        return dateFormat.format(date);
    }
}
