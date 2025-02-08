import java.util.*;

public class BusiestServers {
    public static List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }

        PriorityQueue<int[]> busyServers = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int[] requestCount = new int[k];
        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {
            int startTime = arrival[i];
            int duration = load[i];

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= startTime) {
                availableServers.add(busyServers.poll()[1]);
            }

            Integer server = availableServers.ceiling(i % k);
            if (server == null) {
                server = availableServers.ceiling(0);
            }

            if (server != null) {
                availableServers.remove(server);
                busyServers.offer(new int[]{startTime + duration, server});
                requestCount[server]++;
                maxRequests = Math.max(maxRequests, requestCount[server]);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arrival = {1, 2, 3, 4, 5};
        int[] load = {5, 2, 3, 3, 3};
        System.out.println(busiestServers(k, arrival, load));
    }
}
