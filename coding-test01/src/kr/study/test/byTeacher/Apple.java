package kr.study.test.byTeacher;

import java.util.Arrays;
import java.util.Comparator;

public class Apple {

	public static void main(String[] args) {

		System.out.println(solution(3, 4, new int[] {1, 2, 3, 1, 2, 3, 1}));  // 8
		System.out.println(solution(4, 3, new int[] {4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2}));  // 33
	}
	
	public static int solution(int k, int m, int[] score) {
		
		// score 를 내림차순으로 해보자
		// 아니라면 score 를 오름차순으로 정렬 후 역순으로 계산하자	
		Integer[] apples = Arrays.stream(score)
				.boxed()
				.sorted(Comparator.reverseOrder())
				.toArray(Integer[]::new);
		
		int answer = 0;
		for (int i = m-1; i < apples.length; i+=m) {
			answer += apples[i] * m;
		}
		
		
		return answer;
		
	}

}
