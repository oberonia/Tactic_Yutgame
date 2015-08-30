// package boardgame;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//sc.next();

		System.out.println("Hello players, Glad to see you all");
		System.out.println("From now on, we will play tactic yootnolee");
		
		Player mp = new Player(sc);
		ThrowingYut throwYut = new ThrowingYut();

		for(int i=1;i<5;i++){
			System.out.println("Type your name Player " + i);
			
			mp.name=sc.next();
			
			System.out.println("From now on, your name is " + mp.name + " Player " + i);
			
			mp.selectTeam();
		
			mp.setPlayer(mp.name, mp.team, 2, 1, 0, i);
			
			System.out.println("배틀 윷놀이는 플레이어 4명이 각자 윷을 하나씩 던져서 그 합계로 서로의 전진할 칸수를 결정합니다.");
			System.out.println("플레이어"+i+"님의 전진할 칸수를 정합니다.");
			System.out.println("앞면은 0을, 뒷면은 1을 입력하면 낼 수 있습니다.");
		
			throwYut.throwing(i);
			
		
		}
		
		sc.close();

	}

}
