package swing;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class XylophoneButton extends JFrame {
	XylophoneButton() {
		super("Ten Color Buttons Frame");
		Color[] color={Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,
				Color.CYAN,Color.BLUE,Color.MAGENTA,Color.GRAY,
				Color.PINK,Color.LIGHT_GRAY};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		// 1x10의 GridLayout 배치관리자
		contentPane.setLayout(new GridLayout(1, 10));
		for(int i=0; i<10; i++) { // 10개의 버튼 부착
			JButton button=new JButton(Integer.toString(i));
			button.setOpaque(true);
			button.setBackground(color[i]);
			contentPane.add(button);
		}
		setSize(500, 200);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new XylophoneButton();
		}


}

