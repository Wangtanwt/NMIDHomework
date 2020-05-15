package first.extra;

public abstract class DateAndTimeStamp {
    String timeStamp = null;
    String date = null;
    int year = 1970;
    int month = 1;
    int day = 1;
    int hour = 8;
    int minute = 0;
    int second = 0;

    @Override
    public abstract String toString();
}
