package by.lawaksoft.tradebot.service.entity;

import by.lawaksoft.tradebot.entity.Balance;
import by.lawaksoft.tradebot.entity.User;

public interface AccountService {
    Balance findBalanceByUserId(long userId);
}
