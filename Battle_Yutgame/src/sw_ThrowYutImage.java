import javax.swing.*;

import java.awt.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class sw_ThrowYutImage extends JFrame{
	sw_ThrowYutImage() {
		super("윷 던지기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cpane = getContentPane(); 
		setSize(700,700);
		setResizable(false);
		cpane.setLayout(new BorderLayout(0,50));
		JPanel Field = new JPanel();
		Field.setSize(500,400);
		Field.setLayout(null);
		Field.setBackground(Color.darkGray);
		try {
			roll(Field);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		cpane.add(Field,BorderLayout.CENTER);
		
		JPanel Text = new JPanel();
		Text.setLayout(null);
		Text.setSize(500,200);
		Text.setBackground(Color.LIGHT_GRAY);
		cpane.add(Text,BorderLayout.SOUTH);
		
		setVisible(true);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/2-getWidth()/2, screenSize.height/2-getHeight()/2);
	}
	void roll(JPanel Field) throws IOException {
		ImageIcon front = new ImageIcon("image/Yutfront.png");
		ImageIcon back = new ImageIcon("image/Yutback.png");
		int result = (int) (Math.random()*4); //나중에는 다른곳에서 받아올 변수
		JLabel[] Yut = new JLabel[5];
		
		for(int i=0;i<result;i++) {
			Yut[i] = new JLabel(front);
			Yut[i].setSize(front.getIconWidth(), front.getIconHeight());
			//나온 윷 만큼 이미지 배열에 front를 할당
		}
		for(int i=result;i<5;i++) {
			Yut[i] = new JLabel(back);
			Yut[i].setSize(back.getIconWidth(), back.getIconHeight());
			//나머지 레이블에 back을 할당
		}
		for(int i=0;i<5;i++) //전체 레이블을 랜덤한 위치에 랜덤하게 회전시킴
		{
			int x=(int)(Math.random()*(Field.getWidth()));
			int y=(int)(Math.random()*(Field.getHeight()));
			Yut[i].setLocation(x, y);
			//Yut[i].setOpaque(true);
			Field.add(Yut[i]);
		}
	}
	public static void main(String[] args) {
		new sw_ThrowYutImage();
	}

}
