package swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class MouseEnterEvent extends JFrame {
	public MouseEnterEvent() {
		super("마우스 올리기 내리기 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
				
		JLabel label = new JLabel("노오오력이");
		label.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JLabel la = (JLabel)e.getSource();
				la.setText("필요하다..");
			}
			public void mouseExited(MouseEvent e) {
				JLabel la = (JLabel)e.getSource();
				la.setText("사실은 죽창이");
			}			
		});
		c.add(label);
		setSize(250,150);
		setVisible(true);
	}
	
	static public void main(String [] args) {
		new MouseEnterEvent();
	}
}
