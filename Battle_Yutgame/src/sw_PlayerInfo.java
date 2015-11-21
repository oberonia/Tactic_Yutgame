import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class sw_PlayerInfo extends JFrame{

	public sw_PlayerInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container cPane = getContentPane();
		cPane.setBackground(Color.orange);
		cPane.setLayout(new GridLayout(9,5));
		
		cPane.add(new JLabel("Team"));
		for(int i =0; i<4; i++){
			String t1 = Player.getTeamName1();
			String t2 = Player.getTeamName2();
			cPane.add(new JButton(t1));
			cPane.add(new JLabel(t2));

			// 이름을 플레이어 정보인 mp에서 끌어와야 하는데 어떻게 하지?
//			String n1 = getName(Player, i);
	//		cPane.add(new JLabel(n1));
		}
		cPane.add(new JLabel("Player Name"));
		
		cPane.add(new JLabel("Mal Icon"));
		cPane.add(new JLabel("Mal Number"));
		cPane.add(new JLabel("Lacation"));

		
		setSize(500, 900);
		setVisible(true);
	}
	public static void main(String[] args) {
		new sw_PlayerInfo();
	}

}
