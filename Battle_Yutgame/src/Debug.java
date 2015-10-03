//디버그용 임시 값 저장 메소드를 보관하는 클래스파일
public class Debug implements Yut {
	static Player[] Player() {
		Player a[];
		if(MainFrame.AloneDebugMode) {
			a = new Player[1];
			String t1 = "잉여", t2 = "갓수";
			Player.InitTeamName(t1, t2);
			a[0] = new Player(true,"히키코");
		}
		else {
			a = new Player[4];
			String t1 = "잉여", t2 = "갓수";
			Player.InitTeamName(t1, t2);
			a[0] = new Player(true,"히키1");
			a[1] = new Player(true,"히키2");
			a[2] = new Player(false,"코모리1");
			a[3] = new Player(false,"코모리2");
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

}
