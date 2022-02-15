package model.user;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Category {
    private int id;
    private  String name;
}
