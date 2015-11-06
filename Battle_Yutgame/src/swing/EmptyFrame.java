package swing;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class EmptyFrame extends JFrame {
	public EmptyFrame(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new EmptyFrame("Let's study Java");
	}

}
