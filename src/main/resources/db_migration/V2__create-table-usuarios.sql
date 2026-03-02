reate table usuarios (
    id bigserial primary key,
    nome varchar(100) not null,
    email varchar(150) not null unique,
    senha varchar(255) not null,
    perfil varchar(50) not null
);
