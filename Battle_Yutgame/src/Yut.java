/**Abstract class(메소드와 클래스의 프로토타입만 정해지는 함수)
 * 인터페이스는 조금 더 쉬운 버젼(여기서 상수를 선언하면, 무조건 상수)
*/
interface Yut {
	int 도 = 0;
	int 개 = 1;
	int 걸 = 2;
	int 윷 = 3;
	int 모 = 4;
	String[] YutName = {"도","개","걸","윷","모"};
	
	/****디버그 : 주석에 적힌 모드로 실행할 때는 true로 입력해주세요**********/
	boolean InitDebugMode = true; //팀명과 플레이어명이 임의로 지정됨
	boolean AloneDebugMode = true; //플레이어가 혼자가 됨
	
		/**YutDebugMode가 활성화된 경우 SplitYutMode보다 우선합니다.**/
		boolean YutDebugMode = true; //윷 던질 값을 직접 입력가능
		boolean SplitYutMode = false; //윷을 하나씩 던진다
		/**동시에 활성화할 수 없는 모드이므로 주의하세요.**/
		
	boolean EnemyCatchMode = false; //상대팀의 말을 잡을 수 있음(미구현)
	/*****디버그 : 해당 모드가 아닐때는 false로 입력해주세요**********/

}
