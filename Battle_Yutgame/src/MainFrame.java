
public class MainFrame {

	/****디버그 모드로 실행할 때는 true로 입력해주세요**********/
	static boolean DebugMode = true;
	/*****디버그 모드가 아닐때는 false로 입력해주세요**********/

	static Player mp[]; 
	ThrowingYut throwingPhase = new ThrowingYut();

	public static void main(String[] args) {
		int t1 = 0; // 팀1 인원수를 저장
		int t2 = 0; // 팀2 인원수를 저장

		System.out.println("윷놀이 게임 시작(프로토타입)");

		if(DebugMode) mp = Debug.Player();
		else { 
			mp = new Player[4];
			mp = Action.InitPlayers(mp); //플레이어 전원의 이름과 팀을 입력. 전체 프로그램 내에서 한번만 실행할 것
		}


		Action.scoreboard(mp);
		for(int i=0;;i=(i+1)%4)
		{
			mp[i].ThrowYut();
			Action.scoreboard(mp);
			if(mp[i].getMal1()==777||mp[i].getMal2()==777) {
				System.out.println(mp[i].getTeam()+" 팀의 "+mp[i].getName()+" 플레이어가 E김.");
				break;
			}
		}
	}
}
