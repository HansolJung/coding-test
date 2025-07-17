package kr.study.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gift {

	public static void main(String[] args) {
		String[] friends1 = {"muzi", "ryan", "frodo", "neo"};
		String[] gifts1 = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
		String[] friends2 = {"joy", "brad", "alessandro", "conan", "david"};
		String[] gifts2 = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};
		String[] friends3 = {"a", "b", "c"};
		String[] gifts3 = {"a b", "b a", "c a", "a c", "a c", "c a"};
		
		System.out.println(solution(friends1, gifts1));   // 2
		System.out.println(solution(friends2, gifts2));   // 4
		System.out.println(solution(friends3, gifts3));   // 0
		
	}
	
	public static int solution(String[] friends, String[] gifts) {
		
        List<String> giftRelation = new ArrayList<>();
        
        // 모든 선물 관계 리스트를 만들기
        for (int i = 0; i < friends.length; i++) {
        	for (int j = 0; j < friends.length; j++) {
        		if (friends[i] != friends[j]) {
        			giftRelation.add(friends[i] + " " + friends[j]);  // 예를 들어 giftRelation 리스트엔 "ryan frodo", "ryan muzi"... 처럼 들어가게 된다.
        		}
        	}
        }
        
        Map<String, Integer> giftPointMap = new HashMap<>();   // 각각의 선물 지수를 저장하는 맵. 예) ryan : 1, frodo : 0...
        Map<String, Integer> giftRelationMap = new HashMap<>();  // 서로 선물을 주고받은 횟수를 저장하는 맵. 예) ryan frodo : 2, ryan muzi : 1...
        Map<String, Integer> nextMonthGiftMap = new HashMap<>(); // 각각이 다음 달의 받을 선물 개수를 저장하는 맵. 예) ryan : 1, frodo : 2...
        
        for (int i = 0; i < friends.length; i++) {  // 각각이 다음 달의 받을 선물 개수를 저장하는 맵 초기화
        	nextMonthGiftMap.put(friends[i], 0);  // 전부 0으로 일단 초기화. 예) ryan : 0, frodo : 0
        }
        
        for (int i = 0; i < giftRelation.size(); i++) { // 모든 선물 관계 리스트를 순회
        	int count = 0;  // 선물 횟수 초기화
        	for (int j = 0; j < gifts.length; j++) {  
        		if (giftRelation.get(i).equals(gifts[j])) {  // 만약 선물 관계와 gifts 의 값이 값다면...
        			count++;  // 선물 횟수 증가
        		}
        	}
        	
        	giftRelationMap.put(giftRelation.get(i), count);  // 서로 선물을 주고받은 횟수를 저장하는 맵에 저장
        }
        
        for (int i = 0; i < friends.length; i++) {  // friends 순회.
        	int giftPoint = 0;
        	for (int j = 0; j < gifts.length; j++) {
        		if (friends[i].equals(gifts[j].split(" ")[0])) {  // 만약 선물 내역의 0번째 (선물을 보낸 사람) 값이 나와 같다면...
        			giftPoint++;	// 나의 선물지수 증가
        		} else if (friends[i].equals(gifts[j].split(" ")[1])) { // 만약 선물 내역의 1번째 (선물을 받은 사람) 값이 나와 같다면...
        			giftPoint--;    // 나의 선물지수 감소
        		}
        	}
        	giftPointMap.put(friends[i], giftPoint);   // 선물 지수 맵에 저장
        }
        
        /*
         * 헷갈리지 않도록 giftRelationMap 을 순회할때 전부 '내가~~ 했다면' 기준으로 체크함.
         */
        
        for (String relation : giftRelationMap.keySet()) {
        	
        	String[] temp = relation.split(" ");   // 예를 들어 relation 이 "ryan frodo" 라면 이것을 쪼개서 [ryan, frodo] 로 만듦.
        	String opponent = temp[1]+" "+temp[0];  // 앞과 뒤를 뒤집어서 붙이면 상대방의 선물 내역이 됨. "ryan frodo" 를 "frodo ryan" 으로 바꾼 꼴.
        	
        	if (giftRelationMap.get(relation) == 0) {  // 내가 선물을 준적이 없으면...
        		if (giftRelationMap.get(opponent) == 0) { // 상대방도 나한테 선물을 준적이 없으면...
        			
        			// 선물 지수 비교
        			if (giftPointMap.get(temp[0]) >  giftPointMap.get(temp[1])) { // 내가 상대방보다 선물 지수가 높다면...
        				nextMonthGiftMap.put(temp[0], nextMonthGiftMap.get(temp[0]) + 1);  // 내가 다음달의 받을 선물 + 1
        			}
        			
        		}
        		
        	} else {  // 내가 선물을 준적이 있으면...
        		if (giftRelationMap.get(relation) > giftRelationMap.get(opponent)) { // 내가 상대방보다 선물을 더 많이 줬다면...
        			nextMonthGiftMap.put(temp[0], nextMonthGiftMap.get(temp[0]) + 1);  // 내가 다음달의 받을 선물 + 1
        		} else if (giftRelationMap.get(relation) == giftRelationMap.get(opponent)) { // 내가 상대방과 주고 받은 선물의 개수가 같다면...
        			// 선물 지수 비교
        			if (giftPointMap.get(temp[0]) >  giftPointMap.get(temp[1])) { // 내가 상대방보다 선물 지수가 높다면...
        				nextMonthGiftMap.put(temp[0], nextMonthGiftMap.get(temp[0]) + 1);  // 내가 다음달의 받을 선물 + 1
        			}
        		}
        	}
        }
        
        
        int max = 0;
        for (String name : nextMonthGiftMap.keySet()) {   // 다음 달의 받을 선물 개수 맵 순회
        	if (max < nextMonthGiftMap.get(name)) {  // 선물 개수 최대 값 구하기 
        		max = nextMonthGiftMap.get(name);   
        	}
        }
        
        return max;
    }

}
