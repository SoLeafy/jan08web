package com.poseidon.thread;
// 2024-01-03 웹 서버 프로그램 구현
// Thread (p520)
// Thread, IO, Net, GUI
// 프로젝트 제작, (자바, DB)

/*
 * 프로세스	: 운영체제에서는 실행 중인 하나의 어플리케이션을 프로세스라고 한다.
 * 			  사용자가 애플리케이션을 실행하면 운영체제로부터 실행에 필요한 메모리를 할당받아
 * 			  애플리케이션의 코드를 실행하는 것이 프로세스다.
 * 
 * 스레드	: 운영체제는 두 가지 이상의 작업을 동시에 처리하는 멀티 태스킹을 할 수 있도록 CPU 및
 * 			  메모리 자원을 프로세스마다 적절히 할당해주고, 병렬로 실행시킨다.
 * 
 * 스레드 생성과 실행
 * 		1. Runnable 인터페이스를 구현하는 방법 (구현한다)
 * 		2. Thread 클래스를 상속받는 방법 (확장한다)
 * 왜 두 개?
 * 다른 클래스가 상속 중이라면 Thread 클래스를 상속 받지 못한다. 이럴 때를 대비해서 인터페이스 Runnable이
 * 만들어져 있다. (대부분의 경우 Thread 클래스를 상속해서 제작하는 것이 편하다)
 * 
 * 실행은 start()로 실행
 * 
 * p524 스레드 시작??
 * 
 */
public class ThreadEx extends Thread {
	@Override
	public void run() {
		//super.run();
		System.out.println("Thread 시작");
	}
	public static void main(String[] args) {
		ThreadEx th01 = new ThreadEx();
		th01.start();
	}
}
