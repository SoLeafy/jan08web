package com.poseidon.io;
// input output = io
// p592

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IO01 {
	public static void main(String[] args) throws IOException {
		
		OutputStream os = new FileOutputStream("c:/temp/test.txt"); //백슬래시는 "" 안에서 두번씩 써야하니까 걍슬래시
		byte a = 65;
		byte b = 67;
		byte c = 68;
		
		os.write(a);
		os.write(b);
		os.write(c);
		
		os.flush(); //delay된 애들 싹 다 보내??
		os.close();
		
	}
}
