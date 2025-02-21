import java.util.PriorityQueue;

public class MinRefuelingStops {


    public int minRefuelStops(int target, int startFuel, int[][] stations) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int fuel = startFuel, stops = 0, i = 0;

        while (fuel < target) {
            while (i < stations.length && stations[i][0] <= fuel) {
                maxHeap.offer(stations[i][1]);
                i++;
            }

            if (maxHeap.isEmpty()) return -1;

            fuel += maxHeap.poll();
            stops++;
        }

        return stops;
    }

    public static void main(String[] args) {
        MinRefuelingStops solution = new MinRefuelingStops();
        int[][] stations1 = {};
        int[][] stations2 = {{10, 100}};

        System.out.println(solution.minRefuelStops(1, 1, stations1));
        System.out.println(solution.minRefuelStops(100, 1, stations2));
    }
}
