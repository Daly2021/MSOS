package club.msos.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUser {
    private String updateUser_id;
    private String user_id;
    private String updateUser_do;
    private String updateUser_time;
    private String updateUser_ip;

}
