package first.extra;

public class Date extends DateAndTimeStamp {

    public Date(String timeStamp) {
        if (timeStamp == null) {
            date = "1970-01-01 08:00:00";
            return;
        }
        if (!timeStamp.matches("\\d+"))
            timeStamp = timeStamp.replaceAll("\\D+", "");
        this.timeStamp = timeStamp;
        long time = Long.parseLong(timeStamp) + 28800;
        second = (int) time % 60;
        time /= 60;
        minute = (int) time % 60;
        time /= 60;
        hour = (int) time %24;
        time /= 24;
        year += time / (365 * 4 + 1) * 4;
        time %= 365 * 4 + 1;
        if (time > 730) {
            time -= 730;
            year += 2;
            if (time > 366) {
                getMonthDay((int) time - 366, false);
                year++;
            } else
                getMonthDay((int) time, true);
        } else {
            if (time > 365) {
                getMonthDay((int) time - 365, false);
                year++;
            } else
                getMonthDay((int) time, false);
        }
        this.date = String.format("%d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
    }

    private void getMonthDay(int time, Boolean bissextile) {
        if (time < 0) return;
        int[] months = {31, bissextile ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int i = 0; i < months.length; time -= months[i], i++)
            if (time < 0) {
                month = i;
                day = time + months[i - 1] + 1;
                break;
            }
    }

    @Override
    public String toString() {
        return this.date;
    }
}
