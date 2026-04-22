CREATE TYPE gender AS ENUM ('MALE', 'FEMALE');
CREATE TYPE frequency AS ENUM ('WEEKLY', 'MONTHLY', 'ANNUALLY', 'PUNCTUALLY');
CREATE TYPE activity_status AS ENUM ('ACTIVE', 'INACTIVE');
CREATE TYPE payment_mode AS ENUM ('CASH', 'MOBILE_BANKING', 'BANK_TRANSFER');
CREATE TYPE mobile_service AS ENUM ('AIRTEL_MONEY', 'MVOLA', 'ORANGE_MONEY');
CREATE TYPE bank_name AS ENUM ('BRED', 'MCB', 'BMOI', 'BOA', 'BGFI', 'AFG', 'ACCES_BANQUE', 'BAOBAB', 'SIPEM');
CREATE TYPE member_occupation AS ENUM (
    'JUNIOR',
    'SENIOR',
    'SECRETARY',
    'TREASURER',
    'VICE_PRESIDENT',
    'PRESIDENT'
);
CREATE TABLE collectivities (
                                id varchar(9) PRIMARY KEY,
                                name varchar(100) unique ,
                                number int unique ,
                                location VARCHAR(255),
                                federation_approval BOOLEAN NOT NULL
);
CREATE TABLE members (
                         id VARCHAR(9) PRIMARY KEY,

                         first_name VARCHAR(100) NOT NULL,
                         last_name VARCHAR(100) NOT NULL,
                         birth_date DATE,
                         gender gender,
                         address TEXT,
                         profession VARCHAR(150),
                         phone_number BIGINT,
                         email VARCHAR(150),

                         occupation member_occupation,

                         registration_fee_paid BOOLEAN DEFAULT FALSE,
                         membership_dues_paid BOOLEAN DEFAULT FALSE,
                         federation_join_date DATE,

                         collectivity_id VARCHAR(9),

                         CONSTRAINT fk_collectivity
                             FOREIGN KEY (collectivity_id)
                                 REFERENCES collectivities(id)
                                 ON DELETE CASCADE
);
CREATE TABLE member_referees (
                                 member_id VARCHAR(9),
                                 referee_id VARCHAR(9),
                                 PRIMARY KEY (member_id, referee_id),

                                 FOREIGN KEY (member_id)
                                     REFERENCES members(id)
                                     ON DELETE CASCADE,

                                 FOREIGN KEY (referee_id)
                                     REFERENCES members(id)
                                     ON DELETE CASCADE,

                                 CONSTRAINT no_self_reference
                                     CHECK (member_id <> referee_id)
);

CREATE TABLE collectivity_structure (
                                        collectivity_id VARCHAR(9) PRIMARY KEY,

                                        president_id VARCHAR(9) NOT NULL,
                                        vice_president_id VARCHAR(9) NOT NULL,
                                        treasurer_id VARCHAR(9) NOT NULL,
                                        secretary_id VARCHAR(9) NOT NULL,

                                        FOREIGN KEY (collectivity_id)
                                            REFERENCES collectivities(id)
                                            ON DELETE CASCADE,

                                        FOREIGN KEY (president_id) REFERENCES members(id),
                                        FOREIGN KEY (vice_president_id) REFERENCES members(id),
                                        FOREIGN KEY (treasurer_id) REFERENCES members(id),
                                        FOREIGN KEY (secretary_id) REFERENCES members(id)
);
CREATE TABLE membership_fees (
                                 id VARCHAR(9) PRIMARY KEY,
                                 collectivity_id VARCHAR(9) NOT NULL,
                                 eligible_from DATE NOT NULL,
                                 frequency frequency NOT NULL,
                                 amount DECIMAL(15, 2) NOT NULL CHECK (amount >= 0),
                                 label VARCHAR(255),
                                 status activity_status DEFAULT 'ACTIVE',
                                 federation_percentage DECIMAL(5,2),
                                 CONSTRAINT fk_fee_collectivity FOREIGN KEY (collectivity_id) REFERENCES collectivities(id) ON DELETE CASCADE
);
CREATE TABLE financial_accounts (
                                    id VARCHAR(9) PRIMARY KEY,
                                    collectivity_id VARCHAR(9) NOT NULL,
                                    account_type payment_mode NOT NULL,
                                    balance DECIMAL(15, 2) DEFAULT 0,
                                    CONSTRAINT fk_account_collectivity FOREIGN KEY (collectivity_id) REFERENCES collectivities(id) ON DELETE CASCADE
);
CREATE TABLE mobile_banking_accounts (
                                         account_id VARCHAR(9) PRIMARY KEY REFERENCES financial_accounts(id) ON DELETE CASCADE,
                                         holder_name VARCHAR(150) NOT NULL,
                                         service mobile_service NOT NULL,
                                         mobile_number BIGINT NOT NULL
);

CREATE TABLE bank_accounts (
                               account_id VARCHAR(9) PRIMARY KEY REFERENCES financial_accounts(id) ON DELETE CASCADE,
                               holder_name VARCHAR(150) NOT NULL,
                               bank_name bank_name NOT NULL,
                               bank_code INT,
                               branch_code INT,
                               account_number BIGINT,
                               account_key INT
);
CREATE TABLE cash_accounts (
                               account_id VARCHAR(9) PRIMARY KEY REFERENCES financial_accounts(id) ON DELETE CASCADE
);
CREATE TABLE transactions (
                              id VARCHAR(9) PRIMARY KEY,
                              creation_date DATE DEFAULT CURRENT_DATE,
                              amount DECIMAL(15, 2) NOT NULL,
                              payment_mode payment_mode NOT NULL,

                              member_id VARCHAR(9),
                              membership_fee_id VARCHAR(9),
                              account_id VARCHAR(9) NOT NULL,

                              CONSTRAINT fk_trans_member FOREIGN KEY (member_id) REFERENCES members(id),
                              CONSTRAINT fk_trans_fee FOREIGN KEY (membership_fee_id) REFERENCES membership_fees(id),
                              CONSTRAINT fk_trans_account FOREIGN KEY (account_id) REFERENCES financial_accounts(id)
);