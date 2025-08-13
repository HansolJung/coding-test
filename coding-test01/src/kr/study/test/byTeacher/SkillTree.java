package kr.study.test.byTeacher;

public class SkillTree {

	public static void main(String[] args) {
		System.out.println(solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"})); // 2

	}
	
	public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(String s : skill_trees) {
    		int pos = 0;
        	for (int i = 0; i < s.length(); i++) {
        		// 스킬 순서가 일치해야 하기 때문에 skill tree 의 단어를 하나씩 비교해서 순서 체크
        		char ch = s.charAt(i);
        		
        		if (skill.indexOf(ch) == -1) { // 같은 단어가 없는 경우
        			continue;
        		}
        		
        		if (skill.indexOf(ch) == pos) { // 같은 단어가 있고, 순서도 맞는 경우
        			pos++;
        		} else { // 같은 단어는 있는데, 순서가 안맞는 경우	
        			pos = -1;
        			break;
        		}
        	}
        	
        	if (pos >= 0) { // 스킬 단어가 하나도 없는 스킬트리라면 pos가 0으로 나옴. 그 경우에도 answer 증가.
        		answer++;
        	}
        }
        
        return answer;
    }

}
