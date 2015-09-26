
import java.util.InputMismatchException;
import java.util.Scanner;


public class ThrowingYut {

	Scanner scan = new Scanner(System.in);
	int playerYut = 0;
	int playerNumber;
	int playerInfoArray[][] = new int[4][5];
	int yut;
	// 플레이어의 정보를 저장하는 배열 [순번(플레이어 고유id)][이동할 윷칸]


void throwing(int playerNumber){
		
		// 각 플레이어가 윷을 하나씩 던진다
		// 1은 앞(평평한 면), 0은 뒤(불룩한 면) > 1이 3개면 걸이다
		System.out.println("모든 플레이어가 윷을 던집니다.");
		System.out.println("평평한 면을 내려면 1, 불룩한 면은 0을 입력하세요");
		for (int times = 0; times <4 ; times++){
			System.out.println("유효 윷 갯수 >> "+times+" 나온 윷의 합 >> "+playerYut);
			
			try{
				yut = scan.nextInt();
			}
			catch(InputMismatchException ex){
				System.out.println("딴짓말고 0 또는 1을 입력하셈- 유효 윷 갯수 >> "+times);
				scan.nextLine(); // 쓰레기값은 쓰레기통으로 버리고 새로운 값을 입력받을 준비를 한다
				times--; // 쓰레기 들어갔던 자리가 비었으니 그 자리에 채워넣어야 한다
				continue; //for문을 시행한다
			}

			if ((yut == 0) || (yut == 1)){
				playerYut += yut;
			}
			else {
				// 0이나 1이 아닌 다른 값을 입력했을 경우 걸러낸다
				System.out.println("0 또는 1을 입력하셈- 유효 윷 갯수 >> "+times);
				times--;
			}

		} // end of for loop

		// 집계해서 도개걸윷모 뭐가 나왔는지 플레이어에게 알려준다
		switch(playerYut){
		case 0:
			// 윷이나 모가 나오면 한번 더 던진다(움직일 수 있는 스택은 배열에 넣어야하나?)
			System.out.println("플레이어"+playerNumber+"님은 '모'가 나왔습니다. 대단하군요! 한 번 더 던집니다");
			++playerInfoArray[playerNumber][0];
			playerYut = 0; // 한 번 더 던지기 위해 초기화
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
			playerYut = 0; // 한 번 더 던지기 위해 초기화
			throwing(playerNumber);
			break;
		default:
			System.out.println("던지라는 윷은 안던지고! 나온 윷의 합 >> " + playerYut);
			break;
		}
	//	scan.close(); 여기서 닫으면 모든 jave.util.Scanner가 닫힌다
		playerYut = 0; //초기화
	} // 모든 플레이어의 이동칸수가 정해짐

	// 플레이어에게 몇번째 말을 몇칸 움직일건지 정하라고 한다
	void result(int playerNumber){
		System.out.println("플레이어"+playerNumber+"님의 이동 가능 횟수는 다음과 같습니다.");
		System.out.println("도\t개\t걸\t윷\t모");
		System.out.println(playerInfoArray[playerNumber][1]+"\t"+playerInfoArray[playerNumber][2]
				+"\t"+playerInfoArray[playerNumber][3]+"\t"+playerInfoArray[playerNumber][4]
						+"\t"+playerInfoArray[playerNumber][0]);
	}
}
