create extension postgis;

DROP TABLE IF EXISTS public.wos_gis;

CREATE TABLE public.wos_gis
(
	id serial primary key not null,
	name VARCHAR(32),
	location VARCHAR(512),
	business_key serial,
	business_type VARCHAR(32),
	longitude real,
	latitude real
);
comment on table public.wos_gis IS 'gis表';
comment on table public.wos_gis.id IS '主键id';
comment on table public.wos_gis.name IS '业务对象名称';
comment on table public.wos_gis.business_key IS '业务对象id';
comment on table public.wos_gis.business_type IS '业务对象类型';
comment on table public.wos_gis.longitude IS '经度';
comment on table public.wos_gis.latitude IS '纬度';

CREATE SEQUENCE map_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
alter table wos_gis alter column id set default nextval('map_id_seq');

select AddGeometryColumn('public', 'wos_gis', 'geom', 4326, 'POINT', 2);