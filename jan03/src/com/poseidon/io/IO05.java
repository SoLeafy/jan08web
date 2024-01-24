package com.poseidon.io;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class IO05 {
	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("c:/temp/test4.txt");
		Writer writer = new OutputStreamWriter(fos);
		
		//아까처럼 한글자 한글자가 아니라 통으로 던지는 것! (아까는 스트림 설명일뿐)
		writer.write("문자열을 저장합니다.");
		writer.flush();//전송
		writer.close();
		
	}
}
