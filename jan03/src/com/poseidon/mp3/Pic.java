package com.poseidon.mp3;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Pic extends Frame implements ActionListener, ItemListener,
		Runnable {

	Image img;
	String pic = "C:\\temp\\chiikawa.jpg";

	public Pic(String str) {
		super(str);

		init();
		start();

		this.setLocation(100, 100); // 프레임 시작위치
		super.setVisible(true); // 실제로 프레임을 화면에 출력
		super.setSize(400, 400); // 프레임의 처음 크기
		super.setResizable(true); // 프레임 사이즈 조절

		img = Toolkit.getDefaultToolkit().getImage(pic);  //이미지 객체를 생성

	}

	public void init() {

	}

	public void start() {
		this.addWindowListener(new WindowAdapter() { // x를 눌렀을때 종료
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {

		Pic exam = new Pic("기본프레임");
	}



	@Override
	public void paint(Graphics g)
	{
		g.drawImage(img, 0, 0, this);
	}







	@Override
	public void run() { // 스레드 정의부분

	}

	@Override
	public void itemStateChanged(ItemEvent e) { // 체크상태 확인

	}

	@Override
	public void actionPerformed(ActionEvent e) { // 액션이벤트

	}

}
