import java.util.Random;



//디버그용 임시 값 저장 메소드를 보관하는 클래스파일
public class Debug implements Yut {
	
	static Random keygen = new Random(); //랜덤으로 윷 던지는 모드에서 사용됨
	
	static Player[] Player() {
		Player a[];
		if(MainFrame.AloneDebugMode) {
			a = new Player[1];
			String t1 = "잉여", t2 = "갓수";
			Player.InitTeamName(t1, t2);
			a[0] = new Player(true,"히키코","@");
		}
		else if(OneEnemyMode) {
			a = new Player[2];
			String t1 = "잉여", t2 = "갓수";
			Player.InitTeamName(t1, t2);
			a[0] = new Player(true,"히키코","@");
			a[1] = new Player(false,"모리야","#");
		}
		else {
			a = new Player[4];
			String t1 = "잉여", t2 = "갓수";
			Player.InitTeamName(t1, t2);
			a[0] = new Player(true,"히키1","@");
			a[1] = new Player(true,"히키2","#");
			a[2] = new Player(false,"코모리1","§");
			a[3] = new Player(false,"코모리2","☆");
		}
		return a;
	}
	

	/**
	 * 디버그용 윷던지기
	 * 윷을 던지는데 랜덤이 아님
	 * 으엌
	 * @param p 자기 차례의 플레이어
	 */
	static void ThrowYut(Player p) {
		int a;
		System.out.print("이동할 칸을 입력하세요 >>");
		while(true) {
			a=Action.sc.nextInt();
			if(a<1||a>5) {
				System.out.println("<입력범위 초과>");
				System.out.println("다시 입력하세요 >>");
				continue;
			}
			break;	
		}
		if(a>3) {
			System.out.println(YutName[a-1]+" 감지! 한번 더 던집니다.");
			ThrowYut(p);
		}
		Player.mv[a-1]++;
	}
	
	
	/**
	 * 말 업기와 잡기가 구현되지 않은 MoveMal...
	 * 사실 말 업기와 잡기를 구현하기 전에 Action 클래스에 있었는데
	 * 강등당했쪄.
	 * @param p 자기 차례의 플레이어
	 * @param yb 윷보드
	 * @param MoveCount 이동할 칸 수
	 * @throws Exception 익셉션 새개끼
	 */
	static void MoveMal (Player p, YutBoard yb, int MoveCount) throws Exception {
		if(MoveCount>5||MoveCount<0) throw new Exception("이동할 칸 수 오류발생\n해당값 : "+MoveCount);
		System.out.println("말 위치정보\nmal1 : "+p.mal[0].location+"\nmal2 : "+p.mal[1].location);
		System.out.print("움직일 말을 고르세요. ");
		int tempMal= Action.select(1,2); //이동할 말 선택 임시저장

		switch(tempMal) {
		case 1:
			System.out.print("mal1 이동! ("+p.mal[0]+" -> ");
			p.mal[0].location=Action.board.movNext(p.mal[0].location, true,yb);
			for(int i=1;i<MoveCount;i++)
				p.mal[0].location=Action.board.movNext(p.mal[0].location, false,yb);
			System.out.println(p.mal[0]+")");
			break;
		case 2:
			System.out.print("mal2 이동! ("+p.mal[1]+" -> ");
			p.mal[1].location=Action.board.movNext(p.mal[1].location, true,yb);
			for(int i=1;i<MoveCount;i++)
				p.mal[1].location=Action.board.movNext(p.mal[1].location, false,yb);
			System.out.println(p.mal[1]+")");
			break;
		default:
			System.out.println("select 함수 내부처리 오류 : "+tempMal);
		}
		p.decreaseMv(MoveCount-1);
	} //end of MoveMal

}




/**Player쪽 안쓰던 함수들 모음*/
	/*
	public String getName() {
		return this.name;
	}
	public String getTeam() {
		return this.team;
	}
	public int getMal1(){
		return mal1;
	}
	public int getMal2(){
		return mal2;
	}
	void putMal1(int a) {
		mal1 = a;
	}
	void putMal2(int a) {
		mal2 = a;
	}
	int[] getMv() {
		return mv;//movement
	}
	int getMv(int i) {
		return mv[i];
	}
	String showIcon() {
		return malIcon;
	}*/

/**YutBoard쪽 함수*/
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

/***/