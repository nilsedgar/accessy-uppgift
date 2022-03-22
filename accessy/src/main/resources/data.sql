create table IF NOT EXISTS operation
(
    id        uuid not null
        primary key ,
    name varchar(255),
    lock_id         uuid
    constraint fk_lock
    references lock
);

create table IF NOT EXISTS lock
(
    id        uuid not null
    primary key ,
    name varchar(255)
);

create sequence IF NOT EXISTS operation_sequence;
create sequence IF NOT EXISTS lock_sequence;


INSERT INTO lock (id, name) VALUES ('aaaaaaaa-2654-4dfe-8146-007e40f65347', 'Ytterdörren');
INSERT INTO lock (id, name) VALUES ('bbbbbbbb-2654-4dfe-8146-007e40f65347', 'Cykelförrådet');
INSERT INTO lock (id, name) VALUES ('cccccccc-2654-4dfe-8146-007e40f65347', 'Fort Knox');
INSERT INTO operation (id, name, lock_id) VALUES ('aaaaaaaa-2654-4dfe-8146-007e40f65348', 'unlock','aaaaaaaa-2654-4dfe-8146-007e40f65347');
INSERT INTO operation (id, name, lock_id) VALUES ('bbbbbbbb-2654-4dfe-8146-007e40f65348', 'unlock','bbbbbbbb-2654-4dfe-8146-007e40f65347');
INSERT INTO operation (id, name, lock_id) VALUES ('cccccccc-2654-4dfe-8146-007e40f65348', 'unlock','aaaaaaaa-2654-4dfe-8146-007e40f65347');

