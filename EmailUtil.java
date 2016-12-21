
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * compile 'com.sun.mail:javax.mail:1.5.6'
 */
public class EmailUtil {
    /**
     * 发送邮件
     * @param fromMail  发送方Email地址，比如“yemengsky@163.com”
     * @param user      用户名（一般同上），比如“yemengsky@163.com”
     * @param password  密码（现在一般都用授权码，根据邮件提供商不同而不同）
     * @param toMail    接收方Email地址，比如“yemengsky@gmail.com”
     * @param smtpAddr  发送方stmp地址，比如“smtp.163.com”
     * @param mailTitle 邮件主题
     * @param mailContent 内容
     * @throws Exception
     */
    public static void sendMail(String fromMail, String user, String password,
                                String toMail, String smtpAddr,
                                String mailTitle,
                                String mailContent) throws Exception {

        Properties props = new Properties(); //可以加载一个配置文件
        // 使用smtp：简单邮件传输协议
        props.put("mail.smtp.host", smtpAddr);//存储发送邮件服务器的信息
        props.put("mail.smtp.auth", "true");//同时通过验证
        props.put("mail.smtp.ssl.enable", "true");//（注意）163邮箱只能用ssl！！！
        
        Session session = Session.getInstance(props);//根据属性新建一个邮件会话
        session.setDebug(true); //有他会打印一些调试信息。

        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
        message.setFrom(new InternetAddress(fromMail));//设置发件人的地址
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));//设置收件人,并设置其接收类型为TO
        message.setSubject(mailTitle);//设置标题
        //设置信件内容
//        message.setText(mailContent); //发送 纯文本 邮件 todo
        message.setContent(mailContent, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富
        message.setSentDate(new Date());//设置发信时间
        message.saveChanges();//存储邮件信息

        //发送邮件
//        Transport transport = session.getTransport("smtp");
        Transport transport = session.getTransport();
        transport.connect(user, password);
        transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
        transport.close();
    }
}
