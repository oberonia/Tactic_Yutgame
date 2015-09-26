
import java.util.Scanner;

public class Player{
	Player(int blue, int white) {
		this.inputName();
		if (blue>1) selectTeam(0);
		else if (white>1) selectTeam(1);
		else selectTeam();
		mal1 = -1;	// 
		mal2 = -1;	// 시작할 때 윷판 밖에 있으므로 초기값은 -1
	}
	//player's basic variables
	Scanner sc = new Scanner(System.in);
	int score;
	private String name,team;
	private int mal1;	// 이동할 말1
	private int mal2;	// 말2
		
	private void inputName(){
			System.out.println("플레이어 이름 입력>>");
			name = sc.next();
			System.out.println("Your name is " + name);
	}//end of inputName
	
	private void selectTeam(){
		System.out.println("팀 선택(blue/white 입력)>>");
		while(true){
			team = sc.next();
			team = team.toLowerCase();
			if(team.equals("blue")||team.equals("white")) {
				System.out.println("잘못된 입력!");
				System.out.println("팀 선택(blue/white 입력)>>");
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
