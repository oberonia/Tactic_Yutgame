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
		int v1[] = {23,19,14,10,6,5,4,3,2,1,7,11,16,20,24,25,26,27,28,29}; //20
		int v2[] = {6,9,13,15,17,21,24}; //7
		int v3[] = {1,8,12,15,18,22,29}; //7
		Vector<Integer> v[] = new Vector[3];
		v[0] = new Vector<Integer>(20);
		v[1] = new Vector<Integer>(7);
		v[2] = new Vector<Integer>(7);
		
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
				target = movNext(v[0],v[1],v[2],target);
			}
		}
		
	}

	private static int movNext(Vector<Integer> v0, Vector<Integer> v1,
			Vector<Integer> v2, int target) {
		//v2에서 target에 해당하는 값을 찾음 if()
		//없을 경우 v1에서 target값을 찾음	else if()
		//없을 경우 v0에서 target값을 찾음	else if()
		//그래도 없으면 오류처리			else()

		//찾은 경우 target을 포함하는 v의 인덱스값을 구함
		//1. 구한 인덱스가 laetElement인 경우 연결된 v부분으로 이동하거나 finish 표시
		//구한 인덱스 값을 하나 증가시켜서 그 인덱스에 있는 value를 찾음
		//해당 value를 리턴
		return 0;
	}

}
