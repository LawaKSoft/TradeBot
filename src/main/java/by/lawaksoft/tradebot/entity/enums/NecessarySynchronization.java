package by.lawaksoft.tradebot.entity.enums;

public enum NecessarySynchronization {
    
    ACCEPT, //place - выставить в OKX
    UPDATED,//обновить - обновить в OKX
    FINISHED, //в OKX выполнен
    CLOSED, //должен закрыться в OKX
    CANCELED, //уже закрылся и у нас в системе
    WAITING, //ожидает операции
    DONE; //после выполнения
}
