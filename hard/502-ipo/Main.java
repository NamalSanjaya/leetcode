import java.util.Arrays;
import java.util.PriorityQueue;

public class IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;

        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i] = new int[]{capital[i], profits[i]};
        }

        // Sort projects by capital required
        Arrays.sort(projects, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        int index = 0;
        for (int i = 0; i < k; i++) {
            while (index < n && projects[index][0] <= w) {
                maxProfitHeap.offer(projects[index][1]);
                index++;
            }

            if (maxProfitHeap.isEmpty()) {
                break;
            }

            w += maxProfitHeap.poll();
        }
        return w;
    }

}
