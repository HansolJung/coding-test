package kr.study.test;

public class DeliveryBox {

	public static void main(String[] args) {
		System.out.println(solution(22, 6, 8));
		System.out.println(solution(13, 3, 6));
	}
	
    public static int solution(int n, int w, int num) {
        int answer = 0;
        int count = 1;
        int maxHeight = 1;
        int targetRow = 0, targetCol = 0;
        
        // 택배 상자의 최대 높이 개수 구하기
        while (true) {
        	if (w * maxHeight > n) {  
        		break;
        	} else {
        		maxHeight++;
        	}
        }
        
        int[][] boxArr = new int[maxHeight][w];
        
        for (int i = maxHeight - 1 ; i >= 0; i--) {
        	if (i % 2 == 0) { // 짝수번째 라인이라면 거꾸로 정렬해서 박스를 넣는다
        		for (int j = w-1; j >= 0; j--) {
            		if (count <= n) {
            			if (count == num) { // 만약 지금 넣으면 택배 상자가 꺼내려는 상자면 위치 기억해두기
            				targetRow = i;
            				targetCol = j;
            			}
            			boxArr[i][j] = count++;
            		}
            	}
        	} else {
        		for (int j = 0; j < w; j++) {
            		if (count <= n) {
            			if (count == num) { // 만약 지금 넣으면 택배 상자가 꺼내려는 상자면 위치 기억해두기
            				targetRow = i;
            				targetCol = j;
            			}
            			boxArr[i][j] = count++;
            		}
            	}
        	}
        }

        while (true) {
        	if (targetRow >=0) { // 제일 윗 줄에 이를때까지 탐색하기
        		if (boxArr[targetRow][targetCol] != 0) {  // 0 이 아닌 경우만 세기. 즉, 비어있는 공간은 제외.
        			answer++;  // 꺼내야 하는 택배 개수 증가
        		}
        		targetRow--;  // 한줄씩 위로 올라가면서 탐색
        	} else {
        		break;
        	}
        }
        
        
        return answer;
    }

}
