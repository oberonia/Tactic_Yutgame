import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class sw_YutInfo extends JFrame{

	sw_YutInfo() {
		setTitle("여기는 윷 던지고 결과를 알려줄꺼임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		
		contentPane.setLayout(new BorderLayout(10,10));
		
		// center 에는 윷 던진 결과 이미지를 넣는다. 
		
		// east 에는 던질 윷 앞/뒤 선택 버튼 
		JPanel frontback = new JPanel();	// 앞,뒤 버튼이 들어갈 자리를 만든다. 
		frontback.setLayout(new BorderLayout(4, 20));
		JButton front = new JButton("Front");
		JButton back = new JButton("Back");
		frontback.add(front, BorderLayout.WEST);
		frontback.add(back, BorderLayout.EAST);
		contentPane.add(frontback, BorderLayout.EAST); // 버튼 2개가 들어간 frontback을 east 위치에 추가한다. 

		
		// south 에는 현재 윷 스택(도개걸윷모 각각)을 보여준다. 
		JPanel yutstack = new JPanel();
		new sw_SelectMoveMethod("이동할 칸 수를 고르세요.", yutstack);
		contentPane.add(yutstack, BorderLayout.SOUTH);
		
		setSize(800, 400);
		setVisible(true); // show frame
	}
	
	
	public static void main(String[] args) {
		new sw_YutInfo();
	}

}
