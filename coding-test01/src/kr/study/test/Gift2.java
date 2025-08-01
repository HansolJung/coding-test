package kr.study.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Gift2 {

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
   
	public static class Person {
		private String name;  // 이름
		private int giftPoint;  // 선물 지수
		private List<String> giftInfoList; // 선물 내역 리스트
		private int nextMonthGift; // 다음 달에 받을 선물 개수
      
		public Person(String name) {
			this.name = name;
			this.giftPoint = 0;
			this.giftInfoList = new ArrayList<>();
			this.nextMonthGift = 0;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getGiftPoint() {
			return giftPoint;
		}

		public void setGiftPoint(int giftPoint) {
			this.giftPoint = giftPoint;
		}

		public List<String> getGiftInfoList() {
			return giftInfoList;
		}

		public void setGiftInfoList(List<String> giftInfoList) {
			this.giftInfoList = giftInfoList;
		}
      
		public int getNextMonthGift() {
			return nextMonthGift;
		}

		public void setNextMonthGift(int nextMonthGift) {
			this.nextMonthGift = nextMonthGift;
		}

		@Override
	    public String toString() {
			return "Person [name=" + name + ", giftPoint=" + giftPoint + ", giftInfoList=" + giftInfoList
					+ ", nextMonthGift=" + nextMonthGift + "]";
	    }
	}
   
	public static int solution(String[] friends, String[] gifts) {
      
		Map<String, Person> personMap = new HashMap<>();
        
		for (String name : friends) { 
			personMap.put(name, new Person(name)); // Person 객체를 생성하면서 동시에 personMap 에 추가
        }
        
        for (String record : gifts) {
           
        	String meStr = record.split(" ")[0];
        	String youStr = record.split(" ")[1];
        	Person me = personMap.get(meStr);   // 나
        	Person you = personMap.get(youStr);  // 상대방
           
        	me.getGiftInfoList().add(youStr);  // 내 선물 내역 리스트에 상대방 이름 추가
        	me.setGiftPoint(me.getGiftPoint() + 1);  // 내 선물 지수 증가
        	you.setGiftPoint(you.getGiftPoint() - 1);  // 상대방의 선물 지수 감소

        }

        
        for (String name : personMap.keySet()) {
        	Person me = personMap.get(name);
        	List<String> infoList = me.getGiftInfoList();
           
        	Set<String> noGiveGiftSet = new HashSet<>(personMap.keySet());  // 선물을 주고 받을 수 있는 사람들의 이름을 얻어와서 set 으로 만듦
        	Set<String> infoSet = new HashSet<>(infoList); // 나의 선물 내역 리스트를 선물 내역 set 으로 변환 (이름 중복 제거를 위해서임)
        	noGiveGiftSet.remove(name);  // 선물을 주고 받을 수 있는 set 에서 내 자신의 이름은 제외함
        	noGiveGiftSet.removeAll(infoSet);  // 선물을 주고 받을 수 있는 set 에서 나의 선물 내역 set 을 제거하면 선물을 주고 받지 않은 내역을 얻을 수 있음
           
        	for (String info : infoSet) {  // 내 선물 내역 순회하기
        		Person you = personMap.get(info);
              
        		List<String> youInfoList = you.getGiftInfoList();
        		if (Collections.frequency(infoList, you.getName()) > Collections.frequency(youInfoList, name)) { // 만약 내가 상대방보다 선물을 더 많이 줬다면...
        			me.setNextMonthGift(me.getNextMonthGift() + 1);  // 내가 다음달의 받을 선물의 개수 증가
        		} 	else if (Collections.frequency(infoList, you.getName()) == Collections.frequency(youInfoList, name)){  // 만약 내가 상대방과 선물을 주고 받은 횟수가 같다면... 선물지수 비교
        			if (me.getGiftPoint() > you.getGiftPoint()) { // 만약 내가 상대방보다 선물지수가 높다면...
        				me.setNextMonthGift(me.getNextMonthGift() + 1);  // 내가 다음달의 받을 선물의 개수 증가
        			}
        		}   
        	}
           
        	for (String info : noGiveGiftSet) {  // 내가 선물을 준적 없는 set 을 순회
        		Person you = personMap.get(info);
        		if (!you.getGiftInfoList().contains(name)) { // 상대방도 나한테 선물을 준적이 없으면... 선물지수 비교
        			if (me.getGiftPoint() > you.getGiftPoint()) { // 만약 내가 상대방보다 선물지수가 높다면...
        				me.setNextMonthGift(me.getNextMonthGift() + 1);  // 내가 다음 달의 받을 선물의 개수 증가
        			}
        		}
        	}
        }
        
        int max = 0;
        for (String name : personMap.keySet()) {  // personMap 을 순회하면서 각각의 사람마다 다음 달의 받을 선물개수를 얻어옴.
        	if (max < personMap.get(name).getNextMonthGift()) {  // 선물 개수 최대 값 구하기 
        		max = personMap.get(name).getNextMonthGift();
        	}
        }

        return max;
	}
}
