package by.lawaksoft.tradebot.dto.place_order;

import by.lawaksoft.tradebot.dto.order.OrderDataResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderResponseDTO {

    private long code;
    private String msg;
    private List<OrderDataResponseDTO> data;
}
