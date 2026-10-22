class MyCalendarThree {
    TreeMap<Integer, Integer> calendar = new TreeMap<>();
    int max = 0;

    public MyCalendarThree() {}

    public int book(int startTime, int endTime) {
        calendar.put(startTime, calendar.getOrDefault(startTime, 0) + 1);
        calendar.put(endTime, calendar.getOrDefault(endTime, 0) - 1);

        int active = 0;
        max = 0;

        for (int count : calendar.values()) {
            active += count;
            max = Math.max(max, active);
        }

        return max;
    }
}