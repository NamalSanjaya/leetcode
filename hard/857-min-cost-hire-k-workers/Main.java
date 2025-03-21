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

}
