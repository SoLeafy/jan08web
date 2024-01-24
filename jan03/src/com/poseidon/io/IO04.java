package com.poseidon.io;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class IO04 {
	public static void main(String[] args) throws Exception {

		InputStream is = new FileInputStream("c:/temp/test.txt");
		
		byte arr[] = new byte[2]; // 100칸이라 test.txt 안에 있는 3개만 들어 있다.
		// 2칸 만들면 2번 돈다.

		while (true) {
			int readByteNum = is.read(arr);
			if (readByteNum == -1) { // 더 이상 읽을 값이 없을 때 -1
				break;
			}
//			for (int i = 0; i < arr.length; i++) {
//				System.out.print((char) arr[i]);
//			}
			//시러핑꺼
			for(int i =0; i< arr.length;i++) {
	            if(readByteNum==1) {
	               System.out.print((char)arr[i]);
	               break;
	            }else {
	               System.out.print((char)arr[i]);
	            }
	         }
			
			//티비무지님꺼
//			while (true) {
//			      int readByteNum = is.read(arr);
//			      // .read() : 읽은 메소드 개수 반환
//			      if (readByteNum == -1) {
//			        break;
//			      } else if (readByteNum < arr.length) {
//			        for (int i = 0; i < arr.length - readByteNum; i++) {
//			          System.out.println((char) arr[i]);
//			        }
//			      } else {
//			        for (int i = 0; i < arr.length; i++) {
//			          System.out.println((char) arr[i]);
//			        }
//			      }
//			System.out.println(readByteNum + " : " + Arrays.toString(arr));
		}
		is.close();
	}
}
