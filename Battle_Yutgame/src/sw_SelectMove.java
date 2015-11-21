import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class sw_SelectMove extends JFrame {
	//JLabel 
	JRadioButton 도 = new JRadioButton();
	JRadioButton 개 = new JRadioButton();
	JRadioButton 걸 = new JRadioButton();
	JRadioButton 윷 = new JRadioButton();
	JRadioButton 모 = new JRadioButton();
	JButton confirm = new JButton("확인");
	
	
	
	public sw_SelectMove(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel pRadio = new JPanel();
		JPanel pButton = new JPanel();
		ButtonGroup gr = new ButtonGroup();
		gr.add(도);
		gr.add(개);
		gr.add(걸);
		gr.add(윷);
		gr.add(모);
		pRadio.setLayout(new GridLayout(1,5));
		pRadio.setLocation(20, 70);
		pRadio.add(도);
		pRadio.add(개);
		pRadio.add(걸);
		pRadio.add(윷);
		pRadio.add(모);
		c.add(pRadio,BorderLayout.CENTER);
		
		confirm.setSize(60, 20);
		confirm.setLocation(170, 140);
		pButton.add(confirm);
		c.add(pButton,BorderLayout.SOUTH);
		
		//도.setLocation(HEIGHT, WIDTH);
		
		
		
		setSize(400,200);
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args) {
		new sw_SelectMove("이동할 칸 수를 고르세요.");
	}

}
