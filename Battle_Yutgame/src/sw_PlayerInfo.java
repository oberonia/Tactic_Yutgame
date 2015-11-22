import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class sw_PlayerInfo extends JFrame{

	public sw_PlayerInfo(Player[] mp) { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container cPane = getContentPane();
		cPane.setBackground(Color.cyan);
		cPane.setLayout(new GridLayout(9,5));
		
		cPane.add(new JLabel("Team"));
		for(int i =0; i<mp.length; i++){
			//String t1 = Player.getTeamName1();
			//String t2 = Player.getTeamName2();
			//cPane.add(new JLabel(t1));
			//cPane.add(new JLabel(t2));

			String team = mp[i].team;
			String name = mp[i].name;
			String malIcon = mp[i].malIcon;
			int mal1Location = mp[i].mal1;
			int mal2Location = mp[i].mal2;
			
			cPane.add(new JLabel(team));
			cPane.add(new JLabel(name));
			cPane.add(new JLabel(malIcon));

		}
		cPane.add(new JLabel("Player Name"));
		cPane.add(new JLabel("Mal Icon"));
		cPane.add(new JLabel("Mal Number"));
		cPane.add(new JLabel("Lacation"));

		
		setSize(500, 900);
		setVisible(true);
	}

}
