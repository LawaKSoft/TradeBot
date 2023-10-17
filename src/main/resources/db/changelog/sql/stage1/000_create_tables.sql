CREATE TABLE "algo_types" (
                                    "id" serial NOT NULL,
                                    "name" varchar NOT NULL,
                                    CONSTRAINT "algo_type_pk" PRIMARY KEY ("id")
);


CREATE TABLE "instruments" (
                                     "id" serial NOT NULL,
                                     "name" varchar NOT NULL,
                                     "value" varchar NOT NULL,/*integer?*/
                                     CONSTRAINT "instrument_pk" PRIMARY KEY ("id")
);


CREATE TABLE "algo_settings" (
                                       "id" serial NOT NULL,
                                       "algo_type_id" integer NOT NULL,
                                       "name" varchar NOT NULL,
                                       "value" varchar NOT NULL,
                                       CONSTRAINT "algo_setting_pk" PRIMARY KEY ("id"),
                                       CONSTRAINT "algo_setting_fk0" FOREIGN KEY ("algo_type_id") REFERENCES "algo_types"("id")
);


CREATE TABLE "users" (
                               "id" serial NOT NULL,
                               "username" varchar NOT NULL UNIQUE,
                               "password" varchar NOT NULL UNIQUE,
                               "email" varchar NOT NULL UNIQUE,
                               CONSTRAINT "user_pk" PRIMARY KEY ("id")
);


CREATE TABLE "client_info_types" (
                                           "id" serial NOT NULL,
                                           "name" varchar NOT NULL,/*integer?*/
                                           CONSTRAINT "client_info_type_pk" PRIMARY KEY ("id")
);


CREATE TABLE "client_info_settings" (
                                              "id" serial NOT NULL,
                                              "client_info_type_id" integer NOT NULL,
                                              "name" varchar NOT NULL,
                                              "value" varchar NOT NULL,
                                              CONSTRAINT "client_info_setting_pk" PRIMARY KEY ("id"),
                                              CONSTRAINT "client_info_setting_fk0" FOREIGN KEY ("client_info_type_id") REFERENCES "client_info_types"("id")
);


CREATE TABLE "client_info_params" (
                                            "id" serial NOT NULL,
                                            "user_id" integer NOT NULL,
                                            "client_info_setting_id" integer NOT NULL,
                                            "value" varchar NOT NULL,
                                            CONSTRAINT "client_info_param_pk" PRIMARY KEY ("id"),
                                            CONSTRAINT "client_info_param_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id"),
                                            CONSTRAINT "client_info_param_fk1" FOREIGN KEY ("client_info_setting_id") REFERENCES "client_info_settings"("id")
);


CREATE TABLE "account_infos" (
                                       "id" serial NOT NULL,
                                       "client_type_id" integer NOT NULL,
                                       "user_id" integer NOT NULL,
                                       "balance_value" integer NOT NULL,
                                       "currency" varchar NOT NULL,
                                       CONSTRAINT "account_info_pk" PRIMARY KEY ("id"),
                                       CONSTRAINT "account_info_fk0" FOREIGN KEY ("client_type_id") REFERENCES "client_info_types"("id"),
                                       CONSTRAINT "account_info_fk1" FOREIGN KEY ("user_id") REFERENCES "users"("id")
);


CREATE TABLE "algo_instances" (
                                        "id" serial NOT NULL,
                                        "user_id" integer NOT NULL,
                                        "instrument_id" integer NOT NULL,
                                        "client_info_type_id" integer NOT NULL,
                                        "algo_type_id" integer NOT NULL,
                                        CONSTRAINT "algo_instance_pk" PRIMARY KEY ("id"),
                                        CONSTRAINT "algo_instance_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id"),
                                        CONSTRAINT "algo_instance_fk1" FOREIGN KEY ("instrument_id") REFERENCES "instruments"("id"),
                                        CONSTRAINT "algo_instance_fk2" FOREIGN KEY ("client_info_type_id") REFERENCES "client_info_types"("id"),
                                        CONSTRAINT "algo_instance_fk3" FOREIGN KEY ("algo_type_id") REFERENCES "algo_types"("id")
);


CREATE TABLE "algo_params" (
                                     "id" serial NOT NULL,
                                     "algo_setting_id" integer NOT NULL,
                                     "algo_instance_id" integer NOT NULL,
                                     "value" varchar NOT NULL,
                                     CONSTRAINT "algo_param_pk" PRIMARY KEY ("id"),
                                     CONSTRAINT "algo_param_fk0" FOREIGN KEY ("algo_setting_id") REFERENCES "algo_settings"("id"),
                                     CONSTRAINT "algo_param_fk1" FOREIGN KEY ("algo_instance_id") REFERENCES "algo_instances"("id")
);