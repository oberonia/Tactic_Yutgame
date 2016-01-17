import java.util.ArrayList;




public class malEx {
	static class pex{

		/**팀명 정하는 변수1*/
		static String TeamString1; 
		/**팀명 정하는 변수2*/
		static String TeamString2; 
		/**자기 차례인 플레이어의 Move를 저장*/
		static int mv[] = new int[5];

		String name,team;
		Mal mal1;	
		Mal mal2;	
		/**boardPaper에 표현될 말*/
		String malIcon; 

		/**디버그 모드 오-픈*/
		pex(boolean trigger,String name,String malIcon) { 
			mal1 = new Mal(this);	// 
			mal2 = new Mal(this);	
			this.name = name;
			this.malIcon = malIcon;
			if(trigger) team=TeamString1;
			else team=TeamString2;
			mv[0]=mv[1]=mv[2]=mv[3]=mv[4]=0;
		}
		pex(int t1, int t2) {
			mal1 = new Mal(this);	// 
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


		class Mal {
			private pex master; //자신의 주인을 저장
			private int location; //위치정보
			Group mygroup; //내가 속한 (업힌)그룹. 기본값은 null
			
			Mal(pex pex) {
				this.master = pex;
				location = -1;
				mygroup = null;
			}
			pex yourMaster() { //주인장 불러와 주인장
				return master; //네
			}
			int getloc() { //위치 리턴
				return location;
			}
			void setloc(int i) { //위치설정
				location = i;
			}
			void catched() // 상대팀에게 잡혔어요
			{
				location = -1;
			}
			void finish() // 미국 갔어요
			{
				location = 777;
			}
			boolean isFinished() { //나간 말인지 체크함
				if(location == 777) return true;
				else return false;
			}

			boolean isGrouped() { //업혀있는 상태면 true
				if(mygroup!=null) return true;
				else return false;
			}
			void groupMake(Mal m) { //다른 말을 업는 메소드
				if(m.isGrouped()) {

				}
				else {
					mygroup = new Group(this);
					mygroup.Add(m);
				}
			}
			void groupMake(Group g) { //업혀있는 말 끼리 업는 메소드
				if(!isGrouped()) {
					mygroup = g;
					g.Add(this);
				}
				else { //내 업힌 그룹을 죄다 추출해서 상대방 그룹에 추가
					Mal[] m = g.getMember();
					/*
					 * 업힌 말끼리 업는 과정
					 * */
				}
			}
			void groupLink(Group g) { //다른 말에서 그룹객체가 생성될때 링크
				mygroup = g;
			}
		}

		class Group{
			String team;
			ArrayList<Mal> list;

			Group(Mal m) { //말 여러개를 인자로 받아서 한번에 그룹으로 묶어버린다
				list = new ArrayList<Mal>();
				team = m.yourMaster().team;
				list.add(m);
			}
			void Add(Mal m) { //업힌 그룹에 말 추가
				m.groupLink(this);
				list.add(m);
			}
			boolean remove(Mal m) { //대상 말을 제거
				if(list.contains(m)) {
					m.groupLink(null);
					list.remove(m);
					return true;
				}
				else
					return false; //list에서 m을 찾지 못함
			}
			void catched() { //그룹이 잡혔으므로 내부 인원들 최다 퇴출시킴
				Mal[] m = (Mal[]) list.toArray();
				for(Mal i : m) {
					i.groupLink(null);
					i.catched();
				}
				list.clear();
			}
			void finish() { //업은 채로 포인트 획득, 내부 인원 역시 퇴출
				Mal[] m = (Mal[]) list.toArray();
				for(Mal i : m) {
					i.groupLink(null);
					i.finish();
				}
				list.clear();
			}
			Mal[] getMember() { //업혀있는 모든 말들을 배열로 바꿔서 리턴
				return (Mal[])list.toArray();
			}

		}
		
		/*class Onboard{
			private ArrayList<Object> list;
		}*/

	}

	public static void main(String[] args) throws Exception {
		pex.InitTeamName("잉여", "갓수");
		pex p1 = new pex(true,"히키코","@");
		pex p2 = new pex(true,"모리","$");
		p1.mal1.groupMake(p2.mal2);
		if(p1.mal1.isGrouped()) System.out.println("이 말은 업혔음");
		else System.out.println("안업혔음");
		System.out.println("이 그룹의 팀 이름 : "+p1.mal1.mygroup.team);
		//System.out.println("이 그룹에 속한 말 : ")
	}


}
