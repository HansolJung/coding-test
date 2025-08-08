package kr.study.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FruitSeller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(solution(3, 4, new int[] {1, 2, 3, 1, 2, 3, 1}));  // 8
		System.out.println(solution(4, 3, new int[] {4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2}));  // 33
	}
	
	public static int solution(int k, int m, int[] score) {
        int answer = 0;
        
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < score.length; i++) {  // score 배열을 리스트로 옮겨 담기
        	list.add(score[i]);
        }
        
        Collections.sort(list, Collections.reverseOrder());  // 리스트 내림차순 정렬하기
        
        for (int i = 0; i < list.size(); i++) {
        	if ((i+1) % m == 0) {  // m 으로 나눠떨어지는 차례일 때만 계산하기. 상자의 담기는 마지막 사과가 그 상자의 최솟값이기 때문
        		answer = answer + ((int) list.get(i) * m);   // answer 에 누적하면서 계산
        	}
        }

        return answer;
    }

}
