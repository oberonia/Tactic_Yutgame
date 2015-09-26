
import java.util.Scanner;

public class MainFrame {
	static Player mp[] = new Player[4];
	static ThrowingYut throwingPhase = new ThrowingYut();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t1 = 0; // 팀1 인원수를 저장
		int t2 = 0; // 팀2 인원수를 저장
		String TeamString1, TeamString2;

		System.out.println("윷놀이 게임 시작(프로토타입)");
		System.out.print("첫번째 팀 이름을 정하세요>>");
		TeamString1 = new String(sc.next());
		while(true) {
			System.out.print("두번째 팀 이름을 정하세요>>");
			TeamString2 = new String(sc.next());
			if(TeamString1.equals(TeamString2)) {
				System.out.println("팀 이름은 서로 같을 수 없습니다.다시 입력하세요.");
				continue;
			}
			else break;
		}

		for(int i=0;i<4;i++){
			System.out.println("========"+(i+1)+"번 플레이어 정보 입력========");
			mp[i]= new Player(t1,t2,TeamString1,TeamString2);
			if(mp[i].getTeam().equals(TeamString1)) t1++;
			else if(mp[i].getTeam().equals(TeamString2)) ++t2;
			System.out.println("플레이어 이름 확인 : " + mp[i].getName() + ", Player " + i);
			System.out.println("팀 이름 확인 : " + mp[i].getTeam() + ", Player " + i);
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
