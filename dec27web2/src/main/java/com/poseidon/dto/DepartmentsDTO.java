package com.poseidon.dto;
//데이터 전송 객체
public class DepartmentsDTO {
	//private 걸린 변수들
	private String dept_no, dept_name;

	//getter setter
	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	
}
