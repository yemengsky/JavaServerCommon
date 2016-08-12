public class LogUtil {
    public static void log(Object... objects) {
        StringBuffer sb = new StringBuffer(getString());
        for (Object object : objects) {
            sb.append(" ");
            sb.append(((object == null) ? "NULL" : object.toString()));
        }
        System.out.println(sb);
    }

    private static String getString() {
        StackTraceElement ste = new Throwable().getStackTrace()[2];
        String valueString = DateUtil.TimestampToString(new Timestamp
                (System.currentTimeMillis())) + ":" + ste.getFileName().replace(".java", "") + " "
                + ste.getMethodName() + ":" + ste.getLineNumber() + " ";
        return valueString;
    }
}
