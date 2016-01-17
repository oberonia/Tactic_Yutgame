import java.util.ArrayList;

public class Player{
	
	/**팀명 정하는 변수1*/
	static String TeamString1; 
	/**팀명 정하는 변수2*/
	static String TeamString2; 
	/**자기 차례인 플레이어의 Move를 저장*/
	static int mv[] = new int[5];
	
	String name,team;
	Mal[] mals = new Mal[2];
	Mal mal1;	
	Mal mal2;	
	/**boardPaper에 표현될 말*/
	String malIcon; 
	
	/**디버그 모드 오-픈*/
	Player(boolean trigger,String name,String malIcon) { 
		mal1 = new Mal(this);
		mal2 = new Mal(this);
		this.name = name;
		this.malIcon = malIcon;
		if(trigger) team=TeamString1;
		else team=TeamString2;
		mv[0]=mv[1]=mv[2]=mv[3]=mv[4]=0;
	}
	Player(int t1, int t2) {
		mal1 = new Mal(this);
		mal2 = new Mal(this);	// 시작할 때 윷판 밖에 있으므로 초기값은 -1
		name = Action.user.inputName();
		team = Action.user.selectTeam(t1,t2,TeamString1, TeamString2);
		mv[0]=mv[1]=mv[2]=mv[3]=mv[4]=0;
				
	}//end of Player (Constructor)
	
	/**팀 이름을 정해주는 함수*/
	static void InitTeamName(String ts1, String ts2) {
		TeamString1 = new String(ts1);
		TeamString2 = new String(ts2);
	}//end of InitTeamName
	
	/**InitTeamName 메소드 호출 후 사용할 것*/
	static String getTeamName1() {
		return TeamString1;
	}
	/**InitTeamName 메소드 호출 후 사용할 것*/
	static String getTeamName2() {
		return TeamString2;
	}
	/** mv 값을 1 증가시킨다
	 * 
	 * @param mv 현재 차례의 유저가 이동할 수 있는 도~모의 횟수
	 * @param i 도~모를 입력하여 조정할 변수를 지정
	 * @see src/Yut.java
	 */
	void increaseMv(int i) {
		mv[i]++;
	}
	
	/** mv 값을 1 감소시킨다
	 * 
	 * @param mv 현재 차례의 유저가 이동할 수 있는 도~모의 횟수
	 * @param i 도~모를 입력하여 조정할 변수를 지정
	 * @see src/Yut.java
	 */
	void decreaseMv(int i) {
		mv[i]--;
	}
	
	/** 모든 mv 값을 0으로 초기화한다
	 * 
	 * @param mv 현재 차례의 유저가 이동할 수 있는 도~모의 횟수
	 */
	void resetMv() {
		mv[0]=mv[1]=mv[2]=mv[3]=mv[4]=0;
	}
	/** mv 변수가 모두 비어있는지 검사하는 함수
	 * 
	 * @return 내 차례가 끝났을 때 true 반환
	 */
	boolean isEmptyMv() {
		if(mv[0]==mv[1]&&mv[1]==mv[2]&&mv[2]==mv[3]&&mv[3]==mv[4]&&mv[4]==0)
			return true;
		else
			return false;
	}
	/**
	 * mal1 변수를 -1로 리셋
	 */
	void resetMal1() {
		mal1.catched();
	}
	/**
	 * mal2 변수를 -1로 리셋
	 */
	void resetMal2() {
		mal2.catched();
	}
	
	void GroupMake(int i, Mal m) {
		switch(i) {
		case 0:
			mal1.groupMake(m);
			break;
		case 1:
			mal2.groupMake(m);
			break;
		default:
			System.out.println("올바르지 않은 i값 호출(Player.GroupMake) = "+i);
			return;
		}
	}
	
	
	class Mal {
		private Player master; //자신의 주인을 저장
		int location; //위치정보
		Group mygroup; //내가 속한 (업힌)그룹. 기본값은 null
		
		Mal(Player p) {
			this.master = p;
			location = -1;
			mygroup = null;
		}
		Player yourMaster() { //주인장 불러와 주인장
			return master; //네
		}
		void catched() // 상대팀에게 잡혔어요
		{
			location = -1;
		}
		void finish() // 미국 갔어요
		{
			location = 777;
		}
		boolean isFinished() { //미국간 말인지 체크함
			if(location == 777) return true;
			else return false;
		}
		boolean isCatched() { //말 위치가 -1인지 체크함
			if(location == -1) return true;
			else return false;
		}

		
		boolean isGrouped() { //업혀있는 상태면 true
			if(mygroup!=null) return true;
			else return false;
		}
		void groupMake(Mal m) { //다른 말을 업는 메소드
			if(m.isGrouped()) {
				if(!isGrouped()) { //내가 업힌상태가 아님
					mygroup = m.mygroup;
					mygroup.Add(this);
				}
				else { //내 업힌 그룹을 죄다 추출해서 상대방 그룹에 추가
					Mal[] ms = mygroup.getMember();
					for(Mal i : ms) {
						i.mygroup = m.mygroup;
						m.mygroup.Add(i);
					}
				}
			}
			else {
				mygroup = new Group(m);
				mygroup.Add(this);
			}
		}
		
	}

	class Group {
		String team;
		ArrayList<Mal> list;

		Group(Mal m) { //말을 인자로 받아서 그룹으로 묶어버린다
			list = new ArrayList<Mal>();
			team = m.yourMaster().team;
			list.add(m);
			m.mygroup = this;
		}
		void Add(Mal m) { //업힌 그룹에 말 추가
			m.mygroup=this;
			list.add(m);
		}
		boolean remove(Mal m) { //대상 말을 제거
			if(list.contains(m)) {
				m.mygroup=null;
				list.remove(m);
				return true;
			}
			else
				return false; //list에서 m을 찾지 못함
		}
		void catched() { //그룹이 잡혔으므로 내부 인원들 최다 퇴출시킴
			Mal[] m = (Mal[]) list.toArray();
			for(Mal i : m) {
				i.mygroup=null;
				i.catched();
			}
			list.clear();
		}
		void finish() { //업은 채로 포인트 획득, 내부 인원 역시 퇴출
			Mal[] m = (Mal[]) list.toArray();
			for(Mal i : m) {
				i.mygroup=null;
				i.finish();
			}
			list.clear();
		}
		Mal[] getMember() { //업혀있는 모든 말들을 배열로 바꿔서 리턴
			Mal[] m = new Mal[list.size()];
			return m = (Mal[])list.toArray(m);
		}

		void Move(int target) { //멤버 전체이동
			Mal[] m = new Mal[list.size()]; 
			m = (Mal[])list.toArray(m);
			for(Mal i : m) {
				i.location = target;
			}
		}
	}
	

}
