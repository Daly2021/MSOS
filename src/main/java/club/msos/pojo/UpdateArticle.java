package club.msos.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticle {
    private String updateArticle_id;
    private Integer article_id;
    private String updateArticle_do;
    private String updateArticle_time;
    private String updateArticle_ip;
}
