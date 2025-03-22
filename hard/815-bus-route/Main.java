import java.util.*;

public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        if (!stopToRoutes.containsKey(target)) return -1;

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedRoutes = new HashSet<>();
        Set<Integer> visitedStops = new HashSet<>();

        queue.offer(source);
        visitedStops.add(source);
        int busesTaken = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            busesTaken++;

            for (int i = 0; i < size; i++) {
                int currentStop = queue.poll();

                for (int route : stopToRoutes.getOrDefault(currentStop, new ArrayList<>())) {
                    if (visitedRoutes.contains(route)) continue;
                    visitedRoutes.add(route);

                    for (int stop : routes[route]) {
                        if (stop == target) return busesTaken;
                        if (!visitedStops.contains(stop)) {
                            visitedStops.add(stop);
                            queue.offer(stop);
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BusRoutes solution = new BusRoutes();
        int[][] routes1 = {{1, 2, 7}, {3, 6, 7}};
        System.out.println(solution.numBusesToDestination(routes1, 1, 6));

        int[][] routes2 = {{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}};
        System.out.println(solution.numBusesToDestination(routes2, 15, 12));
    }
}
