package by.lawaksoft.tradebot.dto.algorithm;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class AlgoLayerDto {

	private final OrderDto buyOrder;
	private final OrderDto sellOrder;

	private final String instId;
	private final BigDecimal price;

	@Setter
	private BigDecimal quantity;

	public AlgoLayerDto(OrderDto buyOrder, OrderDto sellOrder, String instId, BigDecimal price, BigDecimal quantity) {

		this.buyOrder = buyOrder;
		this.sellOrder = sellOrder;
		this.instId = instId;
		this.price = price;
		this.quantity = quantity;
	}
}
