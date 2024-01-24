package com.poseidon.inner;

class Button {
	
	OnClickListener listener;
	
	void setOnClickListener(OnClickListener listener) {
		this.listener = listener;
	}
	
	void touch() {
		listener.onClick();
	}
	
	static interface OnClickListener {
		void onClick();
	}
}

public class Inner02 implements Button.OnClickListener {
	public static void main(String[] args) {
		
	}

	@Override
	public void onClick() {
		
	}
}
