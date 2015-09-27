import java.util.Vector;
import java.util.Scanner;

class Action {
	static Scanner sc = new Scanner(System.in);
	
	static String inputName(){
		System.out.print("플레이어 이름 입력>>");
		String name = sc.next();
		System.out.println("이름 확인 : " + name);
		return name;
	} //inputName
	
	
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
	 * */
	static int movNext(int target, boolean trigger, Vector v0, Vector v1, Vector v2) {
		if(trigger) { //꺾습니다
			if(v2.contains(target)){  //v2에서 target에 해당하는 값을 찾음
				int tempIndex=v2.indexOf(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(tempIndex==v2.indexOf(v2.lastElement())) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return v2.indexOf(tempIndex+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
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

}
