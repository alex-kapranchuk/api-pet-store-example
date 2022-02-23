package model;
import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetModel {
    private int id;
    private CategoryModel categoryModel;
    private String name;
    private List<String> photoUrls;
    private List<TagsModel> tags;
    private String status;

    @Getter
    public enum Status {
        AVAILABLE("available"), PENDING("pending"), SOLD("sold");
        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PetModel petModel = (PetModel) o;
        return  id == petModel.getId()
                && categoryModel.equals(petModel.getCategoryModel())
                && name.equals(petModel.getName())
                && photoUrls.equals(petModel.getPhotoUrls())
                && tags.equals(petModel.tags)
                && status.equals(petModel.getStatus());
    }
}
