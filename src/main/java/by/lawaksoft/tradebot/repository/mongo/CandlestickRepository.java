package by.lawaksoft.tradebot.repository.mongo;

import by.lawaksoft.tradebot.document.Candlestick;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandlestickRepository extends MongoRepository<Candlestick, String> {

}
