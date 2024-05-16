insert into particulier(id,civilite,nom,prenom,ref_client)
values(1,'MONSIEUR','user1','prenom1','EKW000000001');

insert into professionnel(id,ref_client,num_siret,raison_sociale,ca)
values(2,'EKW000000002','12345','pro1',1100000);

insert into professionnel(id,ref_client,num_siret,raison_sociale,ca)
values(3,'EKW000000003','45464','pro2',1000000);

-- abonnements particulier
insert into abonnement(id,type,prix_kwh)
values(1,'ELECTRICITE',0.133);

insert into abonnement(id,type,prix_kwh)
values(2,'GAZ',0.108);

-- abonnements pro type 1

insert into abonnement(id,type,prix_kwh)
values(3,'ELECTRICITE',0.110);

insert into abonnement(id,type,prix_kwh)
values(4,'GAZ',0.123);

-- abonnements pro type 2

insert into abonnement(id,type,prix_kwh)
values(5,'ELECTRICITE',0.112);

insert into abonnement(id,type,prix_kwh)
values(6,'GAZ',0.117);


insert into particulier_abonnements(particulier_id,abonnements_id) 
values(1,1);

insert into particulier_abonnements(particulier_id,abonnements_id) 
values(1,2);

insert into professionnel_abonnements(professionnel_id,abonnements_id) 
values(2,3);

insert into professionnel_abonnements(professionnel_id,abonnements_id) 
values(2,4);

insert into professionnel_abonnements(professionnel_id,abonnements_id) 
values(3,5);

insert into professionnel_abonnements(professionnel_id,abonnements_id) 
values(3,6);


insert into consommation(id,qte,date,abonnement_id)
values(1,123,'2024-05-14',1);
insert into consommation(id,qte,date,abonnement_id)
values(2,13,'2024-05-15',2);

insert into consommation(id,qte,date,abonnement_id)
values(3,100,'2024-05-14',3);
insert into consommation(id,qte,date,abonnement_id)
values(4,500,'2024-05-15',4);

insert into consommation(id,qte,date,abonnement_id)
values(5,64,'2024-05-14',5);
insert into consommation(id,qte,date,abonnement_id)
values(6,22,'2024-05-15',6);
