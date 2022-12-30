package Pjt_1_Home;

import java.awt.Font;

public class MyFont {
	Font f1, f2, f3, f2p, fLogLabel, fPlusLabel, fExLabel;
	MyFont(){
		f1 = new Font("맑은 고딕", Font.PLAIN, 23);
		f2 = new Font("맑은 고딕", Font.BOLD, 13); // 버튼용
		f2p = new Font("맑은 고딕", Font.PLAIN, 13); // 버튼용
		f3 = new Font("맑은 고딕", Font.BOLD, 17); // 메인페이지 이름용
		fLogLabel = new Font("맑은 고딕", Font.BOLD, 15);
		fPlusLabel = new Font("맑은 고딕", Font.BOLD, 15); // 메뉴추가 레이블용
		fExLabel = new Font("맑은 고딕", Font.BOLD, 15);
	}
}
