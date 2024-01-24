package com.poseidon.io;

import java.io.FileInputStream;
import java.io.InputStream;

public class IO03 {
	public static void main(String[] args) throws Exception {

		InputStream is = new FileInputStream("c:/temp/test.txt");

		while (true) {
			int data = is.read(); // read도 배열로 읽어들임
			if (data == -1) {
				break;
			}
			System.out.println((char) data);
		}
		is.close();
	}
}
