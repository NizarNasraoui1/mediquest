CREATE TABLE users (
                       id bigint NOT NULL,
                       enabled boolean NOT NULL,
                       name character varying(255),
                       password character varying(255),
                       username character varying(255),
                       uuid uuid,
                       role_id bigint
);


ALTER TABLE users OWNER TO postgres;

ALTER TABLE users ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


ALTER TABLE ONLY a6_schema.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
