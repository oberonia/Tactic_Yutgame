package swing;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Colored4x4Plate extends JFrame {
	public Colored4x4Plate() {
		super("4x4 Color Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new GridLayout(4, 4));
		
		JLabel [] label = new JLabel [16];
		Color [] color = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
				Color.CYAN, Color.BLUE, Color.MAGENTA, Color.GRAY,
				Color.PINK, Color.LIGHT_GRAY, Color.WHITE, Color.DARK_GRAY,
				Color.BLACK, Color.ORANGE, Color.BLUE,Color.MAGENTA}; 

		for(int i=0; i<label.length; i++) {
			label[i] = new JLabel(Integer.toString(i));
			label[i].setOpaque(true);
			label[i].setBackground(color[i]);
			c.add(label[i]);
		}
		setSize(500,200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Colored4x4Plate();
	}

}

