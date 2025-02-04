// DID NOT PASS ALL TEST CASES. THIS CODE HAS SOME ISSUES.

import java.util.*;

public class MaxTasksAssign {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);

        int left = 0, right = Math.min(tasks.length, workers.length), ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canAssign(mid, tasks, workers, pills, strength)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private boolean canAssign(int k, int[] tasks, int[] workers, int pills, int strength) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int j = workers.length - 1, usedPills = 0;

        for (int i = k - 1; i >= 0; i--) {
            while (j >= workers.length - k && workers[j] >= tasks[i]) {
                pq.offer(workers[j]);
                j--;
            }
            if (!pq.isEmpty()) {
                pq.poll();
            } else if (usedPills < pills) {
                usedPills++;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MaxTasksAssign mta = new MaxTasksAssign();
        System.out.println(mta.maxTaskAssign(new int[]{3,2,1}, new int[]{0,3,3}, 1, 1));
        System.out.println(mta.maxTaskAssign(new int[]{5,4}, new int[]{0,0,0}, 1, 5));
        System.out.println(mta.maxTaskAssign(new int[]{5,9,8,5,9}, new int[]{1,6,4,2,6}, 1, 5));
    }
}
