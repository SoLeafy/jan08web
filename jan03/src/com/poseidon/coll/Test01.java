package com.poseidon.coll;

import java.util.HashMap;

public class Test01 {
	public static void main(String[] args) {
		HashMap <String, String> map = new HashMap<String, String>();

		map.put("수원", "왕갈비통닭");
		map.put("제주", "갈치");
		map.put("서울", "마카롱");
		map.put("함흥", "냉면"); //값 추가

		System.out.println(map); //전체출력
	}
}
