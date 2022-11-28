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

insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (1, 'Repository', null);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (2, 'Biblioteca', 1);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (3, 'Livros', 2);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (4, 'Matemática', 3);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (5, 'Geometria', 4);
insert into tb_diretorio (codigo, nome, id_diretorio_pai) values (6, 'Álgebra', 4);

select * from tb_diretorio;

-- Recuperar Diretorios Filhos
select *
from tb_diretorio diretorio
where diretorio.id_diretorio_pai = 4;