package model_assesment;
import java.util.*;
class Song {
    String name;
    Song next, prev;
    Song(String name) {
        this.name = name;
    }
}
public class q4 {
    Song head, tail, current;
    HashMap<String, Song> map = new HashMap<>();
    void addSong(String name) {
        if (map.containsKey(name)) {
            System.out.println("Duplicate ignored: " + name);
            return;
        }
        Song s = new Song(name);
        map.put(name, s);
        if (head == null) {
            head = tail = current = s;
        } else {
            tail.next = s;
            s.prev = tail;
            tail = s;
        }
        System.out.println("Added: " + name);
    }
    void playNext() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Playing next → " + current.name);
        } else {
            System.out.println("No next song!");
        }
    }
    void playPrev() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Playing previous → " + current.name);
        } else {
            System.out.println("No previous song!");
        }
    }
    void removeSong(String name) {
        if (!map.containsKey(name)) {
            System.out.println("Song not found: " + name);
            return;
        }
        Song s = map.get(name);
        if (s.prev != null) s.prev.next = s.next;
        else head = s.next;

        if (s.next != null) s.next.prev = s.prev;
        else tail = s.prev;
        if (current == s) {
            current = (s.next != null) ? s.next : s.prev;
        }
        map.remove(name);
        System.out.println("Removed: " + name);
    }
    void exportForward() {
        Song t = head;
        System.out.print("Playlist → ");
        while (t != null) {
            System.out.print(t.name + " ");
            t = t.next;
        }
        System.out.println();
    }
    void exportBackward() {
        Song t = tail;
        System.out.print("Reverse → ");
        while (t != null) {
            System.out.print(t.name + " ");
            t = t.prev;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        q4 p = new q4();
        p.addSong("Track1");
        p.addSong("Track2");
        p.addSong("Track3");
        p.addSong("Track2");    
        p.exportForward();
        p.playNext();
        p.playNext();
        p.playPrev();
        p.removeSong("Track2");
        p.exportForward();
        p.exportBackward();
    }
}
