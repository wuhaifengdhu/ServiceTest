package com.whf.servicetest;

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

import android.util.Log;

public class MailStore {
	
	@SuppressWarnings("static-access")
	public boolean sendEmail(String emailString)
	{
		Properties props = new Properties();
		
		props.put("mail.smtp.protocol", "smtp");
		props.put("mail.smtp.auth", "true");  		//设置要验证
		props.put("mail.smtp.host", "smtp.163.com");	//设置host
		props.put("mail.smtp.port", "25");	//设置端口

		PassAuthenticator pass = new PassAuthenticator();	//获取帐号密码
		
		Session session = Session.getInstance(props, pass);
		
		try
		{
			//配置发送及接收邮箱
			InternetAddress fromAddress, toAddress;
			fromAddress = new InternetAddress("wuhaifeng20110317@163.com", "myMailFrom");
			fromAddress = new InternetAddress("wuhaifengdhu@163.com", "my163");
			toAddress	= new InternetAddress("wuhaifeng20110317@163.com", "myMailTo");
			
			//配置发送信息
			MimeMessage message = new MimeMessage(session);
			message.setContent(emailString, "text/plain; charset=utf-8");
		    System.out.println("emailString:"+emailString);
			message.setSubject("email test");
			message.setFrom(fromAddress);
			message.addRecipient(Message.RecipientType.TO, toAddress);
			message.saveChanges();
			
			//连接邮箱并发送
			Transport transport = session.getTransport("smtp"); 
			transport.connect("smtp.163.com", "wuhaifeng20110317@163.com", "whf257");
			transport.send(message);
			 
			transport.close();	
		} catch (MessagingException e)
		{
			// TODO Auto-generated catch block
			Log.i("Msg", e.getMessage());
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	
	class PassAuthenticator extends Authenticator  
	{
		public PasswordAuthentication getPasswordAuthentication()
		{
			String username = "wuhaifengdhu@163.com";
			String pwd = "wuya910615";
			return new PasswordAuthentication(username, pwd);
		}
	}
}
