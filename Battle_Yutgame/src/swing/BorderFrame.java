package swing;

import java.awt.*;

import javax.swing.*;
@SuppressWarnings("serial")
public class BorderFrame extends JFrame {
	BorderFrame() {
		setTitle("BorderLayout Practice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cpane = getContentPane();
		cpane.setLayout(new BorderLayout(5,7));
		cpane.add(new JButton("North"),BorderLayout.NORTH);
		cpane.add(new JButton("West"),BorderLayout.WEST);
		cpane.add(new JButton("East"),BorderLayout.EAST);
		cpane.add(new JButton("South"),BorderLayout.SOUTH);
		cpane.add(new JButton("Center"),BorderLayout.CENTER);
		setSize(400,200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new BorderFrame();
	}

}

