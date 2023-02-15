package Pjt_1_Api;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ParsingXmlTest {
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521/xe";
	static String user = "c##green";
	static String password = "green1234";

	static Connection con;
	static Statement stmt;
	static ResultSet rs;
	
	
	
	private static String getTagValue(String tag, Element eElement) { // TagName을 가져와서 nList에 저장
		NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

		Node nValue = (Node) nList.item(0);
		if (nValue == null) {
			return null;
		}
		return nValue.getNodeValue();
	}
	
	static void connDB() {
		try {
			Class.forName(driver);
//			System.out.println("jdbc driver loading success");
			con = DriverManager.getConnection(url, user, password);
//			System.out.println("oracle connection success.");
			stmt = con.createStatement();
//			System.out.println("statement create success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		int num;
		String code, groupname, name, kcal, nutri1, nutri2, nutri6, nutri7, nutri5;
			try {
//다른오픈소스로 시도
//			String key = "iYf+cv2eHcBTnZtc/iFa02ZBNklTGAQ50LaVeITdok98ZySevWFqb7KocBNp6ezMn2uj4LPg3La4HIe6sY2dYg=="; // 발급받은
//			String url =
//					"http://apis.data.go.kr/1470000/FoodNtrIrdntInfoService/getFoodNtrItdntList?ServiceKey=iYf%2Bcv2eHcBTnZtc%2FiFa02ZBNklTGAQ50LaVeITdok98ZySevWFqb7KocBNp6ezMn2uj4LPg3La4HIe6sY2dYg%3D%3D&numOfRows=100&pageNo=1&type=xml";																												// 인증키
//			String url = "http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1?serviceKey=" + key + "&pageNo=2&numOfRows=100&type=xml";																												// 인증키
//			String decodeServiceKey = URLDecoder.decode(key, "UTF-8");
//			String url = "https://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1?serviceKey="
//					+ decodeServiceKey + "&pageNo=2&numOfRows=100&type=xml"; // 요청 주소

			String key = "aa87913a3b694e37bb91"; // 발급받은 인증키
			String decodeServiceKey = URLDecoder.decode(key, "UTF-8");
			String url = "http://openapi.foodsafetykorea.go.kr/api/" + decodeServiceKey + "/I2790/xml/7001/8000"; // 요청 주소
//			String url = "http://openapi.foodsafetykorea.go.kr/api/sample/I2790/xml/1/5"; // 요청 주소

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(url);
			doc.getDocumentElement().normalize();

//			System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("row"); // 파싱할 tagname의 바로위

//			System.out.println("파싱할 리스트 수 : " + nList.getLength());
			
			connDB();

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					num = Integer.parseInt(getTagValue("NUM", eElement)) ;
					code = getTagValue("FOOD_CD", eElement);
					groupname = getTagValue("GROUP_NAME", eElement);
					name = getTagValue("DESC_KOR", eElement);
					kcal = getTagValue("NUTR_CONT1", eElement);
					nutri1 = getTagValue("NUTR_CONT2", eElement); //탄수화물
					nutri2 = getTagValue("NUTR_CONT3", eElement); //단백질
					nutri5 =getTagValue("NUTR_CONT4", eElement); //지방
					nutri7 = getTagValue("NUTR_CONT5", eElement); //당
					nutri6 = getTagValue("NUTR_CONT6", eElement); //나트륨
					
					//1. 탄수화물 2. 단백질 3. 채소 4. 칼슘 5. 지방 6. 나트륨 7. 당
					
					
					String sql = "insert into nutrition values(" + num +",'" + code +"','" + groupname +"','" + name +"','" + kcal +"','" + nutri1 + "','" +nutri2 + "','" +nutri5 +"','" + nutri7 +"','" + nutri6 +"','null','null')";
					
					try {
						boolean b = stmt.execute(sql);
						if (!b) {
							System.out.println("Insert sucess.\n");
						} else {
							System.out.println("Insert fail.\n");
						}
					} catch (SQLException e) {
						System.out.println(e);
					}
					
//					System.out.println("#############################");
//					System.out.println("번호 : " + getTagValue("NUM", eElement));
//					System.out.println("식품코드 : " + getTagValue("FOOD_CD", eElement));
//					System.out.println("지역명 : " + getTagValue("SAMPLING_REGION_NAME", eElement));
//					System.out.println("채취월 : " + getTagValue("SAMPLING_MONTH_NAME", eElement));
//					System.out.println("지역코드 : " + getTagValue("SAMPLING_REGION_CD", eElement));
//					System.out.println("채취월코드 : " + getTagValue("SAMPLING_MONTH_CD", eElement));
//					System.out.println("식품군 : 	" + getTagValue("GROUP_NAME", eElement));
//					System.out.println("식품이름 : " + getTagValue("DESC_KOR", eElement));
//					System.out.println("조사년도 : " + getTagValue("RESEARCH_YEAR", eElement));
//					System.out.println("제조사명 : " + getTagValue("MAKER_NAME", eElement));
//					System.out.println("자료출처 : " + getTagValue("SUB_REF_NAME", eElement));
//					System.out.println("총내용량 : " + getTagValue("SERVING_SIZE", eElement));
//					System.out.println("열량 : " + getTagValue("NUTR_CONT1", eElement));
//					System.out.println("탄수화물 : " + getTagValue("NUTR_CONT2", eElement));
//					System.out.println("단백질 : 	" + getTagValue("NUTR_CONT3", eElement));
//					System.out.println("지방 : " + getTagValue("NUTR_CONT4", eElement));
//					System.out.println("당류 : " + getTagValue("NUTR_CONT5", eElement));
//					System.out.println("나트륨 : " + getTagValue("NUTR_CONT6", eElement));
//					System.out.println("콜레스테롤 : " + getTagValue("NUTR_CONT7", eElement));
//					System.out.println("포화지방산 : " + getTagValue("NUTR_CONT8", eElement));
//					System.out.println("트렌스지방 : " + getTagValue("NUTR_CONT9", eElement));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
