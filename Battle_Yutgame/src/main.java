
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
			mp[i]= new Player(blue,white);
			if(mp[i].getTeam().equals("blue")) blue++;
			else if(mp[i].getTeam().equals("white")) ++white;
		System.out.println("From now on, your name is " + mp[i].getName() + ", Player " + i);
		System.out.println("Now your on " + mp[i].getTeam() + " team, Player " + i);
		}//end of for loop
		
	}

}
