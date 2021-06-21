package club.msos.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@Async
public class emileSend {

    @Autowired
    JavaMailSenderImpl mailSender;

    public  void Send(String user_email,String user_id) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
        messageHelper.setSubject("亲爱的"+user_id+"您正在通过邮箱修改密码");
//        String url = "<a href=http://localhost:8088/ToUpdatePassword?username=" + name + " style='color:red'/>点击此处前往修改密码!</a>";
        messageHelper.setText("<a href=http://60.205.188.140:8888/ToUpdatePassword?user_id="+user_id+" style='color:red'/>点击此处前往修改密码!</a>",true);
        messageHelper.setFrom("2574833532@qq.com");
        messageHelper.setTo(user_email);

        mailSender.send(mimeMessage);
    }

}
