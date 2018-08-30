package controller;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SendFromExchange {

	public static void send(List<String> sent,boolean auth,boolean ssl,boolean tls,String from,String pass,String to,String subj,String msg,String host)
	{
	   // Get system properties
	   Properties properties = System.getProperties();
	   // Setup mail server
	   properties.put("mail.smtp.host", host);
	   properties.put("mail.smtp.user", from);
	   properties.put("mail.smtp.password", pass);
	   properties.put("mail.smtp.port", "587");
	   if(auth)properties.put("mail.smtp.auth", "true");
	   if(tls)properties.put("mail.smtp.starttls.enable", "true");
	   if(ssl)properties.put("mail.smtp.ssl.enable", "true");

	   // Get the default Session object.
	   Session session = Session.getDefaultInstance(properties);

	   try{
	      // Create a default MimeMessage object.
	      MimeMessage message = new MimeMessage(session);

	      // Set From: header field of the header.
	      message.setFrom(new InternetAddress(from));

	      // Set To: header field of the header.
	      message.addRecipient(Message.RecipientType.TO,
	                               new InternetAddress(to));

	      // Set Subject: header field
	      message.setSubject(subj);

	      // Now set the actual message
	      message.setText(msg);

	      // Send message
	      Transport transport = session.getTransport("smtp");
	      transport.connect(host, from, pass);
	      transport.sendMessage(message, message.getAllRecipients());
	      transport.close();
	      sent.add("Sikeresen elküldve...");
	      System.out.println("Sikeresen elküldve....");
	   }catch (MessagingException mex) {
	      mex.printStackTrace();
	   }
	}
}
