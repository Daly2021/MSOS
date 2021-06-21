package club.msos.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "评论实体类",description = "评论对象")
public class Comment {
    @ApiModelProperty(value = "评论id")
    private  Integer comment_id;
    @ApiModelProperty(value = "用户名")
    private String user_id;
    @ApiModelProperty(value = "文章id")
    private Integer article_id;
    @ApiModelProperty(value = "发表时间")
    private String comment_time;
    @ApiModelProperty(value = "评论内容")
    private String comment_content;
    private String user_name;
    private String comment_ip;
}
