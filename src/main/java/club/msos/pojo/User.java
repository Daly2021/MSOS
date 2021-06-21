package club.msos.pojo;

import com.alibaba.fastjson.annotation.JSONType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JSONType(orders={"username","password","name","email","phone","xb","location","role"})
@ApiModel(value = "用户实体类",description = "用户对象")
public class User {
    @ApiModelProperty(value = "用户名")
    private String user_id;
    @ApiModelProperty(value = "密码")
    private String user_password;
    @ApiModelProperty(value = "姓名")
    private String user_name;
    @ApiModelProperty(value = "邮箱")
    private String user_email;
    @ApiModelProperty(value = "电话号")
    private String user_phone;
    @ApiModelProperty(value = "性别")
    private String user_sex;
    @ApiModelProperty(value = "ip地址")
    private String user_ip;
    @ApiModelProperty(value = "权限")
    private String user_role;
    @ApiModelProperty(value = "用户生日")
    private String user_birthday;



}
