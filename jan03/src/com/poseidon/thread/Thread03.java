package com.poseidon.thread;

import java.util.ArrayList;
import java.util.List;

public class Thread03 extends Thread {
	int seq;
	
	public Thread03(int seq) {
		this.seq = seq;
	}
	
	@Override //어노테이션
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
		System.out.println("main 시작");
		
		List<Thread> tList = new ArrayList<Thread>();
		
		//담기
		for (int i = 0; i < 10; i++) {
			Thread th1 = new Thread03(i); //부모, 자식 타입 (Thread 클래스 상속의 경우)
			th1.start();
			tList.add(th1);
		}
		//풀어내서 join() 걸기
		for (int i = 0; i < tList.size(); i++) {
			Thread t = tList.get(i);
			
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("main 끝");
	}

}
