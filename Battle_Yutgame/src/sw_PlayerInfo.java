import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class sw_PlayerInfo extends JFrame{

	public sw_PlayerInfo(Player[] mp, JPanel cPane) { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Container cPane = getContentPane();
		cPane.setBackground(Color.cyan);
		cPane.setLayout(new GridLayout(9,5));
		
		cPane.add(new JLabel("Team"));
		cPane.add(new JLabel("Player Name"));
		cPane.add(new JLabel("Mal Icon"));
		cPane.add(new JLabel("Mal Number"));
		cPane.add(new JLabel("Location"));
		
		for(int i =0; i<mp.length; i++){

			String team = mp[i].team;
			String name = mp[i].name;
			String malIcon = mp[i].malIcon;
			String mal1Location = Integer.toString(mp[i].mal[0].location); // JLabel은 String 타입만 취급함 
			String mal2Location = Integer.toString(mp[i].mal[1].location);
			
			cPane.add(new JLabel(team));
			cPane.add(new JLabel(name));
			cPane.add(new JLabel(malIcon));
			cPane.add(new JLabel("Mal1"));
			cPane.add(new JLabel(mal1Location));
			cPane.add(new JLabel(team));
			cPane.add(new JLabel(name));
			cPane.add(new JLabel(malIcon));
			cPane.add(new JLabel("Mal2"));
			cPane.add(new JLabel(mal2Location));

		}
		
		// 만약 플레이어가 4명이 아닌 경우, 나머지 칸은 모두 빈칸으로 표시 
		for(int j = mp.length;j<4;j++){
			for(int x = 0;x<10;x++){
				cPane.add(new JLabel(""));
			}
		}

		setSize(450, 600);
		setVisible(true);
	}

}
