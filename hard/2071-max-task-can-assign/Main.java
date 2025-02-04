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
        Deque<Integer> dq = new ArrayDeque<>();
        int j = workers.length - k, usedPills = 0;

        for (int i = 0; i < k; i++) {
            while (j < workers.length && workers[j] < tasks[i]) {
                dq.offer(workers[j]);
                j++;
            }
            if (!dq.isEmpty() && usedPills < pills && dq.peek() + strength >= tasks[i]) {
                dq.poll();
                usedPills++;
            } else if (j < workers.length && workers[j] >= tasks[i]) {
                j++;
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
    }
}
