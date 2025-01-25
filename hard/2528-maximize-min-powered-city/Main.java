import java.util.Arrays;

public class MaximizeMinimumPoweredCity {
    public long maximizeMinimumPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] power = new long[n];
        long currentSum = 0;

        for (int i = 0; i < n; i++) {
            currentSum += stations[i];
            if (i > r) {
                currentSum -= stations[i - r - 1];
            }
            if (i >= r) {
                power[i - r] = currentSum;
            }
        }
        for (int i = n - r; i < n; i++) {
            power[i] = currentSum;
            currentSum -= stations[i - r];
        }

        long low = 0, high = Arrays.stream(power).sum() + k;
        long result = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canAchieveMinimumPower(mid, stations, r, k, power)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    private boolean canAchieveMinimumPower(long target, int[] stations, int r, long k, long[] power) {
        int n = stations.length;
        long[] additionalStations = new long[n];
        long extraPower = 0;
        long currentSum = 0;

        for (int i = 0; i < n; i++) {
            if (i > r) {
                currentSum -= additionalStations[i - r - 1];
            }
            currentSum += power[i];
            if (currentSum < target) {
                long needed = target - currentSum;
                if (i + r < n) {
                    additionalStations[i + r] += needed;
                }
                extraPower += needed;
                currentSum += needed;
                if (extraPower > k) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        MaximizeMinimumPoweredCity obj = new MaximizeMinimumPoweredCity();

        int[] stations1 = {1, 2, 4, 5, 0};
        int r1 = 1, k1 = 2;
        System.out.println(obj.maximizeMinimumPower(stations1, r1, k1)); // Output: 5

        int[] stations2 = {4, 4, 4, 4};
        int r2 = 0, k2 = 3;
        System.out.println(obj.maximizeMinimumPower(stations2, r2, k2)); // Output: 4
    }
}
