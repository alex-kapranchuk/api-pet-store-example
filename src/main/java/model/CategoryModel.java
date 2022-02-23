package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CategoryModel {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private  String name;
}
