package com.poseidon.coll;

import java.util.LinkedList;
import java.util.Queue;

class Message {
	public String command;
	public String to;
	
	public Message(String command, String to) {
		this.command = command;
		this.to = to;
	}
}

public class Queue01 {
	public static void main(String[] args) {
		
		//Queue<Integer> queue = new LinkedList<Integer>();
		Queue<Message> messageQueue = new LinkedList<Message>();
		
		messageQueue.offer(new Message("sendMail", "부부"));
		messageQueue.offer(new Message("sendSMS", "바바"));
		messageQueue.offer(new Message("sendKakaotalk", "베베"));
		
		while(!messageQueue.isEmpty()) {
			Message msg = messageQueue.poll();
			System.out.println(msg.command + " : " + msg.to);
		}
		
	}
}
