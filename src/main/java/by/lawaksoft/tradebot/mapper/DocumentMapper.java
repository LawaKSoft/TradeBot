package by.lawaksoft.tradebot.mapper;

import by.lawaksoft.tradebot.dto.model.market.CandlestickDto;
import by.lawaksoft.tradebot.document.Candlestick;
import by.lawaksoft.tradebot.entity.Instrument;

public class DocumentMapper {

	private DocumentMapper() {

	}

	public static Candlestick toCandlestickDocument(CandlestickDto candlestickDto, Instrument instrument) {

		return Candlestick.builder()
				.instId(instrument.getName())
				.timestamp(candlestickDto.getTimestamp())
				.openPrice(candlestickDto.getOpenPrice())
				.highestPrice(candlestickDto.getHighestPrice())
				.lowestPrice(candlestickDto.getLowestPrice())
				.closePrice(candlestickDto.getClosePrice())
				.volumeContract(candlestickDto.getVolumeContract())
				.volCurrency(candlestickDto.getVolCurrency())
				.volCurrencyQuote(candlestickDto.getVolCurrencyQuote())
				.confirm(candlestickDto.isConfirm())
				.build();
	}
}
