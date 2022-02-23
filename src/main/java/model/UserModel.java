package model;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserModel userModel = (UserModel) o;

        return firstName.equals(userModel.getFirstName())
                && lastName.equals(userModel.getLastName())
                && password.equals(userModel.getPassword())
                && userStatus == userModel.getUserStatus()
                && phone.equals(userModel.getPhone())
                && id == userModel.getId()
                && email.equals(userModel.getEmail())
                && username.equals(userModel.getUsername());
    }
}
