CREATE TABLE tb_email (
  id BINARY(16) NOT NULL,
   owner_ref VARCHAR(255) NULL,
   email_from VARCHAR(255) NULL,
   email_to VARCHAR(255) NULL,
   subject VARCHAR(255) NULL,
   text TEXT NULL,
   send_date_email datetime NULL,
   status_email SMALLINT NULL,
   CONSTRAINT pk_tb_email PRIMARY KEY (id)
);