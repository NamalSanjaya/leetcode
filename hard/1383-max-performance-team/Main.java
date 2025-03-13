import java.util.*;

class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int MOD = (int) (1e9 + 7);

        int[][] engineers = new int[n][2];
        for (int i = 0; i < n; i++) {
            engineers[i] = new int[]{efficiency[i], speed[i]};
        }

        Arrays.sort(engineers, (a, b) -> Integer.compare(b[0], a[0]));

        PriorityQueue<Integer> speedHeap = new PriorityQueue<>(k);
        long maxPerformance = 0, speedSum = 0;

        for (int[] engineer : engineers) {
            int currEfficiency = engineer[0];
            int currSpeed = engineer[1];

            if (speedHeap.size() == k) {
                speedSum -= speedHeap.poll();
            }
            speedHeap.add(currSpeed);
            speedSum += currSpeed;

            maxPerformance = Math.max(maxPerformance, speedSum * currEfficiency);
        }
        return (int) (maxPerformance % MOD);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int n1 = 6, k1 = 2;
        int[] speed1 = {2, 10, 3, 1, 5, 8};
        int[] efficiency1 = {5, 4, 3, 9, 7, 2};
        System.out.println(solution.maxPerformance(n1, speed1, efficiency1, k1));

        int n2 = 6, k2 = 3;
        int[] speed2 = {2, 10, 3, 1, 5, 8};
        int[] efficiency2 = {5, 4, 3, 9, 7, 2};
        System.out.println(solution.maxPerformance(n2, speed2, efficiency2, k2));

    }

}
