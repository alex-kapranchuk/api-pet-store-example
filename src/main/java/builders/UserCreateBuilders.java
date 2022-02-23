package builders;

import model.UserModel;
import org.apache.commons.lang3.RandomStringUtils;

public class UserCreateBuilders {
    private final String userFirstName = RandomStringUtils.randomAlphabetic(8);
    private final String userLastName = RandomStringUtils.randomAlphabetic(8);
    private final String password = RandomStringUtils.randomAlphabetic(6);
    private final int userStatus = Integer.parseInt(RandomStringUtils.randomNumeric(1));
    private final String phone = "+380" + RandomStringUtils.randomNumeric(7);
    private final int id = Integer.parseInt(RandomStringUtils.randomNumeric(6));
    private final String email = createEmail();
    private final String username = RandomStringUtils.randomAlphabetic(6);

    public static String createEmail() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(RandomStringUtils
                        .randomAlphabetic(6))
                .append("@gmail.com");
        return stringBuilder.toString();
    }

    public UserModel createUser() {
        return UserModel.builder()
                .firstName(userFirstName)
                .lastName(userLastName)
                .password(password)
                .userStatus(userStatus)
                .phone(phone)
                .id(id)
                .email(email)
                .username(username)
                .build();
    }
}