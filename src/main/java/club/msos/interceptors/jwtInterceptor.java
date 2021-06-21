package club.msos.interceptors;

import club.msos.util.JwtToken;
import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class jwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        HashMap<String, Object> map = new HashMap<>();
        String token = (String) session.getAttribute("token");
        try {
            JwtToken.isToken(token);
            map.put("message","ok");
            return true;
        } catch (Exception e) {
            session.removeAttribute("id");
            session.removeAttribute("ip");
            session.removeAttribute("name");
            session.removeAttribute("birthday");
            session.removeAttribute("email");
            session.removeAttribute("phone");
            response.sendRedirect("/html/login.html");
            map.put("message","error");
        }
        JSON json = (JSON) JSON.toJSON(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
