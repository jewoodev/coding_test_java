## [블록게임](https://school.programmers.co.kr/learn/courses/30/lessons/42894)

일반화를 할 수 있는 방법이랑 일반화를 효율적으로 구현할 수 있는 방법을 잘 생각하면 좋다.

1. **기본 접근 방식**
   - 문제는 2x3 또는 3x2 크기의 블록을 제거할 수 있는데, 이때 검은 블록(빈 공간)이 2개, 같은 색상의 블록이 4개 있어야 합니다.
   - 코드에서는 검은 블록을 201(BLACK)으로 표시하여 관리한다.
2. **주요 알고리즘 흐름**
   - solution 메소드에서는 hasDeleted 플래그를 사용하여 더 이상 제거할 수 있는 블록이 없을 때까지 반복한다.
   - 각 반복에서 두 가지 주요 단계를 수행한다:
     - 검은 블록 표시
     - 제거 가능한 블록 찾기

3. **검은 블록 표시**

```java
for (int j = 0; j < n; j++) {
    for (int i = 0; i < n; i++) {
        if (board[i][j] == 0 || board[i][j] == BLACK) {
            board[i][j] = BLACK;
        } else {
            break;
        }
    }
}
```

- 각 열을 위에서 아래로 검사하면서 빈 공간(0)이나 이미 검은 블록(BLACK)을 만나면 BLACK으로 표시한다.
- 블록을 만나면 해당 열의 검사는 중단한다.

4. **블록 제거 시도**

- 2x3 크기와 3x2 크기의 두 가지 패턴을 모두 검사한다.
- tryDelete 메소드에서 각 패턴에 대해:
   - 검은 블록(BLACK)의 개수를 세고
   - 같은 색상의 블록이 4개 있는지 확인
   - 조건이 맞으면 해당 블록들을 제거(0으로 변경)

5. **제거 조건**
   - 검은 블록이 정확히 2개
   - 같은 색상의 블록이 정확히 4개
   - 이 조건이 만족되면 해당 블록들을 제거하고 answer를 증가시킨다.
이 풀이의 핵심은 다음과 같다:
   - 검은 블록을 명확하게 표시하여 관리
   - 2x3과 3x2 패턴을 모두 검사
   - 조건이 맞는 블록을 찾으면 즉시 제거
   - 더 이상 제거할 수 있는 블록이 없을 때까지 반복

이러한 접근 방식으로 문제에서 요구하는 "제거할 수 있는 블록의 최대 개수"를 효율적으로 찾아낼 수 있다.
