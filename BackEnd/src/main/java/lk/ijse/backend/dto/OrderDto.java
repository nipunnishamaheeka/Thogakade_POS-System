package lk.ijse.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
    private String id;
    private String date;
    private String customerId;
    private List<ItemDto> items = new ArrayList<>();
    private String total;
    private String discount;
    private String subTotal;
    private String cash;
    private String balance;
}
