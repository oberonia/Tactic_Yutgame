import java.awt.*;
import javax.swing.*;

public class sw_YutInfo extends JFrame{

	sw_YutInfo() {
		setTitle("여기는 윷 던지고 결과를 알려줄꺼임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		
		contentPane.setLayout(new BorderLayout(10,10));
		
		// center 에는 윷 던진 결과 이미지를
		
		// wsest 에는 던질 윷 앞/뒤 선택 버튼 
		JPanel frontBack = new JPanel();
		frontBack.setLayout(new GridLayout(1, 2));
		JButton front = new JButton("Front");
		JButton back = new JButton("Back");
		front.setPreferredSize(getMinimumSize());	// 어쩐지 아무일도 하지 않는 코드1
		back.setSize(getWidth(), 200);	// 어쩐지 아무일도 하지 않는 코드2

		frontBack.add(front);
		frontBack.add(back);
		
		contentPane.add(frontBack, BorderLayout.WEST);
		
		// south 에는 현재 윷 스택(도개걸윷모 각각)을 보여준다. 
		JPanel yutstack = new JPanel();
		new sw_SelectMove("이동할 칸 수를 고르세요.", yutstack);
		contentPane.add(yutstack, BorderLayout.SOUTH);
		
		setSize(800, 400);
		setVisible(true); // show frame
	}
	
	
	public static void main(String[] args) {
		new sw_YutInfo();
	}

}
