package com.poseidon.mp3;

import java.io.File;

import jaco.mp3.player.MP3Player;

public class Mp3 {
	public static void main(String[] args) throws InterruptedException {
		File file = new File("c:/temp/Enchanted.mp3");
		
		MP3Player mp3Player = new MP3Player(file);
		mp3Player.play();
		
		while (!mp3Player.isStopped()) {
			Thread.sleep(5000);
		}
	}
}
