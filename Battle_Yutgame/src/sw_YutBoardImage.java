import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class sw_YutBoardImage extends JFrame {
	public sw_YutBoardImage(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
		setResizable(false);
		
		ImageIcon image = new ImageIcon("image/YutBoard.jpg");
		JLabel bg = new JLabel(image);
		Container c = getContentPane();
		c.add(bg);
	}
	public static void main(String[] args) {
		new sw_YutBoardImage("윷판");
	}

}
