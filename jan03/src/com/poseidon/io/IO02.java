package com.poseidon.io;
// input output = io
// p592

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IO02 {
	public static void main(String[] args) throws IOException {
		
		OutputStream os = new FileOutputStream("c:/temp/test3.txt"); //백슬래시는 "" 안에서 두번씩 써야하니까 걍슬래시
		
		byte arr[] = new byte[] {66, 69, 72, 72, 79};
		// 1byte = bit
		os.write(arr, 1, 3); //배열 한번에 보내기
		
		os.flush(); //delay된 애들 싹 다 보내??
		os.close();
		
	}
}
