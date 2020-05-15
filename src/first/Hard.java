package first;

import java.util.Scanner;

public class Hard {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");
        String str = input.next();
        System.out.println(toTimeStamp(str));
    }

    public static String toTimeStamp(String date) {
        String[] dateSplit = new String[6];
        if (date.matches("\\d+\\s*-\\s*\\d+\\s*-\\s*\\d+\\s*\\d+:\\d+:\\d+"))
            dateSplit = date.split("\\D+");
        else {
            if (!date.matches("\\d+"))
                date = date.replaceAll("\\D+", "");
            int[] digit = {4, 2, 2, 2, 2, 2};
            for (int i = 0, begin = 0; i < digit.length; begin += digit[i], i++)
                dateSplit[i] = date.substring(begin, begin + digit[i]);
        }
        int year = Integer.parseInt(dateSplit[0]) - 1970, month = Integer.parseInt(dateSplit[1])-1;
        int day = Integer.parseInt(dateSplit[2])-1, hour = Integer.parseInt(dateSplit[3]);
        int minute = Integer.parseInt(dateSplit[4]), second = Integer.parseInt(dateSplit[5]);
        int[] months = {31, year%4 != 2?28:29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] days = {0, 365, 730, 1096};
        if(month > 12 || day > months[month + 1] || hour > 23 || minute > 59 || second > 59) return "ERROR";
        long dateDig = year / 4 * (365 * 4 + 1) + days[year % 4];
        for (int i = 0; i < month; i++)
            dateDig += months[i];
        dateDig = (dateDig + day) * 3600 * 24 + hour * 3600 + minute * 60 + second-28800;
        return String.valueOf(dateDig);
    }

    public static String toDate(String timeStamp) {
        if (timeStamp == null) return "1970-01-01 08:00:00";
        if (!timeStamp.matches("\\d+"))
            timeStamp = timeStamp.replaceAll("\\D+", "");
        long time = Long.parseLong(timeStamp)+28800;
        int year = 1970;
        int[] monthDay;
        int[][] SMH = {{0, 60}, {0, 60}, {0, 24}};
        for (int i = 0; i < SMH.length; i++) {
            SMH[i][0] = (int) time % SMH[i][1];
            time /= SMH[i][1];
        }
        year += time / (365 * 4 + 1) * 4;
        time %= 365 * 4 + 1;
        if (time > 730) {
            time -= 730;
            year += 2;
            if (time > 366) {
                monthDay = getMonthDay((int) time - 366, 0);
                year++;
            } else
                monthDay = getMonthDay((int) time, 1);
        } else {
            if (time > 365) {
                monthDay = getMonthDay((int) time - 365, 0);
                year++;
            } else
                monthDay = getMonthDay((int) time, 0);
        }
        return String.format("%d-%02d-%02d %02d:%02d:%02d", year, monthDay[0], monthDay[1], SMH[2][0], SMH[1][0], SMH[0][0]);
    }

    public static int[] getMonthDay(int time, int bissextile) {
        int[] day = {1, 1};
        if (time < 0) return day;
        int[] month = {31, bissextile == 1 ? 29:28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int i = 0; i < month.length; time -= month[i], i++)
            if (time < 0) {
                day[0] = i;
                day[1] = time + month[i - 1] + 1;
                break;
            }
        return day;
    }
}
