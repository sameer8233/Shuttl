package com.shuttlassignment.shuttleassignment.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sameer Yadav on 12-10-2017.
 */

public class Utils {

    /*
    * Converting miliseconds to a proper date format 1 Aug 2017 etc
    * */

    public static  String getDateFromTimeStamp(long timeStamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        return dateFormat.format(new Date(timeStamp));
    }

}
