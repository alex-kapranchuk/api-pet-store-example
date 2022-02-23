package repository;

import model.UserModel;
import org.apache.commons.lang3.RandomStringUtils;

public class UserRepository {

    private UserRepository() {
    }

    public static UserModel getValidUser() {
        return UserModel.builder()
                .username(RandomStringUtils.randomAlphabetic(5))
                .firstName(RandomStringUtils.randomAlphabetic(5))
                .lastName(RandomStringUtils.randomAlphabetic(5))
                .id(Integer.parseInt(RandomStringUtils.randomNumeric(6)))
                .phone(RandomStringUtils.randomNumeric(10))
                .userStatus(1)
                .password(RandomStringUtils.randomAlphabetic(5))
                .build();
    }
}
