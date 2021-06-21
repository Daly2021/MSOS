package club.msos.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Links {
    private Integer links_id;
    private String links_name;
    private String links_title;
    private String links_url;
    private String links_img;
}
