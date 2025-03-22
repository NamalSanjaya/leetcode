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
                    if (visitedRoutes.contains(route)) continue; // Skip already visited routes
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


}
