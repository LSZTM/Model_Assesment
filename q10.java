package model_assesment;
import java.util.*;

public class q10 {
    public static void main(String[] args) {
        int n = 5;
        List<List<double[]>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        g.get(0).add(new double[]{1, 10, 4});
        g.get(1).add(new double[]{2, 5, 2});
        g.get(0).add(new double[]{2, 25, 0});
        g.get(2).add(new double[]{3, 2, 6});
        g.get(1).add(new double[]{3, 20, 0});
        g.get(3).add(new double[]{4, 1, 2});
        int src = 0, dest = 4;
        double[] dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        dist[src] = 0;
        PriorityQueue<double[]> pq =
                new PriorityQueue<>(Comparator.comparingDouble(a -> a[0]));
        pq.add(new double[]{0, src});
        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            double d = cur[0];
            int u = (int) cur[1];
            if (d != dist[u]) continue;
            if (u == dest) break;
            for (double[] e : g.get(u)) {
                int v = (int) e[0];
                double cost = e[1] + 0.5 * e[2];
                if (dist[v] > dist[u] + cost) {
                    dist[v] = dist[u] + cost;
                    parent[v] = u;
                    pq.add(new double[]{dist[v], v});
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int cur = dest;
        while (cur != -1) {
            path.add(cur);
            cur = parent[cur];
        }
        Collections.reverse(path);
        System.out.println(path + " cost=" + dist[dest]);
    }
}
