
public class MainFrame {


	/****디버그 : 주석에 적힌 모드로 실행할 때는 true로 입력해주세요**********/
	static boolean InitDebugMode = true; //팀명과 플레이어명이 임의로 지정됨
	static boolean YutDebugMode = false; //윷 던질 값을 직접 입력가능
	static boolean AloneDebugMode = true; //플레이어가 혼자가 됨
	static boolean SplitYutMode = true; //윷을 하나씩 던진다
	static boolean EnemyCatchMode = true; //상대팀의 말을 잡을 수 있음(미구현)

	static Player mp[]; 
	ThrowingYut throwingPhase = new ThrowingYut();

	public static void main(String[] args) {
		YutBoard yb = new YutBoard();

		System.out.println("윷놀이 게임 시작(프로토타입)");

		if(InitDebugMode) mp = Debug.Player();
		else { 
			if(AloneDebugMode) mp = new Player[1];
			else mp = new Player[4];
			mp = Action.InitPlayers(mp); //플레이어 전원의 이름과 팀을 입력. 전체 프로그램 내에서 한번만 실행할 것
		}//end of else


		Action.scoreboard(mp);
		for(int i=0;;i=(i+1)%mp.length)
		{
			if(YutDebugMode) 
				Action.MoveMal(mp[i], yb, Debug.ThrowYut(mp[i]) );
			else 
				Action.MoveMal(mp[i], yb, Action.ThrowYut(mp[i]) );

			//else if(SplitYutMode)
			//	Action.MoveMal(mp[i], yb, Action.ThrowYutSplit(mp[i]) );


			Action.scoreboard(mp);
			if(mp[i].getMal1()==777||mp[i].getMal2()==777) {
				System.out.println(mp[i].getTeam()+" 팀의 "+mp[i].getName()+" 플레이어가 E김.");
				break;
			}//if
		}//for
	}//main
}//class
