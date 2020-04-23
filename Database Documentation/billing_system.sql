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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cdr; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cdr (
    cdr_id integer NOT NULL,
    diala integer,
    dialb text,
    sid integer,
    duration_msg_volume text,
    start_date date,
    start_time time without time zone,
    external_charges money,
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
    msisdn integer NOT NULL,
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
    msisdn integer NOT NULL,
    pid integer NOT NULL,
    start_date date,
    end_date date,
    blocked_services integer,
    free_voice_same text,
    free_voice_diff text,
    free_sms_same text,
    free_sms_diff text,
    free_internet text
);


ALTER TABLE public.customer_profile OWNER TO postgres;

--
-- Name: free_units; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.free_units (
    fid integer NOT NULL,
    free_voice_same text,
    free_voice_diff text,
    free_sms_same text,
    free_sms_diff text,
    free_internet text
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
-- Name: profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile (
    pid integer NOT NULL,
    pname text,
    one_time_fee money,
    renew_duration integer,
    pfees money,
    recurring_services money,
    fid integer
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
    pid integer NOT NULL,
    sid integer NOT NULL,
    round_amount text,
    fees_local_same money,
    fees_local_diff money,
    fees_international money
);


ALTER TABLE public.profile_services OWNER TO postgres;

--
-- Name: services; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.services (
    sid integer NOT NULL,
    sname text
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
    cdr_id integer NOT NULL,
    pid integer,
    has_freeunits boolean,
    cost money
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
-- Name: profile pid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile ALTER COLUMN pid SET DEFAULT nextval('public.profile_pid_seq'::regclass);


--
-- Name: services sid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services ALTER COLUMN sid SET DEFAULT nextval('public.services_sid_seq'::regclass);


--
-- Name: udr udr_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.udr ALTER COLUMN udr_id SET DEFAULT nextval('public.udr_udr_id_seq'::regclass);


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
-- Data for Name: free_units; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.free_units (fid, free_voice_same, free_voice_diff, free_sms_same, free_sms_diff, free_internet) FROM stdin;
\.


--
-- Data for Name: profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile (pid, pname, one_time_fee, renew_duration, pfees, recurring_services, fid) FROM stdin;
\.


--
-- Data for Name: profile_services; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile_services (pid, sid, round_amount, fees_local_same, fees_local_diff, fees_international) FROM stdin;
\.


--
-- Data for Name: services; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.services (sid, sname) FROM stdin;
\.


--
-- Data for Name: udr; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.udr (udr_id, cdr_id, pid, has_freeunits, cost) FROM stdin;
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
-- Name: profile_pid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profile_pid_seq', 1, false);


--
-- Name: services_sid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.services_sid_seq', 1, false);


--
-- Name: udr_udr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.udr_udr_id_seq', 1, false);


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
-- Name: free_units free_units_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.free_units
    ADD CONSTRAINT free_units_pkey PRIMARY KEY (fid);


--
-- Name: profile profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (pid);


--
-- Name: profile_services profile_services_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_services
    ADD CONSTRAINT profile_services_pkey PRIMARY KEY (pid, sid);


--
-- Name: services services_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.services
    ADD CONSTRAINT services_pkey PRIMARY KEY (sid);


--
-- Name: udr udr_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.udr
    ADD CONSTRAINT udr_pkey PRIMARY KEY (udr_id, cdr_id);


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
-- Name: profile profile_fid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_fid_fkey FOREIGN KEY (fid) REFERENCES public.free_units(fid);


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
-- Name: udr udr_cdr_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.udr
    ADD CONSTRAINT udr_cdr_id_fkey FOREIGN KEY (cdr_id) REFERENCES public.cdr(cdr_id);


--
-- Name: udr udr_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.udr
    ADD CONSTRAINT udr_pid_fkey FOREIGN KEY (pid) REFERENCES public.profile(pid);


--
-- PostgreSQL database dump complete
--

