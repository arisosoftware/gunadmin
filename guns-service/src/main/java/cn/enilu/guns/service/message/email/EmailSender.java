package cn.enilu.guns.service.message.email;

import org.springframework.core.io.InputStreamSource;


public interface EmailSender {
	/**
	 * 发送邮件
	 * 
	 * @param from
	 * @param to
	 * @param cc
	 * @param title
	 * @param content
	 * @return
	 */
	boolean sendEmail(String from, String to, String cc, String title, String content);

	/**
	 * 发送带附件的邮件
	 * 
	 * @param from
	 * @param to
	 * @param cc
	 * @param title
	 * @param content
	 * @param attachmentFilename
	 * @param inputStreamSource
	 * @return
	 */
	boolean sendEmail(String from, String to, String cc, String title, String content, String attachmentFilename,
			InputStreamSource inputStreamSource);
}
