
import java.util.Random;

public class Player{
	
	private static String TeamString1; //팀명 정하는 변수1
	private static String TeamString2; //팀명 정하는 변수2
	
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
	}
	Player(int t1, int t2) {
		mal1 = -1;	// 
		mal2 = -1;	// 시작할 때 윷판 밖에 있으므로 초기값은 -1
		name = Action.inputName();
		team = Action.selectTeam(t1,t2,TeamString1, TeamString2);
				
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
	
	
	public void ThrowYut() {
		
		YutBoard yb = new YutBoard();
		
		System.out.println(getTeam()+"팀 "+getName()+" 플레이어 윷 던지기");
		System.out.println("말 위치정보\nmal1 : "+getMal1()+"\nmal2 : "+getMal2());
		int tempMove = keygen.nextInt(5); //이동할 칸 수 랜덤생성+임시저장
		System.out.print("퉤에엣 : ");
		switch(tempMove) {
		case 0:
			System.out.println("도");
			break;
		case 1:
			System.out.println("개");
			break;
		case 2:
			System.out.println("걸");
			break;
		case 3:
			System.out.println("윷");
			break;
		case 4:
			System.out.println("모");
			break;
		default:
			System.out.println("뭐임 왜 있을 수 없는 오류가...");
			return;
		}
		System.out.print("움직일 말을 고르세요 (1/2 입력)>>");
		int tempMal; //이동할 말 선택 임시저장
		
		while(true) {
			tempMal=Action.sc.nextInt();
			switch(tempMal) {
			case 1:
				System.out.print("mal1 이동! ("+mal1+" -> ");
				mal1=Action.movNext(mal1, true,yb.v0(),yb.v1(),yb.v2());
				for(int i=0;i<tempMove;i++)
					mal1=Action.movNext(mal1, false,yb.v0(),yb.v1(),yb.v2());
				System.out.println(mal1+")");
				break;
			case 2:
				System.out.print("mal2 이동! ("+mal2+" -> ");
				mal2=Action.movNext(mal2, true,yb.v0(),yb.v1(),yb.v2());
				for(int i=0;i<tempMove;i++)
					mal2=Action.movNext(mal2, false,yb.v0(),yb.v1(),yb.v2());
				System.out.println(mal2+")");
				break;
			default:
				System.out.println("잘못된 값입니다. 다시 입력하세요.");
				System.out.print("움직일 말을 고르세요 (1/2 입력)>>");
				continue;
			}
			break;
		}
		//int temp2 = SelectInput(1,2);
		
		
		
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

}
