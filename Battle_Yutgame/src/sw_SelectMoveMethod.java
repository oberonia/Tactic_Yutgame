import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class sw_SelectMoveMethod extends JFrame {
	//JLabel 
	JRadioButton mv[] = new JRadioButton[5];
	{
		for(int i=0;i<mv.length;i++) {
			mv[i] = new JRadioButton();
		}
	}
	JButton confirm = new JButton("확인");
	//int a= Player.mv[0]; 플레이어가 가지고 있는 Move를 저장하는 변수


	public sw_SelectMoveMethod(String title, JPanel c) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

		//도.setLocation(HEIGHT, WIDTH);



//		setSize(400,200);
//		setVisible(true);
//		setResizable(false);
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		setLocation(screenSize.width/2-getWidth()/2, screenSize.height/2-getHeight()/2);

	}
	/*
	public static void main(String[] args) {
		new sw_SelectMove("이동할 칸 수를 고르세요.");
	}
	*/

}
