package club.msos.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
 public @interface Log {
    String Type() default "";  // 操作类型
 }