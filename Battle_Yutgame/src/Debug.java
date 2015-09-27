//디버그용 임시 값 저장 메소드를 보관하는 클래스파일
public class Debug {
	static Player[] Player() {
		Player a[] = new Player[4];
		String t1 = "잉여", t2 = "갓수";
		Player.InitTeamName(t1, t2);
		a[0] = new Player(true,"히키1");
		a[1] = new Player(true,"히키2");
		a[2] = new Player(false,"코모리1");
		a[3] = new Player(false,"코모리2");
		return a;
	}

}
