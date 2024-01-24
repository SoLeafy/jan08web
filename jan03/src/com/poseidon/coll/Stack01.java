package com.poseidon.coll;

import java.util.Stack;

/*
 * 선입선출 First In First Out	"먼저 넣은 객체가 먼저 빠져나가는 구조"
 * 후입선출 Last In First Out	"나중에 넣은 객체가 먼저 빠져나가는 구조"
 * 
 * 
 */

class Coin {
	private int value;
	
	public Coin(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}

public class Stack01 {
	public static void main(String[] args) {
		Stack<Coin> coinBox = new Stack<Coin>();
		
		coinBox.push(new Coin(100));
		coinBox.push(new Coin(50));
		coinBox.push(new Coin(500));
		coinBox.push(new Coin(10));
		
		while(!coinBox.isEmpty()) {
			Coin coin = coinBox.pop();
			System.out.println("꺼내온 동전: " + coin.getValue() + "원");
		}
		
	}
}
