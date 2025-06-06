package coding.test.programmers._1._42889;

import java.util.*;

class Solution {
    static class Pair<K, V> {
        private K key;
        private V value;
        
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() {
            return key;
        }
        
        public V getValue() {
            return value;
        }
    }
    
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        Map<Integer, Integer> map = new HashMap<>();
       
        for (int s : stages) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
       
        int total = stages.length;
        List<Pair<Double, Integer>> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(new Pair<>(total == 0 ? 0 : (double)map.getOrDefault(i, 0) / total, i));
            total -= map.getOrDefault(i, 0);
        }
       
        list.sort((a, b) -> a.getKey().equals(b.getKey())
                  ? a.getValue() - b.getValue()
                  : b.getKey().compareTo(a.getKey()));
       
        for (int i = 0; i < N; i++) {
            answer[i] = list.get(i).getValue();
        }
       
        return answer;
    }
}
