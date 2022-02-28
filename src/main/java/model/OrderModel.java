package model;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private Boolean complete;

    @Getter
    public enum Status {
        PLACED("placed"), APPROVED("approved"), DELIVERED("delivered");
        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }
}