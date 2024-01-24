package com.poseidon.thread;

public class Thread02 extends Thread {
	int seq; //자기 변수?로 int값 갖고 있다?
	
	public Thread02(int seq) {
		this.seq = seq;
	}
	
	@Override
	public void run() {
		System.out.println(this.seq + "스레드 시작");
		try { // 못 깨어날 수도 있어서?
			Thread.sleep(1000); // 1초 = 1000 (millisecond 단위라서)
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.seq + "스레드 끝");
	}
	
	
	public static void main(String[] args) {
		System.out.println("main 메소드 시작");
		
		for (int i = 0; i < 10; i++) {
			Thread02 th02 = new Thread02(i);
			th02.start(); // 이래야 동작한다. 10개 인스턴스가 생기고 각각 sleep에 걸릴 것. 어떤 모양이 나올지 보자.
		}
		
		System.out.println("main 메소드 끝");
	}
}
