package model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Category {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private  String name;
}
