package model_assesment;
import java.util.*;
public class q7 {
    public static String lowestCommonDirectory(String p1, String p2) {
        if (p1.length() > 1 && p1.endsWith("/")) p1 = p1.substring(0, p1.length()-1);
        if (p2.length() > 1 && p2.endsWith("/")) p2 = p2.substring(0, p2.length()-1);
        String[] a = p1.split("/");
        String[] b = p2.split("/");
        List<String> common = new ArrayList<>();
        int n = Math.min(a.length, b.length);
        for (int i = 0; i < n; i++) {
            String ca = a[i];
            String cb = b[i];
            if (ca.equals(cb)) {
                if (!ca.isEmpty()) common.add(ca);
            } else break;
        }
        if (common.isEmpty()) return "/";
        StringBuilder sb = new StringBuilder();
        for (String s : common) {
            sb.append("/").append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(lowestCommonDirectory("/a/b/c.txt", "/a/b/d/e.txt")); // /a/b
        System.out.println(lowestCommonDirectory("/usr/bin/python", "/usr/lib/python3")); // /usr
        System.out.println(lowestCommonDirectory("/foo", "/bar")); // /
    }
}
