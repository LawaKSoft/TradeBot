package by.lawaksoft.tradebot.dto.order;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {

    private long code;
    private String msg;
    private List<OrderDataResponseDTO> data;
}
