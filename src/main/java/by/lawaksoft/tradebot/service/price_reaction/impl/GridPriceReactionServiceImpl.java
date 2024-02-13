package by.lawaksoft.tradebot.service.price_reaction.impl;

import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.entity.enums.NecessarySynchronization;
import by.lawaksoft.tradebot.repository.OrderRepository;
import by.lawaksoft.tradebot.dto.algorithm.AlgoLayerDto;
import by.lawaksoft.tradebot.service.price_reaction.PriceReactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class GridPriceReactionServiceImpl implements PriceReactionService {

	private final OrderRepository orderRepository;

	public GridPriceReactionServiceImpl(OrderRepository orderRepository) {

		this.orderRepository = orderRepository;
	}

	@Override
	public void onPriceReaction(AlgoLayerDto algoLayerDto, BigDecimal price) {

		if (price.compareTo(algoLayerDto.getBuyOrder().getPrice()) < 0 && (algoLayerDto.getBuyOrder().getOrderStatus().equals(NecessarySynchronization.DONE)
				|| algoLayerDto.getBuyOrder().getOrderStatus().equals(NecessarySynchronization.FINISHED))) {
			algoLayerDto.getBuyOrder().setOrderStatus(NecessarySynchronization.ACCEPT);
			updateOrder(algoLayerDto.getBuyOrder().getOrderId());
		}

		if (price.compareTo(algoLayerDto.getSellOrder().getPrice()) > 0 && (algoLayerDto.getSellOrder().getOrderStatus().equals(NecessarySynchronization.DONE)
				|| algoLayerDto.getSellOrder().getOrderStatus().equals(NecessarySynchronization.FINISHED))) {
			algoLayerDto.getSellOrder().setOrderStatus(NecessarySynchronization.ACCEPT);
			updateOrder(algoLayerDto.getSellOrder().getOrderId());
		}
	}

	private void updateOrder(Long id) {

		Order order = orderRepository.getReferenceById(id);
		order.setNecessarySynchronization(NecessarySynchronization.ACCEPT);
		orderRepository.save(order);
	}
}
