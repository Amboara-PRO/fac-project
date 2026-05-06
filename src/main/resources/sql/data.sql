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


INSERT INTO collectivities (id, name, number, location, federation_approval)
VALUES ('COLL001', 'Collectivité A', 1, 'Antananarivo', true);

INSERT INTO financial_accounts (id, collectivity_id, account_type)
VALUES
    ('ACC001', 'COLL001', 'CASH');

-- mensuelle
INSERT INTO membership_fees (id, collectivity_id, eligible_from, frequency, amount, label, status)
VALUES
    ('FEE001', 'COLL001', '2025-01-01', 'MONTHLY', 10000, 'Cotisation mensuelle', 'ACTIVE');

-- hebdomadaire
INSERT INTO membership_fees (id, collectivity_id, eligible_from, frequency, amount, label, status)
VALUES
    ('FEE002', 'COLL001', '2025-01-01', 'WEEKLY', 2000, 'Cotisation hebdo', 'ACTIVE');

-- ponctuelle
INSERT INTO membership_fees (id, collectivity_id, eligible_from, frequency, amount, label, status)
VALUES
    ('FEE003', 'COLL001', '2025-03-01', 'PUNCTUALLY', 50000, 'Frais exceptionnel', 'ACTIVE');


-- INSERT INTO transactions (id, creation_date, amount, payment_mode, member_id, membership_fee_id, account_id)
-- VALUES
-- -- Jean paye partiellement
-- ('T0011', '2025-02-01', 10000, 'CASH', 'M0011', 'FEE001', 'ACC001'),
-- ('T0012', '2025-02-15', 4000, 'CASH', 'M0011', 'FEE002', 'ACC001'),
--
-- -- Marie paye tout
-- ('T0013', '2025-02-01', 20000, 'CASH', 'M0012', 'FEE001', 'ACC001'),
-- ('T0014', '2025-02-10', 8000, 'CASH', 'M0012', 'FEE002', 'ACC001'),
--
-- -- Paul ne paye rien
-- ('T0015', '2025-03-10', 0, 'CASH', 'M0013', NULL, 'ACC001');

INSERT INTO transactions (
    id, creation_date, amount, payment_mode, member_id, membership_fee_id, account_id
)
VALUES
    ('TR001', '2026-05-01', 10000, 'CASH', 'MBR001', 'FEE001', 'A1'),
    ('TR002', '2026-05-02', 10000, 'CASH', 'MBR002', 'FEE001', 'A1');

INSERT INTO members (id, first_name, last_name, email, occupation, collectivity_id, federation_join_date)
VALUES
    ('M0011', 'Jean', 'Rakoto', 'jean@test.com', 'PRESIDENT', 'COLL001', '2025-01-01'),
    ('M0012', 'Marie', 'Rasoa', 'marie@test.com', 'TREASURER', 'COLL001', '2025-01-01'),
    ('M0013', 'Paul', 'Randria', 'paul@test.com', 'JUNIOR', 'COLL001', '2025-01-01');

INSERT INTO collectivities (id, name, number, location, federation_approval)
VALUES
    ('COLL002', 'Collectivité B', 2, 'Toamasina', true);

INSERT INTO members (id, first_name, last_name, email, occupation, collectivity_id, federation_join_date)
VALUES
('M2001', 'Alice', 'Rabe', 'alice@test.com', 'VICE_PRESIDENT', 'COLL002', '2025-01-01'),
('M2002', 'Bob', 'Ranaivo', 'bob@test.com', 'SECRETARY', 'COLL002', '2025-01-01'),
('M2003', 'Charlie', 'Rakoto', 'charlie@test.com', 'JUNIOR', 'COLL002', '2025-01-01');

INSERT INTO financial_accounts (id, collectivity_id, account_type, amount)
VALUES
    ('A1', 'COL001', 'CASH', 0),
    ('A2', 'COL-001', 'CASH', 0);

INSERT INTO membership_fees (id, collectivity_id, eligible_from, frequency, amount, label, status)
VALUES
    ('FEE201', 'COLL002', '2025-01-01', 'MONTHLY', 8000, 'Mensuelle B', 'ACTIVE'),
    ('FEE202', 'COLL002', '2025-01-01', 'WEEKLY', 2000, 'Hebdo B', 'ACTIVE'),
    ('FEE203', 'COLL002', '2025-02-01', 'PUNCTUALLY', 30000, 'Event B', 'INACTIVE');

INSERT INTO transactions (id, creation_date, amount, payment_mode, member_id, membership_fee_id, account_id)
VALUES
-- Alice
('T2001', '2025-01-10', 8000, 'CASH', 'M2001', 'FEE201', 'ACC002'),
('T2002', '2025-02-10', 4000, 'CASH', 'M2001', 'FEE202', 'ACC002'),

-- Bob (surpaiement volontaire)
('T2003', '2025-02-01', 20000, 'CASH', 'M2002', 'FEE201', 'ACC002'),
('T2004', '2025-02-15', 15000, 'CASH', 'M2002', 'FEE202', 'ACC002'),

-- Charlie (rien)
('T2005', '2025-03-01', 0, 'CASH', 'M2003', NULL, 'ACC002');

UPDATE members SET federation_join_date = '2026-01-10' WHERE id = 'MBR001';
UPDATE members SET federation_join_date = '2026-02-15' WHERE id = 'MBR002';
UPDATE members SET federation_join_date = '2026-03-01' WHERE id = 'MBR003';
UPDATE members SET federation_join_date = '2025-12-01' WHERE id = 'MBR004';