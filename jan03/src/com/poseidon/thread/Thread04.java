package com.poseidon.thread;

import java.util.ArrayList;
import java.util.List;

class A {
	
}

public class Thread04 extends A implements Runnable { // 자바는 다중 상속이 안돼서 인터페이스로
	int seq;
	
	public Thread04(int seq) {
		this.seq = seq;
	}
	
	@Override
	public void run() {
		System.out.println(this.seq + " 스레드 시작");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(this.seq + " 스레드 끝");
	}

	public static void main(String[] args) {
		List<Thread> tList = new ArrayList<Thread>();
		
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Thread04(i)); // 인터페이스다보니..
			//t의 생성자로 Runnable을 구현한 객체를 넘긴 모습
			
			t.start();
			tList.add(t);
		}
		
		for (int i = 0; i < tList.size(); i++) {
			Thread t = tList.get(i);
			
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("메인 메소드 끝.");
	}
}
