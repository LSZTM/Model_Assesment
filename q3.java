package model_assesment;
import java.util.*;

public class q3 {
    static class FileNode {
        String name; int size;
        FileNode(String n, int s) { name = n; size = s; }
    }
    static class Folder {
        String name;
        List<FileNode> files = new ArrayList<>();
        List<Folder> subFolders = new ArrayList<>();
        Folder(String n) { name = n; }
    }
    static class Result {
        int totalSize;
        PriorityQueue<FileNode> top3;
        Result(int t, PriorityQueue<FileNode> h) { totalSize = t; top3 = h; }
    }
    static class ExplorerEngine {
        Result compute(Folder root) { return dfs(root); }
        private Result dfs(Folder folder) {
            int total = 0;
            PriorityQueue<FileNode> best = new PriorityQueue<>((a, b) -> a.size - b.size);
            for (FileNode f : folder.files) {
                total += f.size;
                push(best, f);
            }
            for (Folder sub : folder.subFolders) {
                Result r = dfs(sub);
                total += r.totalSize;
                for (FileNode f : r.top3) push(best, f);
            }
            return new Result(total, best);
        }
        private void push(PriorityQueue<FileNode> heap, FileNode f) {
            heap.offer(f);
            if (heap.size() > 3) heap.poll();
        }
    }
    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.files.add(new FileNode("a.txt", 10));
        root.files.add(new FileNode("b.txt", 40));
        Folder docs = new Folder("docs");
        docs.files.add(new FileNode("c.pdf", 100));
        docs.files.add(new FileNode("d.pdf", 60));
        Folder images = new Folder("images");
        images.files.add(new FileNode("img1.jpg", 150));
        images.files.add(new FileNode("img2.jpg", 80));
        root.subFolders.add(docs);
        root.subFolders.add(images);
        ExplorerEngine engine = new ExplorerEngine();
        Result r = engine.compute(root);
        System.out.println("Total Size = " + r.totalSize);
        List<FileNode> list = new ArrayList<>(r.top3);
        list.sort((x, y) -> y.size - x.size);
        for (FileNode f : list) System.out.println(f.name + " -> " + f.size + " KB");
    }
}
