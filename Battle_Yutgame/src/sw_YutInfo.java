import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class sw_YutInfo extends JFrame{

	public static JTextField text;
	public static JTextArea systemTextArea;
	
	static void a() {
		text.addActionListener(new ActionListener() {
			// enter 키를 입력받으면 작동하는 리스너 
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource();
				systemTextArea.append(t.getText()+"\n");	// text에 있는 내용을 systemText에 복사하고 줄바꿈 삽입
				Action.inputPlayerTyped(t.getText());
				t.setText("");	// text를 비움 

			}});
	}
	sw_YutInfo() {
		setTitle("여기는 윷 던지고 결과를 알려줄꺼임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();

		contentPane.setLayout(new BorderLayout(10,10));


		// west 윷판 배치
		JPanel score = new JPanel();
		new sw_YutBoardImage(score);
		contentPane.add(score, BorderLayout.WEST);


		// center 에는 윷 던진 결과 이미지를 넣는다. 

		// east 에는 던질 윷 앞/뒤 선택 버튼 
		JPanel frontback = new JPanel();	// 앞,뒤 버튼이 들어갈 자리를 만든다. 
		frontback.setLayout(new BorderLayout(4, 10));
		JButton front = new JButton("Front");
		JButton back = new JButton("Back");
		frontback.add(front, BorderLayout.WEST);
		frontback.add(back, BorderLayout.EAST);
		contentPane.add(frontback, BorderLayout.EAST); // 버튼 2개가 들어간 frontback을 east 위치에 추가한다. 

		// 현재 윷 스택(도개걸윷모 각각)을 보여준다. 
		JPanel yutstack = new JPanel();
		new sw_SelectMoveMethod("이동할 칸 수를 고르세요.", yutstack);
		frontback.add(yutstack, BorderLayout.SOUTH);

		// south 에는 점수판과 시스템 텍스트를 보여준다.
		JPanel info = new JPanel();
		info.setLayout(new BorderLayout(4,4));
		systemTextArea = new JTextArea(5, 20);	// 메시지 내역 박스 생성 
		info.add(systemTextArea);	// 화면에 내역 박스 추가 
		info.add(new JScrollPane(systemTextArea));	// 내역 박스에 스크롤바 추가 

		text = new JTextField();	// 메시지 입력 영역 생성 
		info.add(text, BorderLayout.SOUTH);

		text.addActionListener(new ActionListener() {
			// enter 키를 입력받으면 작동하는 리스너 
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource();
				systemTextArea.append(t.getText()+"\n");	// text에 있는 내용을 systemText에 복사하고 줄바꿈 삽입
				Action.inputPlayerTyped(t.getText());
				Action.printSystemMsg(Action.getPlayerTyped());	// 디버깅용. 제대로 값이 들어갔는지 확인.
				t.setText("");	// text를 비움 

			}
		});

		// 점수판을 보여준다.
		JPanel playerscore = new JPanel();
		new sw_PlayerInfo(MainFrame.mp, playerscore);
		info.add(playerscore, BorderLayout.EAST);

		contentPane.add(info, BorderLayout.SOUTH);


		setSize(800, score.getHeight()+yutstack.getHeight());
		setVisible(true); // show frame
	}
}
