package com.poseidon.inner;
//중첩 클래스: 클래스 속 클래스

/* Inner Class 내부 클래스
 * 클래스 내부에 선언된 클래스입니다. = 두 클래스가 서로 긴밀한 관계
 * 
 * 장점
 * 내부 클래스에서 외부 클래스의 멤버들을 쉽게 접근할 수 있다.
 * 캡슐화, 코드의 복잡성을 줄여준다.
 * 
 * 보통의 클래스들
 * class A {}
 * class B {}
 * 
 * 내부 클래스는 두 클래스가 긴밀하고, 내부 클래스는 잘 사용되지 않는 것.
 * 
 * class A {
 * 		class B {
 * 
 * 		}
 * }
 * 
 * 종류
 * 스태틱 클래스 : 외부 클래스의 멤버 변수 위치에 선언.
 * 					외부 클래스의 스태틱 멤버처럼 다뤄진다.
 * 					주로 외부 클래스의 스태틱 멤버, 특히 스태틱 메소드에서 사용될 목적으로 선언.
 * 
 * 멤버 클래스 : 외부 클래스의 멤버 변수 위치에 선언.
 * 					외부 클래스의 인스턴스 멤버처럼 사용한다.
 * 					주로 외부 클래스의 인스턴스 멤버들과 관련된 작업에 활용된다.
 * 
 * 지역 클래스 : 외부 클래스의 메소드나 초기화 블럭 안에서 선언한다.
 * 					선언된 영역 내부에서만 사용 가능하다.
 * 
 * 익명 클래스 : 클래스 선언과 객체 생성을 동시에 하는 이름이 없는 클래스(일회용)
 * 
 * 
 * 
 * 
 */

class A {
	public A() {
		System.out.println("A클래스가 생성됨.");
	}
	
	class B {//인스턴스 멤버 클래스
		int field1;
		public B() {
			//static int field2; // static 못 붙여요.
			System.out.println("B객체가 생성됨.");
		}
		public void methodB() {}
		//public static void methodB() {} // static 안돼.
	}// class B
	
	static class C { // 정적 멤버 클래스
		int field1;
		static int field2;
		public C() {System.out.println("C객체가 만들어짐");}
		public void methodC() {}
		public static void methodC2() {}
	}// class C
	
	public void method() {//A의 메소드()
		//로컬 영역
		int num = 10;//지역 변수
		class D {//지역 클래스
			int field1;
			//static int field2;
			public D() {
				System.out.println("D 객체가 생성됨.");
			}
			public void methodD() {}
			//public static void methodD2() {}
		}//class D
		D d = new D(); //인스턴스 생성
		d.field1 = 3;
		d.methodD();
	}//method
}// class A




public class Inner01 {
	public static void main(String[] args) {
		
		//A의 객체 a가 만들어져야 B의 객체를 만들 수 있다.
		A a = new A();
		A.B b = a.new B();
		b.field1 = 1;
		b.methodB();
		
		//C는 static 멤버 클래스
		A.C c = new A.C();
		c.field1 = 1;
		c.methodC();
		A.C.methodC2(); //static method
		
		a.method();
		
		
	}
}
