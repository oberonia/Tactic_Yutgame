
public class Player{
	
	
	static String TeamString1; //팀명 정하는 변수1
	static String TeamString2; //팀명 정하는 변수2
	static int mv[] = new int[5];
	
	//asd
	String name,team;
	int mal1;	// 이동할 말1
	int mal2;	// 말2
	String malIcon; // boardPaper에 표현될 말
	Player(boolean trigger,String name,String malIcon) { //디버그 모드 오-픈
		mal1=-1;
		mal2=-1;
		this.name = name;
		this.malIcon = malIcon;
		if(trigger) team=TeamString1;
		else team=TeamString2;
		mv[0]=mv[1]=mv[2]=mv[3]=mv[4]=0;
	}
	Player(int t1, int t2) {
		mal1 = -1;	// 
		mal2 = -1;	// 시작할 때 윷판 밖에 있으므로 초기값은 -1
		name = Action.user.inputName();
		team = Action.user.selectTeam(t1,t2,TeamString1, TeamString2);
		mv[0]=mv[1]=mv[2]=mv[3]=mv[4]=0;
				
	}//end of Player (Constructor)
	
	/**팀 이름을 정해주는 함수*/
	static void InitTeamName(String ts1, String ts2) {
		TeamString1 = new String(ts1);
		TeamString2 = new String(ts2);
	}//end of InitTeamName
	
	/**InitTeamName 메소드 호출 후 사용할 것*/
	static String getTeamName1() {
		return TeamString1;
	}
	/**InitTeamName 메소드 호출 후 사용할 것*/
	static String getTeamName2() {
		return TeamString2;
	}
				
	void increaseMv(int i) {
		mv[i]++;
	}
	void decreaseMv(int i) {
		mv[i]--;
	}
	void resetMv() {
		mv[0]=mv[1]=mv[2]=mv[3]=mv[4]=0;
	}
	boolean isEmptyMv() {
		if(mv[0]==mv[1]&&mv[1]==mv[2]&&mv[2]==mv[3]&&mv[3]==mv[4]&&mv[4]==0)
			return true;
		else
			return false;
	}
	void resetMal1() {
		mal1=-1;
	}
	void resetMal2() {
		mal2=-1;
	}

}
