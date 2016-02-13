import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

@SuppressWarnings("serial")
public class sw_ThrowYutImage extends JFrame{
	String[] YutName = {"모","도","개","걸","윷"};
	Random random = new Random();
	int result; //나중에는 다른곳에서 받아올 변수
	JLabel[] Yut = new JLabel[4];
	JLabel YutText = new JLabel("윷을 던져주세요.");
	ImageIcon front = new ImageIcon("image/Yutfront.png");
	ImageIcon back = new ImageIcon("image/Yutback.png");
	JPanel Field = new JPanel();
	Container cpane = getContentPane(); 
	
	sw_ThrowYutImage() {
		super("윷 던지기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,700);
		setResizable(false);
		cpane.setLayout(new BorderLayout(0,50));
		
		Field.setSize(500,500);
		Field.setLayout(null);
		Field.setBackground(Color.darkGray);
		roll();
		
		cpane.add(Field,BorderLayout.CENTER);
		
		JPanel Text = new JPanel();
		Text.setLayout(new FlowLayout());
		Text.setSize(500,200);
		Text.setBackground(Color.LIGHT_GRAY);
		
		JButton reset = new JButton("다시 던지기");
		reset.addActionListener(new MyActionListener());
		Text.add(YutText);
		Text.add(reset);
		cpane.add(Text,BorderLayout.SOUTH);
		
		setVisible(true);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/2-getWidth()/2, screenSize.height/2-getHeight()/2);
	}
	void roll() {
		switch(random.nextInt(10)) {
		case 0:
			result = 0;
			break;
		case 1:
		case 2:
			result = 1;
			break;
		case 3:
		case 4:
		case 5:
			result = 2;
			break;
		case 6:
		case 7:
		case 8:
			result = 3;
			break;
		case 9:
			result = 4;
			break;
		default:
			System.out.println("default");
		}
		
		if(result>2) YutText.setText(YutName[result]+"이 나왔습니다.");
		else YutText.setText(YutName[result]+"가 나왔습니다.");
		
		for(int i=0;i<result;i++) {
			Yut[i] = new JLabel(front);
			Yut[i].setSize(front.getIconWidth(), front.getIconHeight());
			//나온 윷 만큼 이미지 배열에 front를 할당
		}
		for(int i=result;i<4;i++) {
			Yut[i] = new JLabel(back);
			Yut[i].setSize(back.getIconWidth(), back.getIconHeight());
			//나머지 레이블에 back을 할당
		}
		for(int i=0;i<4;i++) //전체 레이블을 랜덤한 위치에 랜덤하게 회전시킴
		{
			int x=random.nextInt(Field.getWidth()-Yut[i].getWidth());
			int y=random.nextInt(Field.getHeight()-Yut[i].getHeight());
			Yut[i].setLocation(x, y);
			Field.add(Yut[i]);
		}
	}
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Field.removeAll();
			roll();
			cpane.repaint();
		}
		
	}
	
	public static void main(String[] args) {
		new sw_ThrowYutImage();
	}

}
