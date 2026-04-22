INSERT INTO members (
    id,
    first_name,
    last_name,
    birth_date,
    gender,
    address,
    profession,
    phone_number,
    email,
    occupation,
    registration_fee_paid,
    membership_dues_paid
) VALUES
      (
          'm1',
          'Jean',
          'Rakoto',
          '1990-05-10',
          'MALE',
          'Antananarivo',
          'Farmer',
          0341234567,
          'jean@test.com',
          'PRESIDENT',
          true,
          true
      ),
      (
          'm2',
          'Paul',
          'Ranaivo',
          '1988-02-14',
          'MALE',
          'Antsirabe',
          'Farmer',
          0349876543,
          'paul@test.com',
          'VICE_PRESIDENT',
          true,
          true
      ),
      (
          'm3',
          'Marie',
          'Rakoto',
          '1995-07-20',
          'FEMALE',
          'Toamasina',
          'Accountant',
          0324567890,
          'marie@test.com',
          'TREASURER',
          true,
          true
      ),
      (
          'm4',
          'Sarah',
          'Randria',
          '1993-11-11',
          'FEMALE',
          'Fianarantsoa',
          'Secretary',
          0331239876,
          'sarah@test.com',
          'SECRETARY',
          true,
          true
      );
INSERT INTO member_referees (member_id, referee_id) VALUES
                                                        ('m2','m1'),
                                                        ('m3','m1'),
                                                        ('m4','m2');
INSERT INTO members (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, federation_join_date)
VALUES
    ('M001', 'Jean', 'Dupont', '1980-05-15', 'MALE', '123 Rue de Paris', 'Ingénieur', 33601020304, 'jean.dupont@email.com', '2023-01-10'),
    ('M002', 'Marie', 'Curie', '1985-08-22', 'FEMALE', '45 Ave des Sciences', 'Chercheuse', 33605060708, 'marie.curie@email.com', '2023-01-10'),
    ('M003', 'Luc', 'Besson', '1970-03-10', 'MALE', '10 Rue du Cinéma', 'Réalisateur', 33611121314, 'luc.besson@email.com', '2023-01-10'),
    ('M004', 'Sophie', 'Marceau', '1966-11-17', 'FEMALE', '88 Blvd des Arts', 'Actrice', 33621222324, 'sophie.m@email.com', '2023-01-10'),
    ('M005', 'Thomas', 'Pesquet', '1978-02-27', 'MALE', '1 Spatioport', 'Astronaute', 33631323334, 'thomas.p@email.com', '2023-01-10'),
    ('M006', 'Léa', 'Seydoux', '1985-07-01', 'FEMALE', '12 Rue de la Paix', 'Actrice', 33641424344, 'lea.s@email.com', '2023-01-10'),
    ('M007', 'Omar', 'Sy', '1978-01-20', 'MALE', '55 Rue du Rire', 'Acteur', 33651525354, 'omar.sy@email.com', '2026-04-01'), -- Récent
    ('M008', 'Zinedine', 'Zidane', '1972-06-23', 'MALE', '10 Stade de France', 'Coach', 33661626364, 'zizou@email.com', '2026-04-01'),   -- Récent
    ('M009', 'Marion', 'Cotillard', '1975-09-30', 'FEMALE', '99 Rue d Hollywood', 'Actrice', 33671727374, 'marion.c@email.com', '2026-04-01'),-- Récent
    ('M010', 'Alain', 'Delon', '1935-11-08', 'MALE', '7 Rue du Samouraï', 'Acteur', 33681828384, 'alain.delon@email.com', '2026-04-01');  -- Récent
