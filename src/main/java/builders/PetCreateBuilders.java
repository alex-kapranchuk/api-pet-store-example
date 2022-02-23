package builders;

import model.CategoryModel;
import model.PetModel;
import model.TagsModel;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;

public class PetCreateBuilders {
    private int id = Integer.parseInt(RandomStringUtils.randomNumeric(6));
    private String name = RandomStringUtils.randomAlphabetic(7);
    private String status = PetModel.Status.AVAILABLE.getValue();
    private PetModel petModel;

    public PetModel createPet(){
        petModel = PetModel.builder()
                .id(id)
                .categoryModel(CategoryModel.builder()
                        .id(id)
                        .name(name)
                        .build())
                .name(name)
                .photoUrls(Collections.singletonList(RandomStringUtils.randomAlphabetic(10)))
                .tags(Collections.singletonList(TagsModel.builder()
                        .id(id)
                        .name(name)
                        .build()))
                .status(status)
                .build();
        return petModel;
    }
}
