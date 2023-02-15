package Pjt_1_Mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

class MyAuthenticator extends Authenticator{
   private String fromMail;
   private String fromPassword;
   
   public MyAuthenticator(String fromMail,String fromPassword) {
      this.fromMail=fromMail;
      this.fromPassword=fromPassword;
   }
   
   protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(fromMail,fromPassword);
   }
}

public class MailTest {
   private String fromMail;
   private String fromPassword;
   
   public MailTest() {
      this.fromMail="youmi8255@gmail.com";
      this.fromPassword="amazejkrygrfficv";
   }
   
   public void sendMail(String toMail,String toName,String subject,String content) {
      
      try {
         Properties props = new Properties();
         props.put("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.port", "465");
         
         props.put("mail.smtp.ssl.enable", "true");
         props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
         props.put("mail.transport.protocol", "smtp");
         props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         props.put("mail.smtp.user", "youmi8255@gmail.com");
         props.put("mail.smtp.password", "amazejkrygrfficv");
         props.put("mail.smtp.ssl.protocols", "TLSv1.2");
         
         
         Session session;
         if(fromMail!=null && fromPassword!=null && fromMail.length()>0 && fromPassword.length()>0) {
            props.put("mail.smtp.auth","true");
            MyAuthenticator ma=new MyAuthenticator(fromMail, fromPassword);
            session=Session.getDefaultInstance(props,ma);
            
         }else {
            session = Session.getDefaultInstance(props,null);
         }

         MimeMessage msg=new MimeMessage(session);
         
//         msg.setHeader("Content-Type", "text/html");
         msg.setHeader("Content-Type", "text/html; charset=utf-8");
         
//         msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail,toName));
         msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail,toName,"utf-8"));
         //TO : 받는 사람 , CC : 받는사람(참조인) , BCC : 받는사람(숨은참조인)
         
         msg.setSubject(subject);
         
//         msg.setContent(content,"text/html");
         msg.setContent(content,"text/html; charset=utf-8");
         msg.setSentDate(new java.util.Date());
         
         Transport.send(msg);
         JOptionPane.showMessageDialog(null, "작성해주신 메일로 비밀번호를 전송하였습니다.","비밀번호 전송 완료",JOptionPane.PLAIN_MESSAGE);
         
      }catch(UnsupportedEncodingException e) {
         e.printStackTrace();
      }catch(MessagingException e) {
         e.printStackTrace();
      }
      
   }
   
//   public static void main(String[] args) {
//      String toMail="youmi8255@naver.com";
//      String toName="강유미";
//      String subject="비밀번호 확인";
//      //이미지 절대경로 넣기
//      //저작권 확인하기
//      //이미지 우클릭하여 링크 복사한 값을 넣어야함
//      String content="비밀번호는 ";
//      
//      MailTest mI = new MailTest();
//      mI.sendMail(toMail, toName, subject, content);
//   }
}
//MimeMessage객체의 설정값을 text/html로 하기