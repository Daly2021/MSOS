package club.msos.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "留言实体类",description = "留言对象")
public class Message {
    @ApiModelProperty(value = "留言id")
    private Integer message_id;
    @ApiModelProperty(value = "用户名")
    private String user_id;
    @ApiModelProperty(value = "留言内容")
    private String message_content;
    @ApiModelProperty(value = "发表时间")
    private String message_time;
    @ApiModelProperty(value = "父评论id")
    private  Integer message_parant_id;
    @ApiModelProperty(value = "评论ip")
    private String message_ip;
    private String user_name;

}
