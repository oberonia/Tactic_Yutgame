

//디버그용 임시 값 저장 메소드를 보관하는 클래스파일
public class Debug implements Yut {
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
	
	/* 디버그용 윷던지기
	 * 윷을 던지는데 랜덤이 아님
	 * 으엌
	 * */
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
		p.increaseMv(a-1);
	}
	
	
	/**
	 * 디버그용 MoveMal
	 * @param p
	 * @param yb
	 * @param MoveCount
	 * @throws Exception
	 */
	static void MoveMal (Player p, YutBoard yb, int MoveCount) throws Exception {
		if(MoveCount>5||MoveCount<0) throw new Exception("이동할 칸 수 오류발생\n해당값 : "+MoveCount);
		System.out.println("말 위치정보\nmal1 : "+p.getMal1()+"\nmal2 : "+p.getMal2());
		System.out.print("움직일 말을 고르세요. ");
		int tempMal= Action.select(1,2); //이동할 말 선택 임시저장

		switch(tempMal) {
		case 1:
			System.out.print("mal1 이동! ("+p.getMal1()+" -> ");
			p.putMal1(Action.board.movNext(p.getMal1(), true,yb.v0(),yb.v1(),yb.v2()));
			for(int i=1;i<MoveCount;i++)
				p.putMal1(Action.board.movNext(p.getMal1(), false,yb.v0(),yb.v1(),yb.v2()));
			System.out.println(p.getMal1()+")");
			break;
		case 2:
			System.out.print("mal2 이동! ("+p.getMal2()+" -> ");
			p.putMal2(Action.board.movNext(p.getMal2(), true,yb.v0(),yb.v1(),yb.v2()));
			for(int i=1;i<MoveCount;i++)
				p.putMal2(Action.board.movNext(p.getMal2(), false,yb.v0(),yb.v1(),yb.v2()));
			System.out.println(p.getMal2()+")");
			break;
		default:
			System.out.println("select 함수 내부처리 오류 : "+tempMal);
		}
		p.decreaseMv(MoveCount-1);
	} //end of MoveMal

}
