import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
// 다른 클래스에서 사용할 수 있도록 메소드화 
public class sw_SelectMoveMethod extends JFrame {
	//JLabel 
	JRadioButton mv[] = new JRadioButton[5];
	{
		for(int i=0;i<mv.length;i++) {
			mv[i] = new JRadioButton(Yut.YutName[i]);
		}
	}
	JButton confirm = new JButton("확인");
	//int a= Player.mv[0]; 플레이어가 가지고 있는 Move를 저장하는 변수


	public sw_SelectMoveMethod(JPanel c) {

		// Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel pRadio = new JPanel();
		JPanel pButton = new JPanel();
		ButtonGroup gr = new ButtonGroup();
		for(JRadioButton i : mv) {
			gr.add(i);
		}
		pRadio.setLayout(new GridLayout(1,5));
		pRadio.setLocation(20, 70);
		for(JRadioButton i : mv) {
			pRadio.add(i);
		}
		c.add(pRadio,BorderLayout.CENTER);

		confirm.setSize(60, 20);
		confirm.setLocation(170, 140);
		pButton.add(confirm);
		c.add(pButton,BorderLayout.SOUTH);

	}

}
