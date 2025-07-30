package kr.study.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingFee {
	
	// Car 객체. 추후 차량번호로 정렬을 하기 위해 Comparable 구현.
	public static class Car implements Comparable<Car> {
		private String carNumber;
		private int parkingTime;
		private int totalParkingTime;
		private boolean isIn;
		private int parkingFee;
		
		public Car(String carNumber) {
			this.carNumber = carNumber;
			this.parkingTime = 0;
			this.totalParkingTime = 0;
			this.isIn = false;
			this.parkingFee = 0;
		}
		
		public String getCarNumber() {
			return this.carNumber;
		}
		
		public void setCarNumber(String carNumber) {
			this.carNumber = carNumber;
		}
		
		public int getParkingTime() {
			return this.parkingTime;
		}
		
		public void setParkingTime(int parkingTime) {
			this.parkingTime = parkingTime;
		}
		
		public int getTotalParkingTime() {
			return this.totalParkingTime;
		}
		
		public void setTotalParkingTime(int totalParkingTime) {
			this.totalParkingTime = totalParkingTime;
		}
		
		public boolean isIn() {
			return this.isIn;
		}
		
		public void setIsIn(boolean isIn) {
			this.isIn = isIn;
		}
		
		public int getParkingFee() {
			return this.parkingFee;
		}
		
		public void setParkingFee(int parkingFee) {
			this.parkingFee = parkingFee;
		}

		@Override
		public int compareTo(Car o) {  // Comparable 을 구현하기 위해서 comareTo 메소드를 구현함. 나의 차량번호가 뒷 차량번호 보다 큰 경우에만 양수를 리턴함. (오름차순 정렬)
			if (Integer.parseInt(this.carNumber) > Integer.parseInt(o.carNumber)) {
				return 1;
			} else {
				return -1;
			}
		}
	}
	
	public static final String LAST_TIME = "23:59";  // 마지막 출차 시간

	public static void main(String[] args) {
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", 
				"07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", 
				"19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		
		int[] fees2 = {120, 0, 60, 591};
		String[] records2 = {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT",
				"18:00 0202 OUT","23:58 3961 IN"};
		
		int[] fees3 = {1, 461, 1, 10};
		String[] records3 = {"00:00 1234 IN"};
		
		System.out.println(Arrays.toString(solution(fees, records)));  // [14600, 34400, 5000] 출력
		System.out.println(Arrays.toString(solution(fees2, records2)));  // [0, 591] 출력
		System.out.println(Arrays.toString(solution(fees3, records3)));  // [14841] 출력
	}
	
	public static int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        
        Map<String, Car> carMap = new HashMap<>();
        
        for (int i = 0; i < records.length; i++) {  // records 배열을 순회하면서 carMap 에 넣을 car 객체를 만든다.
        	String parkingTimeStr = records[i].split(" ")[0];  
        	String carNum = records[i].split(" ")[1];
        	String isInStr = records[i].split(" ")[2];
        	
        	if (carMap.get(carNum) == null) {  // 만약 해당 차 번호가 map 에 없다면 Car 를 새로 생성한다.
        		Car car = new Car(carNum);
        		car.setParkingTime(calcTime(parkingTimeStr));
        		car.setIsIn(calcIsIn(isInStr));
        		
        		carMap.put(carNum, car);  // map 에 Car 넣기
        		
        	} else { // 해당 차 번호가 map 에 존재했다면 총 주차 시간과 주차 여부를 따져야 한다.
        		Car car = carMap.get(carNum);
        		boolean isIn = calcIsIn(isInStr);
        		int parkingTime = calcTime(parkingTimeStr);
        		
        		if (isIn == false && car.isIn() == true) {  // 만약 차가 주차되어있던 상태였고 이번에 주차장을 나간다면...
        			car.setTotalParkingTime(car.getTotalParkingTime() + (parkingTime - car.getParkingTime()));  // 현재 주차 시간에서 원래 가지고 있던 주차시간을 빼고 그 시간을 원래 가지고 있었던 총 주차시간에 더한다.
        			car.setIsIn(isIn);
        		} else if (isIn == true && car.isIn() == false) {  // 만약 차가 주차되어있지 않은 상태였고 이번에 주차한다면...
        			car.setParkingTime(parkingTime);
        			car.setIsIn(isIn);
        		}
        		
        		carMap.put(carNum, car);  // map 에 Car 넣기
        	}
        }
        
        List<Car> carList = new ArrayList<>();  // car 를 정렬하기 위해서 만든 리스트. carMap 을 순회하면서 car 객체들을 이 리스트에 넣는다.
        
        for (String carNum : carMap.keySet()) {
        	Car car = carMap.get(carNum);
        	if (car.isIn() == true) {  // 아직까지 주차장을 빠져나가지 않은 차는 23:59 분에 주차장을 빠져나갔다고 설정함.
        		int parkingTime = calcTime(LAST_TIME);
        		car.setTotalParkingTime(car.getTotalParkingTime() + (parkingTime - car.getParkingTime()));  // 23:59 에서 원래 가지고 있던 주차시간을 빼고 그 시간을 원래 가지고 있었던 총 주차시간에 더한다.
        	}
        	
        	car.setParkingFee(calcParkingFee(fees, car.getTotalParkingTime()));
        	carList.add(car);
        }
        

        Collections.sort(carList);  // carList 를 오름차순 정렬한다. Car 가 Comparable 을 구현했기 때문에 정렬이 가능함.
        
        int index = 0;
        answer = new int[carList.size()];
        for (Car car : carList) {  
        	answer[index++] = car.getParkingFee(); // carList 를 순회하면서 car 객체의 주차 요금을 answer 에 넣는다. 
        }
        
        return answer;
    }
	
	// 주차 시간 string 을 int 로 계산해서 돌려주는 메소드
	public static int calcTime(String parkingTime) {
		int hour = Integer.parseInt(parkingTime.split(":")[0]);
		int minute = Integer.parseInt(parkingTime.split(":")[1]);
		
		return (hour * 60) + minute;
	}
	
	// 입/출차 내역 string 을 boolean 으로 바꿔서 돌려주는 메소드
	public static boolean calcIsIn(String isInStr) {
		if (isInStr.equals("IN")) {
    		return true;
    	} else {
    		return false;
    	}
	}
	
	// 총 주차시간으로 주차 요금 계산하는 메소드
	public static int calcParkingFee(int[] fees, int totalParkingTime) {
		
		int baseTime = fees[0]; // 기본 시간
		int baseFee = fees[1];  // 기본 요금
		int unitTime = fees[2]; // 단위 시간
		int unitFee = fees[3]; // 단위 요금
		
		if (totalParkingTime <= baseTime) { // 만약 총 주차시간이 기본 시간보다 낮다면...
			return baseFee;  // 그냥 기본 요금 리턴
		} else {
			return baseFee + (int) (Math.ceil((double)(totalParkingTime - baseTime) / unitTime) * unitFee); // 아니라면 요금 계산해서 리턴
		}
	}
}
