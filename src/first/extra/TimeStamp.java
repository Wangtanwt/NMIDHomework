package first.extra;

public class TimeStamp extends DateAndTimeStamp {

    public TimeStamp(String date) {
        if (date == null) return;
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
        year = Integer.parseInt(dateSplit[0]);
        month = Integer.parseInt(dateSplit[1]);
        day = Integer.parseInt(dateSplit[2]);
        hour = Integer.parseInt(dateSplit[3]);
        minute = Integer.parseInt(dateSplit[4]);
        second = Integer.parseInt(dateSplit[5]);
        int tempYear = year - 1970;
        int[] months = {31, tempYear % 4 != 2 ? 28 : 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month > 12 || day > months[month] || hour > 23 || minute > 59 || second > 59)
            return;
        int[] days = {0, 365, 730, 1096};
        long dateDig = tempYear / 4 * (365 * 4 + 1) + days[tempYear % 4];
        for (int i = 0; i < month - 1; i++)
            dateDig += months[i];
        dateDig = (dateDig + day - 1) * 3600 * 24 + hour * 3600 + minute * 60 + second - 28800;
        this.timeStamp = String.valueOf(dateDig);
    }

    @Override
    public String toString() {
        return timeStamp;
    }
}
