package coding.test.doit.graph.fail.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PlanningTrip { /* P286 문제 51, 백준 1976번. 여행 계획 짜기 */
    /* 뭐가 틀린 건지 모르겠다. */
    private static int[] g;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        g = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            g[i] = i;
        }
        StringTokenizer st;
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int c = Integer.parseInt(st.nextToken());
                if (c == 1) union(i, j + 1);
            }
        }
        st = new StringTokenizer(br.readLine());
        boolean isPossible = false;
        int c1 = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m - 1; i++) {
            int c2 = Integer.parseInt(st.nextToken());
            if (find(c1) == find(c2)) isPossible = true;
            else isPossible = false;
        }
        if (isPossible) System.out.println("YES");
        else System.out.println("NO");
    }

    private static void union(int c1, int c2) {
        c1 = find(c1);
        c2 = find(c2);
        if (c1 < c2) g[c2] = g[c1];
        else g[c1] = g[c2];
    }

    private static int find(int n) {
        if (g[n] == n) return n;
        else return g[n] = find(g[n]);
    }
}
