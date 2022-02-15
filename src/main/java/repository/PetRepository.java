package repository;

import model.user.Pet;
import org.apache.commons.lang3.RandomStringUtils;

public class PetRepository {

    private PetRepository() {
    }

    public static Pet getValidPet() {
        return Pet.builder()
                .id(Integer.parseInt(RandomStringUtils.randomNumeric(6)))
                .name(RandomStringUtils.randomAlphabetic(5))
                .build();
    }
}
