package model.user;
import lombok.*;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private int id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<String> tags; //?
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
}
