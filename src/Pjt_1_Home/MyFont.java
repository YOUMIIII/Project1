package Pjt_1_Home;

import java.awt.Font;

public class MyFont {
	public Font f1, f2, f3, f2p, f16,f16b, f15, fLogLabel, fPlusLabel, fExLabel, fPlusMenuList, fNorthLabel;
	public Font f18, f17, f17b, f18b;
	public MyFont(){
		f1 = new Font("나눔고딕", Font.PLAIN, 23);
		f2 = new Font("나눔고딕", Font.BOLD, 13); // 버튼용
		f2p = new Font("나눔고딕", Font.PLAIN, 13); // 버튼용
		f3 = new Font("나눔고딕", Font.BOLD, 17); // 메인페이지 이름용
		fLogLabel = new Font("나눔고딕", Font.BOLD, 15);
		fPlusLabel = new Font("나눔고딕", Font.BOLD, 15); // 메뉴추가 레이블용
		fPlusMenuList = new Font("나눔고딕", Font.PLAIN, 15); // 메뉴추가 레이블용
		fExLabel = new Font("나눔고딕", Font.BOLD, 15);
		fNorthLabel = new Font("나눔고딕", Font.PLAIN, 16);
		f18 = new Font("나눔고딕", Font.PLAIN, 18);
		f18b = new Font("나눔고딕", Font.BOLD, 18);
		f16 = new Font("나눔고딕",Font.PLAIN, 16);
		f16b = new Font("나눔고딕",Font.BOLD, 16);
		f15 = new Font("나눔고딕",Font.PLAIN, 15);
		f17 = new Font("나눔고딕",Font.PLAIN, 17);
		f17b = new Font("나눔고딕",Font.BOLD, 17);
	}
}
