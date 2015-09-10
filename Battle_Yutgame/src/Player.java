
import java.util.Scanner;

public class Player{	
	Player(int blue, int white) {
		this.inputName();
		if (blue>1) selectTeam(0);
		else if (white>1) selectTeam(1);
		else selectTeam();
	}
	//player's basic variables
	Scanner sc = new Scanner(System.in);
	int score;
	private String name,team;
		
	private void inputName(){
			System.out.println("Type your name, Player");
			name = sc.next();
			System.out.println("Your name is " + name);
	}//end of inputName
	private void selectTeam(){
		System.out.println("choose your team, Player (blue or white)");
		team = sc.next();
		team = team.toLowerCase();
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

}
