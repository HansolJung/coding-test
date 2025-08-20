package kr.study.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class OvertimeIndex {

	public static void main(String[] args) {

		System.out.println(solution(4, new int[] {4, 3, 3}));  // 12
		System.out.println(solution(1, new int[] {2, 1, 2}));  // 6
		System.out.println(solution(3, new int[] {1, 1}));  // 0
	}
	
	public static long solution(int n, int[] works) {
        long answer = 0;
        
        if (Arrays.stream(works).sum() <= n) { // 만약 works 의 전체 합이 n 보다 작거나 같다면 야근없이 모든 작업을 처리할 수 있다는 뜻이므로 바로 0 리턴
            return 0;
        }
        
        // 넣을때마다 자동 정렬을 하기 위해 PriorityQueue 사용
        // 기존에는 단순 배열을 사용, n 만큼 순회하면서 배열의 가장 큰 수에서 1씩 빼는 과정에서 Arrays.sort(); 를 사용했으나 
        // 효율성 테스트에 지속적으로 걸려서 다른 자료구조로 바꿈 
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());  // queue 제일 맨 앞에 큰 수가 오도록 내림차순으로 설정
        
        for (int i : works) {  // works 배열을 순회하면서 queue 에 하나씩 집어넣음 (넣으면서 자동정렬됨)
        	queue.add(i);
        }

        for (int i = 0; i < n; i++) {  // n 만큼 순회하면서 queue 의 가장 큰 수에서 1씩 뺀다
        	int num = queue.poll();  // queue 에서 제일 맨 앞에 있는 요소(제일 큰 수)를 빼온다
            num -= 1;  // 그 수에서 1을 뺀다
            queue.add(num); // 다시 queue 에 넣으면 자동 정렬된다
        }

        for (int i : queue) {  // queue 를 순회하면서 각각의 요소를 제곱해서 합을 구한다
            answer += (i * i);
        }
        
        return answer;
    }

}
