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
    diala integer,
    dialb json,
    sid integer,
    duration integer,
    start_date date,
    end_date date,
    external_charges integer
);


ALTER TABLE public.cdr OWNER TO postgres;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    msisdn integer NOT NULL,
    first_name text,
    last_name text,
    email text,
    address text
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- Name: customer_msisdn_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_msisdn_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_msisdn_seq OWNER TO postgres;

--
-- Name: customer_msisdn_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_msisdn_seq OWNED BY public.customer.msisdn;


--
-- Name: customer_profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer_profile (
    msisdn integer NOT NULL,
    pid integer NOT NULL,
    start_date date,
    end_date date,
    additional_services integer
);


ALTER TABLE public.customer_profile OWNER TO postgres;

--
-- Name: free_units; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.free_units (
    fid integer NOT NULL,
    free_voice integer,
    free_sms integer,
    free_internet integer
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
    renew_duration integer,
    pfees integer
);


ALTER TABLE public.profile OWNER TO postgres;

--
-- Name: profile_freeunits; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile_freeunits (
    pid integer NOT NULL,
    fid integer NOT NULL
);


ALTER TABLE public.profile_freeunits OWNER TO postgres;

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
    round_amount numeric,
    fees_local_same_operator integer,
    fees_local_diff_operator integer,
    fees_international integer,
    additional_quota integer
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
-- Name: customer msisdn; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN msisdn SET DEFAULT nextval('public.customer_msisdn_seq'::regclass);


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
-- Data for Name: cdr; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cdr (diala, dialb, sid, duration, start_date, end_date, external_charges) FROM stdin;
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (msisdn, first_name, last_name, email, address) FROM stdin;
1	Ali	Mohamed	ali@telecom.com	Masr el gedida
\.


--
-- Data for Name: customer_profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer_profile (msisdn, pid, start_date, end_date, additional_services) FROM stdin;
\.


--
-- Data for Name: free_units; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.free_units (fid, free_voice, free_sms, free_internet) FROM stdin;
3	10	0	300
\.


--
-- Data for Name: profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile (pid, pname, renew_duration, pfees) FROM stdin;
\.


--
-- Data for Name: profile_freeunits; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile_freeunits (pid, fid) FROM stdin;
\.


--
-- Data for Name: profile_services; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile_services (pid, sid, round_amount, fees_local_same_operator, fees_local_diff_operator, fees_international, additional_quota) FROM stdin;
\.


--
-- Data for Name: services; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.services (sid, sname) FROM stdin;
\.


--
-- Name: customer_msisdn_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_msisdn_seq', 1, true);


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
-- Name: profile_freeunits profile_freeunits_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_freeunits
    ADD CONSTRAINT profile_freeunits_pkey PRIMARY KEY (pid, fid);


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
-- Name: cdr cdr_sid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cdr
    ADD CONSTRAINT cdr_sid_fkey FOREIGN KEY (sid) REFERENCES public.services(sid);


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
-- Name: profile_freeunits profile_freeunits_fid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_freeunits
    ADD CONSTRAINT profile_freeunits_fid_fkey FOREIGN KEY (fid) REFERENCES public.free_units(fid);


--
-- Name: profile_freeunits profile_freeunits_pid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_freeunits
    ADD CONSTRAINT profile_freeunits_pid_fkey FOREIGN KEY (pid) REFERENCES public.profile(pid);


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
-- PostgreSQL database dump complete
--

