package club.msos.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class dateTime {
    public String getTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT-8"));
        return dateFormat.format(new Date());
    }
}
