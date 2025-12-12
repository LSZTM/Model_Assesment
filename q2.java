package model_assesment;
import java.util.*;

public class q2 {
    static List<String> dict = Arrays.asList(
            "apple","app","apply","apt","ant","ants","bat","batch","bath","baton","bay","be","bee","beef","bend","bent","best","hover"
    );
    static {
        Collections.sort(dict);
    }

    public static List<String> suggest(String word) {
        int pos = Collections.binarySearch(dict, word);
        if (pos < 0) pos = -(pos + 1);
        List<String> candidates = new ArrayList<>();
        int start = Math.max(0, pos - 25);
        int end = Math.min(dict.size(), pos + 25);
        for (int i = start; i < end; i++) candidates.add(dict.get(i));
        PriorityQueue<String> heap = new PriorityQueue<>((a,b)->{
            int da = dist(word,a), db = dist(word,b);
            if (da != db) return db - da;
            return b.compareTo(a);
        });
        for (String w : candidates) {
            heap.offer(w);
            if (heap.size() > 3) heap.poll();
        }
        List<String> out = new ArrayList<>(heap);
        out.sort((a,b)->{
            int da = dist(word,a), db = dist(word,b);
            if (da != db) return da - db;
            return a.compareTo(b);
        });
        return out;
    }

    private static int dist(String a, String b) {
        int n = a.length(), m = b.length();
        int[][] d = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) d[i][0] = i;
        for (int j = 0; j <= m; j++) d[0][j] = j;
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                d[i][j] = (a.charAt(i-1)==b.charAt(j-1)) ? d[i-1][j-1] : 1+min(d[i-1][j], d[i][j-1], d[i-1][j-1]);
        return d[n][m];
    }

    private static int min(int a,int b,int c){ 
    	return Math.min(a,Math.min(b,c)); }

    public static void main(String[] args) {
        System.out.println(suggest("an"));
        System.out.println(suggest("bth"));
        System.out.println(suggest("apl"));
    }
}
