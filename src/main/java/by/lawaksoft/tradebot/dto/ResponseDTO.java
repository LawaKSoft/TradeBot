package by.lawaksoft.tradebot.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO<T> {

    private String code;
    private List<T> data;
    private String msg;
}
