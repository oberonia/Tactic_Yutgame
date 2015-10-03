
public class MainFrame {
	public static final int 도 = 0;
	public static final int 개 = 1;
	public static final int 걸 = 2;
	public static final int 윷 = 3;
	public static final int 모 = 4;

	/****디버그 모드로 실행할 때는 true로 입력해주세요**********/
	static boolean InitDebugMode = true; //팀명과 플레이어명이 임의로 지정됨
	static boolean YutDebugMode = true; //윷 던질 값을 직접 입력가능
	static boolean AloneDebugMode = true; //플레이어가 혼자가 됨
	static boolean EnemyCatchMode = true; //상대팀의 말을 잡을 수 있음(미구현)
	/*****디버그 모드가 아닐때는 false로 입력해주세요**********/
	
	static Player mp[]; 
	ThrowingYut throwingPhase = new ThrowingYut();

	public static void main(String[] args) {
		int t1 = 0; // 팀1 인원수를 저장
		int t2 = 0; // 팀2 인원수를 저장
		YutBoard yb = new YutBoard();

		System.out.println("윷놀이 게임 시작(프로토타입)");

		if(InitDebugMode) mp = Debug.Player();
		else { 
			if(AloneDebugMode) mp = new Player[1];
			else mp = new Player[4];
			mp = Action.InitPlayers(mp); //플레이어 전원의 이름과 팀을 입력. 전체 프로그램 내에서 한번만 실행할 것
		}


		Action.scoreboard(mp);
		for(int i=0;;i=(i+1)%mp.length)
		{
			if(YutDebugMode) Action.MoveMal(mp[i], yb, Debug.ThrowYut(mp[i],yb) );
			else Action.MoveMal(mp[i], yb, Action.ThrowYut(mp[i],yb) );
			Action.scoreboard(mp);
			if(mp[i].getMal1()==777||mp[i].getMal2()==777) {
				System.out.println(mp[i].getTeam()+" 팀의 "+mp[i].getName()+" 플레이어가 E김.");
				break;
			}
		}
	}
}
