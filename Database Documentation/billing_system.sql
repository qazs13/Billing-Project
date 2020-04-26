--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: checklogin(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.checklogin(auser text, pass text) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
DECLARE
count int;
BEGIN
SELECT COUNT(*) INTO count FROM admin WHERE adminuser = auser AND adminpassword = pass;
IF count = 1 THEN RETURN TRUE;
ELSE RETURN FALSE;
END IF;
END;
$$;


ALTER FUNCTION public.checklogin(auser text, pass text) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin (
    adminuser text NOT NULL,
    adminpassword text
);


ALTER TABLE public.admin OWNER TO postgres;

--
-- Name: cdr; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cdr (
    cdr_id integer NOT NULL,
    diala text,
    dialb text,
    sid integer,
    duration_msg_volume integer,
    start_date text,
    start_time text,
    external_charges double precision,
    is_rated boolean
);


ALTER TABLE public.cdr OWNER TO postgres;

--
-- Name: cdr_cdr_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cdr_cdr_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cdr_cdr_id_seq OWNER TO postgres;

--
-- Name: cdr_cdr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cdr_cdr_id_seq OWNED BY public.cdr.cdr_id;


--
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    msisdn text NOT NULL,
    f_name text,
    l_name text,
    email text,
    address text
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- Name: customer_profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer_profile (
    msisdn text NOT NULL,
    pid integer NOT NULL,
    start_date text,
    end_date text,
    blocked_services integer,
    free_voice_same double precision,
    free_voice_diff double precision,
    free_sms_same double precision,
    free_sms_diff double precision,
    free_internet double precision
);


ALTER TABLE public.customer_profile OWNER TO postgres;

--
-- Name: customer_profile_services; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer_profile_services (
    msisdn text NOT NULL,
    pid integer NOT NULL,
    occ_id integer NOT NULL
);


ALTER TABLE public.customer_profile_services OWNER TO postgres;

--
-- Name: free_units; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.free_units (
    fid integer NOT NULL,
    free_voice_same double precision,
    free_voice_diff double precision,
    free_sms_same double precision,
    free_sms_diff double precision,
    free_internet double precision,
    pid integer
);


ALTER TABLE public.free_units OWNER TO postgres;

--
-- Name: free_units_fid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.free_units_fid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.free_units_fid_seq OWNER TO postgres;

--
-- Name: free_units_fid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.free_units_fid_seq OWNED BY public.free_units.fid;


--
-- Name: occ; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.occ (
    occ_id integer NOT NULL,
    msisdn text,
    one_time_service_id integer,
    service_processed boolean
);


ALTER TABLE public.occ OWNER TO postgres;

--
-- Name: occ_occ_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.occ_occ_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.occ_occ_id_seq OWNER TO postgres;

--
-- Name: occ_occ_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.occ_occ_id_seq OWNED BY public.occ.occ_id;


--
-- Name: one_time_service; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.one_time_service (
    one_time_service_id integer NOT NULL,
    osname text,
    osfee double precision
);


ALTER TABLE public.one_time_service OWNER TO postgres;

--
-- Name: one_time_service_one_time_service_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.one_time_service_one_time_service_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.one_time_service_one_time_service_id_seq OWNER TO postgres;

--
-- Name: one_time_service_one_time_service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.one_time_service_one_time_service_id_seq OWNED BY public.one_time_service.one_time_service_id;


--
-- Name: profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile (
    pid integer NOT NULL,
    pname text,
    renew_duration integer,
    pfees double precision
);


ALTER TABLE public.profile OWNER TO postgres;

--
-- Name: profile_pid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profile_pid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profile_pid_seq OWNER TO postgres;

--
-- Name: profile_pid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profile_pid_seq OWNED BY public.profile.pid;


--
-- Name: profile_services; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile_services (
    profile_services_id integer NOT NULL,
    pid integer,
    sid integer,
    round_amount integer,
    fees_local_same double precision,
    fees_local_diff double precision,
    fees_international double precision
);


ALTER TABLE public.profile_services OWNER TO postgres;

--
-- Name: profile_services_profile_services_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profile_services_profile_services_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profile_services_profile_services_id_seq OWNER TO postgres;

--
-- Name: profile_services_profile_services_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profile_services_profile_services_id_seq OWNED BY public.profile_services.profile_services_id;


--
-- Name: services; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.services (
    sid integer NOT NULL,
    sname text,
    is_recurring boolean,
    recurring_fees double precision
);


ALTER TABLE public.services OWNER TO postgres;

--
-- Name: services_sid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.services_sid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.services_sid_seq OWNER TO postgres;

--
-- Name: services_sid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.services_sid_seq OWNED BY public.services.sid;


--
-- Name: udr; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.udr (
    udr_id integer NOT NULL,
    pid integer,
    diala text,
    dialb text,
    sid integer,
    duration_msg_volume integer,
    start_date text,
    start_time text,
    external_charges double precision,
    has_freeunits boolean,
    cost double precision,
    is_billed boolean
);


ALTER TABLE public.udr OWNER TO postgres;

--
-- Name: udr_udr_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.udr_udr_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.udr_udr_id_seq OWNER TO postgres;

--
-- Name: udr_udr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.udr_udr_id_seq OWNED BY public.udr.udr_id;


--
-- Name: cdr cdr_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cdr ALTER COLUMN cdr_id SET DEFAULT nextval('public.cdr_cdr_id_seq'::regclass);


--
-- Name: free_units fid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.free_units ALTER COLUMN fid SET DEFAULT nextval('public.free_units_fid_seq'::regclass);


--
-- Name: occ occ_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.occ ALTER COLUMN occ_id SET DEFAULT nextval('public.occ_occ_id_seq'::regclass);


--
-- Name: one_time_service one_time_service_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.one_time_service ALTER COLUMN one_time_service_id SET DEFAULT nextval('public.one_time_service_one_time_service_id_seq'::regclass);


--
-- Name: profile pid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile ALTER COLUMN pid SET DEFAULT nextval('public.profile_pid_seq'::regclass);


--
-- Name: profile_services profile_services_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_services ALTER COLUMN profile_services_id SET DEFAULT nextval('public.profile_services_profile_services_id_seq'::regclass);


--
-- Name: services sid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services ALTER COLUMN sid SET DEFAULT nextval('public.services_sid_seq'::regclass);


--
-- Name: udr udr_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.udr ALTER COLUMN udr_id SET DEFAULT nextval('public.udr_udr_id_seq'::regclass);


--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admin (adminuser, adminpassword) FROM stdin;
Rim	rim
Menna	1234
\.


--
-- Data for Name: cdr; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cdr (cdr_id, diala, dialb, sid, duration_msg_volume, start_date, start_time, external_charges, is_rated) FROM stdin;
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (msisdn, f_name, l_name, email, address) FROM stdin;
\.


--
-- Data for Name: customer_profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer_profile (msisdn, pid, start_date, end_date, blocked_services, free_voice_same, free_voice_diff, free_sms_same, free_sms_diff, free_internet) FROM stdin;
\.


--
-- Data for Name: customer_profile_services; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer_profile_services (msisdn, pid, occ_id) FROM stdin;
\.


--
-- Data for Name: free_units; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.free_units (fid, free_voice_same, free_voice_diff, free_sms_same, free_sms_diff, free_internet, pid) FROM stdin;
\.


--
-- Data for Name: occ; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.occ (occ_id, msisdn, one_time_service_id, service_processed) FROM stdin;
\.


--
-- Data for Name: one_time_service; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.one_time_service (one_time_service_id, osname, osfee) FROM stdin;
1	Xml	20
\.


--
-- Data for Name: profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile (pid, pname, renew_duration, pfees) FROM stdin;
\.


--
-- Data for Name: profile_services; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile_services (profile_services_id, pid, sid, round_amount, fees_local_same, fees_local_diff, fees_international) FROM stdin;
\.


--
-- Data for Name: services; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.services (sid, sname, is_recurring, recurring_fees) FROM stdin;
1	Trial	t	30
\.


--
-- Data for Name: udr; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.udr (udr_id, pid, diala, dialb, sid, duration_msg_volume, start_date, start_time, external_charges, has_freeunits, cost, is_billed) FROM stdin;
\.


--
-- Name: cdr_cdr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cdr_cdr_id_seq', 1, false);


--
-- Name: free_units_fid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.free_units_fid_seq', 1, false);


--
-- Name: occ_occ_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.occ_occ_id_seq', 1, false);


--
-- Name: one_time_service_one_time_service_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.one_time_service_one_time_service_id_seq', 1, true);


--
-- Name: profile_pid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profile_pid_seq', 1, false);


--
-- Name: profile_services_profile_services_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profile_services_profile_services_id_seq', 1, false);


--
-- Name: services_sid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.services_sid_seq', 1, true);


--
-- Name: udr_udr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.udr_udr_id_seq', 1, false);


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (adminuser);


--
-- Name: cdr cdr_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cdr
    ADD CONSTRAINT cdr_pkey PRIMARY KEY (cdr_id);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (msisdn);


--
-- Name: customer_profile customer_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer_profile
    ADD CONSTRAINT customer_profile_pkey PRIMARY KEY (msisdn, pid);


--
-- Name: customer_profile_services customer_profile_services_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer_profile_services
    ADD CONSTRAINT customer_profile_services_pkey PRIMARY KEY (msisdn, pid, occ_id);


--
-- Name: free_units free_units_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.free_units
    ADD CONSTRAINT free_units_pkey PRIMARY KEY (fid);


--
-- Name: occ occ_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.occ
    ADD CONSTRAINT occ_pkey PRIMARY KEY (occ_id);


--
-- Name: one_time_service one_time_service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.one_time_service
    ADD CONSTRAINT one_time_service_pkey PRIMARY KEY (one_time_service_id);


--
-- Name: profile profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (pid);


--
-- Name: profile_services profile_services_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_services
    ADD CONSTRAINT profile_services_pkey PRIMARY KEY (profile_services_id);


--
-- Name: services services_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services
    ADD CONSTRAINT services_pkey PRIMARY KEY (sid);


--
-- Name: udr udr_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.udr
    ADD CONSTRAINT udr_pkey PRIMARY KEY (udr_id);


--
-- Name: cdr cdr_sid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cdr
    ADD CONSTRAINT cdr_sid_fkey FOREIGN KEY (sid) REFERENCES public.services(sid);


--
-- Name: customer_profile customer_profile_blocked_services_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer_profile
    ADD CONSTRAINT customer_profile_blocked_services_fkey FOREIGN KEY (blocked_services) REFERENCES public.services(sid);


--
-- Name: customer_profile customer_profile_msisdn_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer_profile
    ADD CONSTRAINT customer_profile_msisdn_fkey FOREIGN KEY (msisdn) REFERENCES public.customer(msisdn);


--
-- Name: customer_profile customer_profile_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer_profile
    ADD CONSTRAINT customer_profile_pid_fkey FOREIGN KEY (pid) REFERENCES public.profile(pid);


--
-- Name: customer_profile_services customer_profile_services_msisdn_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer_profile_services
    ADD CONSTRAINT customer_profile_services_msisdn_fkey FOREIGN KEY (msisdn) REFERENCES public.customer(msisdn);


--
-- Name: customer_profile_services customer_profile_services_occ_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer_profile_services
    ADD CONSTRAINT customer_profile_services_occ_id_fkey FOREIGN KEY (occ_id) REFERENCES public.occ(occ_id);


--
-- Name: customer_profile_services customer_profile_services_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer_profile_services
    ADD CONSTRAINT customer_profile_services_pid_fkey FOREIGN KEY (pid) REFERENCES public.profile(pid);


--
-- Name: free_units my_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.free_units
    ADD CONSTRAINT my_fk FOREIGN KEY (pid) REFERENCES public.profile(pid);


--
-- Name: occ occ_msisdn_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.occ
    ADD CONSTRAINT occ_msisdn_fkey FOREIGN KEY (msisdn) REFERENCES public.customer(msisdn);


--
-- Name: occ occ_one_time_service_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.occ
    ADD CONSTRAINT occ_one_time_service_id_fkey FOREIGN KEY (one_time_service_id) REFERENCES public.one_time_service(one_time_service_id);


--
-- Name: profile_services profile_services_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_services
    ADD CONSTRAINT profile_services_pid_fkey FOREIGN KEY (pid) REFERENCES public.profile(pid);


--
-- Name: profile_services profile_services_sid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_services
    ADD CONSTRAINT profile_services_sid_fkey FOREIGN KEY (sid) REFERENCES public.services(sid);


--
-- Name: udr udr_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.udr
    ADD CONSTRAINT udr_pid_fkey FOREIGN KEY (pid) REFERENCES public.profile(pid);


--
-- Name: udr udr_sid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.udr
    ADD CONSTRAINT udr_sid_fkey FOREIGN KEY (sid) REFERENCES public.services(sid);


--
-- PostgreSQL database dump complete
--

