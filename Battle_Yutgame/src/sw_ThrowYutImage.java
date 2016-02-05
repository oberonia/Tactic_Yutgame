import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class sw_ThrowYutImage extends JFrame{
	sw_ThrowYutImage() {
		super("윷 던지기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cpane = getContentPane(); 
		setSize(500,500);
		setResizable(false);
		cpane.setLayout(new BorderLayout(0,50));
		JPanel Field = new JPanel();
		Field.setSize(500,400);
		Field.setLayout(null);
		Field.setBackground(Color.darkGray);
				
		for(int i=0;i<5;i++)
		{
			ImageIcon front = new ImageIcon("image/Yutfront.png");
			ImageIcon back = new ImageIcon("image/Yutback.png");
			
			
			JLabel label=new JLabel();
			int x=(int)(Math.random()*Field.getWidth());
			int y=(int)(Math.random()*Field.getHeight());
			label.setLocation(x, y);
			label.setSize(10,10);
			label.setOpaque(true);
			label.setBackground(Color.BLUE);
			Field.add(label);
		}
		cpane.add(Field,BorderLayout.CENTER);
		
		JPanel Text = new JPanel();
		Text.setLayout(null);
		Text.setSize(500,200);
		Text.setBackground(Color.LIGHT_GRAY);
		cpane.add(Text,BorderLayout.SOUTH);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		new sw_ThrowYutImage();
	}

}
