-- Инициализируем сиквенс
create sequence rm_seq minvalue 0 cache 100;

-- Создание таблицы 'Заведения'
create table rm_backend.rm_estblsh
(
    id          bigint        not null
        constraint rm_estblsh_pk primary key,
    name        varchar(1000) not null,
    description bytea,
    address     varchar(1000) not null
);
comment on table rm_backend.rm_estblsh is 'Данные о заведениях';
comment on column rm_backend.rm_estblsh.name is 'Название';
comment on column rm_backend.rm_estblsh.description is 'Описание';
comment on column rm_backend.rm_estblsh.address is 'Адрес';

-- Создание общей таблицы 'Пользователи'
create table rm_backend.rm_user
(
    id        bigint            not null
        constraint rm_user_pk primary key,
    firstname varchar(1000),
    lastname  varchar(1000),
    password  varchar(64)       not null,
    email     varchar(100)      not null,
    phone     bigint,
    type      varchar(20)       not null,
    role      varchar(20)       not null
);
comment on column rm_backend.rm_user.firstname is 'Имя';
comment on column rm_backend.rm_user.lastname is 'Фамилия';
comment on column rm_backend.rm_user.password is 'Хэш пароля';
comment on column rm_backend.rm_user.email is 'Email';
comment on column rm_backend.rm_user.type is 'Тип учетной записи';
comment on column rm_backend.rm_user.role is 'Роль пользователя';

-- Создание таблицы 'Пользователи-владельцы'
create table rm_backend.rm_user_owner
(
    id         bigint not null
        constraint rm_user_owner_pk primary key,
    estblsh_id bigint
        constraint rm_user_owner_fk1 references rm_backend.rm_estblsh (id) on delete cascade
);
comment on table rm_backend.rm_user_owner is 'Пользователи-владельцы';
comment on column rm_backend.rm_user_owner.estblsh_id is 'ID заведения';
comment on constraint rm_user_owner_fk1 on rm_backend.rm_user_owner is 'Связь пользователь-заведение';

-- Создание таблицы 'Расписание'
create table rm_backend.rm_schedule
(
    id          bigint        not null
        constraint rm_schedule_pk primary key,
    name        varchar(1000) not null,
    user_id bigint
        constraint rm_schedule_fk1 references rm_backend.rm_user (id) on delete cascade
);
comment on table rm_backend.rm_schedule is 'Данные о расписании';
comment on column rm_backend.rm_schedule.name is 'Название';
comment on constraint rm_schedule_fk1 on rm_backend.rm_schedule is 'Связь расписание-пользователь';

-- Создание таблицы 'Пользователи-клиенты'
create table rm_backend.rm_user_client
(
    id         bigint not null
        constraint rm_user_client_pk primary key,
    schedule_id bigint
        constraint rm_user_client_fk1 references rm_backend.rm_schedule (id) on delete cascade
);
comment on table rm_backend.rm_user_client is 'Пользователи-клиенты';
comment on column rm_backend.rm_user_client.schedule_id is 'ID расписания';
comment on constraint rm_user_client_fk1 on rm_backend.rm_user_client is 'Связь пользователь-расписание';

-- Создание таблицы 'Токены авторизации'
create table rm_backend.rm_user_token
(
    token         varchar(512) not null
        constraint rm_user_token_pk primary key,
    user_id bigint
        constraint rm_user_token_fk1 references rm_backend.rm_user (id) on delete cascade
);
comment on table rm_backend.rm_user_token is 'Авторизационные токены пользователей';
comment on column rm_backend.rm_user_token.user_id is 'ID пользователя';
comment on constraint rm_user_token_fk1 on rm_backend.rm_user_token is 'Связь токен-пользователь';
