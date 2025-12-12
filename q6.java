package model_assesment;
import java.util.*;

public class q6 {

    static class Node {
        String name;
        int arrival_time;
        int severity;
        Node(String name, int arrival_time, int severity) {
            this.name = name;
            this.arrival_time = arrival_time;
            this.severity = severity;
        }
    }

    static Node pollEarliest(Queue<Node> q) {
        if (q.isEmpty()) return null;
        List<Node> list = new ArrayList<>(q);
        list.sort(Comparator.comparingInt(a -> a.arrival_time));
        Node earliest = list.get(0);
        q.remove(earliest);
        return earliest;
    }

    static void addPatient(Queue<Node> normal, Queue<Node> critical, String name, int time, int severity) {
        Node p = new Node(name, time, severity);
        if (severity >= 8) critical.add(p);
        else normal.add(p);
    }

    static Node getNextPatient(Queue<Node> normal, Queue<Node> critical, int currentTime, int lastCriticalTime) {
        if (!critical.isEmpty() && currentTime - lastCriticalTime >= 5)
            return pollEarliest(critical);
        if (!critical.isEmpty())
            return pollEarliest(critical);
        return pollEarliest(normal);
    }

    public static void main(String[] args) {
        Queue<Node> NormalQueue = new LinkedList<>();
        Queue<Node> CriticalQueue = new LinkedList<>();

        addPatient(NormalQueue, CriticalQueue, "Arun", 1, 5);
        addPatient(NormalQueue, CriticalQueue, "Bala", 2, 9);
        addPatient(NormalQueue, CriticalQueue, "Chitra", 3, 7);

        int lastCritical = 0;

        Node next = getNextPatient(NormalQueue, CriticalQueue, 5, lastCritical);
        if (next != null) {
            if (next.severity >= 8) lastCritical = 5;
            System.out.println(next.name);
        }
    }
}
