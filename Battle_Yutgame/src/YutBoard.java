import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;


public class YutBoard {

	@SuppressWarnings("unchecked")
	public YutBoard() {
		// 길만 표기되어 있는 윷판 생성
		boardReset();
		int v1[] = {42,35,21,14,7,6,5,3,2,1,8,15,29,36,43,44,45,47,48,49}; //add가 귀찮은 배열 1 
		int v2[] = {7,13,19,25,31,37,43}; //add가 귀찮은 배열 2
		int v3[] = {1,9,17,25,33,41,49}; //add가 귀찮은 배열 3
		v = new Vector[3];
		v[0] = new Vector<Integer>(20); //20개, 우하단부터 시작하는 테두리 윷판, 검색 2순위
		v[1] = new Vector<Integer>(7); //7개, 대각선 방향(↙), 검색 1순위
		v[2] = new Vector<Integer>(7); //7개, 대각선 방향(↘), 검색 0순위
		
		for (int i=0;i<20;i++)
			v[0].add(v1[i]);
		for (int i=0;i<7;i++)
			v[1].add(v2[i]);
		for (int i=0;i<7;i++)
			v[2].add(v3[i]);
		
		
	} 
	Vector<Integer> v0() {
		return v[0];
	}
	Vector<Integer> v1() {
		return v[1];
	}
	Vector<Integer> v2() {
		return v[2];
	}

	String boardPaper[][] = new String[7][7]; // 윷판에 그림을 찍을 종이
	private Vector<Integer> v[];

	private void boardReset() {
		for(int i=0;i<boardPaper.length;i++){
			for(int j=0;j<boardPaper[i].length;j++){
				boardPaper[i][j] = ""; // default value is space
			}
		}
	}
	
	public void boardDisplay(){
		// show the board at console
		for(int i=0;i<boardPaper.length;i++){
			for(int j=0;j<boardPaper[i].length;j++){
				System.out.print(boardPaper[i][j]+"\t");
			}
			System.out.println();
		}
	}

	public void Print() {
		for(int i=0;i<3;i++) {
			Enumeration<Integer> e = v[i].elements();
			while(e.hasMoreElements()) {
				int temp=(int)e.nextElement();
				int a = (temp-1)/7;
				int b = (temp-1)%7;
				boardPaper[a][b] = "O"; // 말이 다닐 수 있는 곳은 O로 표시할 수 있도록 함
				// O이 표시된 곳에 말이 들어가면 특수문자로 표현해야 한다
				// 0) add가 귀찮은 곳의 배열에 해당하는 원소들을 1~49 기준 표현으로 바꿔넣는다
				// 1) movNext로 값이 바뀐 다음, 이전 위치에 해당하는 boardDisplay는 O으로 바꾸고,
				// 2) 새로운 위치는 boardDisplay에 해당플레이어의 특수문자를 바꿔넣는다
				// 3) boardDisplay의 모든 원소들을 /t을 이용해서 콘솔에 뿌린다
			}
		}
	
		while(true) {
			int target=-1,mov=0;
			Scanner sc = new Scanner(System.in);
			System.out.println("Target Location : "+target);
			System.out.println("input(a,s,d,f,g,q)>>");
			switch(sc.next()) {
			case "a": case "A":	mov=1; break;
			case "s": case "S":	mov=2; break;
			case "d": case "D":	mov=3; break; 
			case "f": case "F":	mov=4; break;
			case "g": case "G":	mov=5; break;
			case "q": case "Q":
				System.out.println("Terminated");
				return;
			default:
				System.out.println("<WRONG INPUT>try again");
				continue;
			}
			target = 23; mov--;
			for(int i=0;i<mov;i++) {
				target = Action.movNext(target, false, v[0], v[1], v[2]);
			}
		}

	}
}
