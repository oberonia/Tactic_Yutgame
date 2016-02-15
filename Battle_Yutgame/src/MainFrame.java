
public class MainFrame implements Yut {
	static Player mp[]; 

	public static void main(String[] args) throws Exception {
		YutBoard yb = new YutBoard();

		System.out.println("윷놀이 게임 시작(프로토타입)");

		if(InitDebugMode) mp = Debug.Player();
		else { 
			if(AloneDebugMode) mp = new Player[1];
			else if(OneEnemyMode) mp = new Player[2];
			else mp = new Player[4];
			mp = Action.user.InitPlayers(mp); //플레이어 전원의 이름과 팀을 입력. 전체 프로그램 내에서 한번만 실행할 것
		}//end of else

		Action.board.scoreboard(mp);
		new sw_YutInfo();
		for(int i=0;;i=(i+1)%mp.length)
		{
			Action.board.myPhase(mp, i, yb);
			Action.board.scoreboard(mp);
			yb.boardDisplay();
			if(mp[i].mal[0].isFinished()||mp[i].mal[1].isFinished()) {
				System.out.println(mp[i].team+" 팀의 "+mp[i].name+" 플레이어가 E김.");
				break;
			}//if
			
		}//for
		
	}//main
}//class
