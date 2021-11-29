package model.user;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
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
        User user = (User) o;

        return firstName.equals(user.getFirstName())
                && lastName.equals(user.getLastName())
                && password.equals(user.getPassword())
                && userStatus == user.getUserStatus()
                && phone.equals(user.getPhone())
                && id == user.getId()
                && email.equals(user.getEmail())
                && username.equals(user.getUsername());

    }
}
