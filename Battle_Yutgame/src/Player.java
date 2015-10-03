
import java.util.Random;

public class Player{
	
	
	private static String TeamString1; //팀명 정하는 변수1
	private static String TeamString2; //팀명 정하는 변수2
	private static int mv[] = new int[5];
	
	
	private int score;
	private String name,team;
	private int mal1;	// 이동할 말1
	private int mal2;	// 말2
	Random keygen = new Random();
	Player(boolean trigger,String name) { //디버그 모드 오-픈
		mal1=-1;
		mal2=-1;
		this.name = name;
		if(trigger) team=TeamString1;
		else team=TeamString2;
		mv[0]=mv[1]=mv[2]=mv[3]=mv[4]=0;
	}
	Player(int t1, int t2) {
		mal1 = -1;	// 
		mal2 = -1;	// 시작할 때 윷판 밖에 있으므로 초기값은 -1
		name = Action.inputName();
		team = Action.selectTeam(t1,t2,TeamString1, TeamString2);
		mv[0]=mv[1]=mv[2]=mv[3]=mv[4]=0;
				
	}//end of Player (Constructor)
	
	static void InitTeamName(String ts1, String ts2) {
		TeamString1 = new String(ts1);
		TeamString2 = new String(ts2);
	}
	//InitTeamName 메소드 호출 후 사용할 것
	static String getTeamName1() {
		return TeamString1;
	}
	//InitTeamName 메소드 호출 후 사용할 것
	static String getTeamName2() {
		return TeamString2;
	}
	
	
	
	
	public String getName() {
		return this.name;
	}
	public String getTeam() {
		return this.team;
	}
	public int getMal1(){
		return mal1;
	}
	public int getMal2(){
		return mal2;
	}
	void putMal1(int a) {
		mal1 = a;
	}
	void putMal2(int a) {
		mal2 = a;
	}
	int[] getMv() {
		return mv;//movement
	}
	int getMv(int i) {
		return mv[i];
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

}
