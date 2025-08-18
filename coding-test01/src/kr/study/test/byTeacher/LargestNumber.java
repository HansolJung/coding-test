package kr.study.test.byTeacher;

import java.util.Arrays;

public class LargestNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new int[] {6, 10, 2}));  // 6210
		System.out.println(solution(new int[] {3, 30, 34, 5, 9}));  // 9534330
	}
	
	public static String solution(int[] numbers) {
		
		String[] arr = new String[numbers.length];
		
		for (int i = 0; i< numbers.length; i++) {
			arr[i] = String.valueOf(numbers[i]);
		}
		
		// sort 는 비교 조건이 0보다 커야 변경한다
		// b+a 가 a+b 보다 클때만 a와 b의 자리를 변경함
		Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));
		
		// 0 일때
		if (arr[0].equals("0")) return "0";
		
		String answer = String.join("", arr);
		
		return answer;
	}

}
