--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2025-01-31 18:26:57 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3659 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 24784)
-- Name: annuncio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.annuncio (
    id integer NOT NULL,
    prezzo real NOT NULL,
    descrizione character varying,
    foto bytea,
    titolo character varying NOT NULL,
    prezzo_scontato real,
    venditore character varying NOT NULL,
    marca character varying NOT NULL,
    modello character varying NOT NULL,
    id_categoria integer NOT NULL
);


ALTER TABLE public.annuncio OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24789)
-- Name: annuncio_id_categoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.annuncio_id_categoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.annuncio_id_categoria_seq OWNER TO postgres;

--
-- TOC entry 3660 (class 0 OID 0)
-- Dependencies: 218
-- Name: annuncio_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.annuncio_id_categoria_seq OWNED BY public.annuncio.id_categoria;


--
-- TOC entry 219 (class 1259 OID 24790)
-- Name: annuncio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.annuncio_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.annuncio_id_seq OWNER TO postgres;

--
-- TOC entry 3661 (class 0 OID 0)
-- Dependencies: 219
-- Name: annuncio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.annuncio_id_seq OWNED BY public.annuncio.id;


--
-- TOC entry 220 (class 1259 OID 24791)
-- Name: asta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.asta (
    id integer NOT NULL,
    id_annuncio integer NOT NULL,
    prezzo real NOT NULL,
    acquirente character varying,
    terminata boolean DEFAULT false
);


ALTER TABLE public.asta OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24797)
-- Name: asta_id_annuncio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.asta_id_annuncio_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.asta_id_annuncio_seq OWNER TO postgres;

--
-- TOC entry 3662 (class 0 OID 0)
-- Dependencies: 221
-- Name: asta_id_annuncio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.asta_id_annuncio_seq OWNED BY public.asta.id_annuncio;


--
-- TOC entry 222 (class 1259 OID 24798)
-- Name: asta_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.asta_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.asta_id_seq OWNER TO postgres;

--
-- TOC entry 3663 (class 0 OID 0)
-- Dependencies: 222
-- Name: asta_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.asta_id_seq OWNED BY public.asta.id;


--
-- TOC entry 223 (class 1259 OID 24799)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    id integer NOT NULL,
    nome character varying NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 24804)
-- Name: categoria_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categoria_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categoria_id_seq OWNER TO postgres;

--
-- TOC entry 3664 (class 0 OID 0)
-- Dependencies: 224
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_id_seq OWNED BY public.categoria.id;


--
-- TOC entry 225 (class 1259 OID 24805)
-- Name: recensione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recensione (
    id integer NOT NULL,
    id_annuncio integer NOT NULL,
    testo character varying NOT NULL,
    autore character varying NOT NULL
);


ALTER TABLE public.recensione OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 24810)
-- Name: recensione_id_annuncio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.recensione_id_annuncio_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.recensione_id_annuncio_seq OWNER TO postgres;

--
-- TOC entry 3665 (class 0 OID 0)
-- Dependencies: 226
-- Name: recensione_id_annuncio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recensione_id_annuncio_seq OWNED BY public.recensione.id_annuncio;


--
-- TOC entry 227 (class 1259 OID 24811)
-- Name: recensione_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.recensione_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.recensione_id_seq OWNER TO postgres;

--
-- TOC entry 3666 (class 0 OID 0)
-- Dependencies: 227
-- Name: recensione_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recensione_id_seq OWNED BY public.recensione.id;


--
-- TOC entry 228 (class 1259 OID 24812)
-- Name: utente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utente (
    tipo boolean NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    email character varying NOT NULL,
    amministratore boolean DEFAULT false NOT NULL
);


ALTER TABLE public.utente OWNER TO postgres;

--
-- TOC entry 3667 (class 0 OID 0)
-- Dependencies: 228
-- Name: COLUMN utente.tipo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.utente.tipo IS 'false = acquirente';


--
-- TOC entry 3472 (class 2604 OID 24866)
-- Name: annuncio id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio ALTER COLUMN id SET DEFAULT nextval('public.annuncio_id_seq'::regclass);


--
-- TOC entry 3473 (class 2604 OID 24867)
-- Name: annuncio id_categoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio ALTER COLUMN id_categoria SET DEFAULT nextval('public.annuncio_id_categoria_seq'::regclass);


--
-- TOC entry 3474 (class 2604 OID 24868)
-- Name: asta id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta ALTER COLUMN id SET DEFAULT nextval('public.asta_id_seq'::regclass);


--
-- TOC entry 3475 (class 2604 OID 24869)
-- Name: asta id_annuncio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta ALTER COLUMN id_annuncio SET DEFAULT nextval('public.asta_id_annuncio_seq'::regclass);


--
-- TOC entry 3477 (class 2604 OID 24870)
-- Name: categoria id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN id SET DEFAULT nextval('public.categoria_id_seq'::regclass);


--
-- TOC entry 3478 (class 2604 OID 24871)
-- Name: recensione id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione ALTER COLUMN id SET DEFAULT nextval('public.recensione_id_seq'::regclass);


--
-- TOC entry 3479 (class 2604 OID 24872)
-- Name: recensione id_annuncio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione ALTER COLUMN id_annuncio SET DEFAULT nextval('public.recensione_id_annuncio_seq'::regclass);


--
-- TOC entry 3642 (class 0 OID 24784)
-- Dependencies: 217
-- Data for Name: annuncio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.annuncio (id, prezzo, descrizione, foto, titolo, prezzo_scontato, venditore, marca, modello, id_categoria) FROM stdin;
2	300	Nuovo	\N	Telefono vecchio	\N	Ciccio	Apple	m345	2
3	600	Bel PC	\N	PC nuovo	\N	Ciccio	Huawei	k34	2
\.


--
-- TOC entry 3645 (class 0 OID 24791)
-- Dependencies: 220
-- Data for Name: asta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.asta (id, id_annuncio, prezzo, acquirente, terminata) FROM stdin;
3	2	100	Domenico	t
\.


--
-- TOC entry 3648 (class 0 OID 24799)
-- Dependencies: 223
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria (id, nome) FROM stdin;
1	Smartphone
2	PC
\.


--
-- TOC entry 3650 (class 0 OID 24805)
-- Dependencies: 225
-- Data for Name: recensione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recensione (id, id_annuncio, testo, autore) FROM stdin;
\.


--
-- TOC entry 3653 (class 0 OID 24812)
-- Dependencies: 228
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utente (tipo, username, password, email, amministratore) FROM stdin;
t	admin	admin		t
t	Franco	Franco1234.	franco126@gmail.com	f
f	Donato	Donato1234.	donato@gmail.com	f
f	Ciccio	Ciccio1234.	ciccio@gmail.com	f
t	Domenico	password	domenico@gmail.com	t
f	bruno	Bruno04*	domenico040204@gmail.com	f
f	ciccio	Bruno04*	domenico040204@gmail.com	f
f	maiale	Bruno04*	domenico040204@gmail.com	f
\.


--
-- TOC entry 3668 (class 0 OID 0)
-- Dependencies: 218
-- Name: annuncio_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.annuncio_id_categoria_seq', 1, false);


--
-- TOC entry 3669 (class 0 OID 0)
-- Dependencies: 219
-- Name: annuncio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.annuncio_id_seq', 4, true);


--
-- TOC entry 3670 (class 0 OID 0)
-- Dependencies: 221
-- Name: asta_id_annuncio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.asta_id_annuncio_seq', 1, true);


--
-- TOC entry 3671 (class 0 OID 0)
-- Dependencies: 222
-- Name: asta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.asta_id_seq', 9, true);


--
-- TOC entry 3672 (class 0 OID 0)
-- Dependencies: 224
-- Name: categoria_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_id_seq', 2, true);


--
-- TOC entry 3673 (class 0 OID 0)
-- Dependencies: 226
-- Name: recensione_id_annuncio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recensione_id_annuncio_seq', 1, false);


--
-- TOC entry 3674 (class 0 OID 0)
-- Dependencies: 227
-- Name: recensione_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recensione_id_seq', 6, true);


--
-- TOC entry 3482 (class 2606 OID 24827)
-- Name: annuncio annuncio_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio
    ADD CONSTRAINT annuncio_pk PRIMARY KEY (id);


--
-- TOC entry 3484 (class 2606 OID 24829)
-- Name: asta asta_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT asta_pk PRIMARY KEY (id);


--
-- TOC entry 3486 (class 2606 OID 24831)
-- Name: categoria categoria_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pk PRIMARY KEY (id);


--
-- TOC entry 3488 (class 2606 OID 24833)
-- Name: recensione recensione_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_pk PRIMARY KEY (id);


--
-- TOC entry 3490 (class 2606 OID 24835)
-- Name: utente utente_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pk PRIMARY KEY (username);


--
-- TOC entry 3491 (class 2606 OID 24836)
-- Name: annuncio annuncio_categoria_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio
    ADD CONSTRAINT annuncio_categoria_fk FOREIGN KEY (id_categoria) REFERENCES public.categoria(id);


--
-- TOC entry 3492 (class 2606 OID 24841)
-- Name: annuncio annuncio_utente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio
    ADD CONSTRAINT annuncio_utente_fk FOREIGN KEY (venditore) REFERENCES public.utente(username);


--
-- TOC entry 3493 (class 2606 OID 24846)
-- Name: asta asta_annuncio_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT asta_annuncio_fk FOREIGN KEY (id_annuncio) REFERENCES public.annuncio(id);


--
-- TOC entry 3494 (class 2606 OID 24851)
-- Name: asta asta_utente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT asta_utente_fk FOREIGN KEY (acquirente) REFERENCES public.utente(username);


--
-- TOC entry 3495 (class 2606 OID 24879)
-- Name: recensione recensione_annuncio_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_annuncio_fk FOREIGN KEY (id_annuncio) REFERENCES public.annuncio(id);


--
-- TOC entry 3496 (class 2606 OID 24861)
-- Name: recensione recensione_utente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_utente_fk FOREIGN KEY (autore) REFERENCES public.utente(username);


-- Completed on 2025-01-31 18:26:58 CET

--
-- PostgreSQL database dump complete
--

