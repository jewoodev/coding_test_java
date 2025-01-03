package coding.test.thisis.bfs;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CompetitiveContagion { //p344
    static int n, k, s, x, y; //첫째와 마지막 줄에 주어지는 값
    static int[][] map; //시험관 맵
    static boolean visited[][]; // 방문 맵
    static int dx[] = {-1, 1, 0, 0}; //이동 배열
    static int dy[] = {0, 0, -1, 1};
    static PriorityQueue<Node> q = new PriorityQueue<>(); //바이러스를 담는 Queue
    static BufferedWriter bw;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); //맵 정보 저장
                if (map[i][j] != 0) { //바이러스 있는 곳은 방문, 바이러스 저장
                    visited[i][j] = true;
                    q.add(new Node(i, j, 0, map[i][j]));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        
        bfs();
        br.close();
        bw.close();
    }

    private static void bfs() throws IOException {
        while (!q.isEmpty()) {
            Node a = q.poll();
            if (a.time > s) { //바이러스 존재하지 않는 경우
                bw.write("0\n");
                return;
            }
            if (a.x == x - 1 && a.y == y - 1 && map[a.x][a.y] != 0) {
                bw.write(String.valueOf(a.num));
            }
            for (int i = 0; i < 4; i++) {
                int nx = a.x + dx[i];
                int ny = a.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        map[nx][ny] = a.num;
                        q.add(new Node(nx, ny, a.time + 1, a.num));
                    }
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {

        // x, y -> 좌표, time -> 시간, num -> 바이러스 번호
        int x, y, time, num;

        public Node(int x, int y, int time, int num) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            if (this.time != o.time) return this.time - o.time;
            else return this.num - o.num;
        }
    }
}
