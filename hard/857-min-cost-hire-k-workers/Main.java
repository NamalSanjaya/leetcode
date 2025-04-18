import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumCostToHireKWorkers {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Worker[] workers = new Worker[n];

        for (int i = 0; i < n; i++) {
            workers[i] = new Worker(quality[i], wage[i]);
        }

        Arrays.sort(workers, (a, b) -> Double.compare(a.ratio, b.ratio));

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int totalQuality = 0;
        double minCost = Double.MAX_VALUE;

        for (Worker worker : workers) {
            maxHeap.add(worker.quality);
            totalQuality += worker.quality;

            if (maxHeap.size() > k) {
                totalQuality -= maxHeap.poll();
            }

            if (maxHeap.size() == k) {
                minCost = Math.min(minCost, totalQuality * worker.ratio);
            }
        }

        return minCost;
    }

    static class Worker {
        int quality;
        double ratio;

        Worker(int quality, int wage) {
            this.quality = quality;
            this.ratio = (double) wage / quality;
        }
    }

    public static void main(String[] args) {
        MinimumCostToHireKWorkers solver = new MinimumCostToHireKWorkers();
        int[] quality1 = {10, 20, 5};
        int[] wage1 = {70, 50, 30};
        int k1 = 2;
        System.out.println(solver.mincostToHireWorkers(quality1, wage1, k1));

        int[] quality2 = {3, 1, 10, 10, 1};
        int[] wage2 = {4, 8, 2, 2, 7};
        int k2 = 3;
        System.out.println(solver.mincostToHireWorkers(quality2, wage2, k2));
    }

}
