package model_assesment;
import java.util.*;

public class q8 {
    public static void main(String[] args) {
        Map<String, double[]> vids = new HashMap<>();
        PriorityQueue<double[]> pq = new PriorityQueue<>(
                (a, b) -> {
                    if (a[1] != b[1]) return Double.compare(b[1], a[1]);
                    return Double.compare(b[2], a[2]);
                }
        );
        long time = 1;
        Runnable update = (Runnable) () -> {};
        updateScore(vids, pq, "A", 100, ++time);
        updateScore(vids, pq, "B", 150, ++time);
        updateScore(vids, pq, "C", 150, ++time);
        updateScore(vids, pq, "A", 180, ++time);
        List<String> top = topK(pq, 3);
        System.out.println(top);
    }
    static void updateScore(Map<String, double[]> vids, PriorityQueue<double[]> pq,
                            String id, double score, long time) {
        if (vids.containsKey(id)) pq.remove(vids.get(id));
        double[] obj = new double[]{id.hashCode(), score, time};
        vids.put(id, obj);
        pq.add(obj);
    }
    static List<String> topK(PriorityQueue<double[]> pq, int k) {
        PriorityQueue<double[]> copy = new PriorityQueue<>(pq);
        List<String> res = new ArrayList<>();
        while (!copy.isEmpty() && k-- > 0) {
            double[] x = copy.poll();
            res.add(findId(x[0]));
        }
        return res;
    }
    static String findId(double hash) {
        for (char c = 'A'; c <= 'Z'; c++) {
            if (("" + c).hashCode() == (int) hash) return "" + c;
        }
        return "?";
    }
}
