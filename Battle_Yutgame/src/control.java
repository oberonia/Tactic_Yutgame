import java.util.Scanner;

public class control {
String name;
Scanner sc = new Scanner(System.in);

void inputName(){
	System.out.print("플레이어 이름 입력>>");
	name = sc.next();
	System.out.println("이름 확인 : " + name);
}//end of inputName

void selectTeam(){
	System.out.print("팀 선택("+Player.TeamString1+"/"+Player.TeamString2+" 입력)>>");
	while(true){
		Player.team = sc.next();
		Player.team = Player.team.toLowerCase();
		if(!Player.team.equals(Player.TeamString1)&&!Player.team.equals(Player.TeamString2)) {
			System.out.println("잘못된 입력!");
			System.out.print("팀 선택("+Player.TeamString1+"/"+Player.TeamString2+" 입력)>>");
			continue;
		}
		else break;
	}	
}

void selectTeam(int i) {
	switch(i) {
	case 0:
		Player.team="white";
		System.out.println("your team is white, you don't have any choice");
		break;
	case 1:
		Player.team="blue";
		System.out.println("your team is blue, you don't have any choice");
		break;
	default:
			System.out.println("selectTEAM method ERROR");
			return;
	}
}
}
	

