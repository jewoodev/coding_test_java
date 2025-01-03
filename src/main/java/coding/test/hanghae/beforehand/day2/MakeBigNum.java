package coding.test.hanghae.beforehand.day2;

//큰 수 만들기
public class MakeBigNum {

    public static void main(String[] args) {
        System.out.println(solution("1924", 2));
    }

    public static String solution(String number, int k) {
        //그리디 알고리즘
        //각 자리에서 최고로 높은 수가 나오는 경우를 생각하기

        String answer = "";
        StringBuilder answerBuilder = new StringBuilder();


        char[] array = number.toCharArray();

        int length = array.length - k; //k개 제거한 길이

        //문자 비교를 시작하는 인덱스를 나타내는 start 변수
        int start = 0;

        for(int i = 0; i < length; i++){
            char max = '0';
            for(int j = start; j <= i + k; j++){
                //가장 큰수를 골라서 그 다음 인덱스를 시작 인덱스로 설정하기
                if(array[j] > max){
                    max = array[j];
                    start= j + 1;
                }
            }
            //가장 큰 문자를 String에 넣어주기
            answerBuilder.append(max);
        }

        //k개의 수를 제거할 때 얻을 수 있는 가장 큰 숫자를 구하려 한다
        answer = answerBuilder.toString();
        return answer;
    }
}
