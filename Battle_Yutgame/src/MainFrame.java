
import java.util.Scanner;

public class MainFrame {

	static Player mp[] = new Player[4];
	static ThrowingYut throwingPhase = new ThrowingYut();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t1 = 0; // 팀1 인원수를 저장
		int t2 = 0; // 팀2 인원수를 저장

		System.out.println("윷놀이 게임 시작(프로토타입)");

		for(int i=0;i<4;i++){

			mp[i]= new Player(t1,t2);
			if(mp[i].getTeam().equals("blue")) t1++;
			else if(mp[i].getTeam().equals("white")) ++t2;
			System.out.println("From now on, your name is " + mp[i].getName() + ", Player " + i);
			System.out.println("Now your on " + mp[i].getTeam() + " team, Player " + i);
			//throwingPhase.throwing(i);
			//throwingPhase.result(i);
		}//end of for loop

		scoreboard();

	}

	static void scoreboard(){
		System.out.println("Name\t"+"Team\t"+"Mal No.\t"+"Location");
		for(int j=0; j<mp.length; j++){
			System.out.println(mp[j].getName()+"\t"+mp[j].getTeam()+"\t"+"mal1\t"+mp[j].getMal1());
			System.out.println(mp[j].getName()+"\t"+mp[j].getTeam()+"\t"+"mal2\t"+mp[j].getMal2());
		}
		
	}

}
