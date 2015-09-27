import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;


public class YutBoard {

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
				target = movNext(target, false);
			}
		}

	}
	/*
	 * 말 하나를 집어서 다음 칸으로 넘기거나 finish 처리를 하는 메쏘오드
	 * target에 말 정보가 들어가고, trigger로 말을 꺾을지 말지를 결정하게 됨
	 * trigger가 true인 경우 : 말을 처음 이동할 때를 가정하여 분기점을 만나면 꺾게 됨
	 * trigger가 false인 경우 : 말을 이동하던 중에 호출된 것으로 가정하여 분기점을 통과하게 됨
	 * 말이 finish 상태가 된 경우 777 리턴
	 * 알 수 없는 오류를 뿜는 경우 -222222 리턴 
	 * */
	public int movNext(int target, boolean trigger) {
		if(trigger) { //꺾습니다
			if(v[2].contains(target)){  //v2에서 target에 해당하는 값을 찾음
				int tempIndex=v[2].indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(tempIndex==v[2].indexOf(v[2].lastElement())) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return v[2].indexOf(tempIndex+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				//해당 value를 리턴	
			}
			else if(v[1].contains(target) && target!=v[1].lastElement()) { //없을 경우 v1에서 target값을 찾음
				return v[1].elementAt((v[1].indexOf(target)+1));//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
			}
			else if(v[0].contains(target)) {//없을 경우 v0에서 target값을 찾음	else if()
				int temp=v[0].indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(temp==v[0].indexOf(v[0].lastElement())) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return v[0].elementAt(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				//해당 value를 리턴	
			}
			else if(target==-1) return v[0].firstElement();
			else return -222222; //그래도 없으면 오류처리
		}
		else { //꺾지마
			if(v[0].contains(target)) {//v0에서 target값을 찾음	else if()
				int temp=v[0].indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(temp==v[0].indexOf(v[0].lastElement())) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return v[0].elementAt(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				//해당 value를 리턴
			}
			else if(v[1].contains(target)) { //v1에서 target값을 찾음
				return v[1].elementAt((v[1].indexOf(target)+1));//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
			}
			else if(v[2].contains(target)){  //v2에서 target에 해당하는 값을 찾음
				int temp=v[2].indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(temp==v[2].indexOf(v[2].lastElement())) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return v[2].elementAt(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				//해당 value를 리턴	
			}
			else if(target==777) return 777; //피니시 된 말을 다시 움직이려고 할때 777 리턴
			else return -222222;
		}
	}
}
