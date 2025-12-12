package model_assesment;
import java.util.*;
class Transaction {
    String id;
    double amount;
    long ts; 
    Transaction(String id, double amount, long ts) {
        this.id = id; this.amount = amount; this.ts = ts;
    }
}
public class q9 {
    Deque<Transaction> window = new ArrayDeque<>();
    Map<String, Integer> counts = new HashMap<>();
    long kSeconds;
    public q9(long kSeconds) { this.kSeconds = kSeconds; }
    private String keyOf(Transaction t) {
        return t.id + "#" + t.amount;
    } 
    public boolean process(Transaction t, Set<String> suspiciousIds) {
        long now = t.ts;
        while (!window.isEmpty() && now - window.peekFirst().ts > kSeconds) {
            Transaction old = window.removeFirst();
            String k = keyOf(old);
            counts.put(k, counts.get(k) - 1);
            if (counts.get(k) == 0) counts.remove(k);
        }
        window.addLast(t);
        String k = keyOf(t);
        counts.put(k, counts.getOrDefault(k, 0) + 1);
        if (counts.get(k) >= 2) { 
            suspiciousIds.add(t.id); 
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        long k = 10; 
        q9 fd = new q9(k);
        Set<String> suspicious = new HashSet<>();
        List<Transaction> stream = Arrays.asList(
            new Transaction("tx1", 100.0, 1000),
            new Transaction("tx2", 50.0, 1001),
            new Transaction("tx1", 100.0, 1005), 
            new Transaction("tx3", 200.0, 1020)
        );
        for (Transaction t : stream) {
            boolean fraud = fd.process(t, suspicious);
            System.out.println("Processed " + t.id + " fraud? " + fraud);
        }
        System.out.println("Suspicious IDs: " + suspicious);
    }
}
