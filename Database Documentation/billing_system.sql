--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

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
-- Name: checkifcustomerbilledbefore(text, date); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.checkifcustomerbilledbefore(dialnum text, billdate date) RETURNS boolean
    LANGUAGE plpgsql
    AS $$

DECLARE

	is_billed integer;

BEGIN

	SELECT COUNT(customer_name) INTO is_billed FROM invoicesheet WHERE customer_number = dialNum AND  bill_date = billDate;

	IF is_billed = 0

	THEN

		return true;

	ELSE

		return false;

	END IF;

END;

$$;


ALTER FUNCTION public.checkifcustomerbilledbefore(dialnum text, billdate date) OWNER TO postgres;

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
-- Name: customerremainedfreeunits(integer, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.customerremainedfreeunits(profileid integer, dialnum text) RETURNS SETOF public.customer_profile
    LANGUAGE plpgsql
    AS $$
DECLARE 
BEGIN
RETURN QUERY(SELECT * FROM customer_profile WHERE msisdn=dialNum AND pid=profileID);
END
$$;


ALTER FUNCTION public.customerremainedfreeunits(profileid integer, dialnum text) OWNER TO postgres;

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
-- Name: customerudr(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.customerudr(dialnum text) RETURNS SETOF public.udr
    LANGUAGE plpgsql
    AS $$

DECLARE

	timestamp text;

BEGIN

	RETURN QUERY(SELECT * FROM udr WHERE dialA=dialNum ORDER BY TO_DATE(start_date, 'DD/MM/YYYY'));

END

$$;


ALTER FUNCTION public.customerudr(dialnum text) OWNER TO postgres;

--
-- Name: insertbillsheet(text, text, text, text, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision, date); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.insertbillsheet(customername text, cnumber text, caddress text, pname text, profilefees double precision, onetimefee double precision, recurringfees double precision, totalvoicecost double precision, totalsmscost double precision, totaldatacost double precision, totalinvoicebefore double precision, totalinvoiceafter double precision, billdate date) RETURNS boolean
    LANGUAGE plpgsql
    AS $$

DECLARE

BEGIN

	INSERT INTO invoiceSheet (customer_name,customer_number,address, p_name, pfees, osfee, recurring_fees, total_voice_cost, total_sms_cost,

		total_data_cost,total_invoice_before, total_invoice_after, bill_date) 

		VALUES (customerName, cNumber, cAddress, pName, profileFees, oneTimeFee, recurringFees, totalVoiceCost, totalSMSCost, totalDataCost,

		totalInvoiceBefore, totalInvoiceAfter, billDate);



 	RETURN true;

END;

$$;


ALTER FUNCTION public.insertbillsheet(customername text, cnumber text, caddress text, pname text, profilefees double precision, onetimefee double precision, recurringfees double precision, totalvoicecost double precision, totalsmscost double precision, totaldatacost double precision, totalinvoicebefore double precision, totalinvoiceafter double precision, billdate date) OWNER TO postgres;

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
-- Name: profilefreeunits(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.profilefreeunits(profileid integer) RETURNS SETOF public.free_units
    LANGUAGE plpgsql
    AS $$
DECLARE
BEGIN
RETURN QUERY(SELECT * FROM free_units WHERE pid=profileID);
END
$$;


ALTER FUNCTION public.profilefreeunits(profileid integer) OWNER TO postgres;

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
-- Name: retieveprofiledetails(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.retieveprofiledetails(profileid integer) RETURNS SETOF public.profile
    LANGUAGE plpgsql
    AS $$

DECLARE

BEGIN

	RETURN QUERY(SELECT * FROM profile WHERE pid = profileID);

END;

$$;


ALTER FUNCTION public.retieveprofiledetails(profileid integer) OWNER TO postgres;

--
-- Name: retrieveallcustomershaveudrs(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.retrieveallcustomershaveudrs() RETURNS TABLE(dialnum text, profileid integer)
    LANGUAGE plpgsql
    AS $$

DECLARE

BEGIN

	RETURN QUERY(SELECT DISTINCT diala,pid  FROM udr);

END;

$$;


ALTER FUNCTION public.retrieveallcustomershaveudrs() OWNER TO postgres;

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
-- Name: retrievecustomerdetails(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.retrievecustomerdetails(dialnum text) RETURNS SETOF public.customer
    LANGUAGE plpgsql
    AS $$

DECLARE

BEGIN

	RETURN QUERY(SELECT * FROM customer WHERE msisdn=DialNum);

END;

$$;


ALTER FUNCTION public.retrievecustomerdetails(dialnum text) OWNER TO postgres;

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
-- Name: retrieveprofileservices(integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.retrieveprofileservices(profileid integer, serviceid integer) RETURNS SETOF public.profile_services
    LANGUAGE plpgsql
    AS $$

DECLARE

BEGIN

	RETURN QUERY(SELECT * FROM profile_services WHERE pid=profileID AND sid=serviceID);

END;

$$;


ALTER FUNCTION public.retrieveprofileservices(profileid integer, serviceid integer) OWNER TO postgres;

--
-- Name: udrrow(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.udrrow(udrid integer) RETURNS SETOF public.udr
    LANGUAGE plpgsql
    AS $$

DECLARE

BEGIN

	RETURN QUERY(SELECT * FROM udr WHERE udr_id = udrID);

END

$$;


ALTER FUNCTION public.udrrow(udrid integer) OWNER TO postgres;

--
-- Name: updatecustomerfus(text, integer, integer, double precision, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.updatecustomerfus(dialnum text, profileid integer, serviceid integer, quantity double precision, netconnection text) RETURNS boolean
    LANGUAGE plpgsql
    AS $$

DECLARE

BEGIN

	if  serviceID = 1  THEN

		IF netConnection = 'onNet' THEN 

			UPDATE customer_profile SET free_voice_same = quantity WHERE msisdn = dialNum AND pid = profileID;

		ELSIF netConnection = 'crossNet' THEN

			UPDATE customer_profile SET free_voice_diff = quantity WHERE msisdn = dialNum AND pid = profileID;

		END IF;

		UPDATE udr SET is_billed = true WHERE diala = dialNum AND pid = profileID AND sid = serviceID;	

		RETURN true; 

	elsif serviceID = 2 THEN

		IF netConnection = 'onNet' THEN 

			UPDATE customer_profile SET free_sms_same = quantity WHERE msisdn = dialNum AND pid = profileID;

		ELSIF netConnection = 'crossNet' THEN

			UPDATE customer_profile SET free_sms_diff = quantity WHERE msisdn = dialNum AND pid = profileID;

		END IF;

		UPDATE udr SET is_billed = true WHERE diala = dialNum AND pid = profileID AND sid = serviceID;

		RETURN true;

	elsif serviceID = 3 THEN 

		UPDATE customer_profile SET free_internet = quantity WHERE msisdn = dialNum AND pid = profileID;

		UPDATE udr SET is_billed = true WHERE diala = dialNum AND pid = profileID AND sid = serviceID;

		RETURN true;

	ELSE 

		RETURN false;

	END IF; 

END;

$$;


ALTER FUNCTION public.updatecustomerfus(dialnum text, profileid integer, serviceid integer, quantity double precision, netconnection text) OWNER TO postgres;

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
-- Name: invoicesheet; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoicesheet (
    bill_id integer NOT NULL,
    customer_name text,
    customer_number text,
    address text,
    p_name text,
    pfees double precision,
    osfee double precision,
    recurring_fees double precision,
    total_voice_cost double precision,
    total_sms_cost double precision,
    total_data_cost double precision,
    total_invoice_before double precision,
    total_invoice_after double precision,
    bill_date date
);


ALTER TABLE public.invoicesheet OWNER TO postgres;

--
-- Name: invoicesheet_bill_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.invoicesheet_bill_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.invoicesheet_bill_id_seq OWNER TO postgres;

--
-- Name: invoicesheet_bill_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.invoicesheet_bill_id_seq OWNED BY public.invoicesheet.bill_id;


--
-- Name: occ; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.occ (
    occ_id integer NOT NULL,
    msisdn text NOT NULL,
    one_recurring_id integer,
    type_of_service text,
    is_service_processed boolean,
    service_processed_date date
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
-- Name: invoicesheet bill_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoicesheet ALTER COLUMN bill_id SET DEFAULT nextval('public.invoicesheet_bill_id_seq'::regclass);


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
-- Data for Name: free_units; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.free_units (fid, free_voice_same, free_voice_diff, free_sms_same, free_sms_diff, free_internet, pid) FROM stdin;
\.


--
-- Data for Name: invoicesheet; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.invoicesheet (bill_id, customer_name, customer_number, address, p_name, pfees, osfee, recurring_fees, total_voice_cost, total_sms_cost, total_data_cost, total_invoice_before, total_invoice_after, bill_date) FROM stdin;
\.


--
-- Data for Name: occ; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.occ (occ_id, msisdn, one_recurring_id, type_of_service, is_service_processed, service_processed_date) FROM stdin;
\.


--
-- Data for Name: one_time_service; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.one_time_service (one_time_service_id, osname, osfee) FROM stdin;
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
\.


--
-- Data for Name: udr; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.udr (udr_id, pid, diala, dialb, sid, duration_msg_volume, start_date, start_time, external_charges, has_freeunits, cost, is_billed) FROM stdin;
\.


--
-- Name: cdr_cdr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cdr_cdr_id_seq', 1, true);


--
-- Name: free_units_fid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.free_units_fid_seq', 4, true);


--
-- Name: invoicesheet_bill_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.invoicesheet_bill_id_seq', 1, false);


--
-- Name: occ_occ_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.occ_occ_id_seq', 9, true);


--
-- Name: one_time_service_one_time_service_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.one_time_service_one_time_service_id_seq', 16, true);


--
-- Name: profile_pid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profile_pid_seq', 4, true);


--
-- Name: profile_services_profile_services_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profile_services_profile_services_id_seq', 6, true);


--
-- Name: services_sid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.services_sid_seq', 11, true);


--
-- Name: udr_udr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.udr_udr_id_seq', 58, true);


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
-- Name: free_units free_units_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.free_units
    ADD CONSTRAINT free_units_pkey PRIMARY KEY (fid);


--
-- Name: invoicesheet invoicesheet_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoicesheet
    ADD CONSTRAINT invoicesheet_pkey PRIMARY KEY (bill_id);


--
-- Name: occ occ_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.occ
    ADD CONSTRAINT occ_pkey PRIMARY KEY (occ_id, msisdn);


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

