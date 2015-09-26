
import java.util.Scanner;

public class Player{
	Player(int t1, int t2, String TeamString1, String TeamString2) {
		mal1 = -1;	// 
		mal2 = -1;	// 시작할 때 윷판 밖에 있으므로 초기값은 -1
		this.TeamString1 = new String(TeamString1); 
		this.TeamString2 = new String(TeamString2);
		this.inputName();
		if (t1>1) selectTeam(0);
		else if (t2>1) selectTeam(1);
		else selectTeam();
		
		
	}
	//player's basic variables
	
	Scanner sc = new Scanner(System.in);
	int score;
	private String name,team;
	private int mal1;	// 이동할 말1
	private int mal2;	// 말2
	private final String TeamString1; //팀명 정하는 변수1
	private final String TeamString2; //팀명 정하는 변수2
	
	private void inputName(){
			System.out.print("플레이어 이름 입력>>");
			name = sc.next();
			System.out.println("이름 확인 : " + name);
	}//end of inputName
	
	private void selectTeam(){
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
	}
	
	private void selectTeam(int i) {
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
