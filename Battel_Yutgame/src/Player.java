// package boardgame;
import java.util.Scanner;

public class Player{
	public Player(Scanner sc){
		this.sc = sc;
	}
	//player's basic variables
	Scanner sc;
	int piece,yoot,score,id;
	int blue = 0;
	int white = 0;
	String a,w, name,team;;

	//추가로 들어갈 것 : position of piece(make array which saves location data), whole board x 3

	void getName(){
		System.out.println("Type your name, Player");
		name = sc.next();

		System.out.println("Your name is " + name);
	}//end of getName

	void selectTeam(){

		boolean out;
		while(out=true){
			if(blue==2){
				team="white";
				System.out.println("your team is white, you don't have any choice");
				return;
			}

			if(white==2){
				team="blue";
				System.out.println("your team is blue, you don't have any choice");
				return;
			}

			System.out.println("choose your team, Player (blue or white)");
			team = sc.next();

			team = team.toLowerCase();

			if(team.equals("blue")){
				blue++;
				return;
			}

			else if(team.equals("white")){
				white++;
				return;
			}

			else System.out.println("You've got wrong team!!");
		}

	}

	void setPlayer(String n, String t, int p, int y, int s, int i){
		name = n;
		team = t;
		piece = p;
		yoot = y;
		score = s;
		id = i;
	}//end of setPlayer

}
