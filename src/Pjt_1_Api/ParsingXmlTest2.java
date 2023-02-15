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


public class ParsingXmlTest2 {
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
		String name, way, kind, kcal, nutri1, nutri2, nutri4, nutri5, ingredient, info, manual, manual2;
		manual = "";
		manual2 = "";
			try {
			String key = "aa87913a3b694e37bb91"; // 발급받은 인증키
			String decodeServiceKey = URLDecoder.decode(key, "UTF-8");
			String url = "http://openapi.foodsafetykorea.go.kr/api/" + decodeServiceKey + "/COOKRCP01/xml/101/200"; // 요청 주소

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(url);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("row"); // 파싱할 tagname의 바로위

			connDB();

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					num = Integer.parseInt(getTagValue("RCP_SEQ", eElement)) ;
					name = getTagValue("RCP_NM", eElement);
					way = getTagValue("RCP_WAY2", eElement);
					kind = getTagValue("RCP_PAT2", eElement);
					kcal = getTagValue("INFO_ENG", eElement);
					nutri1 = getTagValue("INFO_CAR", eElement); //탄수화물
					nutri2 = getTagValue("INFO_PRO", eElement); //단백질
					nutri4 =getTagValue("INFO_FAT", eElement); //지방
					nutri5 = getTagValue("INFO_NA", eElement); //당
					ingredient = getTagValue("HASH_TAG", eElement); //나트륨
					info = getTagValue("RCP_PARTS_DTLS", eElement); //재료
					for(int i = 1; i<=9; i++) {
						String m = getTagValue("MANUAL0" + i, eElement);
//						if(m.equals("")) {
//							m = "";
//						}
						manual += m + "\n";
					}
					for(int i = 10; i <= 20; i++) {
						String m = getTagValue("MANUAL" + i, eElement);
//						if(m.equals("")) {
//							m = "";
//						}
						manual2 += m + "\n";
					}
					
					
					String sql = "insert into recipe values(" + num +",'" + name +"','" + way +"','" + kind +"','" + kcal +"','" + nutri1 + "','" +nutri2 + "','0','" +nutri4 +"','" + nutri5 +"','" + ingredient +"','" + info+ "','" + manual +"','" + manual2 +"')";
					
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
					manual = "";
					manual2 = "";
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
