package by.lawaksoft.tradebot.mapper;

import by.lawaksoft.tradebot.dto.model.market.CandlestickDto;
import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentDto;
import by.lawaksoft.tradebot.dto.model.market.TickerDto;
import by.lawaksoft.tradebot.dto.response.instrument.FullInstrumentDto;
import by.lawaksoft.tradebot.dto.response.ticker.FullTickerDto;

import java.math.BigDecimal;

public class DtoMapper {

	private DtoMapper() {

	}

	public static TickerDto toTickerDto(FullTickerDto fullTickerDto) {

		return TickerDto.builder()
				.instrumentType(fullTickerDto.getInstrumentType())
				.instrumentId(fullTickerDto.getInstrumentId())
				.lastPrice(new BigDecimal(fullTickerDto.getLastPrice()))
				.lastSize(new BigDecimal(fullTickerDto.getLastSize()))
				.askPrice(new BigDecimal(fullTickerDto.getAskPrice()))
				.askSize(new BigDecimal(fullTickerDto.getAskSize()))
				.bestPrice(new BigDecimal(fullTickerDto.getBestPrice()))
				.bestSize(new BigDecimal(fullTickerDto.getBestSize()))
				.open24hPrice(new BigDecimal(fullTickerDto.getOpen24hPrice()))
				.highest24hPrice(new BigDecimal(fullTickerDto.getHighest24hPrice()))
				.lowest24h(new BigDecimal(fullTickerDto.getLowest24h()))
				.volume24hCurrency(new BigDecimal(fullTickerDto.getVolume24hCurrency()))
				.volume24hContract(new BigDecimal(fullTickerDto.getVolume24hContract()))
				.priceUTC0(new BigDecimal(fullTickerDto.getPriceUTC0()))
				.priceUTC8(new BigDecimal(fullTickerDto.getPriceUTC8()))
				.timestamp(Long.parseLong(fullTickerDto.getTimestamp()))
				.build();
	}

	public static CandlestickDto toCandlestickDto(String[] fullCandlestickDto) {

		return CandlestickDto.builder()
				.timestamp(Long.parseLong(fullCandlestickDto[0]))
				.openPrice(new BigDecimal(fullCandlestickDto[1]))
				.highestPrice(new BigDecimal(fullCandlestickDto[2]))
				.lowestPrice(new BigDecimal(fullCandlestickDto[3]))
				.closePrice(new BigDecimal(fullCandlestickDto[4]))
				.volumeContract(new BigDecimal(fullCandlestickDto[5]))
				.volCurrency(new BigDecimal(fullCandlestickDto[6]))
				.volCurrencyQuote(new BigDecimal(fullCandlestickDto[7]))
				.confirm(Boolean.parseBoolean(fullCandlestickDto[8]))
				.build();
	}

	public static InstrumentDto toInstrumentDto(FullInstrumentDto fullInstrumentDto) {

		return InstrumentDto.builder()
				.instrumentType(fullInstrumentDto.getInstrumentType())
				.instrumentId(fullInstrumentDto.getInstrumentId())
				.baseCurrency(fullInstrumentDto.getBaseCurrency())
				.quoteCurrency(fullInstrumentDto.getQuoteCurrency())
				.listingTime(Long.parseLong(fullInstrumentDto.getListingTime()))
				.tickSize(new BigDecimal(fullInstrumentDto.getTickSize()))
				.lotSize(new BigDecimal(fullInstrumentDto.getLotSize()))
				.minOrderSize(new BigDecimal(fullInstrumentDto.getMinOrderSize()))
				.maxLimitSize(new BigDecimal(fullInstrumentDto.getMaxLimitSize()))
				.maxMarketSize(new BigDecimal(fullInstrumentDto.getMaxMarketSize()))
				.build();
	}
}
