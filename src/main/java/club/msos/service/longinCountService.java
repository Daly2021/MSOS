package club.msos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class longinCountService {

    @Autowired
    RedisTemplate redisTemplate;
    @Scheduled(cron = "0 0 0 * * ?")
    public void longinCount(){
        Date date = new Date();
        int day = date.getDay();
        String Day;
        if (day==1){
            Day="Monday";
        }else if(day==2){
            Day="Tuesday";
        }else if(day==3){
            Day="Wednesday";
        }else if(day==4){
            Day="Thursday";
        }else if(day==5){
            Day="Friday";
        }else if(day==6){
            Day="Saturday";
        }else {
            Day="Sunday";
        }
        redisTemplate.opsForHash().put("DayTime",Day,0);
    }

}
