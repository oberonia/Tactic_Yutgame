import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;


public class YutBoard {
	public void Print(Vector<Integer> v) {
		Enumeration<Integer> e = v.elements();
		while(e.hasMoreElements()) {
			int temp=(int)e.nextElement();
			if(temp!=(int)v.lastElement())
				System.out.print(temp+", ");
			else
				System.out.print(temp);
		}
		System.out.println();
	}

	@SuppressWarnings("unchecked")
	public void vectorGenerate() {
		int v1[] = {23,19,14,10,6,5,4,3,2,1,7,11,16,20,24,25,26,27,28,29}; //add가 귀찮은 배열 1 
		int v2[] = {6,9,13,15,17,21,24}; //add가 귀찮은 배열 2
		int v3[] = {1,8,12,15,18,22,29}; //add가 귀찮은 배열 3
		Vector<Integer> v[] = new Vector[3];
		v[0] = new Vector<Integer>(20); //20개, 우하단부터 시작하는 테두리 윷판, 검색 2순위
		v[1] = new Vector<Integer>(7); //7개, 대각선 방향(↙), 검색 1순위
		v[2] = new Vector<Integer>(7); //7개, 대각선 방향(↘), 검색 0순위
		
		for (int i=0;i<20;i++)
			v[0].add(v1[i]);
		for (int i=0;i<7;i++)
			v[1].add(v2[i]);
		for (int i=0;i<7;i++)
			v[2].add(v3[i]);
		
		
		for(int i=0;i<3;i++) {
			Enumeration<Integer> e = v[i].elements();
			while(e.hasMoreElements()) {
				int temp=(int)e.nextElement();
				if(temp!=(int)v[i].lastElement())
					System.out.print(temp+", ");
				else
					System.out.print(temp);
			}
			System.out.println();
		}
		while(true) {
			int target=-1,mov=0;
			Scanner sc = new Scanner(System.in);
			System.out.println("Target Location : "+target);
			System.out.println("input(a,s,d,f,g,q)>>");
			switch(sc.next()) {
			case "a": case "A":	mov=1; break;
			case "s": case "S":	mov=2; break;
			case "d": case "D":	mov=3; break; 
			case "f": case "F":	mov=4; break;
			case "g": case "G":	mov=5; break;
			case "q": case "Q":
				System.out.println("Terminated");
				return;
			default:
				System.out.println("<WRONG INPUT>try again");
				continue;
			}
			target = 23; mov--;
			for(int i=0;i<mov;i++) {
				target = movNext(v[0],v[1],v[2],target, false);
			}
		}

	}
/*
 * 말 하나를 집어서 다음 칸으로 넘기거나 finish 처리를 하는 메쏘오드
 * target에 말 정보가 들어가고, trigger로 말을 꺾을지 말지를 결정하게 됨
 * trigger가 true인 경우 : 말을 처음 이동할 때를 가정하여 분기점을 만나면 꺾게 됨
 * trigger가 false인 경우 : 망릉 이동하던 중에 호출된 것으로 가정하여 분기점을 통과하게 됨
 * 말이 finish 상태가 된 경우 777 리턴
 * 알 수 없는 오류를 뿜는 경우 -222222 리턴 
 * */
	private static int movNext(Vector<Integer> v0, Vector<Integer> v1,
			Vector<Integer> v2, int target, boolean trigger) {
		if(trigger) { //꺾습니다
			if(v2.contains(target)){  //v2에서 target에 해당하는 값을 찾음
				int temp=v2.elementAt(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(temp==v2.lastElement()) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return v2.indexOf(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				//해당 value를 리턴	
			}
			else if(v1.contains(target) && target!=v1.lastElement()) { //없을 경우 v1에서 target값을 찾음
				return v1.indexOf(v1.elementAt(target)+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
			}
			else if(v0.contains(target)) {//없을 경우 v0에서 target값을 찾음	else if()
				int temp=v0.elementAt(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(temp==v0.lastElement()) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return v0.indexOf(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				//해당 value를 리턴	
			}
			else return -222222; //그래도 없으면 오류처리
		}
		else { //꺾지마
			if(v0.contains(target)) {//v0에서 target값을 찾음	else if()
				int temp=v0.elementAt(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(temp==v0.lastElement()) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return v0.indexOf(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				//해당 value를 리턴
			}
			else if(v1.contains(target)) { //v1에서 target값을 찾음
				return v1.indexOf(v1.elementAt(target)+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
			}
			else if(v2.contains(target)){  //v2에서 target에 해당하는 값을 찾음
				int temp=v2.elementAt(target); //찾은 경우 target을 포함하는 v의 인덱스값을 구함
				if(temp==v2.lastElement()) {  //구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
					return 777; 
				}
				else return v2.indexOf(temp+1);//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
				//해당 value를 리턴	
			}
			else return -222222;
		}
	}
}
