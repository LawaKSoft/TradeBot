package by.lawaksoft.tradebot.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private long code;
    private String msg;
    private List<OrderDataResponseDTO> data;
}
