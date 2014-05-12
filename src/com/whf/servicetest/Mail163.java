package com.whf.servicetest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Part;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;


import android.util.Log;

public class Mail163 {
	private static final String TAG = "Mail163";

	/**  
	 * 以pop3方式读取邮件，此方法不能读取邮件是否为已读，已经通过测试  
	 * */  
	public void getEmail() {  
	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
	  
	    try {  
	        Properties props = System.getProperties();  
	        props.put("mail.smtp.host", "smtp.163.com");  
	        props.put("mail.smtp.auth", "true");  
	        PassAuthenticator pass = new PassAuthenticator();	//获取帐号密码
	        Session session = Session.getDefaultInstance(props, pass); 
	        
	        
	        URLName urln = new URLName("pop3", "pop3.163.com", 110, null,  
	                "wuhaifeng20110317", "whf257");  
	        // 邮件协议为pop3，邮件服务器是pop3.163.com，端口为110，用户名/密码为abcw111222/123456w  
	        Store store = session.getStore(urln);  
	        store.connect();  
	        Folder folder = store.getFolder("INBOX");  
	        folder.open(Folder.READ_WRITE);  
	        Message message[] = folder.getMessages();  
	        if (message.length > 0) {  
	            Map<String, Object> map;  
	            System.out.println("Messages's length: " + message.length);  
	            ReciveOneMail pmm = null;  
	            for (int i = 0; i < message.length; i++) {  
	                System.out.println("======================");  
	                pmm = new ReciveOneMail((MimeMessage) message[i]);  
	                System.out.println("Message " + i + " subject: "  
	                        + pmm.getSubject());  
	                System.out.println("Message " + i + " sentdate: "  
	                        + pmm.getSentDate());  
	                System.out.println("Message " + i + " replysign: "  
	                        + pmm.getReplySign());  
	  
	                boolean isRead = pmm.isNew();// 判断邮件是否为已读  
	                System.out.println("Message " + i + " hasRead: " + isRead);  
	                System.out.println("Message " + i + "  containAttachment: "  
	                        + pmm.isContainAttach((Part) message[i]));  
	                System.out.println("Message " + i + " form: "  
	                        + pmm.getFrom());  
	                System.out.println("Message " + i + " to: "  
	                        + pmm.getMailAddress("to"));  
	                System.out.println("Message " + i + " cc: "  
	                        + pmm.getMailAddress("cc"));  
	                System.out.println("Message " + i + " bcc: "  
	                        + pmm.getMailAddress("bcc"));  
	                pmm.setDateFormat("yy年MM月dd日 HH:mm");  
	                System.out.println("Message " + i + " sentdate: "  
	                        + pmm.getSentDate());  
	                System.out.println("Message " + i + " Message-ID: "  
	                        + pmm.getMessageId());  
	                // 获得邮件内容===============  
	                pmm.getMailContent((Part) message[i]);  
	                System.out.println("Message " + i + " bodycontent: \r\n"  
	                        + pmm.getBodyText());  
	                String file_path = File.separator + "mnt" + File.separator  
	                        + "sdcard" + File.separator;  
	                System.out.println(file_path);  
	                pmm.setAttachPath(file_path);  
	                pmm.saveAttachMent((Part) message[i]);  
	  
	                map = new HashMap<String, Object>();  
	                map.put("from", pmm.getFrom());  
	                map.put("title", pmm.getSubject());  
	                map.put("time", pmm.getSentDate());  
	                map.put("read", isRead);  
	                list.add(map);  
	            } 
	  
	        } else {  
	        	Log.i(TAG,"没有邮件");
	             
	        }  
	    } catch (NoSuchProviderException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    } catch (MessagingException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    } catch (Exception e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    }  
	} 
	class PassAuthenticator extends Authenticator  
	{
		public PasswordAuthentication getPasswordAuthentication()
		{
			String username = "wuhaifeng20110317@163.com";
			String pwd = "whf257";
			return new PasswordAuthentication(username, pwd);
		}
	}

}
