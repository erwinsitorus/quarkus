create sequence if not exists post_id_seq;
create table if not exists public.post (
    post_id bigint not null default nextval('post_id_seq'),
    content varchar(255),
    title varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    primary key (post_id)
);

create sequence if not exists tag_id_seq;
create table if not exists public.tag (
    tag_id bigint not null default nextval('tag_id_seq'),
    label varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    primary key (tag_id)
);

create sequence if not exists post_tag_id_seq;
create table if not exists public.post_tag (
    post_tag_id bigint not null default nextval('post_tag_id_seq'),
    post_id bigint,
    tag_id bigint,
    primary key (post_tag_id),
	FOREIGN KEY (post_id) REFERENCES post(post_id),
	FOREIGN KEY (tag_id) REFERENCES tag(tag_id)
);