package by.lawaksoft.tradebot.mapper;

import by.lawaksoft.tradebot.dto.model.market.CandlestickDto;
import by.lawaksoft.tradebot.document.Candlestick;
import by.lawaksoft.tradebot.entity.Instrument;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class DocumentMapper {

	private static final Long EXPIRED_TIME = 7889400000L;
	private DocumentMapper() {

	}

	public static Candlestick toCandlestickDocument(CandlestickDto candlestickDto, Instrument instrument) {

		return Candlestick.builder()
				.instId(instrument.getInstrumentId())
				.timestamp(candlestickDto.getTimestamp())
				.openPrice(candlestickDto.getOpenPrice())
				.highestPrice(candlestickDto.getHighestPrice())
				.lowestPrice(candlestickDto.getLowestPrice())
				.closePrice(candlestickDto.getClosePrice())
				.volumeContract(candlestickDto.getVolumeContract())
				.volCurrency(candlestickDto.getVolCurrency())
				.volCurrencyQuote(candlestickDto.getVolCurrencyQuote())
				.confirm(candlestickDto.isConfirm())
				.expiredTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(candlestickDto.getTimestamp() + EXPIRED_TIME),
						TimeZone.getDefault().toZoneId()))
				.build();
	}
}
