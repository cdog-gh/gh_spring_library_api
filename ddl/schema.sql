-- public.book definition

-- Drop table

-- DROP TABLE public.book;

CREATE TABLE public.book (
    book_id bigserial NOT NULL,
    book_name varchar NOT NULL COLLATE "en_US.utf8",
    book_class int4 NOT NULL,
    CONSTRAINT book_pk PRIMARY KEY (book_id)
);
CREATE INDEX book_book_name_idx ON public.book USING btree (book_name);

-- public.borrow definition

-- Drop table

-- DROP TABLE public.borrow;

CREATE TABLE public.borrow (
    borrow_id bigserial NOT NULL,
    user_id int8 NOT NULL,
    borrow_time timestamptz NOT NULL,
    book_id int8 NOT NULL,
    CONSTRAINT borrow_pk PRIMARY KEY (borrow_id),
    CONSTRAINT borrow_un UNIQUE (book_id)
);
CREATE INDEX borrow_book_id_idx ON public.borrow USING btree (book_id);
CREATE INDEX borrow_user_id_idx ON public.borrow USING btree (user_id);

-- public."user" definition

-- Drop table

-- DROP TABLE public."user";

CREATE TABLE public."user" (
    user_id bigserial NOT NULL,
    user_email varchar NOT NULL,
    user_name varchar NOT NULL,
    user_pw varchar NOT NULL,
    user_role_name varchar NOT NULL,
    user_auth bool NULL,
    CONSTRAINT user_pk PRIMARY KEY (user_id),
    CONSTRAINT user_un UNIQUE (user_name)
);
CREATE INDEX user_user_name_idx ON public."user" USING btree (user_name);

INSERT INTO public."user"
(user_email, user_name, user_pw, user_role_name, user_auth)
VALUES('cho@example.com', 'cho', '$2a$10$VFV3RtUm5tUfTfDKOYbfzex9Qk4TfN69.XW0MIMtwxHjh8.HrB6ky', 'ROLE_ADMIN', true)
ON CONFLICT(user_name) DO NOTHING;
INSERT INTO public.book
(book_name, book_class) values
('스프링 퀵 스타트', 5);