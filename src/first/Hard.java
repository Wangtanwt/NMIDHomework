package first;

import java.util.Scanner;

public class Hard {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        System.out.println(toData(str));
    }

    public static String toData(String timeStamp) {
        if (timeStamp == null) return "1970-01-01 00:00:00";
        if (!timeStamp.matches("\\d+"))
            timeStamp = timeStamp.replaceAll("\\D+", "");
        long time = Long.parseLong(timeStamp);
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
        return String.format("%d-%02d-%02d %02d:%02d:%02d",year ,monthDay[0], monthDay[1], SMH[2][0], SMH [1][0], SMH[0][0]);
    }

    public static int[] getMonthDay(int time, int bissextile) {
        int[] day = {1, 1};
        if (time < 0) return day;
        int[][] month = {{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
                {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};
        for (int i = 0; i < month[bissextile].length; time -= month[bissextile][i], i++)
            if (time < 0) {
                day[0] = i;
                day[1] = time + month[bissextile][i - 1]+1;
                break;
            }
        return day;
    }
}
