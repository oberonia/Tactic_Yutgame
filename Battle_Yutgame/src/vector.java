import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;


public class vector {
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
		int v1[] = {23,19,14,10,6,5,4,3,2,1,7,11,16,20,24,25,26,27,28,29}; //20, 윷판 테두리 부분
		int v2[] = {6,9,13,15,17,21,24}; //7, 윷판 대각선(↙방향)
		int v3[] = {1,8,12,15,18,22,29}; //7 윷판 대각선 (↘방향)
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
			System.out.println("Target 위치 : "+target);
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
		int out;
		//v2���� target�� �ش��ϴ� ���� ã�� if()
		//���� ��� v1���� target���� ã��	else if()
		//���� ��� v0���� target���� ã��	else if()
		//�׷��� ������ ����ó��			else()
		
		//ã�� ��� target�� �����ϴ� v�� �ε������� ����
		//1. ���� �ε����� laetElement�� ��� ����� v�κ����� �̵��ϰų� finish ǥ��
		//���� �ε��� ���� �ϳ� �������Ѽ� �� �ε����� �ִ� value�� ã��
		//�ش� value�� ����
		return 0;
	}

}
