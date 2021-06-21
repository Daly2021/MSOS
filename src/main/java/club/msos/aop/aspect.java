package club.msos.aop;

import club.msos.pojo.UpdateArticle;
import club.msos.pojo.UpdateUser;
import club.msos.service.updateArticleService;
import club.msos.service.updateUserService;
import club.msos.util.GetIp;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@Component
@Aspect
public class aspect {
    @Autowired
    updateArticleService updateArticleService;
    @Autowired
    updateUserService updateUserService;
    @Pointcut("execution(public * club.msos.controller..*.*(..))")
    public void Pointcut() {
    }


    @Around(value ="Pointcut()&&@annotation(log)")
    public Object doAround(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        Object result = joinPoint.proceed();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
             HttpServletRequest request = (HttpServletRequest) requestAttributes
                      .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpSession session=attr.getRequest().getSession(true);
        GetIp ip = new GetIp(request);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT-8"));
        if (log.Type().equals("用户删除")||log.Type().equals("用户更新")||log.Type().equals("用户登录")||log.Type().equals("头像更新")){
                UpdateUser updateUser = new UpdateUser();
                updateUser.setUpdateUser_do(log.Type());
                updateUser.setUpdateUser_id((String) request.getAttribute("UpdateUser_id"));
                updateUser.setUser_id((String) session.getAttribute("id"));
                updateUser.setUpdateUser_ip(ip.getIpAddress());
                updateUser.setUpdateUser_time(dateFormat.format(new Date()));
                updateUserService.insertUpdateUser(updateUser);

            }else {
            UpdateArticle updateArticle = new UpdateArticle();
            updateArticle.setUpdateArticle_do(log.Type());
            updateArticle.setArticle_id((Integer) request.getAttribute("UpdateArticle_id"));
            updateArticle.setUpdateArticle_id((String) session.getAttribute("id"));
            updateArticle.setUpdateArticle_ip(ip.getIpAddress());
            updateArticle.setUpdateArticle_time(dateFormat.format(new Date()));
            System.out.println(updateArticle);
            updateArticleService.insertUpdateArticle(updateArticle);
            }

        return result;
    }
}
