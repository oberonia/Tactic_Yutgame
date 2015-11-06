package swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class MakeThisZero extends JFrame {
	String text [] = {"+2", "-1", "%4"};
	JLabel la = new JLabel();
	JButton btn [] = new JButton [text.length];
	
	MakeThisZero() {
		super("계산해서 0으로 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		// 정수 레이블 생성
		int num= (int)(Math.random()*60 + 1); // 1에서 60 사이의 임의의 정수  
		la.setText(Integer.toString(num)); // 정수를 문자열로 만들어 레이블에 출력
		la.setFont(new Font("Gothic", Font.ITALIC, 20));
		JPanel p = new JPanel();
		c.add(p, BorderLayout.CENTER);
		p.add(la);
		
		JPanel q = new JPanel();
		c.add(q, BorderLayout.SOUTH);
		// 3 개의 버튼 생성
		MyActionListener listener = new MyActionListener(); // Action 리스너 객체 생성
		for(int i=0; i<text.length; i++) {
			btn[i] = new JButton(text[i]);
			btn[i].addActionListener(listener); // 리스너 달기
			q.add(btn[i]); // 컨텐트팬에 버튼 달기
		}
		setSize(250,150);
		setVisible(true);
	}
	
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MakeThisZero.this.setTitle("계산해서 0으로 만들기"); // 타이틀 바 초기화
			
			int n = Integer.parseInt(la.getText()); // 현재 정수를 알아냄
			switch(e.getActionCommand()) {
			case "+2":
				n += 2; // 2 증가
				btn[0].setEnabled(false); // 버튼 비활성화시켜 다시 클릭되지 않게 함
				break;
			case "-1":
				n--; // 1 증가
				btn[1].setEnabled(false); // 버튼 비활성화시켜 다시 클릭되지 않게 함
				break;
			case "%4":
				n %= 4; // 4로 나눈 나머지 계산
				btn[2].setEnabled(false); // 버튼 비활성화시켜 다시 클릭되지 않게 함				
				break;
			}
			la.setText(Integer.toString(n)); // 정수를 문자열로 만들어 레이블에 출력
			if(n == 0) {
				MakeThisZero.this.setTitle("성공! 다음문제");
				System.out.println("성공");
				for(int i=0; i<text.length; i++) {
					btn[i].setEnabled(true);
				}
				int num= (int)(Math.random()*60 + 1); // 1에서 60 사이의 임의의 정수  
				la.setText(Integer.toString(num)); // 정수를 문자열로 만들어 레이블에 출력
			}
			else {
				if(btn[0].isEnabled() == false &&
  				   btn[1].isEnabled() == false &&
				   btn[2].isEnabled() == false) setTitle("실패");
			}			
		}
	}
	
	public static void main(String[] args) {
		new MakeThisZero();
	}
}

