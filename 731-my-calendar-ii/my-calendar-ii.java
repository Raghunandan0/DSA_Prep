class MyCalendarTwo {
    List<int[]> calendar = new ArrayList<>();
    List<int[]> overlaps = new ArrayList<>();

    public MyCalendarTwo() {}

    public boolean book(int startTime, int endTime) {
        for (int[] interval : overlaps) {
            if (startTime < interval[1] && interval[0] < endTime) {
                return false;
            }
        }

        for (int[] interval : calendar) {
            int s = Math.max(startTime, interval[0]);
            int e = Math.min(endTime, interval[1]);

            if (s < e) {
                overlaps.add(new int[]{s, e});
            }
        }

        calendar.add(new int[]{startTime, endTime});
        return true;
    }
}