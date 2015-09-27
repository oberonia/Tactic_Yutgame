import java.util.Vector;
import java.util.Scanner;

class Action {
	/*
	 * 추후 모든 스캐너는 Action에서 따오는 것으로 잠정 결정
	 */
	static Scanner sc = new Scanner(System.in);
	
	
	/* Player 클래스에서 접근하게 되는 이름 입력 메소드
	 * 단순히 이름을 입력받아 반환한다.
	 */
	static String inputName(){
		System.out.print("플레이어 이름 입력>>");
		String name = sc.next();
		System.out.println("이름 확인 : " + name);
		return name;
	} //end of inputName
	
	/* MainFrame 클래스에서 접근하게 되는 팀명 정하는 메소드
	 * 
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
	
	
	/* Player 클래스에서 접근하게 되는 팀 선택 메소드
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
		else{
			while(true){
				System.out.print("팀 선택("+TeamString1+"/"+TeamString2+" 입력)>>");
				team = sc.next();
				if(!team.equals(TeamString1)&&!team.equals(TeamString2)) {
					System.out.println("잘못된 입력!\n*팀명은 대소문자를 구분합니다.");
					continue;
				}
				else break;
			}
			return team;
		}
	}//end of selectTeam	
	
	
	
	/*
	 * 말 하나를 집어서 다음 칸으로 넘기거나 finish 처리를 하는 메쏘오드
	 * target에 말 정보가 들어가고, trigger로 말을 꺾을지 말지를 결정하게 됨
	 * trigger가 true인 경우 : 말을 처음 이동할 때를 가정하여 분기점을 만나면 꺾게 됨
	 * trigger가 false인 경우 : 말을 이동하던 중에 호출된 것으로 가정하여 분기점을 통과하게 됨
	 * 말이 finish 상태가 된 경우 777 리턴
	 * 알 수 없는 오류를 뿜는 경우 -222222 리턴 
	 * finish 상태가 된 말을 다시 움직이려고 할 경우 역시 777을 리턴
	 * */
	static int movNext(int target, boolean trigger, Vector<Integer> v0, Vector<Integer> v1, Vector<Integer> v2) {
		if(trigger) { //꺾습니다
			if(v2.contains(target)){  //v2에서 target에 해당하는 값을 찾음
				int tempIndex=v2.indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(tempIndex==v2.indexOf(v2.lastElement())) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return v2.elementAt(tempIndex+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				//해당 value를 리턴	
			}
			else if(v1.contains(target) && target!=(int)v1.lastElement()) { //없을 경우 v1에서 target값을 찾음
				return (int) v1.elementAt((v1.indexOf(target)+1));//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
			}
			else if(v0.contains(target)) {//없을 경우 v0에서 target값을 찾음	else if()
				int temp=v0.indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(temp==v0.indexOf(v0.lastElement())) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return (int) v0.elementAt(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				//해당 value를 리턴	
			}
			else if(target==-1) return (int) v0.firstElement();
			else return -222222; //그래도 없으면 오류처리
		}
		else { //꺾지마
			if(v0.contains(target)) {//v0에서 target값을 찾음	else if()
				int temp=v0.indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(temp==v0.indexOf(v0.lastElement())) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return (int) v0.elementAt(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				//해당 value를 리턴
			}
			else if(v1.contains(target)) { //v1에서 target값을 찾음
				return (int) v1.elementAt((v1.indexOf(target)+1));//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
			}
			else if(v2.contains(target)){  //v2에서 target에 해당하는 값을 찾음
				int temp=v2.indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(temp==v2.indexOf(v2.lastElement())) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return (int) v2.elementAt(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				//해당 value를 리턴	
			}
			else if(target==777) return 777; //피니시 된 말을 다시 움직이려고 할때 777 리턴
			else return -222222;
		}
	}
	
	
	/* MainFrame에서 접근하는 점수판 출력 메소드
	 * 현재 디버그용으로 제작됨
	 */
	static void scoreboard(Player[] mp){
		System.out.println("Name\t"+"Team\t"+"Mal No.\t"+"Location");
		for(int j=0; j<mp.length; j++){
			System.out.println(mp[j].getName()+"\t"+mp[j].getTeam()+"\t"+"mal1\t"+mp[j].getMal1());
			System.out.println(mp[j].getName()+"\t"+mp[j].getTeam()+"\t"+"mal2\t"+mp[j].getMal2());
		}
		
	}
	
	/* MainFrame 클래스에서 접근하게 되는 플레이어 정보 입력하는 메소드. 전체 프로그램에서 이 함수는 한번만 실행할 것
	 * 
	 */
	static Player[] InitPlayers(Player[] mp) {
		Action.InitTeamString(); //최초로 두 팀의 이름을 정함. 전체 프로그램에서 이 함수는 한번만 실행할 것! 
		int t1=0,t2=0;
		for(int i=0;i<mp.length;i++){
			System.out.println("========"+(i+1)+"번 플레이어 정보 입력========");
			mp[i]= new Player(t1,t2);
			if(mp[i].getTeam().equals(Player.getTeamName1())) t1++;
			else if(mp[i].getTeam().equals(Player.getTeamName2())) ++t2;
			System.out.println("플레이어 이름 확인 : " + mp[i].getName() + ", Player " + i);
			System.out.println("팀 이름 확인 : " + mp[i].getTeam() + ", Player " + i);
		}//end of for loop
		return mp;
	}
	
	static void MoveMal (Player p, YutBoard yb, int tempMove) {
		System.out.println("말 위치정보\nmal1 : "+p.getMal1()+"\nmal2 : "+p.getMal2());
		System.out.print("움직일 말을 고르세요 (1/2 입력)>>");
		int tempMal; //이동할 말 선택 임시저장
		
		while(true) {
			tempMal=Action.sc.nextInt();
			switch(tempMal) {
			case 1:
				System.out.print("mal1 이동! ("+p.getMal1()+" -> ");
				p.putMal1(Action.movNext(p.getMal1(), true,yb.v0(),yb.v1(),yb.v2()));
				for(int i=1;i<tempMove;i++)
					p.putMal1(Action.movNext(p.getMal1(), false,yb.v0(),yb.v1(),yb.v2()));
				System.out.println(p.getMal1()+")");
				break;
			case 2:
				System.out.print("mal2 이동! ("+p.getMal2()+" -> ");
				p.putMal2(Action.movNext(p.getMal2(), true,yb.v0(),yb.v1(),yb.v2()));
				for(int i=1;i<tempMove;i++)
					p.putMal2(Action.movNext(p.getMal2(), false,yb.v0(),yb.v1(),yb.v2()));
				System.out.println(p.getMal2()+")");
				break;
			default:
				System.out.println("잘못된 값입니다. 다시 입력하세요.");
				System.out.print("움직일 말을 고르세요 (1/2 입력)>>");
				continue;
			}
			break;
		}
	} //end of MoveMal
	
	
	
	static int ThrowYut(Player p,YutBoard yb) {
		
		System.out.println(p.getTeam()+"팀 "+p.getName()+" 플레이어 윷 던지기");
		int tempMove = p.keygen.nextInt(5); //이동할 칸 수 랜덤생성+임시저장
		System.out.print("퉤에엣 : ");
		switch(tempMove) {
		case 0:
			System.out.println("도");
			break;
		case 1:
			System.out.println("개");
			break;
		case 2:
			System.out.println("걸");
			break;
		case 3:
			System.out.println("윷");
			break;
		case 4:
			System.out.println("모");
			break;
		default:
			System.out.println("뭐임 왜 있을 수 없는 오류가...");
			return -1;
		}
		return tempMove;
		//int temp2 = SelectInput(1,2);
	} //end of ThrowYut
	

}
