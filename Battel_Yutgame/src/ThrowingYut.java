
import java.util.Scanner;


public class ThrowingYut {

	Scanner scan = new Scanner(System.in);
	int playerYut = 0;
	int playerNumber;
	int playerInfoArray[][] = new int[4][5];
	// 플레이어의 정보를 저장하는 배열 [순번(플레이어 고유id)][이동할 윷칸]


	void throwing(int playerNumber){
		int yut;
		// 각 플레이어가 윷을 하나씩 던진다
		// 1은 앞(평평한 면), 0은 뒤(불룩한 면) > 1이 3개면 걸이다
		for (int times = 0; times <4 ;){
			if ((yut = scan.nextInt()) == 0 || (yut == 1))	{
				playerYut += yut;
				times++;
			}
			else
				// 0이나 1이 아닌 다른 값을 입력했을 경우 걸러낸다
				System.out.println("0 또는 1을 입력하셈");
		}
		// 집계해서 도개걸윷모 뭐가 나왔는지 플레이어에게 알려준다
		switch(playerYut){
		case 0:
			// 윷이나 모가 나오면 한번 더 던진다(움직일 수 있는 스택은 배열에 넣어야하나?)
			System.out.println("플레이어"+playerNumber+"님은 '모'가 나왔습니다. 대단하군요! 한 번 더 던집니다");
			++playerInfoArray[playerNumber][0];
			throwing(playerNumber);
			break;
		case 1:
			System.out.println("플레이어"+playerNumber+"님은 '백도'가 나왔습니다. 이것은 신의 한수가 될까요?");
			++playerInfoArray[playerNumber][1];
			break;
		case 2:
			System.out.println("플레이어"+playerNumber+"님은 '개'가 나왔습니다.");
			++playerInfoArray[playerNumber][2];
			break;
		case 3:
			System.out.println("플레이어"+playerNumber+"님은 '걸'이 나왔습니다.");
			++playerInfoArray[playerNumber][3];
			break;
		case 4:
			System.out.println("플레이어"+playerNumber+"님은 '윷'이 나왔습니다. 한 번 더 던집니다");
			++playerInfoArray[playerNumber][4];
			throwing(playerNumber);
			break;

		}
	//	scan.close();
		playerYut = 0; //초기화
	} // 모든 플레이어의 이동칸수가 정해짐

	// 플레이어에게 몇번째 말을 몇칸 움직일건지 정하라고 한다
}

