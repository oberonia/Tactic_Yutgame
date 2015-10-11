import java.util.InputMismatchException;
import java.util.Vector;
import java.util.Scanner;

class Action implements Yut{

	/**
	 * 추후 모든 스캐너는 Action에서 따오는 것으로 잠정 결정
	 */
	static Scanner sc = new Scanner(System.in);

	/**
	 * user와 관련된 action을 모두 모아놓은 클래스
	 */
	static class user{

		/** 
		 * Player 클래스에서 접근하게 되는 이름 입력 메소드
		 * 단순히 이름을 입력받아 반환한다.
		 */
		static String inputName(){
			System.out.print("플레이어 이름 입력>>");
			String name = sc.next();
			System.out.println("이름 확인 : " + name);
			return name;
		} //end of inputName

		/** 
		 * MainFrame 클래스에서 접근하게 되는 팀명 정하는 메소드
		 */
		static void InitTeamString() {
			String TeamString1, TeamString2;
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
			Player.InitTeamName(TeamString1, TeamString2);
		}

		/** 
		 * Player 클래스에서 접근하게 되는 팀 선택 메소드
		 * t1 : 팀1에 등록된 사람 수, 이 수를 이용하여 팀 자동선택 기능을 제공 (각 팀 인원은 두명까지)
		 * t2 : 팀2에 등록된 사람 수
		 * TeamString1 : 팀1의 팀 이름
		 * TeamString1 : 팀2의 팀 이름
		 * team : 팀 선택과 오타 확인에 사용되고, 반환값으로 사용되는 팀 이름
		 * */
		static String selectTeam(int t1, int t2, String TeamString1, String TeamString2){
			String team;
			if(t1>1) {
				System.out.println("다른 팀에 여분의 자리가 없으므로 자동으로 "+TeamString2+" 팀으로 선택됩니다.");
				return TeamString2;
			}
			else if (t2>1) {
				System.out.println("다른 팀에 여분의 자리가 없으므로 자동으로 "+TeamString1+" 팀으로 선택됩니다.");
				return TeamString1;
			}
			else {
				System.out.print("팀 선택");
				team = select(TeamString1,TeamString2);
				return team;
			}
		}//end of selectTeam	

		static Player[] InitPlayers(Player[] mp) {
			Action.user.InitTeamString(); //최초로 두 팀의 이름을 정함. 전체 프로그램에서 이 함수는 한번만 실행할 것! 
			int t1=0,t2=0;
			for(int i=0;i<mp.length;i++){
				System.out.println("========"+(i+1)+"번 플레이어 정보 입력========");
				mp[i]= new Player(t1,t2);
				if(mp[i].getTeam().equals(Player.getTeamName1())) t1++;
				else if(mp[i].getTeam().equals(Player.getTeamName2())) ++t2;
				System.out.println("플레이어 이름 확인 : " + mp[i].getName() + ", Player " + i);
				System.out.println("팀 이름 확인 : " + mp[i].getTeam() + ", Player " + i);
			}//end of loop
			return mp;
		}//end of InitPlayers

	}//end of user class

	static class board{
		/**
		 * 말 하나를 집어서 다음 칸으로 넘기거나 finish 처리를 하는 메쏘오드
		 * target에 말 정보가 들어가고, trigger로 말을 꺾을지 말지를 결정하게 됨
		 * trigger가 true인 경우 : 말을 처음 이동할 때를 가정하여 분기점을 만나면 꺾게 됨
		 * trigger가 false인 경우 : 말을 이동하던 중에 호출된 것으로 가정하여 분기점을 통과하게 됨
		 * 말이 finish 상태가 된 경우 777 리턴
		 * 알 수 없는 오류를 뿜는 경우 -222222 리턴 
		 * finish 상태가 된 말을 다시 움직이려고 할 경우 역시 777을 리턴
		 * */
		static int movNext(int target, boolean trigger, 
				Vector<Integer> v0, Vector<Integer> v1, Vector<Integer> v2) {
			if(trigger) { //꺾습니다
				if(v2.contains(target)){  //v2에서 target에 해당하는 값을 찾음
					int tempIndex=v2.indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
					if(tempIndex==v2.indexOf(v2.lastElement()))  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
						return 777; 
					else 
						return v2.elementAt(tempIndex+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 리턴	
				}//if v2
				else if(v1.contains(target) && target!=(int)v1.lastElement()) { //없을 경우 v1에서 target값을 찾음
					return (int) v1.elementAt((v1.indexOf(target)+1));//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				}//else if v1
				else if(v0.contains(target)) {//없을 경우 v0에서 target값을 찾음	else if()
					int temp=v0.indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
					if(temp==v0.indexOf(v0.lastElement())) 
						return 777;  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					else 
						return (int) v0.elementAt(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 리턴	
				}//else if v0 
				else if(target==-1) return (int) v0.firstElement();
				else return -222222; //그래도 없으면 오류처리
			}//꺾습니다 끝
			else { //꺾지마
				if(v0.contains(target)) {//v0에서 target값을 찾음	else if()
					int temp=v0.indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
					if(temp==v0.indexOf(v0.lastElement())) 
						return 777;  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					else 
						return (int) v0.elementAt(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 리턴
				}//if v0
				else if(v1.contains(target)) { //v1에서 target값을 찾음
					return (int) v1.elementAt((v1.indexOf(target)+1));//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				}//else if v1
				else if(v2.contains(target)){  //v2에서 target에 해당하는 값을 찾음
					int temp=v2.indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
					if(temp==v2.indexOf(v2.lastElement())) 
						return 777;  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					else 
						return (int) v2.elementAt(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 리턴	
				}//else if v2
				else if(target==777) return 777; //피니시 된 말을 다시 움직이려고 할때 777 리턴
				else return -222222;
			}
		}


		/** 
		 * MainFrame에서 접근하는 점수판 출력 메소드
		 * 현재 디버그용으로 제작됨
		 */
		static void scoreboard(Player[] mp){
			System.out.println("Name\t"+"Team\t"+"Mal No.\t"+"Location");
			for(int j=0; j<mp.length; j++){
				System.out.println(mp[j].getName()+"\t"+mp[j].getTeam()+"\t"+"mal1\t"+mp[j].getMal1());
				System.out.println(mp[j].getName()+"\t"+mp[j].getTeam()+"\t"+"mal2\t"+mp[j].getMal2());
			}

		}//end of scoreboard

		/** 
		 * MainFrame 클래스에서 접근하게 되는 플레이어 정보 입력하는 메소드. 전체 프로그램에서 이 함수는 한번만 실행할 것
		 */

		static void MoveMal (Player p, YutBoard yb, int MoveCount) throws Exception {
			if(MoveCount>5||MoveCount<0) throw new Exception("이동할 칸 수 오류발생\n해당값 : "+MoveCount);
			System.out.println("말 위치정보\nmal1 : "+p.getMal1()+"\nmal2 : "+p.getMal2());
			System.out.print("움직일 말을 고르세요. ");
			int tempMal= select(1,2); //이동할 말 선택 임시저장

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



		static int ThrowYut(Player p) {
			p.resetMv();
			System.out.println(p.getTeam()+"팀 "+p.getName()+" 플레이어 윷 던지기");
			int tempMove = p.keygen.nextInt(5); //이동할 칸 수 랜덤생성+임시저장
			System.out.print("퉤에엣 : ");
			switch(tempMove) {
			case 0:
				System.out.println("도");
				p.increaseMv(도);
				break;
			case 1:
				System.out.println("개");
				p.increaseMv(개);
				break;
			case 2:
				System.out.println("걸");
				p.increaseMv(걸);
				break;
			case 3:
				System.out.println("윷");
				p.increaseMv(윷);
				System.out.println("한번 더 던질거임");
				ThrowYut(p);
				break;
			case 4:
				System.out.println("모");
				p.increaseMv(모);
				System.out.println("한번 더 던질거임");
				ThrowYut(p);
				break;
			default:
				System.out.println("뭐임 왜 있을 수 없는 오류가...");
				return -1;
			}
			return tempMove;
			//int temp2 = SelectInput(1,2);
		} //end of ThrowYut



		static void ThrowYutSplit(Player p){
			int playerYut = 0;
			int yut;

			// 각 플레이어가 윷을 하나씩 던진다
			// 1은 앞(평평한 면), 0은 뒤(불룩한 면) > 1이 3개면 걸이다
			System.out.println("모든 플레이어가 윷을 던집니다.");
			System.out.println("평평한 면을 내려면 1, 불룩한 면은 0을 입력하세요");
			for (int times = 0; times <4 ; times++){
				System.out.println("유효 윷 갯수 : "+times+", 나온 윷의 합 : "+playerYut);

				try{
					System.out.print("입력>>");
					yut = Action.sc.nextInt();
				}
				catch(InputMismatchException ex){
					System.out.println("딴짓말고 0 또는 1을 입력하셈- 유효 윷 갯수 : "+times);
					Action.sc.nextLine(); // 쓰레기값은 쓰레기통으로 버리고 새로운 값을 입력받을 준비를 한다
					times--; // 쓰레기 들어갔던 자리가 비었으니 그 자리에 채워넣어야 한다
					continue; //for문을 시행한다
				}

				if ((yut == 0) || (yut == 1)){
					playerYut += yut;
				}
				else {
					// 0이나 1이 아닌 다른 값을 입력했을 경우 걸러낸다
					System.out.println("0 또는 1을 입력하셈- 유효 윷 갯수 : "+times);
					times--;
				}

			} // end of loop

			// 집계해서 도개걸윷모 뭐가 나왔는지 플레이어에게 알려준다
			switch(playerYut){
			case 0:
				// 윷이나 모가 나오면 한번 더 던진다(움직일 수 있는 스택은 배열에 넣어야하나?)
				System.out.println(p.getName()+"님은 '모'가 나왔습니다. 대단하군요! 한 번 더 던집니다");
				p.increaseMv(모);
				playerYut = 0; // 한 번 더 던지기 위해 초기화
				ThrowYutSplit(p);
				break;
			case 1:
				System.out.println(p.getName()+"님은 '백도'가 나왔습니다. 이것은 신의 한수가 될까요?");
				p.increaseMv(도);
				break;
			case 2:
				System.out.println(p.getName()+"님은 '개'가 나왔습니다.");
				p.increaseMv(개);
				break;
			case 3:
				System.out.println(p.getName()+"님은 '걸'이 나왔습니다.");
				p.increaseMv(걸);
				break;
			case 4:
				System.out.println(p.getName()+"님은 '윷'이 나왔습니다. 한 번 더 던집니다");
				p.increaseMv(윷);
				playerYut = 0; // 한 번 더 던지기 위해 초기화
				ThrowYutSplit(p);
				break;
			default:
				System.out.println("던지라는 윷은 안던지고! 나온 윷의 합 >> " + playerYut);
				break;
			}
		} // 모든 플레이어의 이동칸수가 정해짐

		// 플레이어에게 몇번째 말을 몇칸 움직일건지 정하라고 한다
		static void yutThrowResult(Player p){
			System.out.println(p.getName()+" 플레이어의 이동 가능 횟수는 다음과 같습니다.");
			System.out.println("도\t개\t걸\t윷\t모");
			System.out.println(p.getMv(도)+"\t"+p.getMv(개)
					+"\t"+p.getMv(걸)+"\t"+p.getMv(윷)
					+"\t"+p.getMv(모));
		}

		static void statusCall(){

		}

		static void AreYouInMyTeam(String team){ //넌 나의 노예이니?
			//if(노예)
			//나와 함께가자
			//else
			//넌 돌아가
		}

		static void WhoisThere(Player[] mp) { //갔더니 누군가가 있다
			//if(만난놈이 적이다)
			//	catchMal(); //잡았다 요놈!
			//else 그게 아님
			//	withMal(); //어부바
		}


		void catchMal(){
			//말이 도착한 위치에 다른 말이 있습니까?
			// 적군이면 상대의 말 위치를 초기화하고
			// 나는 윷을 한번 더 던집니다.
		}

		void withMal(){
			//말이 도착한 위치에 다른 말이 있습니까?
			// 아군이면 말 위치를 강제로 싱크합니다. 하하하하
			// 앞으로 값이 같이 안 바뀌면 안되는거임...
		}


		/** 
		 *  Action 클래스의 myPhase에서만 사용되는 메소드
		 *  윷 던진 후에 이동할 칸이 뭐 남았는지 알아내서 뭐 고를건지 선택까지 완료하는 과정
		 *  반환값은 도~모 중 하나를 담은 String
		 */
		static int selectMvString(Player p) {

			int mvCount=0; //이동할 칸 종류를 저장하는 배열을 초기화할 임시변수
			for(int i=0;i<5;i++)
				if(p.getMv(i)>0) mvCount++;
			if(mvCount==1) {
				for(int i=0;i<5;i++)
					if(p.getMv(i)>0) {
						System.out.println(YutName[i]+" 만큼 이동합니다.");
						return i+1;
					}
			}
			String[] mvParam = new String[mvCount];
			for(int i=0,a=0;i<5;i++) {
				if(p.getMv(i)>0) mvParam[a++]=new String(YutName[i]);
			}
			String tempMv = select(mvParam);
			System.out.println(tempMv+" 만큼 이동합니다.");
			if(tempMv.equals("도")) return 도+1;
			else if(tempMv.equals("개")) return 개+1;
			else if(tempMv.equals("걸")) return 걸+1;
			else if(tempMv.equals("윷")) return 윷+1;
			else if(tempMv.equals("모")) return 모+1;
			else return -1;
		}


		static void myPhase(Player p, YutBoard yb) throws Exception {
			int MoveCount;
			if(YutDebugMode) {
				Debug.ThrowYut(p);
				while(!p.isEmptyMv()) {
					yutThrowResult(p); //mv 출력 및 결과표시
					MoveCount = selectMvString(p);
					Action.board.MoveMal(p,yb,MoveCount);
				}
			}
			else if(SplitYutMode) {
				ThrowYutSplit(p); //윷던지기
				while(!p.isEmptyMv()) {
					yutThrowResult(p); //mv 출력 및 결과표시
					MoveCount = selectMvString(p);
					if(MoveCount<0) throw new Exception("selectMvString 메소드 에러");
					if(EnemyCatchMode){
						System.out.println("경고 : EnemyCatchMode 모드는 미구현 상태입니다.");
						System.out.println("따라서 기본 모드의 메소드를 실행합니다.");
						MoveMal(p,yb,MoveCount);
					}
					else {
						MoveMal(p,yb,MoveCount);
					}
				}

			}//end of SplitYutMode
			else 
				Action.board.MoveMal(p,yb,Action.board.ThrowYut(p) );

			System.out.println(p.getName()+"의 차례가 끝났습니다. 다음 플레이어의 차례로 넘어갑니다.");

			//이동된 위치에 다른말이 있음 -> WhoisThere호출

		}//end of myPhase


		static void BeforeMoveMalIcon(Player p, int whichMal){ 
			// 움직이기 전의 말의 위치를 보드에서 안보이게 보드의 상태를 업데이트합니다.
			int tempMalPosition;
			if (whichMal == 1) { //MoveMal()에서 select의 결과물인 tempMal의 값
				tempMalPosition = p.getMal1();
				int a = (tempMalPosition-1)/7;
				int b = (tempMalPosition-1)%7;
				YutBoard.boardPaper[a][b] = "O";
			}
			else { 
				tempMalPosition = p.getMal2();
				int a = (tempMalPosition-1)/7;
				int b = (tempMalPosition-1)%7;
				YutBoard.boardPaper[a][b] = "O";
			}
			//임시) 각 플레이어는 임의로 지정된 말 표식을 받습니다. @#%^
			//한칸 이동할 때마다 boardpaper에 업데이트 합니다.
			//ㄴ 값을 넘겨받기 전의 위치를 알려면 임시 변수가 필요
			//ㄴ 임시 변수 위치에 해당하는 특문을 O으로 바꾸고,
			//ㄴ 말의 현재 위치를 받아와서 보드에 있는 같은 위치에 특문을 넣습니다.
			//ㄴ 임시 변수의 위치값을 업데이트합니다.

			//moveMal에서 이동할 때마다 보드도 함께 업데이트
			//어떤 말을 골랐는지 모르니 메소드로 만들어서 두 번 호출해야 함
		}//end of BeforeMoveMalIcon

	}//end of board class


	static String select(String ...strings) {
		while(true) {
			int a=0;
			System.out.print("(");
			for(String s : strings) {
				System.out.print(s);
				if(++a==strings.length);
				else System.out.print("/");
			}
			System.out.print(" 입력)>>");
			String input = sc.next();
			for(String s : strings) {
				if(s.equals(input))
					return s;
			}
			System.out.println("입력 오류! 다시 입력하세요.");
		}
	}//end of select
	static int select(int ...nums) {
		while(true) {
			int a=0;
			System.out.print("(");
			for(int i : nums) {
				System.out.print(i);
				if(++a==nums.length);
				else System.out.print("/");
			}
			System.out.print(" 입력)>>");
			int input = sc.nextInt();
			for(int i : nums) {
				if(input==i)
					return input;
			}
			System.out.println("입력 오류! 다시 입력하세요.");
		}
	}//end of select
}//end of Action
