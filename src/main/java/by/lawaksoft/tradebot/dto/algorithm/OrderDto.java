package by.lawaksoft.tradebot.dto.algorithm;

import by.lawaksoft.tradebot.entity.enums.NecessarySynchronization;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderDto {

	private Long orderId;
	private BigDecimal price;
	private OrderType type;
	private NecessarySynchronization orderStatus;

	public OrderDto(BigDecimal price, OrderType type, NecessarySynchronization orderStatus) {

		this.price = price;
		this.type = type;
		this.orderStatus = orderStatus;
	}
}