
import java.util.Random;
import java.util.Scanner;

public class Player{
	
	Scanner sc = new Scanner(System.in);
	static int score;
	static String name,team;
	static int mal1;	// 이동할 말1
	static int mal2;	// 말2
	static String TeamString1; //팀명 정하는 변수1
	static String TeamString2; //팀명 정하는 변수2
	Random keygen = new Random();
	control con = new control();
	Player(boolean trigger,String name) { //디버그 모드 오-픈
		mal1=-1;
		mal2=-1;
		this.TeamString1 = "잉여"; 
		this.TeamString2 = "갓수";
		this.name = name;
		if(trigger) team=TeamString1;
		else team=TeamString2;
	}
	Player(int t1, int t2, String TeamString1, String TeamString2) {
		mal1 = -1;	// 
		mal2 = -1;	// 시작할 때 윷판 밖에 있으므로 초기값은 -1
		this.TeamString1 = new String(TeamString1); 
		this.TeamString2 = new String(TeamString2);
		this.con.inputName();
		if (t1>1) selectTeam(0);
		else if (t2>1) selectTeam(1);
		else con.selectTeam();
				
	}
	//player's basic variables

	/**void inputName(){
			System.out.print("플레이어 이름 입력>>");
			name = sc.next();
			System.out.println("이름 확인 : " + name);
	}//end of inputName**/
	
	/**void selectTeam(){
		System.out.print("팀 선택("+TeamString1+"/"+TeamString2+" 입력)>>");
		while(true){
			team = sc.next();
			team = team.toLowerCase();
			if(!team.equals(TeamString1)&&!team.equals(TeamString2)) {
				System.out.println("잘못된 입력!");
				System.out.print("팀 선택("+TeamString1+"/"+TeamString2+" 입력)>>");
				continue;
			}
			else break;
		}
	}*/
	
	void selectTeam(int i) {
		switch(i) {
		case 0:
			team="white";
			System.out.println("your team is white, you don't have any choice");
			break;
		case 1:
			team="blue";
			System.out.println("your team is blue, you don't have any choice");
			break;
		default:
				System.out.println("selectTEAM method ERROR");
				return;
		}
	}
	
	public void ThrowYut() {
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
		YutBoard yb = new YutBoard();
		while(true) {
			tempMal=sc.nextInt();
			switch(tempMal) {
			case 1:
				System.out.print("mal1 이동! ("+mal1+" -> ");
				mal1=yb.movNext(mal1, true);
				for(int i=0;i<tempMove;i++)
					mal1=yb.movNext(mal1, false);
				System.out.println(mal1+")");
				break;
			case 2:
				System.out.print("mal2 이동! ("+mal2+" -> ");
				mal2=yb.movNext(mal2, true);
				for(int i=0;i<tempMove;i++)
					mal2=yb.movNext(mal2, false);
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
