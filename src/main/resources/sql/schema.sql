CREATE TYPE gender AS ENUM ('MALE', 'FEMALE');
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
                                 ON DELETE SET NULL
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

                                        president_id VARCHAR(9),
                                        vice_president_id VARCHAR(9),
                                        treasurer_id VARCHAR(9),
                                        secretary_id VARCHAR(9),

                                        FOREIGN KEY (collectivity_id)
                                            REFERENCES collectivities(id)
                                            ON DELETE CASCADE,

                                        FOREIGN KEY (president_id) REFERENCES members(id),
                                        FOREIGN KEY (vice_president_id) REFERENCES members(id),
                                        FOREIGN KEY (treasurer_id) REFERENCES members(id),
                                        FOREIGN KEY (secretary_id) REFERENCES members(id)
);