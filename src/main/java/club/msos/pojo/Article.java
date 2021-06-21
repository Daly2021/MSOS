package club.msos.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "文章实体类",description = "文章对象")
public class Article {
    @ApiModelProperty(value = "文章id")
    private Integer article_id;
    @ApiModelProperty(value = "文章标题")
    private String article_title;
    @ApiModelProperty(value = "用户名")
    private String user_id;
    @ApiModelProperty(value = "文章内容")
    private String article_content;
    @ApiModelProperty(value = "观看人数")
    private  Integer article_views;
    @ApiModelProperty(value = "文章评论数量")
    private  Integer article_comment_count;
    @ApiModelProperty(value = "发表时间")
    private  String article_time;
    @ApiModelProperty(value = "文章类型")
    private  String article_type;
    private String user_name;
}
