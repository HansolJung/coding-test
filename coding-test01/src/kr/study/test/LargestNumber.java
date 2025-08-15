package kr.study.test;

import java.util.Arrays;

public class LargestNumber {

	public static void main(String[] args) {
		
		System.out.println(solution(new int[] {6, 10, 2}));  // 6210
		System.out.println(solution(new int[] {3, 30, 34, 5, 9}));  // 9534330
	}
	
	public static String solution(int[] numbers) {
        String answer = "";

        String[] arr = new String[numbers.length]; 
        
        int count = 0;
        for (int i : numbers) {  // 정수를 편하게 이어붙이기 위해 String 배열에 변환해서 집어넣기
        	String s = String.valueOf(i);
        	arr[count++] = s;
        }   
        
        Arrays.sort(arr, (s1, s2) -> {   // 해당 String 배열을 정렬함
        	int n1 = Integer.parseInt(s1 + s2);   // 앞 String 과 뒤 String 을 이어붙이고 그것을 정수로 변환  예) // '12'와 '3' 을 이어붙인 정수 123
        	int n2 = Integer.parseInt(s2 + s1);   // 뒤 String 과 앞 String 을 이어붙이고 그것을 정수로 변환  예) // '3' 과 '12' 를 이어붙인 정수 312
        	
        	if (n1 < n2) { // 뒤앞을 이어붙여서 만든 정수가 앞뒤를 이어붙여서 만든 정수보다 큰 경우 둘의 자리를 바꿈
        		return 1;
        	} else if (n1 > n2) { 
        		return -1;
        	} else {
        		return 0;
        	}
        });
        
        answer = String.join("", arr);  // 위의 과정을 거쳐서 정렬된 String 배열의 요소들을 이어붙임
        
        if (answer.charAt(0) == '0') {  // 만약 배열을 이어붙여서 만든 문자열이 0 으로 시작된다면...
        	answer = "0";  // 그냥 "0" 으로 리턴
        }
         
        return answer;
    }

}
