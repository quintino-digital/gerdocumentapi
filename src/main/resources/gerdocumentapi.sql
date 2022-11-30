drop table if exists flyway_schema_history cascade;
drop table if exists tb_diretorio cascade;

create table if not exists tb_diretorio (
	codigo int not null,
	id_diretorio_pai int null,
	nome varchar(100) not null
);

alter table tb_diretorio add constraint pk_diretorio primary key (codigo);

alter table tb_diretorio add foreign key (id_diretorio_pai) references tb_diretorio(codigo);

alter table tb_diretorio add unique (nome);

select * from tb_diretorio;

insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (1, 'Repository', null);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (2, 'Biblioteca', 1);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (3, 'Livros', 2);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (4, 'Matemática', 3);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (5, 'Geometria', 4);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (6, 'Álgebra', 4);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (7, 'Multimidia', 1);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (8, 'Imagem', 7);

-- Recuperar Diretorios Filhos
select *
from tb_diretorio diretorio
where diretorio.codigo = '5bd8ef4d-26b2-4357-908a-4a0869f6dd1c';

-- Recuperar Arquivos de um determinado Diretorio
	select *
	from tb_arquivo arquivo
	join tb_diretorio diretorio on diretorio.codigo = arquivo.id_diretorio
	where arquivo.id_diretorio = '87be7dff-46e6-40e9-9176-67e48ea94cca';

select * from tb_diretorio;

select * from tb_arquivo;

update tb_arquivo
set id_diretorio = 8
where codigo = 'e835d219-d6fc-46df-9c25-b91cf9e58fb3';

-- Recuperar Diretorio Raiz
select *
from tb_diretorio
where id_diretorio_pai = '' or id_diretorio_pai IS null;

-- Recuperar subpastas de um determinado diretorio
select *
from tb_diretorio diretorio
where diretorio.id_diretorio_pai = "4d5fdab3-27e4-45ff-b659-b0db65f04cd8";

select UUID();

insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('4d5fdab3-27e4-45ff-b659-b0db65f04cd8', '@Repository', null);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('fa575bb9-5f20-4a83-90d8-f88e0778c36d', '@Organizador', null);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('4724c1df-258a-4056-b3d1-d9eacbb84441', '@Backup', null);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('5b39cb1a-a2f7-4228-9c83-682d1d3aebdb', 'Biblioteca', '4d5fdab3-27e4-45ff-b659-b0db65f04cd8');
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('04ece865-76a1-4742-ba09-6ca8cafacfe4', 'Livros', '5b39cb1a-a2f7-4228-9c83-682d1d3aebdb');
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('ae3a5712-3e85-49a4-a54c-3850afc5e143', 'Matemática', '04ece865-76a1-4742-ba09-6ca8cafacfe4');
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('a9dec5a5-7afa-4766-80c8-3431f4c46b60', 'Geometria', 'ae3a5712-3e85-49a4-a54c-3850afc5e143');
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('38b66030-bac6-4c6b-a2a0-5895e25d3ae2', 'Álgebra', 'ae3a5712-3e85-49a4-a54c-3850afc5e143');
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('a6273613-fd3d-45f7-9766-5bf2221579ab', 'Multimidia', '4d5fdab3-27e4-45ff-b659-b0db65f04cd8');
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('aade2ab7-6f26-11ed-ae61-f4f26d891850', 'Imagem', 'a6273613-fd3d-45f7-9766-5bf2221579ab');
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('18efd88d-6f51-11ed-ae61-f4f26d891850', 'HD EXTERNO - Dados Recuperados', '4724c1df-258a-4056-b3d1-d9eacbb84441');
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('d308d926-6f52-11ed-ae61-f4f26d891850', 'Documentos', '4d5fdab3-27e4-45ff-b659-b0db65f04cd8');
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('97d9079a-70e7-11ed-9fae-9fb5ad4550c3', 'Música', 'a6273613-fd3d-45f7-9766-5bf2221579ab');
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values ('03b772a8-70e8-11ed-9fae-9fb5ad4550c3', 'Vídeos', 'a6273613-fd3d-45f7-9766-5bf2221579ab');
