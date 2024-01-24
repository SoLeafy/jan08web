package com.poseidon.io;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//p640꺼
//파일 클래스를 이용한 파일 및 폴더 정보 출력
public class IO06 {
	public static void main(String[] args) {
		File dir = new File("c:/temp/images");
		
		if(dir.exists() == false) {
			dir.mkdirs(); //temp는 있으니까 images만 만들게 mkidr만 해도 된다.
		}
		
		File temp = new File("c:/temp");
		File contents[] = temp.listFiles();
		
		System.out.println("시간\t\t\t형태\t\t크기\t이름");
		System.out.println("============================================================");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		for (File file : contents) {
			System.out.print(sdf.format(new Date(file.lastModified())));
			if(file.isDirectory()) {
				System.out.print("\t<DIR>\t\t\t" + file.getName());
			} else {
				System.out.print("\t\t\t" + file.length() + "\t" + file.getName());
			}
			System.out.println();
		}
		
		
	}
}
