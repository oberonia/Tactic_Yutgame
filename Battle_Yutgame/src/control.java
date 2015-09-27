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
}
	

