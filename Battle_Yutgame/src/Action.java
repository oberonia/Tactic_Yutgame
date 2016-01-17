import java.util.InputMismatchException;
import java.util.Scanner;

class Action implements Yut{

	/**
	 * 추후 모든 스캐너는 Action에서 따오는 것으로 잠정 결정
	 */
	static Scanner sc = new Scanner(System.in);

	/**
	 * user와 관련된 action을 모두 모아놓은 클래스
	 * @author PR
	 */
	static class user{


		/**
		 * Player 클래스에서 접근하게 되는 이름 입력 메소드
		 * @return
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
		 * @param t1 팀1에 등록된 사람 수, 이 수를 이용하여 팀 자동선택 기능을 제공 (각 팀 인원은 두명까지)
		 * @param t2 팀2에 등록된 사람 수
		 * @param TeamString1 팀1의 팀 이름
		 * @param TeamString2 팀2의 팀 이름
		 * @return
		 * 팀 선택과 오타 확인에 사용되는 team 변수를 반환
		 */
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

		/**
		 * MainFrame 클래스에서 접근하게 되는 플레이어 정보 입력하는 메소드. 전체 프로그램에서 이 함수는 한번만 실행할 것
		 * @param mp 원시 상태의 플레이어 몽땡이
		 * @return 정보가 입력된 플레이어 몽땡이(레퍼런스 형식)
		 */
		static Player[] InitPlayers(Player[] mp) {
			user.InitTeamString(); //최초로 두 팀의 이름을 정함. 전체 프로그램에서 이 함수는 한번만 실행할 것! 
			int t1=0,t2=0;
			for(int i=0;i<mp.length;i++){
				System.out.println("========"+(i+1)+"번 플레이어 정보 입력========");
				mp[i]= new Player(t1,t2);
				if(mp[i].team.equals(Player.getTeamName1())) t1++;
				else if(mp[i].team.equals(Player.getTeamName2())) ++t2;
				System.out.println("플레이어 이름 확인 : " + mp[i].name + ", Player " + i);
				System.out.println("팀 이름 확인 : " + mp[i].team + ", Player " + i);
			}//end of loop
			return mp;
		}//end of InitPlayers

	}//end of user class

	/**
	 * 윷판에서 일어나는 일들을 처리하고 Player의 변수를 제어/출력하는 일을 담당하는 메소드를 모아놓음
	 * @author PR
	 *
	 */
	static class board{



		/**
		 * 말 하나를 집어서 다음 칸으로 넘기거나 finish 처리를 하는 메쏘오드
		 * @param target 말의 정보가 담긴 int형 변수.
		 * @param trigger true인 경우 : 말을 처음 이동할 때를 가정하여 분기점을 만나면 꺾게 됨
		 * @param trigger false인 경우 : 말을 이동하던 중에 호출된 것으로 가정하여 분기점을 통과하게 됨		
		 * @param v0 윷보드의 벡터 (테두리)
		 * @param v1 윷보드의 벡터 (대각선 ↙)
		 * @param v2 윷보드의 벡터 (대각선 ↘)
		 * @param yb 윷판을 다루는 벡터를 저장한 클래스
		 * @return
		 * 말이 finish 상태가 된 경우 777 //
		 * 알 수 없는 오류를 뿜는 경우 -222222 //
		 * finish 상태가 된 말을 다시 움직이려고 할 경우 역시 777 //
		 */
		static int movNext(int target, boolean trigger, 
				YutBoard yb) {
			if(trigger) { //꺾습니다
				if(yb.v[2].contains(target)){  //v2에서 target에 해당하는 값을 찾음
					int tempIndex=yb.v[2].indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
					if(tempIndex==yb.v[2].indexOf(yb.v[2].lastElement()))  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
						return 777; 
					else 
						return yb.v[2].elementAt(tempIndex+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 리턴	
				}//if v2
				else if(yb.v[1].contains(target) && target!=(int)yb.v[1].lastElement()) { //없을 경우 v1에서 target값을 찾음
					return (int) yb.v[1].elementAt((yb.v[1].indexOf(target)+1));//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				}//else if v1
				else if(yb.v[0].contains(target)) {//없을 경우 v0에서 target값을 찾음	else if()
					int temp=yb.v[0].indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
					if(temp==yb.v[0].indexOf(yb.v[0].lastElement())) 
						return 777;  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					else 
						return (int) yb.v[0].elementAt(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 리턴	
				}//else if v0 
				else if(target==-1) return (int) yb.v[0].firstElement();
				else return -222222; //그래도 없으면 오류처리
			}//꺾습니다 끝
			else { //꺾지마
				if(yb.v[0].contains(target)) {//v0에서 target값을 찾음	else if()
					int temp=yb.v[0].indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
					if(temp==yb.v[0].indexOf(yb.v[0].lastElement())) 
						return 777;  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					else 
						return (int) yb.v[0].elementAt(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 리턴
				}//if v0
				else if(yb.v[1].contains(target)) { //v1에서 target값을 찾음
					return (int) yb.v[1].elementAt((yb.v[1].indexOf(target)+1));//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				}//else if v1
				else if(yb.v[2].contains(target)){  //v2에서 target에 해당하는 값을 찾음
					int temp=yb.v[2].indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
					if(temp==yb.v[2].indexOf(yb.v[2].lastElement())) 
						return 777;  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					else 
						return (int) yb.v[2].elementAt(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 리턴	
				}//else if v2
				else if(target==777) return 777; //피니시 된 말을 다시 움직이려고 할때 777 리턴
				else return -222222;
			}
		}


		/**
		 * MainFrame에서 접근하는 점수판 출력 메소드
		 * 현재 디버그용으로 제작됨 
		 * @param mp 전체 플레이어 몽땡이
		 */
		static void scoreboard(Player[] mp){
			System.out.println("Name\t"+"Team\t"+"Mal No.\t"+"Place\t"+"Mal Icon");
			for(int j=0; j<mp.length; j++){
				System.out.println(mp[j].name+"\t"+mp[j].team+
						"\t"+"mal1\t"+mp[j].mal1+"\t"+mp[j].malIcon);
				System.out.println(mp[j].name+"\t"+mp[j].team+
						"\t"+"mal2\t"+mp[j].mal2+"\t"+mp[j].malIcon);
			}

		}//end of scoreboard




		/**
		 * 이동할 칸 수를 입력받아 말을 이동시키는 메소드.
		 * 업기와 잡기 기능이 구현되어 있음.
		 * @param mp 전체 플레이어 몽땡이
		 * @param i 이 메소드를 호출한 플레이어에 해당하는 인덱스 (mp[i] = 호출한 플레이어가 된다.)
		 * @param yb 윷판을 다루는 벡터를 저장한 클래스
		 * @param MoveCount 말이 이동하는 칸 수
		 * @throws Exception 익셉션 새개끼
		 */
		static void MoveMal (Player[] mp, int i, YutBoard yb, int MoveCount) throws Exception {
			Player p = mp[i];
			if(MoveCount>5||MoveCount<0) throw new Exception("이동할 칸 수 오류발생\n해당값 : "+MoveCount);
			System.out.println("말 위치정보\nmal1 : "+p.mal1.location+"\nmal2 : "+p.mal2.location);
			System.out.print("움직일 말을 고르세요. ");
			int tempMal= select(1,2); //이동할 말 선택 임시저장

			switch(tempMal) {
			case 1:
				board.FriendlyMove(mp, i, yb, MoveCount, tempMal);
				if(board.EnemyCatch(mp, yb, p.team, p.mal1.location)){
					System.out.println("상대팀 말을 잡았으므로 윷을 한번 더 던집니다.");
					if(YutDebugMode)
						Debug.ThrowYut(p);
					else if(SplitYutMode) board.ThrowYutSplit(p);
					else board.ThrowYut(p);
				}
				else {
					board.GroupMaking(mp, i, tempMal);
				}
				break;
			case 2:
				board.FriendlyMove(mp, i, yb, MoveCount, tempMal); 
				if(board.EnemyCatch(mp, yb, p.team, p.mal2.location)){
					System.out.println("상대팀 말을 잡았으므로 윷을 한번 더 던집니다.");
					if(YutDebugMode)
						Debug.ThrowYut(p);
					else if(SplitYutMode) board.ThrowYutSplit(p);
					else board.ThrowYut(p);
				}
				else {
					board.GroupMaking(mp, i, tempMal);
				}
				break;
			default:
				System.out.println("select 함수 내부처리 오류 : "+tempMal);
			}
			p.decreaseMv(MoveCount-1);
		} //end of MoveMal


		/**
		 * 윷을 자동으로 랜덤하게 던진다.
		 * YutDebugMode와 SplitYutMode가 false 상태면 호출됨
		 * @param p 자기 차례 플레이어
		 * @return
		 * 도 개 걸 윷 모 중 하나
		 */
		static int ThrowYut(Player p) {
			p.resetMv();
			System.out.println(p.team+"팀 "+p.name+" 플레이어 윷 던지기");
			int tempMove = Debug.keygen.nextInt(5); //이동할 칸 수 랜덤생성+임시저장
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


		/**
		 * 윷을 나눠서 던지는 모드
		 * YutDebugMode가 false이고 SplitYutMode가 true면 호출된다
		 * @param p 자기 차례의 플레이어
		 */
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
					yut = sc.nextInt();
				}
				catch(InputMismatchException ex){
					System.out.println("딴짓말고 0 또는 1을 입력하셈- 유효 윷 갯수 : "+times);
					sc.nextLine(); // 쓰레기값은 쓰레기통으로 버리고 새로운 값을 입력받을 준비를 한다
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
				System.out.println(p.name+"님은 '모'가 나왔습니다. 대단하군요! 한 번 더 던집니다");
				p.increaseMv(모);
				playerYut = 0; // 한 번 더 던지기 위해 초기화
				ThrowYutSplit(p);
				break;
			case 1:
				System.out.println(p.name+"님은 '백도'가 나왔습니다. 이것은 신의 한수가 될까요?");
				p.increaseMv(도);
				break;
			case 2:
				System.out.println(p.name+"님은 '개'가 나왔습니다.");
				p.increaseMv(개);
				break;
			case 3:
				System.out.println(p.name+"님은 '걸'이 나왔습니다.");
				p.increaseMv(걸);
				break;
			case 4:
				System.out.println(p.name+"님은 '윷'이 나왔습니다. 한 번 더 던집니다");
				p.increaseMv(윷);
				playerYut = 0; // 한 번 더 던지기 위해 초기화
				ThrowYutSplit(p);
				break;
			default:
				System.out.println("던지라는 윷은 안던지고! 나온 윷의 합 >> " + playerYut);
				break;
			}
		} // 모든 플레이어의 이동칸수가 정해짐

		/**
		 * 플레이어가 이동 가능한 횟수를 출력
		 * 윷을 던진 후에 출력된다.
		 * @param p 자기 차례의 플레이어
		 */
		static void yutThrowResult(Player p){
			System.out.println(p.name+" 플레이어의 이동 가능 횟수는 다음과 같습니다.");
			System.out.println("도\t개\t걸\t윷\t모");
			System.out.println(Player.mv[도]+"\t"+Player.mv[개]
					+"\t"+Player.mv[걸]+"\t"+Player.mv[윷]
							+"\t"+Player.mv[모]);
		}

		/**
		 * 업힌거 전체이동하는 함수. 단, 시작점에 있을 경우 업지 않은 것으로 간주한다.
		 * @param mp 플레이어 몽땡이
		 * @param i 자기 차례의 플레이어에 해당하는 인덱스(mp[i] = 자기 차례의 플레이어)
		 * @param yb 윷판을 다루는 벡터를 저장한 클래스
		 * @param team 호출한 플레이어의 소속 팀
		 * @param MoveCount 호출한 플레이어가 이동하기로 한 칸 수
		 * @param targetMal 호출한 플레이어가 이동하기로 한 말의 위치정보
		 * @param num 말 1과 2를 구분하는 변수
		 * @throws Exception 익셉션 새개끼
		 */
		static void FriendlyMove(Player[] mp,int i, YutBoard yb, int MoveCount, int num) throws Exception {
			String team = mp[i].team;
			Player p = mp[i];
			switch(num) {
			case 1:
				if(mp[i].mal1.isCatched()) {
					System.out.print(p.name+" 플레이어의 말 1 이동! ("+p.mal1+" -> ");

					// 지정된 수만큼 말 이동하는 부분
					p.mal1.location=Action.board.movNext(p.mal1.location, true,yb);
					for(int j=1;j<MoveCount;j++)
						p.mal1.location=Action.board.movNext(p.mal1.location, false,yb);
					// 지정 말 이동 end

					System.out.println(p.mal1+")");
					break;
				}

				break;
			case 2:
				if(mp[i].mal2.isCatched()) {
					System.out.print(p.name+" 플레이어의 말 2 이동! ("+p.mal2+" -> ");
					p.mal2.location=Action.board.movNext(p.mal2.location, true,yb);
					for(int j=1;j<MoveCount;j++)
						p.mal2.location=Action.board.movNext(p.mal2.location, false,yb);
					System.out.println(p.mal2+")");
					break;
				}
				break;
			default:
				System.out.println("예상되지 않은 num 값 = "+num);
				return;

			}
			/*
			if(targetMal==-1 && num==1) { //mal1이 끝장났음 = 일반이동
				System.out.print(p.name+" 플레이어의 말 1 이동! ("+p.mal1+" -> ");

				// 지정된 수만큼 말 이동하는 부분
				p.mal1.location=Action.board.movNext(p.mal1.location, true,yb);
				for(int j=1;j<MoveCount;j++)
					p.mal1.location=Action.board.movNext(p.mal1.location, false,yb);
				// 지정 말 이동 end

				System.out.println(p.mal1+")");
				return;
			} //일반이동 말1 end


			else if(targetMal==-1 && num==2) {
				System.out.print(p.name+" 플레이어의 말 2 이동! ("+p.mal2+" -> ");
				p.mal2.location=Action.board.movNext(p.mal2.location, true,yb);
				for(int j=1;j<MoveCount;j++)
					p.mal2.location=Action.board.movNext(p.mal2.location, false,yb);
				System.out.println(p.mal2+")");
				return;
			}
			//여기까지는 일반이동 (시작점에서 이동할 경우)
			 * */

			switch(num) {

			case 1:
				if(p.mal1.isGrouped()) {
					p.mal1.mygroup.Move(board.movNext(p.mal1.location, true,yb));
					for(int j=1;j<MoveCount;j++)
						p.mal1.mygroup.Move(board.movNext(p.mal1.location, false,yb));
				}
				break;
			case 2:
				if(p.mal2.isGrouped()) {
					p.mal2.mygroup.Move(board.movNext(p.mal2.location, true,yb));
					for(int j=1;j<MoveCount;j++)
						p.mal2.mygroup.Move(board.movNext(p.mal2.location, false,yb));
				}
				break;
			default:
				System.out.println("예상되지 않은 num 값 = "+num);
				return;
			}
			/*
			for(Player p : mp) {
				if(p.team.equals(team) && targetMal!=-1) {
					if(targetMal==p.mal1) {
						System.out.print(p.name+" 플레이어의 말 1 이동! ("+p.mal1+" -> ");
						p.mal1.location=board.movNext(p.mal1.location, true,yb);
						for(int j=1;j<MoveCount;j++)
							p.mal1.location=board.movNext(p.mal1.location, false,yb);
						System.out.println(p.mal1+")");
					}
					if(targetMal==p.mal2){
						System.out.print(p.name+" 플레이어의 말 2 이동! ("+p.mal2+" -> ");
						p.mal2=board.movNext(p.mal2, true,yb);
						for(int j=1;j<MoveCount;j++)
							p.mal2=board.movNext(p.mal2, false,yb);
						System.out.println(p.mal2+")");
					}
				}
			}
			 */
		}
		static void GroupMaking(Player[] mp,int i,int num) {
			num--;
			int target;
			switch(num) {
			case 0:
				target = mp[i].mal1.location;
				break;
			case 1:
				target = mp[i].mal2.location;
				break;
			default:
				System.out.println("예상되지 않은 num 값 = "+num);
				return;
			}
			for (Player p : mp) {
				if(target == p.mal1.location) {
					mp[i].GroupMake(num, p.mal1);
				}
				else if(target == p.mal2.location) {
					mp[i].GroupMake(num, p.mal2);
				}
				

			}

		}




		/**
		 * 자신의 차례에 이동한 후 호출하게 되고, 자리에 적이 있는 경우 전체몰살 및 true 반환
		 * @param mp 전체 플레이어 몽땡이
		 * @param yb 윷보드
		 * @param team 호출한 플레이어의 소속 팀
		 * @param targetMal 호출한 플레이어가 이동하기로 한 말의 위치정보
		 * @return
		 * 적을 잡았을 경우 true,그렇지 않은 경우 false
		 * 
		 * @throws Exception 익셉션 새개끼
		 */
		static boolean EnemyCatch (Player[] mp,YutBoard yb, String team, int targetMal) throws Exception {
			int Enemy[] = new int[4];
			int i=0;
			boolean CatchEnemy = false;

			for(Player p : mp) {
				if(!p.team.equals(team)) {
					Enemy[i++] = p.mal1.location;
					Enemy[i++] = p.mal2.location;
				}
			}
			for(int j : Enemy) {
				if(j==targetMal) {
					for(Player p : mp) {
						//이동한 뒤의 말의 위치가 일치하고 인자로 받은 팀이 이 플레이어의 팀과 일치하지 않을 때
						if(p.mal1.location==targetMal && !(p.team.equals(team))) {
							p.resetMal1();
							CatchEnemy=true;
							System.out.println(p.name+" 플레이어의 말 1이 잡혔습니다!");
						}
						if(p.mal2.location==targetMal && !(p.team.equals(team))) {
							p.resetMal2();
							CatchEnemy=true;
							System.out.println(p.name+" 플레이어의 말 2가 잡혔습니다!");
						}
					}//for p : mp
					break;
				}//if k==AfterMove
			}//for k : Enemy

			return CatchEnemy;
		}



		/**
		 *  Action 클래스의 myPhase에서만 사용되는 메소드
		 *  윷 던진 후에 이동할 칸이 뭐 남았는지 알아내서 뭐 고를건지 선택까지 완료하는 과정 
		 * @param p 자기 차례의 플레이어
		 * @return
		 * 실제로 이동하게 되는 칸 수. 모를 고르면 5가 반환됨.
		 */
		static int selectMvString(Player p) {

			int mvCount=0; //이동할 칸 종류를 저장하는 배열을 초기화할 임시변수
			for(int i=0;i<5;i++)
				if(Player.mv[i]>0) mvCount++;
			if(mvCount==1) {
				for(int i=0;i<5;i++)
					if(Player.mv[i]>0) {
						System.out.println(YutName[i]+" 만큼 이동합니다.");
						return i+1;
					}
			}
			String[] mvParam = new String[mvCount];
			for(int i=0,a=0;i<5;i++) {
				if(Player.mv[i]>0) mvParam[a++]=new String(YutName[i]);
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

		/**
		 * 플레이어의 차례에 하는 모든 행동을 실행
		 * @param mp 플레이어 몽땡이 배열
		 * @param i 플레이어의 순서
		 * @param yb 윷판을 다루는 벡터를 저장한 클래스
		 * @throws Exception select 중 mv 선택 오류시 방출됨
		 */
		static void myPhase(Player[] mp, int i, YutBoard yb) throws Exception {
			Player p = mp[i];
			int MoveCount;
			if(YutDebugMode) {
				Debug.ThrowYut(p);
				while(!p.isEmptyMv()) {
					yutThrowResult(p); //mv 출력 및 결과표시
					MoveCount = selectMvString(p);
					if(EnemyOrFriendMode)
						board.MoveMal(mp,i,yb,MoveCount);
					else 
						Debug.MoveMal(p, yb, MoveCount);
				}
			}
			else if(SplitYutMode) {
				ThrowYutSplit(p); //윷던지기
				while(!p.isEmptyMv()) {
					yutThrowResult(p); //mv 출력 및 결과표시
					MoveCount = selectMvString(p);
					if(MoveCount<0) throw new Exception("selectMvString 메소드 에러");
					if(EnemyOrFriendMode)
						board.MoveMal(mp,i,yb,MoveCount);
					else 
						Debug.MoveMal(p, yb, MoveCount);
				}

			}//end of SplitYutMode
			else //윷을 랜덤하게 네개 알아서 던져줌
				board.MoveMal(mp,i,yb,board.ThrowYut(p) );
			System.out.println(p.name+"의 차례가 끝났습니다. 다음 플레이어의 차례로 넘어갑니다.");
		}//end of myPhase

	}//end of board class


	/**
	 * 문자열 목록을 받아 반드시 하나를 고르게 하는 함수
	 * @param strings 선택할 수 있는 보기를 저장한 문자열 배열
	 * @return strings에서 선택된 하나의 문자열 반환
	 */
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

	/**
	 * int 목록을 받아 반드시 하나를 고르게 하는 함수 
	 * @param nums 선택할 수 있는 int를 저장한 배열
	 * @return nums에서 선택된 하나의 int
	 */
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
