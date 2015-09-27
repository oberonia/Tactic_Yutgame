
public class MainFrame {
	static Player mp[] = new Player[4];
	static ThrowingYut throwingPhase = new ThrowingYut();

	public static void main(String[] args) {
		int t1 = 0; // 팀1 인원수를 저장
		int t2 = 0; // 팀2 인원수를 저장
		String TeamString1, TeamString2;
		System.out.println("윷놀이 게임 시작(프로토타입)");
		/* 팀 이름을 정하는 기능. 뻐킹 디버그 모드에서는 주석처리
		System.out.print("첫번째 팀 이름을 정하세요>>");
		TeamString1 = new String(sc.next());
		while(true) {
			System.out.print("두번째 팀 이름을 정하세요>>");
			TeamString2 = new String(sc.next());
			if(TeamString1.equals(TeamString2)) {
				System.out.println("팀 이름은 서로 같을 수 없습니다.다시 입력하세요.");
				continue;
			}
			else break;
		}
		for(int i=0;i<4;i++){
			System.out.println("========"+(i+1)+"번 플레이어 정보 입력========");
			mp[i]= new Player(t1,t2);
			if(mp[i].getTeam().equals(TeamString1)) t1++;
			else if(mp[i].getTeam().equals(TeamString2)) ++t2;
			System.out.println("플레이어 이름 확인 : " + mp[i].getName() + ", Player " + i);
			System.out.println("팀 이름 확인 : " + mp[i].getTeam() + ", Player " + i);
		}//end of for loop
		*/
		
		/* 임시 팀이름 플레이어 이름 강제 정하기 문장 */
		Player.InitTeamName("잉여", "갓수");
		mp[0]= new Player(true,"히키1");
		mp[1]= new Player(true,"히키2");
		mp[2]= new Player(false,"코모리1");
		mp[3]= new Player(false,"코모리2");
		/*개같은 뻐킹 디버그 모드*/

		scoreboard();
		
		for(int i=0;;i=(i+1)%4)
		{
			mp[i].ThrowYut();
			scoreboard();
			if(mp[i].getMal1()==777||mp[i].getMal2()==777) {
				System.out.println(mp[i].getTeam()+" 팀의 "+mp[i].getName()+" 플레이어가 E김.");
				break;
			}
		}
		

	}

	static void scoreboard(){
		System.out.println("Name\t"+"Team\t"+"Mal No.\t"+"Location");
		for(int j=0; j<mp.length; j++){
			System.out.println(mp[j].getName()+"\t"+mp[j].getTeam()+"\t"+"mal1\t"+mp[j].getMal1());
			System.out.println(mp[j].getName()+"\t"+mp[j].getTeam()+"\t"+"mal2\t"+mp[j].getMal2());
		}
		
	}

}
