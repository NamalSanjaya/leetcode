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

    public static void main(String[] args) {
        IPO ipo = new IPO();

        int k1 = 2, w1 = 0;
        int[] profits1 = {1, 2, 3};
        int[] capital1 = {0, 1, 1};
        System.out.println(ipo.findMaximizedCapital(k1, w1, profits1, capital1));

        int k2 = 3, w2 = 0;
        int[] profits2 = {1, 2, 3};
        int[] capital2 = {0, 1, 2};
        System.out.println(ipo.findMaximizedCapital(k2, w2, profits2, capital2));

        int k3 = 2, w3 = 2;
        int[] profits3 = {2, 3, 5};
        int[] capital3 = {1, 2, 3};
        System.out.println(ipo.findMaximizedCapital(k3, w3, profits3, capital3));
    }

}
