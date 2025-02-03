--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2025-02-03 11:56:32

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
-- TOC entry 4905 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16875)
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
-- TOC entry 218 (class 1259 OID 16880)
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
-- TOC entry 4906 (class 0 OID 0)
-- Dependencies: 218
-- Name: annuncio_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.annuncio_id_categoria_seq OWNED BY public.annuncio.id_categoria;


--
-- TOC entry 219 (class 1259 OID 16881)
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
-- TOC entry 4907 (class 0 OID 0)
-- Dependencies: 219
-- Name: annuncio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.annuncio_id_seq OWNED BY public.annuncio.id;


--
-- TOC entry 220 (class 1259 OID 16882)
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
-- TOC entry 221 (class 1259 OID 16888)
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
-- TOC entry 4908 (class 0 OID 0)
-- Dependencies: 221
-- Name: asta_id_annuncio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.asta_id_annuncio_seq OWNED BY public.asta.id_annuncio;


--
-- TOC entry 222 (class 1259 OID 16889)
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
-- TOC entry 4909 (class 0 OID 0)
-- Dependencies: 222
-- Name: asta_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.asta_id_seq OWNED BY public.asta.id;


--
-- TOC entry 223 (class 1259 OID 16890)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    id integer NOT NULL,
    nome character varying NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16895)
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
-- TOC entry 4910 (class 0 OID 0)
-- Dependencies: 224
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_id_seq OWNED BY public.categoria.id;


--
-- TOC entry 225 (class 1259 OID 16896)
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
-- TOC entry 226 (class 1259 OID 16901)
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
-- TOC entry 4911 (class 0 OID 0)
-- Dependencies: 226
-- Name: recensione_id_annuncio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recensione_id_annuncio_seq OWNED BY public.recensione.id_annuncio;


--
-- TOC entry 227 (class 1259 OID 16902)
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
-- TOC entry 4912 (class 0 OID 0)
-- Dependencies: 227
-- Name: recensione_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recensione_id_seq OWNED BY public.recensione.id;


--
-- TOC entry 228 (class 1259 OID 16903)
-- Name: utente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utente (
    venditore boolean NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    email character varying NOT NULL,
    amministratore boolean DEFAULT false NOT NULL,
    bannato boolean DEFAULT false NOT NULL,
    telefono character varying
);


ALTER TABLE public.utente OWNER TO postgres;

--
-- TOC entry 4913 (class 0 OID 0)
-- Dependencies: 228
-- Name: COLUMN utente.venditore; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.utente.venditore IS 'false = acquirente';


--
-- TOC entry 4717 (class 2604 OID 16909)
-- Name: annuncio id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio ALTER COLUMN id SET DEFAULT nextval('public.annuncio_id_seq'::regclass);


--
-- TOC entry 4718 (class 2604 OID 16910)
-- Name: annuncio id_categoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio ALTER COLUMN id_categoria SET DEFAULT nextval('public.annuncio_id_categoria_seq'::regclass);


--
-- TOC entry 4719 (class 2604 OID 16911)
-- Name: asta id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta ALTER COLUMN id SET DEFAULT nextval('public.asta_id_seq'::regclass);


--
-- TOC entry 4720 (class 2604 OID 16912)
-- Name: asta id_annuncio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta ALTER COLUMN id_annuncio SET DEFAULT nextval('public.asta_id_annuncio_seq'::regclass);


--
-- TOC entry 4722 (class 2604 OID 16913)
-- Name: categoria id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN id SET DEFAULT nextval('public.categoria_id_seq'::regclass);


--
-- TOC entry 4723 (class 2604 OID 16914)
-- Name: recensione id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione ALTER COLUMN id SET DEFAULT nextval('public.recensione_id_seq'::regclass);


--
-- TOC entry 4724 (class 2604 OID 16915)
-- Name: recensione id_annuncio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione ALTER COLUMN id_annuncio SET DEFAULT nextval('public.recensione_id_annuncio_seq'::regclass);


--
-- TOC entry 4888 (class 0 OID 16875)
-- Dependencies: 217
-- Data for Name: annuncio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.annuncio (id, prezzo, descrizione, foto, titolo, prezzo_scontato, venditore, marca, modello, id_categoria) FROM stdin;
2	300	Nuovo	\N	Telefono vecchio	\N	Ciccio	Apple	m345	2
3	600	Bel PC	\N	PC nuovo	\N	Ciccio	Huawei	k34	2
\.


--
-- TOC entry 4891 (class 0 OID 16882)
-- Dependencies: 220
-- Data for Name: asta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.asta (id, id_annuncio, prezzo, acquirente, terminata) FROM stdin;
11	3	3500	\N	f
12	2	2900	maiale	f
\.


--
-- TOC entry 4894 (class 0 OID 16890)
-- Dependencies: 223
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria (id, nome) FROM stdin;
1	Smartphone
2	PC
\.


--
-- TOC entry 4896 (class 0 OID 16896)
-- Dependencies: 225
-- Data for Name: recensione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recensione (id, id_annuncio, testo, autore) FROM stdin;
\.


--
-- TOC entry 4899 (class 0 OID 16903)
-- Dependencies: 228
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utente (venditore, username, password, email, amministratore, bannato, telefono) FROM stdin;
t	admin	admin		t	f	\N
t	Domenico	password	domenico@gmail.com	t	f	\N
f	Donato	Donato1234.	donato@gmail.com	f	f	\N
f	Ciccio	Ciccio1234.	ciccio@gmail.com	f	f	\N
f	maiale	Bruno04*	domenico040204@gmail.com	t	f	\N
t	Franco	Franco1234.	franco126@gmail.com	t	f	\N
t	Giuseppe	pastore	cavalli@gmail.com	t	f	\N
f	bruno	Bruno04*	domenico040204@gmail.com	t	f	\N
\.


--
-- TOC entry 4914 (class 0 OID 0)
-- Dependencies: 218
-- Name: annuncio_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.annuncio_id_categoria_seq', 1, false);


--
-- TOC entry 4915 (class 0 OID 0)
-- Dependencies: 219
-- Name: annuncio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.annuncio_id_seq', 4, true);


--
-- TOC entry 4916 (class 0 OID 0)
-- Dependencies: 221
-- Name: asta_id_annuncio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.asta_id_annuncio_seq', 1, true);


--
-- TOC entry 4917 (class 0 OID 0)
-- Dependencies: 222
-- Name: asta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.asta_id_seq', 12, true);


--
-- TOC entry 4918 (class 0 OID 0)
-- Dependencies: 224
-- Name: categoria_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_id_seq', 2, true);


--
-- TOC entry 4919 (class 0 OID 0)
-- Dependencies: 226
-- Name: recensione_id_annuncio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recensione_id_annuncio_seq', 1, false);


--
-- TOC entry 4920 (class 0 OID 0)
-- Dependencies: 227
-- Name: recensione_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recensione_id_seq', 6, true);


--
-- TOC entry 4728 (class 2606 OID 16917)
-- Name: annuncio annuncio_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio
    ADD CONSTRAINT annuncio_pk PRIMARY KEY (id);


--
-- TOC entry 4730 (class 2606 OID 16919)
-- Name: asta asta_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT asta_pk PRIMARY KEY (id);


--
-- TOC entry 4732 (class 2606 OID 16921)
-- Name: categoria categoria_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pk PRIMARY KEY (id);


--
-- TOC entry 4734 (class 2606 OID 16923)
-- Name: recensione recensione_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_pk PRIMARY KEY (id);


--
-- TOC entry 4736 (class 2606 OID 16925)
-- Name: utente utente_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pk PRIMARY KEY (username);


--
-- TOC entry 4737 (class 2606 OID 16926)
-- Name: annuncio annuncio_categoria_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio
    ADD CONSTRAINT annuncio_categoria_fk FOREIGN KEY (id_categoria) REFERENCES public.categoria(id);


--
-- TOC entry 4738 (class 2606 OID 16931)
-- Name: annuncio annuncio_utente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio
    ADD CONSTRAINT annuncio_utente_fk FOREIGN KEY (venditore) REFERENCES public.utente(username);


--
-- TOC entry 4739 (class 2606 OID 16936)
-- Name: asta asta_annuncio_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT asta_annuncio_fk FOREIGN KEY (id_annuncio) REFERENCES public.annuncio(id);


--
-- TOC entry 4740 (class 2606 OID 16941)
-- Name: asta asta_utente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT asta_utente_fk FOREIGN KEY (acquirente) REFERENCES public.utente(username);


--
-- TOC entry 4741 (class 2606 OID 16946)
-- Name: recensione recensione_annuncio_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_annuncio_fk FOREIGN KEY (id_annuncio) REFERENCES public.annuncio(id);


--
-- TOC entry 4742 (class 2606 OID 16951)
-- Name: recensione recensione_utente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_utente_fk FOREIGN KEY (autore) REFERENCES public.utente(username);


-- Completed on 2025-02-03 11:56:32

--
-- PostgreSQL database dump complete
--

