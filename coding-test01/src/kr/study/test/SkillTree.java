package kr.study.test;

import java.util.Map;
import java.util.TreeMap;

public class SkillTree {

	public static void main(String[] args) { 
	      System.out.println(solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"})); // 2
	      System.out.println(solution("ABC", new String[]{"ACB", "ACE", "BAC", "ABCF", "AEBDC", "ABC"})); // 3
	}
	   
	public static int solution(String skill, String[] skill_trees) {
		int answer = 0;

       
		for (String str : skill_trees) {  // 스킬 트리 순회
			String temp = "";
			Map<Integer, String> map = new TreeMap<>();  // 키 값으로 indexOf 의 값을 넣을 건데 정렬하기 위해서 HashMap 이 아니라 TreeMap 을 사용
		
			for (String s : skill.split("")) {  // 선행 스킬 순서를 split 해서 한 글자씩 순회
				if (str.contains(s)) {   // 예들 들어서 스킬 트리 "BACDE" 에 "CBD" 중 한 글자인 "C" 가 들어있다면...
					map.put(str.indexOf(s), s);  // TreeMap 에 스킬 트리 "BACDE"에 C 가 위치한 인덱스를 키 값으로 해서 C 를 넣는다
				}
			}
		
			for (Integer i : map.keySet()) {  // TreeMap 이기 때문에 자동 정렬된 상태로 맵을 순회
				temp += map.get(i);  // 맵 안에 들어있는 값을 이어붙이면 일치하는 스킬들이 어떤 순서로 들어가 있는지 알 수 있다. 예) BCD, CBD, CB 등...
			}
		
		
			// 1. temp 의 길이가 0보다 크다는 소리는 일치하는 스킬이 있었다는 뜻.
			// 2. 선행 스킬 순서에 temp 가 포함되어 있는지 확인. 예) 선행 스킬 순서 CBD 에 CB 가 포함되어 있는지 확인
			// 3. 선행 스킬 순서 제일 맨 앞 글자와 temp 의 맨 앞 글자의 인덱스가 같아야 함. 왜냐하면 제일 먼저 그 스킬부터 배워야 하기 때문. 
			//			예) 선행 스킬 순서 CBD 에서 C 의 인덱스는 0, temp CB 에서 C 의 인덱스는 0. 그러면 temp 는 배울 수 있는 스킬 트리임.  
			if (temp.length() > 0 && skill.contains(temp) && temp.charAt(0) == skill.charAt(0)) {  
				answer++;  // 답 증가
			} else if (temp.length() == 0) {  // 만약 temp 의 길이가 0이라면 일치하는 스킬이 없었다는 뜻. 즉, 아무런 제약 조건없이 스킬 트리를 그냥 배울 수 있다.
				answer++;   // 답 증가
		    }
		       
		}
		      
		return answer;
	}
}
