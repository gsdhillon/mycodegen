package mycodegen.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class Util {
    public static final String JAVA_DATE_FORMAT = "dd/MM/yyyy";
    public static final String JAVA_DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    /**
     * 
     * @param file
     * @param text
     * @throws Exception 
     */
    public static void writeToFile(File file, String text) throws Exception{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(text);
        }
        System.out.println("Created file - "+file.getAbsolutePath());
    }
    /**
     * 
     * @return 
     */
    public static String getDateStamp() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(JAVA_DATE_FORMAT);
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
    /**
     * 
     * @return 
     */
    public static String getDateTimeStamp() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(JAVA_DATETIME_FORMAT);
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
    public static String formatDate(java.sql.Date date) {
        DateFormat df = new SimpleDateFormat(JAVA_DATE_FORMAT);
        return df.format(date);
    }
    public static String formatDateTime(java.sql.Date date) {
        DateFormat df = new SimpleDateFormat(JAVA_DATETIME_FORMAT);
        return df.format(date);
    }
    //    public static void main(String[] args) {
//        String text = "_AAA_bbb___cccC_Barc_DDD__7GGG___";
//        System.out.println(getJavaName(text, false));
//    }
}
