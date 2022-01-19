drop database livraison;
create database livraison;
use livraison;

create table axe (
	id int primary key not null,
	nomAxe varchar(20) not null,
	constraint contrainteIdAxe check(id > 0),
	constraint contrainteNomAxe check(length(nomAxe) > 2)
);

create table frais (
	id int primary key not null,
	valeur int not null,
	constraint contrainteIdFrais check(id > 0),
	constraint contrainteValeur check(valeur > 0)
);

create table livreur (
	id int primary key not null auto_increment,
	nomLivreur varchar(40) not null,
	prenom varchar(40) not null,
	idAxe int,
	dateEntree date,
	etat int,
	foreign key(idAxe) references axe(id),
	constraint contrainteNomLivreur check(length(nomLivreur) > 2)
);

create table livraison (
	id int primary key not null auto_increment,
	idAxe int,
	idFrais int,
	etat int not null,
	idLivreur int,
	dateLivraison date not null,
	produit varchar(40),
	foreign key (idAxe) references axe(id),
	foreign key (idFrais) references frais(id),
	foreign key (idLivreur) references livreur(id),
	constraint contrainteEtat check(etat > 0 and etat < 4)
);

create table utilisateurs (
	id int primary key not null,
	identifiant varchar(20),
	mdp varchar(50)
);

insert into axe values (1,'Ivato');
insert into axe values (2,'Tanjombato');
insert into axe values (3,'Ambohijanaka');

insert into frais values (1, 2000);
insert into frais values (2, 3500);
insert into frais values (3, 4000);

insert into utilisateurs values (1, 'Johan', sha1('Johan'));

create view listeLivreurs as select livreur.id, nomLivreur, prenom, livreur.idAxe, nomAxe, dateEntree, etat
from (select * from livreur where etat = 1) as livreur join axe on livreur.idAxe = axe.id order by livreur.id;

create view vLivraison as
	select
		livraison.id,
		axe.nomAxe,
		frais.valeur,
		case
			when livraison.etat = 1 then "en cours"
			when livraison.etat = 2 then "termin�e"
			when livraison.etat = 3 then "inachev�e"
		end as etat,
		livreur.nomLivreur,
		livreur.prenom,
		dateLivraison,
		produit
	from livraison
	join axe on axe.id = livraison.idAxe
	join frais on frais.id = livraison.idFrais
	join livreur on livreur.id = livraison.idLivreur;

INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('1', 'Ramorasata', '1', '2020-10-17', 'Mihaja', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('2', 'Rakotoniaina', '2', '2020-10-15', 'Johan', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('3', 'Ramamisoa', '3', '2020-09-22', 'Iavotiana', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('4', 'Ramaroson', '2', '2020-10-26', 'Faniry', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('5', 'Randriamihaja', '1', '2020-09-30', 'Maria', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('6', 'Randriamihanta', '3', '2020-10-20', 'Fanilo', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('7', 'Andrianina', '2', '2020-10-12', 'Gaelle', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('8', 'Johan', '1', '2020-09-08', 'Mickael', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('9', 'Nambiiy', '3', '2020-10-01', 'Larissa', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('10', 'Fitia', '1', '2020-10-16', 'Andrianina', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('11', 'Andrianjato', '2', '2020-09-22', 'Joachim', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('12', 'Mirantsoa', '3', '2020-10-23', 'Harivelo', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('13', 'Andrianaly', '1', '2020-10-05', 'Mitia', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('14', 'Mananirina', '1', '2020-10-18', 'Jonathan', '1'), ('15', 'Miharintsoa', '2', '2020-10-13', 'Fitahiana', '1');
INSERT INTO `livreur` (`id`, `nomLivreur`, `idAxe`, `dateEntree`, `prenom`, `etat`) VALUES ('16', 'Haritiana', '3', '2020-10-12', 'Johanna', '1'),
('17', 'Rajerison', '1', '2020-09-13', 'Zinah', '1'),
('18', 'Rakoto', '2', '2020-10-20', 'Jese', '1'),
('19', 'Rabarison', '3', '2020-09-17', 'Harinirina', '1'),
('20', 'Nirina', '2', '2020-09-22', 'Nathalie', '1'),
('21', 'Haingo', '1', '2020-09-22', 'Tiana', '1'),
('22', 'Rakotolobo', '3', '2020-09-28', 'Tahiana', '1'),
('23', 'Gino', '3', '2020-10-21', 'Allison', '1'),
('24', 'Kaky', '1', '2020-10-27', 'Jean', '1'),
('25', 'Faneva', '3', '2020-10-20', 'Niaina', '1');

INSERT INTO `livraison` (`id`, `idAxe`, `idFrais`, `etat`, `idLivreur`, `dateLivraison`, `produit`) VALUES ('1', '1', '1', '1', '21', '2020-10-31', 'Tee shirt'),
('2', '2', '3', '2', '15', '2020-10-21', 'Livres'),
('3', '1', '1', '1', '1', '2020-11-03', 'Livres'),
('4', '3', '2', '1', '9', '2020-11-02', 'Tee shirt'),
('5', '2', '2', '2', '2', '2020-10-29', 'Blouson'),
('6', '2', '3', '1', '7', '2020-11-10', 'Montre'),
('7', '1', '2', '3', '13', '2020-10-30', 'Tee shirt'),
('8', '3', '3', '1', '6', '2020-11-07', 'Polo'),
('9', '2', '2', '1', '11', '2020-11-18', 'Tee shirt'),
('10', '3', '2', '1', '16', '2020-11-10', 'Livres'),
('11', '2', '3', '1', '20', '2020-11-02', 'Sac'),
('12', '1', '2', '1', '24', '2020-10-31', 'Montre'),
('13', '2', '1', '1', '18', '2020-11-11', 'Tennis'),
('14', '1', '3', '1', '8', '2020-11-09', 'Blouson'),
('15', '2', '2', '2', '4', '2020-10-29', 'Tee shirt'),
('16', '1', '1', '1', '14', '2020-11-23', 'Blouson'),
('17', '3', '2', '1', '25', '2020-11-10', 'Livres'),
('18', '2', '2', '1', '7', '2020-11-10', 'Blouson'),
('19', '3', '1', '1', '19', '2020-11-06', 'Tee shirt'),
('20', '1', '3', '1', '1', '2020-11-04', 'Livres');

