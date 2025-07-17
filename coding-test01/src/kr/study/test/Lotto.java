package kr.study.test;

import java.util.Arrays;

public class Lotto {

	public static void main(String[] args) {
		
		int[] lottos1 = {44, 1, 0, 0, 31, 25};
		int[] win_nums1 = {31, 10, 45, 1, 6, 19};
		int[] lottos2 = {0, 0, 0, 0, 0, 0};
		int[] win_nums2 = {38, 19, 20, 40, 15, 25};
		int[] lottos3 = {45, 4, 35, 20, 3, 9};
		int[] win_nums3 = {20, 9, 3, 45, 4, 35};
		
		System.out.println(Arrays.toString(solution(lottos1, win_nums1)));   // [3, 5]  최대 3등, 최소 5등.
		System.out.println(Arrays.toString(solution(lottos2, win_nums2)));   // [1, 6]  최대 1등, 최소 6등.
		System.out.println(Arrays.toString(solution(lottos3, win_nums3)));   // [1, 1]  최대 1등, 최소 1등.
	}
	
	public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int min_count = 0, zero_count = 0;
        
        for (int i = 0; i < lottos.length; i++) {
        	 // 0 이 몇 개 들어있는 지 확인하기
        	if (lottos[i] == 0) {  
        		zero_count++;
        		continue;  // 0 이라면 아래 비교를 위한 for 문을 순회할 필요가 없으므로 continue
        	}
        	
			for (int j = 0; j < win_nums.length; j++) {
				// 만약 내가 뽑은 번호와 로또 번호가 같다면 최소로 맞춘 개수 증가
				if (lottos[i] == win_nums[j]) {	
					min_count++;
					break;
				}
			}
		}
        
        answer[0] = judgeRank(min_count + zero_count);    // 최대로 맞출 수 있는 로또 번호의 개수는 '최소 맞춘 개수 + 0의 개수'다.
        answer[1] = judgeRank(min_count);  
        
        return answer;
    }
	
	// 로또 번호를 맞춘 개수로 등수를 판별하는 함수
	public static int judgeRank(int count) {
		
		// 6개를 맞췄으면 1등 리턴, 5개를 맞췄으면 2등 리턴, ... 하나도 못맞췄으면 6등 리턴
		return switch (count) {
			case 6 -> 1;
			case 5 -> 2;
			case 4 -> 3;
			case 3 -> 4;
			case 2 -> 5;
			case 1 -> 6;
			default -> 6;
		};
	}
}
