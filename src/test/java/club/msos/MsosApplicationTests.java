package club.msos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

@SpringBootTest
class MsosApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void redisTest() {

//        redisTemplate.opsForValue().set("username","501664112");
//        System.out.println(redisTemplate.opsForValue().get("username"));
        Date date = new Date();
//        int day = date.getDay();
//        System.out.println(day);
        System.out.println(redisTemplate.opsForHash().get("DayTime", "Wednesday"));
        System.out.println(redisTemplate.opsForHash().values("DayTime"));
    }

}
