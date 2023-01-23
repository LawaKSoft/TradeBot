package by.lawaksoft.tradebot.mapper;

import by.lawaksoft.tradebot.dto.model.CandlestickDto;
import by.lawaksoft.tradebot.dto.model.InstrumentDto;
import by.lawaksoft.tradebot.dto.model.TickerDto;
import by.lawaksoft.tradebot.dto.model.builder.CandlestickBuilderDto;
import by.lawaksoft.tradebot.dto.model.builder.InstrumentBuilderDto;
import by.lawaksoft.tradebot.dto.model.builder.TickerBuilderDto;
import by.lawaksoft.tradebot.dto.response.FullCandlestickDto;
import by.lawaksoft.tradebot.dto.response.FullInstrumentDto;
import by.lawaksoft.tradebot.dto.response.FullTickerDto;

public final class DtoMapper {

	public static TickerDto toTickerDto(FullTickerDto fullTickerDto) {

		return TickerBuilderDto.newBuilder()
				.instrumentType(fullTickerDto.getInstrumentType())
				.instrumentId(fullTickerDto.getInstrumentId())
				.lastPrice(Double.parseDouble(fullTickerDto.getLastPrice()))
				.lastSize(Double.parseDouble(fullTickerDto.getLastSize()))
				.askPrice(Double.parseDouble(fullTickerDto.getAskPrice()))
				.askSize(Double.parseDouble(fullTickerDto.getAskSize()))
				.bestPrice(Double.parseDouble(fullTickerDto.getBestPrice()))
				.bestSize(Double.parseDouble(fullTickerDto.getBestSize()))
				.open24hPrice(Double.parseDouble(fullTickerDto.getOpen24hPrice()))
				.highest24hPrice(Double.parseDouble(fullTickerDto.getHighest24hPrice()))
				.lowest24h(Double.parseDouble(fullTickerDto.getLowest24h()))
				.volume24hCurrency(Double.parseDouble(fullTickerDto.getVolume24hCurrency()))
				.volume24hContract(Double.parseDouble(fullTickerDto.getVolume24hContract()))
				.priceUTC0(Double.parseDouble(fullTickerDto.getPriceUTC0()))
				.priceUTC8(Double.parseDouble(fullTickerDto.getPriceUTC8()))
				.timestamp(Long.parseLong(fullTickerDto.getTimestamp()))
				.build();
	}

	public static CandlestickDto toCandlestickDto(FullCandlestickDto fullCandlestickDto) {

		return CandlestickBuilderDto.newBuilder()
				.timestamp(Long.parseLong(fullCandlestickDto.getTimestamp()))
				.openPrice(Double.parseDouble(fullCandlestickDto.getOpenPrice()))
				.highestPrice(Double.parseDouble(fullCandlestickDto.getHighestPrice()))
				.lowestPrice(Double.parseDouble(fullCandlestickDto.getLowestPrice()))
				.closePrice(Double.parseDouble(fullCandlestickDto.getClosePrice()))
				.volumeContract(Double.parseDouble(fullCandlestickDto.getVolumeContract()))
				.volCurrency(Double.parseDouble(fullCandlestickDto.getVolCurrency()))
				.volCurrencyQuote(Double.parseDouble(fullCandlestickDto.getVolCurrencyQuote()))
				.confirm(Boolean.parseBoolean(fullCandlestickDto.getConfirm()))
				.build();
	}

	public static InstrumentDto toInstrumentDto(FullInstrumentDto fullInstrumentDto) {

		return InstrumentBuilderDto.newBuilder()
				.instrumentType(fullInstrumentDto.getInstrumentType())
				.instrumentId(fullInstrumentDto.getInstrumentId())
				.baseCurrency(fullInstrumentDto.getBaseCurrency())
				.quoteCurrency(fullInstrumentDto.getQuoteCurrency())
				.listingTime(Long.parseLong(fullInstrumentDto.getListingTime()))
				.tickSize(Double.parseDouble(fullInstrumentDto.getTickSize()))
				.lotSize(Double.parseDouble(fullInstrumentDto.getLotSize()))
				.minOrderSize(Double.parseDouble(fullInstrumentDto.getMinOrderSize()))
				.maxLimitSize(Double.parseDouble(fullInstrumentDto.getMaxLimitSize()))
				.maxMarketSize(Double.parseDouble(fullInstrumentDto.getMaxMarketSize()))
				.build();
	}
}
