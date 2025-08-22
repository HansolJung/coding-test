package kr.study.test.byTeacher;

import java.util.Arrays;
import java.util.Collections;

public class OvertimeIndex {

	public static void main(String[] args) {
		
		System.out.println(solution(4, new int[] {4, 3, 3}));  // 12
		
		
	}

	
	public static long solution(int n, int[] works) {
		long sum = Arrays.stream(works).sum();
		
		// 일해야할 시간과 남은 시간의 차이가 없거나 남은 시간이 많으면 야근 안함.
		if (sum <= n) {
			return 0L;
		}
		
		Integer[] arr = Arrays.stream(works).boxed().toArray(Integer[]::new);
		
		// 내림차순 정렬
		Arrays.sort(arr, Collections.reverseOrder());
		
		loop: while (n > 0) {
			int maxHour = arr[0];
			for (int i = 0; i < arr.length; i++) {
				if (maxHour == arr[i]) {
					arr[i]--;
					n--;
				} else {
					// max 값을 1씩 빼는 루틴
					// 내림차순을 해놓은 상태라
					// 현재 index 에 위치한 값이 max 값보다 작으면 뒤의 값도 작을것이기 때문에 loop를 도는 의미가 없음
					break;  
				}
				
				if (n == 0) {
					break loop;  // 라벨링 사용해서 while 문도 벗어나기
				}
			}
		}
		
		long answer = 0;
		
		for (int work : arr) {
			answer +=  (long) (Math.pow(work, 2));
		}
		
		return answer;
	}
}
