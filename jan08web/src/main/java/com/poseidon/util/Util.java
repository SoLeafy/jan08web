package com.poseidon.util;

import javax.servlet.http.HttpServletRequest;

public class Util {
	//String 값이 들어오면 int타입인지 확인해보는 메소드 (모두 int로 확인되면 참)
	// 127 -> true
	// 1A2A5 -> false
	
	public static int str2Int(String str) {
		//A59 -> 59
		//5A9 -> 59
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i<str.length(); i++) {
			if(Character.isDigit(str.charAt(i))) {
				sb.append(str.charAt(i));
			}
		}
		
		int number = 0;
		if(sb.length() > 0) {
			number = Integer.parseInt(sb.toString());
		}
		//System.out.println("변환된 숫자 " + number);
		return number;
	}

	public static int str2Int2(String str) {
		//A59 -> 59
		//5A9 -> 59
		//정규표현식 굿~
		String numberOnly = str.replaceAll("[^0-9]", "");
	    return Integer.parseInt(numberOnly);
	}
	
	public static boolean intCheck(String str) {
		//하나 하나 꺼내는 것도 좋은 방법이지만, 업계에서 자주 쓰는 방법.
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean intCheck2(String str) {
		//하나 하나 꺼내는 것도 좋은 방법이지만, 하나라도 문자일 경우 틀려야 한다. 
		boolean result = true;
		for(int i = 0; i < str.length(); i++) {
			if(!Character.isDigit(str.charAt(i))) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	//ip가져오기
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARED-FOR");
		if(ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");   
		}
		if(ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if(ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if(ip == null) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
	
	//HTML태그를 특수기호로 변경하기	<는 &lt	>는 &gt
	public static String removeTag(String str) {
		str = str.replaceAll("<", "&lt");
		str = str.replaceAll(">", "&gt");
		return str; 
	}

	//이게 잘못됐다..?
	public static String addBr(String str) {
		str = str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		return str;
	}
	//아이피 0을 하트로 바꾸는 메소드 <- 이게 아니었다 ㅎ
	public static String zeroToHeart(String str) {
		char[] charArr = str.toCharArray();
		charArr[5] = '♡';
		str = String.valueOf(charArr);
		return str;
	}
	
	//아이피 중간을 ♡로 가리기 172.30.1.27 -> 172.♡.1.27
	public static String ipMasking(String ip) {
		if(ip.indexOf('.') != -1) { //ip가 아닐 때를 대비
			StringBuffer sb = new StringBuffer();//멀티스레드 환경에서도 동기화 지원
			sb.append(ip.substring(0, ip.indexOf('.')+1));
			sb.append("♡");
			sb.append(ip.substring(ip.indexOf('.', ip.indexOf('.')+1)));
			return sb.toString();
		} else {
			return ip;
		}
	}
	
}
