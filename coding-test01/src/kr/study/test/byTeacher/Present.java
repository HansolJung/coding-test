package kr.study.test.byTeacher;

import java.util.HashMap;
import java.util.Map;

class Friend {
	private int myPoint;  // 나의 선물 지수
	private Map<String, Integer> toGive = new HashMap<>(); // 내가 준 사람과 횟수
	
	public void setMyPoint(int point) {
		this.myPoint += point;
	}
	
	public int getMyPoint() {
		return this.myPoint;
	}
	
	// 내가 선물을 준 사람 저장
	public void setToGive(String name) {
		this.toGive.put(name, toGive.getOrDefault(name, 0) + 1);
	}
	
	// 내가 상대방에게 준 선물 개수
	public int getGiveCount(String name) {
		return this.toGive.containsKey(name) ? this.toGive.get(name) : 0;
	}
}


public class Present {
	
	public static int solution(String[] friends, String[] gifts) {
		
		// 선물을 주고받은 기록을 저장해야 한다
		int answer = 0;
		Map<String, Friend> userMap = new HashMap<>();  // 선물 주고받는 목록
		
		for (String me : friends) {
			Friend friend = new Friend();
			
			for (String gift : gifts) {
				String[] person = gift.split(" ");
				
				if (me.equals(person[0])) {
					// 준 사람
					friend.setMyPoint(1);
					friend.setToGive(person[1]);
				} else if (me.equals(person[1])) {
					// 내가 받은 사람
					friend.setMyPoint(-1);
					
				}
			}
			
			userMap.put(me, friend);
		}
		
		// 비교
		for(String me : userMap.keySet()) {
			int myPresentCount = 0; // 내가 받은 선물 개수
			Friend myPresentInfo = userMap.get(me);  // 내가 선물 준 애들 정보(내가 받아야 할 대상)
			
			// 비교하기 위해서 친구 목록을 가져온다
			for (String compare : friends) {
				if (me.equals(compare)) { // 중복을 피하기 위해서 나는 제외
					continue;
				}
				
				Friend comparePerson = userMap.get(compare); // 비교 대상이 선물 준 정보
				
				if (myPresentInfo.getGiveCount(compare) - comparePerson.getGiveCount(me) > 0) { // 내가 상대방한테 준 선물 개수가 더 많을 경우
					myPresentCount++; // 선물 개수 증가
				} else if (myPresentInfo.getGiveCount(compare) - comparePerson.getGiveCount(me) == 0) { // 서로 주고 받은 선물개수가 똑같다면
					if (myPresentInfo.getMyPoint() > comparePerson.getMyPoint()) { 	// 둘이 동등할 경우 선물지수를 비교
						myPresentCount++;  // 선물 개수 증가
					}
					
					// 선물지수가 상대방과 같거나 작으면 선물 개수 증가 없음
				}
			}
			
			answer = Math.max(answer, myPresentCount); // 최댓값 비교
		}
		
		return answer;
	}

	public static void main(String[] args) {
		
		String[] friends = {"muzi", "ryan", "frodo", "neo"};
		String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
		
		System.out.println("가장 많이 받은 개수 : " + solution(friends, gifts));
	}
}
