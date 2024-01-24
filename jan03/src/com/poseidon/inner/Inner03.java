package com.poseidon.inner;

public class Inner03 {
	public static void main(String[] args) {
		
		Button button = new Button();
		
		button.setOnClickListener(new Inner02());
		button.touch();
		
	}
}
