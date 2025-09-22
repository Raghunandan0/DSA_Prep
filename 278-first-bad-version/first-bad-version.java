/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int ans = -1;
        int start = 1;
        int prev = -1;
        while (start <= n) {
            int mid = start + (n - start) / 2;
            if (isBadVersion(mid)) {
                prev = mid;
                n = mid - 1;
            } else {
                start = mid + 1;
            }
            if (isBadVersion(mid)) {
                ans = mid;
            } else {
                ans = prev;
            }
        }
        return ans;
    }
}