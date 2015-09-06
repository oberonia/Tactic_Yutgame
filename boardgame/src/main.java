
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int blue = 0; // temporary variable for team selection
		int white = 0; // temporary variable for team selection
		
		//hello world
		
		System.out.println("Hello players, Glad to see you all");
		System.out.println("From now on, we will play battle yootnolee");
		
		Player mp[] = new Player[4];
		
		
		for(int i=0;i<4;i++){
	
		mp[i]= new Player();
		System.out.println("Type your name Player " + i);
		mp[i].name=sc.next();
		
		while(!("blue".equals(mp[i])) && !("white".equals(mp[i]))){
			
		mp[i].selectTeam();
		if(blue==2){
			mp[i].team="white";
			System.out.println("your team is white, you don't have any choice");
		}
		
		if(white==2){
			mp[i].team="blue";
			System.out.println("your team is blue, you don't have any choice");
		}
		
		if(mp[i].team.equals("blue")){
			mp[i].team = "blue";
			blue++;
		}
		
		else if(mp[i].team.equals("white")){
			mp[i].team = "white";
			white++;
		}
		
		else System.out.println("You've got wrong team!!");
		
		System.out.println(blue + " " + white);
		}//end of while loop
		
		System.out.println("From now on, your name is " + mp[i].name + ", Player " + i);
		System.out.println("Now your on " + mp[i].team + " team, Player " + i);
		
		}
		
		System.out.println(mp[0].name + mp[0].team);
		System.out.println(mp[3].name + mp[3].team);
		System.out.println(mp[2].name + mp[2].team);
		System.out.println(mp[1].name + mp[1].team);
	}

}
