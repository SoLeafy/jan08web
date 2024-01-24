package com.poseidon.inner;

/*
 * 클래스 명이 없는 클래스, 선언과 동시에 인스턴스 생성을 하나로 통합한 클래스
 * 클래스를 인수값으로 사용하는 클래스
 * 객체를 한 번만 사용할 경우에 씀.
 * 
 * 클래스의 선언부가 없기 때문에 이름이 없다 = 생성자를 가질 수 없다.
 * 슈퍼 클래스의 이름이나 구현할 인터페이스를 구현하거나 둘 이상의 인터페이스를 구현할 수 없다.
 * 오직 하나의 클래스를 상속받거나 하나의 인터페이스만 구현 가능함.
 * 
 * 코드 블럭에 클래스를 선언하는 점만 제외하고는 생성자를 호출하는 것과 동일하다.
 * 객체를 구성하는 new문장 뒤에 클래스의 블럭{} 즉, 메소드를 구현한 블럭이 있고 블럭 끝에는
 * 세미콜론이 붙는다.
 * new 뒤에 오는 생성자명이 기존 클래스명이면 익명 클래스고 자동으로 클래스의 하위 클래스가 됨.
 * 
 * 인터페이스인 경우에는 인터페이스를 상속하는 부모 클래스가 Object가 됨.
 * 
 *  
 */

class Anonymous {
	public void print() {
		System.out.println("출력합니다.");
	}
}

public class Inner04 {
	public static void main(String[] args) {
		
		//익명객체?? -> 여기서만 쓸 목적으로 뜯어고친 것.
		Anonymous any = new Anonymous() {
			@Override
			public void print() {
				System.out.println("프린트 합니다.");
			}
		};
		
		System.exit(0); //이 줄에 닿자마자 시스템 종료
		
		any.print();
		
		
		
	}
}
