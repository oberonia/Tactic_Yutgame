import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class sw_YutBoardImage extends JFrame {
	public sw_YutBoardImage(JPanel c) {
	//	super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(new BorderLayout());
		
		c.setSize(500,500);
		//setResizable(false);
		
		ImageIcon image = new ImageIcon("image/YutBoard.jpg");
		JLabel bg = new JLabel(image);
		// Container c = getContentPane();
		c.add(bg);
//		setVisible(true);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/2-getWidth()/2, screenSize.height/2-getHeight()/2);
	}
	/*
	public static void main(String[] args) {
		new sw_YutBoardImage("윷판");
	}
	*/

}
