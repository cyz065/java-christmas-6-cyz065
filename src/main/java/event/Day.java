package event;

public enum Day {
    FRIDAY(1), SATURDAY(2), SUNDAY(3), MONDAY(4),
    TUESDAY(5), WEDNESDAY(6), THURSDAY(0);

    private int value;
    Day(int value) {
        this.value = value;
    }

    public static Day getDay(int day) {
        for (Day date : values()) {
            if (date.value == day) {
                return date;
            }
        }

        throw new IllegalArgumentException();
    }

    public int getDayValue() {
        return value;
    }
}
