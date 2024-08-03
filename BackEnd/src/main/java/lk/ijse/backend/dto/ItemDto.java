package lk.ijse.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto {
    private int id;
    private String name;
    private int qty;
    private double price;
}
