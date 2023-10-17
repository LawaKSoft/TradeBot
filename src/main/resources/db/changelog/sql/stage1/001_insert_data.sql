insert into users (id, username, password, email)
values (1, 'admin', 'admin', 'admin@admin.com');

insert into client_info_types (id, name)
values (1, 'OKX');

insert into client_info_settings (id, client_info_type_id, name, value)
values (1, 1, 'API_KEY', 'API_KEY');
insert into client_info_settings (id, client_info_type_id, name, value)
values (2, 1, 'PASSPHRASE', 'PASSPHRASE');
insert into client_info_settings (id, client_info_type_id, name, value)
values (3, 1, 'SECRET_KEY', 'SECRET_KEY');

insert into client_info_params (id, user_id, client_info_setting_id, value)
values (1, 1, 1, 'f01bf2b6-7ada-4244-9fd3-d7133cc1df3d');
insert into client_info_params (id, user_id, client_info_setting_id, value)
values (2, 1, 2, 'Password_2023');
insert into client_info_params (id, user_id, client_info_setting_id, value)
values (3, 1, 3, 'D7D155331E0838FB63CC16C9EA6FFD42');

insert into instruments (id, name)
values (1, 'BTC-USDT');
insert into instruments (id, name)
values (2, 'ETH-USDT');

insert into algo_types (id, name)
values (1, 'Grid');

insert into algo_settings (id, algo_type_id, name, value)
values (1, 1, 'accountBalance', 'accountBalance');
insert into algo_settings (id, algo_type_id, name, value)
values (2, 1, 'lowerGridRange', 'lowerGridRange');
insert into algo_settings (id, algo_type_id, name, value)
values (3, 1, 'upperGridRange', 'upperGridRange');
insert into algo_settings (id, algo_type_id, name, value)
values (4, 1, 'stepsCounts', 'stepsCounts');
insert into algo_settings (id, algo_type_id, name, value)
values (5, 1, 'tradeMarketPare', 'tradeMarketPare');