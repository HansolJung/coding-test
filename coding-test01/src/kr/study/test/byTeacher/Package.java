package kr.study.test.byTeacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Package {

	public static void main(String[] args) {
		System.out.println(solution(22, 6, 8));
		System.out.println(solution(13, 3, 6));
	}
	
	public static int solution(int n, int w, int num) {
		
		int answer = 0;
		
		// n: 전체 개수 , w: 한 줄에 개수
		int totalRow = (int) Math.ceil((double) n / w);
		List<List<Integer>> box = IntStream.range(0, totalRow)
				.mapToObj(i -> IntStream.range(0, w).mapToObj(j -> 0).collect(Collectors.toList()))
				.collect(Collectors.toList());
		
		int count = 1;
		int targetRow = 0;
		int targetCol = 0;
		
		// 역순으로 loop
		
		for (int i = totalRow - 1; i >= 0; i--) {
			List<Integer> row = new ArrayList<>();
			
			for(int j = 0; j < w; j++) {
				if (count > n) {
					row.add(0);
					continue;
				}
				
				row.add(count++);
			}
			
			// 전체 높이가 홀수와 짝수일 때 뒤집히는 라인이 다르다.
			int reverseCount = totalRow % 2 == 0 ? 0 : 1;
			if (i % 2 == reverseCount) {
				Collections.reverse(row);
			}
			
			box.set(i, row);
			
			// 찾아야할 숫자의 위치 찾기
			if (row.contains(num)) {
				targetRow = i;
				targetCol = row.indexOf(num);
			}
		}
		
		// 내 위치에서 위로 몇개가 있는지 찾기
		for (int i = 0; i <= targetRow; i++) {
			if (box.get(i).get(targetCol) != 0) {
				answer++;
			}
		}
	
		return answer;
	}

}
