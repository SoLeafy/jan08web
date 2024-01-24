package com.poseidon.team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
	public List<List<String>> getTeam() {
		List<List<String>> team = new ArrayList<List<String>>();
		String temp[] = new String[] {
			"강기병", "김지훈", "나우석", "노재희", "박대종",
			"박선우", "박시호", "배현배", "백건하", "손현석",
			"신동일", "신유진", "신진수", "오상민", "오초록",
			"이남규", "이문희", "이민우", "이수현", "이지은",
			"이진선", "정은숙", "정진수", "정효진", "채영선",
			"한솔범"
		};
		
		List<String> members = new ArrayList<String>();
		for (String str : temp) {
			members.add(str);
		}
		//저장할 변수
		List<String> select = new ArrayList<String>();
		
		Collections.shuffle(members); //?
		Collections.shuffle(members); //?
		Collections.shuffle(members); //?
		Collections.shuffle(members); //?
		Collections.shuffle(members); //?
		for (int i = 0; i < members.size() / 5; i++) {
//			String teamMem = "";
			select = new ArrayList<String>();
			if(i != 4) {
				select = members.subList(i*5,  i*5 + 5);
			} else {
				select = members.subList(i*5,  members.size());
			}
			Collections.sort(select);
			team.add(select);
		}
		
		return team;
	}
	
}
