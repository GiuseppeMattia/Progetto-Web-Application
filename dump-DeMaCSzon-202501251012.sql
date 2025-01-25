--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2025-01-25 10:12:26

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
-- TOC entry 4917 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 223 (class 1259 OID 16585)
-- Name: annuncio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.annuncio (
    id integer NOT NULL,
    id_articolo integer NOT NULL,
    prezzo real NOT NULL,
    descrizione character varying,
    foto bytea,
    titolo character varying NOT NULL,
    prezzo_scontato real,
    venditore character varying NOT NULL
);


ALTER TABLE public.annuncio OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16584)
-- Name: annuncio_id_articolo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.annuncio_id_articolo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.annuncio_id_articolo_seq OWNER TO postgres;

--
-- TOC entry 4918 (class 0 OID 0)
-- Dependencies: 222
-- Name: annuncio_id_articolo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.annuncio_id_articolo_seq OWNED BY public.annuncio.id_articolo;


--
-- TOC entry 221 (class 1259 OID 16582)
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
-- TOC entry 4919 (class 0 OID 0)
-- Dependencies: 221
-- Name: annuncio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.annuncio_id_seq OWNED BY public.annuncio.id;


--
-- TOC entry 228 (class 1259 OID 16615)
-- Name: articolo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.articolo (
    id integer NOT NULL,
    id_categoria integer NOT NULL,
    colore character varying NOT NULL,
    lunghezza integer NOT NULL,
    larghezza integer NOT NULL,
    "profondità" character varying NOT NULL,
    marca character varying NOT NULL,
    modello character varying NOT NULL
);


ALTER TABLE public.articolo OWNER TO postgres;

--
-- TOC entry 4920 (class 0 OID 0)
-- Dependencies: 228
-- Name: COLUMN articolo.lunghezza; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.articolo.lunghezza IS 'cm';


--
-- TOC entry 4921 (class 0 OID 0)
-- Dependencies: 228
-- Name: COLUMN articolo.larghezza; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.articolo.larghezza IS 'cm';


--
-- TOC entry 4922 (class 0 OID 0)
-- Dependencies: 228
-- Name: COLUMN articolo."profondità"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.articolo."profondità" IS 'cm';


--
-- TOC entry 227 (class 1259 OID 16614)
-- Name: articolo_id_categoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.articolo_id_categoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.articolo_id_categoria_seq OWNER TO postgres;

--
-- TOC entry 4923 (class 0 OID 0)
-- Dependencies: 227
-- Name: articolo_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.articolo_id_categoria_seq OWNED BY public.articolo.id_categoria;


--
-- TOC entry 226 (class 1259 OID 16613)
-- Name: articolo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.articolo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.articolo_id_seq OWNER TO postgres;

--
-- TOC entry 4924 (class 0 OID 0)
-- Dependencies: 226
-- Name: articolo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.articolo_id_seq OWNED BY public.articolo.id;


--
-- TOC entry 231 (class 1259 OID 16651)
-- Name: asta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.asta (
    id integer NOT NULL,
    id_annuncio integer NOT NULL,
    prezzo real NOT NULL,
    acquirente character varying
);


ALTER TABLE public.asta OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 16650)
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
-- TOC entry 4925 (class 0 OID 0)
-- Dependencies: 230
-- Name: asta_id_annuncio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.asta_id_annuncio_seq OWNED BY public.asta.id_annuncio;


--
-- TOC entry 229 (class 1259 OID 16649)
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
-- TOC entry 4926 (class 0 OID 0)
-- Dependencies: 229
-- Name: asta_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.asta_id_seq OWNED BY public.asta.id;


--
-- TOC entry 220 (class 1259 OID 16576)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    id integer NOT NULL,
    nome character varying NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16595)
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
-- TOC entry 4927 (class 0 OID 0)
-- Dependencies: 224
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_id_seq OWNED BY public.categoria.id;


--
-- TOC entry 219 (class 1259 OID 16565)
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
-- TOC entry 218 (class 1259 OID 16563)
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
-- TOC entry 4928 (class 0 OID 0)
-- Dependencies: 218
-- Name: recensione_id_annuncio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recensione_id_annuncio_seq OWNED BY public.recensione.id_annuncio;


--
-- TOC entry 217 (class 1259 OID 16562)
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
-- TOC entry 4929 (class 0 OID 0)
-- Dependencies: 217
-- Name: recensione_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recensione_id_seq OWNED BY public.recensione.id;


--
-- TOC entry 225 (class 1259 OID 16605)
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
-- TOC entry 4726 (class 2604 OID 16588)
-- Name: annuncio id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio ALTER COLUMN id SET DEFAULT nextval('public.annuncio_id_seq'::regclass);


--
-- TOC entry 4727 (class 2604 OID 16590)
-- Name: annuncio id_articolo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio ALTER COLUMN id_articolo SET DEFAULT nextval('public.annuncio_id_articolo_seq'::regclass);


--
-- TOC entry 4729 (class 2604 OID 16618)
-- Name: articolo id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articolo ALTER COLUMN id SET DEFAULT nextval('public.articolo_id_seq'::regclass);


--
-- TOC entry 4730 (class 2604 OID 16619)
-- Name: articolo id_categoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articolo ALTER COLUMN id_categoria SET DEFAULT nextval('public.articolo_id_categoria_seq'::regclass);


--
-- TOC entry 4731 (class 2604 OID 16654)
-- Name: asta id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta ALTER COLUMN id SET DEFAULT nextval('public.asta_id_seq'::regclass);


--
-- TOC entry 4732 (class 2604 OID 16655)
-- Name: asta id_annuncio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta ALTER COLUMN id_annuncio SET DEFAULT nextval('public.asta_id_annuncio_seq'::regclass);


--
-- TOC entry 4725 (class 2604 OID 16596)
-- Name: categoria id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN id SET DEFAULT nextval('public.categoria_id_seq'::regclass);


--
-- TOC entry 4723 (class 2604 OID 16568)
-- Name: recensione id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione ALTER COLUMN id SET DEFAULT nextval('public.recensione_id_seq'::regclass);


--
-- TOC entry 4724 (class 2604 OID 16569)
-- Name: recensione id_annuncio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione ALTER COLUMN id_annuncio SET DEFAULT nextval('public.recensione_id_annuncio_seq'::regclass);


--
-- TOC entry 4903 (class 0 OID 16585)
-- Dependencies: 223
-- Data for Name: annuncio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.annuncio (id, id_articolo, prezzo, descrizione, foto, titolo, prezzo_scontato, venditore) FROM stdin;
\.


--
-- TOC entry 4908 (class 0 OID 16615)
-- Dependencies: 228
-- Data for Name: articolo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.articolo (id, id_categoria, colore, lunghezza, larghezza, "profondità", marca, modello) FROM stdin;
\.


--
-- TOC entry 4911 (class 0 OID 16651)
-- Dependencies: 231
-- Data for Name: asta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.asta (id, id_annuncio, prezzo, acquirente) FROM stdin;
\.


--
-- TOC entry 4900 (class 0 OID 16576)
-- Dependencies: 220
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria (id, nome) FROM stdin;
\.


--
-- TOC entry 4899 (class 0 OID 16565)
-- Dependencies: 219
-- Data for Name: recensione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recensione (id, id_annuncio, testo, autore) FROM stdin;
\.


--
-- TOC entry 4905 (class 0 OID 16605)
-- Dependencies: 225
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utente (tipo, username, password, email, amministratore) FROM stdin;
t	admin	admin		t
\.


--
-- TOC entry 4930 (class 0 OID 0)
-- Dependencies: 222
-- Name: annuncio_id_articolo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.annuncio_id_articolo_seq', 1, false);


--
-- TOC entry 4931 (class 0 OID 0)
-- Dependencies: 221
-- Name: annuncio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.annuncio_id_seq', 1, false);


--
-- TOC entry 4932 (class 0 OID 0)
-- Dependencies: 227
-- Name: articolo_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.articolo_id_categoria_seq', 1, false);


--
-- TOC entry 4933 (class 0 OID 0)
-- Dependencies: 226
-- Name: articolo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.articolo_id_seq', 1, false);


--
-- TOC entry 4934 (class 0 OID 0)
-- Dependencies: 230
-- Name: asta_id_annuncio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.asta_id_annuncio_seq', 1, false);


--
-- TOC entry 4935 (class 0 OID 0)
-- Dependencies: 229
-- Name: asta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.asta_id_seq', 1, false);


--
-- TOC entry 4936 (class 0 OID 0)
-- Dependencies: 224
-- Name: categoria_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_id_seq', 1, false);


--
-- TOC entry 4937 (class 0 OID 0)
-- Dependencies: 218
-- Name: recensione_id_annuncio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recensione_id_annuncio_seq', 1, false);


--
-- TOC entry 4938 (class 0 OID 0)
-- Dependencies: 217
-- Name: recensione_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recensione_id_seq', 1, false);


--
-- TOC entry 4738 (class 2606 OID 16594)
-- Name: annuncio annuncio_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio
    ADD CONSTRAINT annuncio_pk PRIMARY KEY (id);


--
-- TOC entry 4742 (class 2606 OID 16623)
-- Name: articolo articolo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articolo
    ADD CONSTRAINT articolo_pk PRIMARY KEY (id);


--
-- TOC entry 4744 (class 2606 OID 16657)
-- Name: asta asta_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT asta_pk PRIMARY KEY (id);


--
-- TOC entry 4736 (class 2606 OID 16601)
-- Name: categoria categoria_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pk PRIMARY KEY (id);


--
-- TOC entry 4734 (class 2606 OID 16574)
-- Name: recensione recensione_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_pk PRIMARY KEY (id);


--
-- TOC entry 4740 (class 2606 OID 16664)
-- Name: utente utente_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pk PRIMARY KEY (username);


--
-- TOC entry 4747 (class 2606 OID 16639)
-- Name: annuncio annuncio_articolo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio
    ADD CONSTRAINT annuncio_articolo_fk FOREIGN KEY (id_articolo) REFERENCES public.articolo(id);


--
-- TOC entry 4748 (class 2606 OID 16665)
-- Name: annuncio annuncio_utente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio
    ADD CONSTRAINT annuncio_utente_fk FOREIGN KEY (venditore) REFERENCES public.utente(username);


--
-- TOC entry 4749 (class 2606 OID 16644)
-- Name: articolo articolo_categoria_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articolo
    ADD CONSTRAINT articolo_categoria_fk FOREIGN KEY (id_categoria) REFERENCES public.categoria(id);


--
-- TOC entry 4750 (class 2606 OID 16658)
-- Name: asta asta_annuncio_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT asta_annuncio_fk FOREIGN KEY (id_annuncio) REFERENCES public.annuncio(id);


--
-- TOC entry 4751 (class 2606 OID 16677)
-- Name: asta asta_utente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT asta_utente_fk FOREIGN KEY (acquirente) REFERENCES public.utente(username);


--
-- TOC entry 4745 (class 2606 OID 16629)
-- Name: recensione recensione_annuncio_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_annuncio_fk FOREIGN KEY (id_annuncio) REFERENCES public.annuncio(id);


--
-- TOC entry 4746 (class 2606 OID 16670)
-- Name: recensione recensione_utente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_utente_fk FOREIGN KEY (autore) REFERENCES public.utente(username);


-- Completed on 2025-01-25 10:12:26

--
-- PostgreSQL database dump complete
--

