package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagsModel {
    private int id;
    private String name;

    @Override
    public boolean equals(Object o) {
        boolean rezult;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagsModel tags = (TagsModel) o;
        rezult = id == tags.getId() &&
                name.equals(tags.getName());
        return rezult;
    }
}
