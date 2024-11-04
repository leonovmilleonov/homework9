package com.viktor.src.utils;

import com.viktor.src.dto.DataRow;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FileUtil {
    private static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    private static final String TIME_REGEX = "\\d{2}:\\d{2}";
    private static final String NUMBER_REGEX = "\\s+(\\+375)?\\s?(\\d{2})\\s?(\\d{3})\\s?(\\d{2})\\s?(\\d{2})";

    private FileUtil() {
    }

    public static String formateRow(DataRow dataRow) {
        return dataRow.getId() +
               ", " + formateDate(dataRow.getTime())
               + ", " + formateNumber(dataRow.getNumber());
    }

    private static String formateDate(String date) {
        Pattern datePattern = Pattern.compile(DATE_REGEX);
        Pattern timePattern = Pattern.compile(TIME_REGEX);
        Matcher dateMatcher = datePattern.matcher(date);
        Matcher timeMatcher = timePattern.matcher(date);
        String result = "";
        if (dateMatcher.find()) {
            result += dateMatcher.group() + " ";
        }
        if (timeMatcher.find()) {
            result += timeMatcher.group();
        }
        return result;
    }

    private static String formateNumber(String number) {
        Pattern pattern = Pattern.compile(NUMBER_REGEX);
        Matcher matcher = pattern.matcher(number);
        if(matcher.matches()){
            return "+375 (" + matcher.group(2) +") " + matcher.group(3) + "-" + matcher.group(4) + "-" + matcher.group(5);
        }
        return number;
    }

}
