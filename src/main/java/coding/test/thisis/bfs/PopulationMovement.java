package coding.test.thisis.bfs;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PopulationMovement { //p353

    private static int n, l, r;
    private static int[][] map;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static boolean[][] visited;
    private static boolean isMoved;

    static class Ctr {
        int x, y;
        public Ctr(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(move()));
        bw.close();
        br.close();
    }

    //모든 좌표에서 BFS를 하는 메소드
    static int move() {
        int cnt = 0; //국경이동 횟수
        while (true) {
            isMoved = false;
            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) bfs(i, j); //방문하지 않은 국가라면 BFS 시작
                }
            }
            if (!isMoved) break;
            else cnt++; //국경이동이 있었다면 횟수 추가
        }
        return cnt;
    }

    static void bfs(int x, int y) {
        //방문한 국가로부터 BFS를 시작하기 위한 초기화
        visited[x][y] = true; //방문 시작한 위치 방문 처리
        Queue<Ctr> q = new LinkedList<>(); //BFS에 쓸 Queue
        q.add(new Ctr(x, y));
        ArrayList<Ctr> point = new ArrayList<>(); //인구이동(평균 연산)에 쓸 ArrayList
        point.add(new Ctr(x, y));

        while (!q.isEmpty()) {
            Ctr a = q.poll();
            x = a.x;
            y = a.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                //국경선을 공유하는 두 나라의 인구 차이 조건을 부합하면
                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (!visited[nx][ny] && l <= Math.abs(map[x][y] - map[nx][ny]) && Math.abs(map[x][y] - map[nx][ny]) <= r) {
                        isMoved = true; //국경을 열고 이동 가능함을 명시.
                        visited[nx][ny] = true;  //이동가능한 국가 방문처리
                        point.add(new Ctr(nx, ny));
                        q.add(new Ctr(nx, ny));
                    }
                }
            }
        }

        //BFS 후 결과 맵에 반영
        int sum = 0;
        for (int i = 0; i < point.size(); i++) {
            Ctr ctr = point.get(i);
            sum += map[ctr.x][ctr.y]; //이동가능한 국가 인구 총합을 구해서
        }
        for (int i = 0; i < point.size(); i++) {
            Ctr ctr = point.get(i);
            map[ctr.x][ctr.y] = sum / point.size(); //평균을 구해 이민 결과를 반영해준다.
        }
    }
}
