import java.util.Enumeration;
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
	//사용하지 않게 된 함수
	/*
	Vector<Integer> v0() {
		return v[0];
	}
	Vector<Integer> v1() {
		return v[1];
	}
	Vector<Integer> v2() {
		return v[2];
	}*/

	static String boardPaper[][] = new String[7][7]; // 윷판에 그림을 찍을 종이
	Vector<Integer> v[];

	private void boardReset() {
		for(int i=0;i<boardPaper.length;i++){
			for(int j=0;j<boardPaper[i].length;j++){
				boardPaper[i][j] = ""; // default value is space
			}
		}
	} //end of boardReset
	
	public void boardDisplay(){
		// show the board at console
		boardReset();
		Print();
		ShowMalIcon();
		for(int i=0;i<boardPaper.length;i++){
			for(int j=0;j<boardPaper[i].length;j++){
				System.out.print(boardPaper[i][j]+"\t");
			}
			System.out.println();
		}
	} //end of boardDisplay

	public void Print() {
		for(int i=0;i<3;i++) {
			Enumeration<Integer> e = v[i].elements();
			while(e.hasMoreElements()) {
				int temp=(int)e.nextElement();
				int a = (temp-1)/7;
				int b = (temp-1)%7;
				boardPaper[a][b] = "O"; // 말이 다닐 수 있는 곳은 O로 표시 함
			}
		}
	} //end of Print
	

	void ShowMalIcon(){ 
		// show all player's Mal to boardPaper
		for(int i=0;i<MainFrame.mp.length;i++){
			if(MainFrame.mp[i].mal1 == 777 || MainFrame.mp[i].mal2 == 777){
				System.out.println("Game is over");
				break;
			}
			if(MainFrame.mp[i].mal1 != -1){
				int tempMalPosition = MainFrame.mp[i].mal1;
				int a = (tempMalPosition-1)/7;
				int b = (tempMalPosition-1)%7;
				YutBoard.boardPaper[a][b] = MainFrame.mp[i].malIcon;
			}
			if(MainFrame.mp[i].mal2 != -1){
				int tempMalPosition = MainFrame.mp[i].mal2;
				int a = (tempMalPosition-1)/7;
				int b = (tempMalPosition-1)%7;
				YutBoard.boardPaper[a][b] = MainFrame.mp[i].malIcon;
			}
		}//end of for loop
	}//end of ShowMalIcon

	
}
