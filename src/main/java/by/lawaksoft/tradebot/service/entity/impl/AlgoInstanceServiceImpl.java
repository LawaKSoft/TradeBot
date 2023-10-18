package by.lawaksoft.tradebot.service.entity.impl;

import by.lawaksoft.tradebot.entity.AlgoInstance;
import by.lawaksoft.tradebot.exception.dto.BusinessException;
import by.lawaksoft.tradebot.exception.dto.enums.ERROR_MESSAGE;
import by.lawaksoft.tradebot.repository.AlgoInstanceRepository;
import by.lawaksoft.tradebot.service.entity.AlgoInstanceService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlgoInstanceServiceImpl implements AlgoInstanceService {

    private final AlgoInstanceRepository algoInstanceRepository;

    private static final String NOT_FOUND_BY_USER_ID = "List with algoInstances cant found by userId %s and instrument %s";

    @Override
    @Transactional
    public List<AlgoInstance> findAllByUserIdAndInstrumentId(long userId, String instrumentId) {
        return algoInstanceRepository.findAllByUserIdAndInstrumentId(userId, instrumentId)
                .orElseThrow(() -> new BusinessException(String.format(NOT_FOUND_BY_USER_ID, userId, instrumentId),
                        ERROR_MESSAGE.BAD_ALGORITHM_INSTANCE, HttpStatus.NO_CONTENT));
    }

}
