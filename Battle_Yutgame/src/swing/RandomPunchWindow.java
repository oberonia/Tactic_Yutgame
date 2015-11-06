package swing;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class RandomPunchWindow extends JFrame{
	RandomPunchWindow() {
		super("Random Labels");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cpane = getContentPane(); 
		setSize(300,300);
		cpane.setLayout(null);
		for(int i=0;i<20;i++)
		{
			JLabel label=new JLabel();
			int x=(int)(Math.random()*200)+50;
			int y=(int)(Math.random()*200)+50;
			label.setLocation(x, y);
			label.setSize(10,10);
			label.setOpaque(true);
			label.setBackground(Color.BLUE);
			cpane.add(label);
		}
		setVisible(true);
	}
	public static void main(String[] args) {
		new RandomPunchWindow();
	}

}
