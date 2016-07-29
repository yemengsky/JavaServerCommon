
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
    //"yyyy-MM-dd HH:mm"
    private static final String DEF_FORMAT = "yyyy-MM-dd HH:mm";

    public static Timestamp StringToTimestampWithFormat(String date, String format) {
        return new Timestamp(StringToDateWithFromat(date, format).getTime()); //再转换为sql.Date对象
    }

    public static Timestamp StringToTimestamp(String date) {
        return StringToTimestampWithFormat(date, DEF_FORMAT); //再转换为sql.Date对象
    }

    private static java.util.Date StringToDateWithFromat(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date); //先把字符串转为util.Date对象
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new java.util.Date();
    }

    public static String TimestampToString(Timestamp date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEF_FORMAT);
        return sdf.format(date);
    }

    public static String[] TimestampToStrings(Timestamp date) {
        String dateTime = TimestampToString(date);
        return dateTime.split(" ");
    }
}
