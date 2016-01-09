


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
			private pex master;
			private int location;
			private boolean grouped; // 업혀있는지 여부를 저장함
			Mal() {
				master = null;
				location = -1;
				grouped = false;
			}
			Mal(pex pex) {
				this.master = pex;
				location = -1;
				grouped = false;
			}
			void setloc(int i) {
				location = i;
			}
			void catched() // 상대팀에게 잡혔어요
			{
				location = -1;
			}
			
			void finished() // 미국 갔어요
			{
				location = 777;
			}
			
			pex Master() {
				return master;
			}
			int getloc() {
				return location;
			}
			boolean isGrouped() {
				return grouped;
			}
			
		}
		class Grouped extends Mal{

			Grouped() {
				
			}
			Grouped(Mal ...mals) { //말 여러개를 인자로 받아서 한번에 그룹으로 묶어버린다
				//super(pex);
				// TODO Auto-generated constructor stub
			}
			void Add(Mal m) { //업힌 그룹에 말 추가
				
			}
			void remove(Mal m) { //대상 말을 제거
				
			}
			void catched() { //그룹이 잡혔으므로 내부 인원들 최다 퇴출시킴
				
			}
			
		}
		class Onboard{
			
		}

	}
	
	public static void main(String[] args) throws Exception {
		pex p = new pex(true,"히키코","@");
		
	}
	

}
