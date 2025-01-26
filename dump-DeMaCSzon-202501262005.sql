--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-01-26 20:05:19

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
-- TOC entry 4904 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16607)
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
-- TOC entry 218 (class 1259 OID 16612)
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
-- TOC entry 4905 (class 0 OID 0)
-- Dependencies: 218
-- Name: annuncio_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.annuncio_id_categoria_seq OWNED BY public.annuncio.id_categoria;


--
-- TOC entry 219 (class 1259 OID 16613)
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
-- TOC entry 4906 (class 0 OID 0)
-- Dependencies: 219
-- Name: annuncio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.annuncio_id_seq OWNED BY public.annuncio.id;


--
-- TOC entry 220 (class 1259 OID 16614)
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
-- TOC entry 221 (class 1259 OID 16620)
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
-- TOC entry 4907 (class 0 OID 0)
-- Dependencies: 221
-- Name: asta_id_annuncio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.asta_id_annuncio_seq OWNED BY public.asta.id_annuncio;


--
-- TOC entry 222 (class 1259 OID 16621)
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
-- TOC entry 4908 (class 0 OID 0)
-- Dependencies: 222
-- Name: asta_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.asta_id_seq OWNED BY public.asta.id;


--
-- TOC entry 223 (class 1259 OID 16622)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    id integer NOT NULL,
    nome character varying NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16627)
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
-- TOC entry 4909 (class 0 OID 0)
-- Dependencies: 224
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_id_seq OWNED BY public.categoria.id;


--
-- TOC entry 225 (class 1259 OID 16628)
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
-- TOC entry 226 (class 1259 OID 16633)
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
-- TOC entry 4910 (class 0 OID 0)
-- Dependencies: 226
-- Name: recensione_id_annuncio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recensione_id_annuncio_seq OWNED BY public.recensione.id_annuncio;


--
-- TOC entry 227 (class 1259 OID 16634)
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
-- TOC entry 4911 (class 0 OID 0)
-- Dependencies: 227
-- Name: recensione_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recensione_id_seq OWNED BY public.recensione.id;


--
-- TOC entry 228 (class 1259 OID 16635)
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
-- TOC entry 4912 (class 0 OID 0)
-- Dependencies: 228
-- Name: COLUMN utente.tipo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.utente.tipo IS 'false = acquirente';


--
-- TOC entry 4717 (class 2604 OID 16641)
-- Name: annuncio id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio ALTER COLUMN id SET DEFAULT nextval('public.annuncio_id_seq'::regclass);


--
-- TOC entry 4718 (class 2604 OID 16642)
-- Name: annuncio id_categoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio ALTER COLUMN id_categoria SET DEFAULT nextval('public.annuncio_id_categoria_seq'::regclass);


--
-- TOC entry 4719 (class 2604 OID 16643)
-- Name: asta id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta ALTER COLUMN id SET DEFAULT nextval('public.asta_id_seq'::regclass);


--
-- TOC entry 4720 (class 2604 OID 16644)
-- Name: asta id_annuncio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta ALTER COLUMN id_annuncio SET DEFAULT nextval('public.asta_id_annuncio_seq'::regclass);


--
-- TOC entry 4722 (class 2604 OID 16645)
-- Name: categoria id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN id SET DEFAULT nextval('public.categoria_id_seq'::regclass);


--
-- TOC entry 4723 (class 2604 OID 16646)
-- Name: recensione id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione ALTER COLUMN id SET DEFAULT nextval('public.recensione_id_seq'::regclass);


--
-- TOC entry 4724 (class 2604 OID 16647)
-- Name: recensione id_annuncio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione ALTER COLUMN id_annuncio SET DEFAULT nextval('public.recensione_id_annuncio_seq'::regclass);


--
-- TOC entry 4887 (class 0 OID 16607)
-- Dependencies: 217
-- Data for Name: annuncio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.annuncio VALUES (1, 1200, 'Come nuovo', '\x89504e470d0a1a0a0000000d494844520000007f000000440806000000a800ea7d000000017352474200aece1ce90000000467414d410000b18f0bfc6105000000097048597300000ec300000ec301c76fa86400002c2b49444154785e8d7d07b89d5599ee775a3ae9a4407a028462101d429148912ac5323ad8c0325e1b82ce5c479df1eae33c772ca0833afae8b50083e38c3a162c544588088223038298504249028154482fe7e4e4beeffb7ddffaff7fef7de2bcfbacbdd6fafafad6faebfef73e5d571dbe681f605d5d5dc6dad185fe3eef77a1ab0a7cbcd8edeaea969441c6f68580fad4735edaa2dd845a7893d98a2cb8ffa05355fdb00b2499427ce94f72d9aee21d1c1c749ec4d9261df6447739b2b39d636771a03de83a1966f2283b183aeaa390135d75d20a948a1ca9cc5ef6849a4e83113644c29bb33cff258e60384fae2ad4e2a67bf114070dd306baddddd6cd815092865d092f69f24d0cd1d8d0405094283244973df51ce020c99257b7e2c817e0742f19e620934dc8362b17a04f1671f1e6524e0bd5807338d962e0cfe3649b139f3113e9b5861227a44a3362f19edb5115fa451040337d90a7a21823f6bacf6c9021833455d94a1bdddc90448e9ca390d68d8913c04bb5f4a59ab27c49c7ed958d00b652a69b4e8a110a87126984048150d3cbffdc00d1052bde2f82de170984948b77f5b8c5021e905a6a73d89470d940eabb49d5a4504e5b28914cbeb14ebba8d9f5614412f92a6a120e9d2cd0f1cca85d50b35544d9671d8c9c0441554d9f0856c65180265db12877c0205e92d2c644b38c1fb41c3325e5cbc74565d92c711429bde4c30942d9edb354f2c115f6d9e0c0801b0f744330777d7cc9a17c7a9babb3aba747bb16f679282816d1705bd0a4c3b0cb76dd260dba944c0ba50d39266470ef5e15d901a8ef5b7eeefa214d1e62d93740598c03bc1c4bb537c0189950d0bb7b7b15b7f4428e60ab1e2f6dd469dd3d18ab4ce8adc4540763ddc782971f1edd967298f6b0e87afa7a3d36caa22eb658d17c698267d813f422bfdd3d94909d94971cda0a31178c88deeefad6c24568e18ff22140050f05401217bef10d36e9b0436def403fc4dcb014a2c9c5d1bf7dbb0df6f7dbae8d9b6ccbaad5f6c2638fd98e0d1bb408ba7b7a195591577034cf7ed4c5a7c4d27f0a247c207be1afbbb7cfa6bce4189bb6f8581b3d6daa44e5225498e82d4f3f6d4fdf7997ad7bf08f76c0c107dbcc9397601c8759f7b06112ca4540dfbb366fb167effd6f5b79c76f6cd7a64dd6c545e0a60ad48fe44a97ced28676c568c73950f25d160b118b6fc4c489361b314c7fe94b6df8d8b1a23b7c5c3b366cb467eef99d3d75db6dd8c2ba6d1ac6377bc912c47e906f48114c78b0adcfacd1f8d6dc7bafc6db8d05102242235ebc7cee28e12d4cfe8b187110720250180dda83fd0376ced5dfb0192f5f82fed0700b9ef481ed3bec85271eb7477f709d3d71e34db677d74e2d020af1789581704be55ec47dca8cfb957f7514788e9a3b414312878d3dc016bdf7dd76c86b5f83241e20de50d8be6e9dadbee34e9b74c4e1366921265e93d419037bf6d8aa5fffc6eefccce5b661d9326c817d252cc6e01389b64242ec71b8d34427824745cab3302753161d65277ee4ef6ce689275a8f165f3bb837bbfd139fb4e53ffc911d7bc9fbeca837bdd1464d9a18dcced8f9fcf3f6e0b7bf63f77dedebb67bdb36cf178b9013ce56c6efb191dc73c1e4299f54abe8d4842885bdc182579d6fe366cf6e0eb20e19741e4df40c1f6e63a64fb7834f3a512b76fd030f220183e2490ab20a12203fe90ad4c9aa3d8ee8a24fff4cdc4b3e78a91df9d68b3c89a095b8c2a694a20c1b3dda261f79848d3ef040c9499605a892e1850b63e282f93661de5c5bf59b3bad1fc904d1f990a575b9405fed089636682bbc0bb28d9a7ba9f173e7da199fff9ccd3ce178d1a99f314b8ec774d48ffcf467f6bb2f7cc98ebbecfd76ecfbdea3d8c9abc74cd463ee1b35ca0e3af62f6c2f16ee9afffa7d48007053f2196d6d64351e0f6e22137ccf4b35d7e13b9d88e4a805a201b37002631205f0193417c11117bdd966bde2342c224c3e070ef0b8a601c4c0dd1a74f258449b6a867fbea3e23170f6d967da6117bedef55be2900fd0d20f65b877e1f19f855415c64adf35d904e399b5e4243bfc75afb5bdf0570059f706d01fc780a6e208943e1b348bdd7a776f8fbdf43dffcba6bfe4c5b29de042530c8c0bed3d5bb7da9fbefb3d3b78f1627bf1dbdfa63d65ca538e321980f65e113765ba217bf43bde6e535e74940ec18a8f61442c3adec730c171bf7869cb97f1964488462118d7963f67b6e8f20fdae6279fc2717d856d7be6191506df3b7294f562c299280135fb7da3c7d8d338960eecdae5bb79f2e14af9a15fd40c8abc1201e5bc51e218063bc7bcffbd367ede3cd9962e78fd3b77da8a9f5d6fcbbff77d7bf6f7f7ea7c600c8f93e0518eb66443d6cc9efbeffbecc17ffdb63d76c34d382cacb5b1b3665adfc891854f591e931fbff9161bd8b1a3249b3cc54ec82c2859c28fc0c301ba9c88038f3ad25ef6d18f680b950cc0897ce1a995f6876baeb565d8c5bff0c493b613c7fb47afbfc1165f76a91d8843140f1529bb07e7538ffeece7f6e0b5ff66abefba4b27a563710e9363f2dc8cd21c3cb57429ce6f718e059eae5822248223e0a4b3e62b76fbcee288d2a046c78280175c504dbef818d47d5ffe8afdfe7357e2987eb33d89243ef58b5fda3377fdd6fac68cb171d86d56c9d887139d09f6ecef7e6f5b57aff2633fe8ba22902ffa00898112748b2693acc5201be82319e3e6cfb3c3711c1c76c0012e16bc87aebac6feebb357d8ba3f3c60cfe1a46d0d4e9a261d7eb88dc3a412f2437b48244ffe6eb9f403f6f82dbfb0f568afbc7d2925ece0e3176b0b7205d32e77e5d25fdbe6d5aba1c79841870d79a4dff04db0451f49a9c73cefac336de1ab2fd004119ccc5d2fbc60bffae8c7ec014c3ee359fddbbb6d354edc862177c75e7a890d8ff1c9077c3e70f5bfdad24ffca34e4859283bf9f0853a3c2926fa4619d8bddb56dc70a32f9c884793ad76e49a66c9c11ff2eb8a14649d973cec4ba203c0c63166002772bbb135ef54d9b97ebdadb9eb6efbfde7afb44d0f3fe2977a1236acfad1b120a42e039c0cda910ffc692be60b31508e3583253d2fdb464d39d0868ff3133cd946227986fcc44d376b2be33900cb56ec8956de7aab929f903cf0e4adbfb2cdd8ea7848a22c1345dad6679f0b9f0072c03d16cf5ba48737d6e4b1a42dc624a41e638ff8351ee460c2bc39122932c0ba3f2dc304fe5697748c837e776edc68a3a74cc15e12c779e5822aa46fb2c76ec4846262fb460c475c18df9a35f63836b6dc3bc827c045237d2d349feadcfd67517e1922c6d88dbe332425badeb806c0d031b31d2108437e8e4083b8de4432b7af79ced6630b949d0057fbb83973fc389601000ccf5b39e90c5004404b41fcb4d587c34a5ecfca372a2eba5d1b9f179db6c542d9be761db6843df2ed442c6c2c101eaed445a177eaedd8b80149dee0f4888df461078cd1027330ee6cc99cf64c1e33282446ecea83d5d5d3652371791744af816db844e32e9a3e64123cc63962c2045d6108b0cd58766fd96cdb9f5bab38943b1e5240e7652c4ff21448d8eec662e2decb7bb4cc79255f04edf9d89638c831329eac79d307efc75fd69ee2567800215264b4ebc66adcb6e6599de5d6317cfc38b7cd6b5b1777c80e09d8325073412438585e363331f4d13766b41f366ae079c45e6c1579874d3a901dd8097a7fbffa09f6776f7ec13b948f5806f7f44bbe81b02731da9419f414afd34461c7c98a1d954309eef6633d11b6085e9279871a10a432d087e3b6c6277f6e9f13cc314a1ec56ffa74e35c64a7eeabb87fd7a78022085d99669d6ca0c4089ee6d83fc4f163929f7167502279bb01f4f9878c4b9c7234a4a00735196abba4d0835d96b622c8656c052404d15d8663dac460957c3479e3a5f012c9439345ab1b5e7d3c2d8e20bb778077047d31258daf6a6a6a505878f324b94484caa4d33a0bdffceea2ef29cb82e8643390932bc1a828dfaaa13375fc353848ba7a6922211bc10b48846f206ae148c215b1dbf7a039b84c826f893e20a23da4ec5323f451273d1712eda42d251c4dd917c1f9d263956d8283235f6dbe814e1f28eab620d62bfe3c16cac8b7b84d90e651b3e31292ec24dc02e9f01d3e346475bd4fd0443193fcfd2063f5b6abb4aa891642458635df3ac44cbe907cd6403d1b395eedeb95a8d06215f260faddb88e80b207df342a9f91d42662629257ccba8e0613bab2c3152f51e7fb447181b64331928d2cd10e65c25c6784b80aecca7707b85d48253feafaf87c6210b11bf3439fe0e3ed04170db91461dd415eb2deac50d769817247db54223fda1c4b5efa695cf82b277cda6d519b8741c9f0ad36590df8c0c8a2965e34145b6c27d0362794275d3c4bd587162c3833a58a9f63a4bd40347c41fc19d03d847c97be7f78dc0ec65dfc3580b1f09d571a2c8895712b76eac419b56f3d1e23fbbc93a95c80d8397794e29b7bcdad5aa29dc5816040c78fd3ec0c258cb8c3b664d066f694419251fcbc01e7753a44921536913b1790e4502ed25cf325db31704249ca4050f3b266d4d42976c0cc836dcccc1936129736ddc3fbfc0e1c92ea722e4b133a7e461c44d56a87fcc8974b0d95f80622e392ed609c24de50e1e5136f1af18a652ce2665f67d59ae890e59e2a6dfd19d7eed693df8cb3b362e6d0f72a915334bb7a3479b2e515f7d4f5a1f824279247bfb4a12d3fc529e80295520eae0910e338519d17809a0910a5021df14395975ffe297bc5d7be8cf21595d3bffa2ff6f2cf7e5ab780c7ce9de3fab407dfb4219b618d366ae36840d2197be8579f9835e132fe56d9e48849ac816650e69f75a69d7fd537ecd5d75e63afbaf66a7bf5b7afb1f3bef9755bf2f17fb0192f3b41f70ab4f7a221d98b785bedd591febd27b01daa0de85e000ac1789922b91adc6b7bb66dd7dd3f5e36f22ee7004a312ab98805b9505eb21060c5118aef4c187831816a8bde0e255b7cdf0d51c3b55c9eed0660ec8059b36cce9967d8e4238eb009f3e7a3ccd3276da41dfba1bfb193fff90a9bffaa73cba58e4658cb460ea4133c8d9e78168e61a8e42b0935ae5af8cbd89bd867e3e7ccb6d92f5fa25bae930f3dc4262f5c88fe4976cc3bffdacef9ca976df165efd79d391d061433f5686d7ff10294552c006a760770c9c95be7ece4787b468c8c1b3f5ccc4ee347e4cf3ffe84fdf2431fb69bde77a9dd7cc96576d37b2fb13bffe9535a087cb680c8b1728f90e1943c82d52df7212490c7258f9ad3da29e9453a54d85012632fd49a78c9c3471e2f1349e3c316dc332cfec8876dee2bcf8e738190abc7d6625768098fe20c79a8e4eb38c7e4a25d241a9d0ab4c573143fbe3b18b3faa8c7e010c6dbb1475f7c917f0844850e21b6a1c59f6e44416fcf962dd5e10fa0afbe51236de48409ca878fcbe5776d7ade9ebcf556dda6e6ddbe1537dd62abefbadb2fb31bc6a3adbcf8464db0ade9f263891f07147c9c3513e544b081b4c0566c717a39ad2360875bf5de5dbb6ccbaa55e50e55de81e3719f378216bdfb9d3827985112eeb1f9dea543208190215f4dc492836e418eabc489ae9212dd3a68823173f7fafc934fdaf34fadd4fd73c69c8b80c7fea32e7a93f6645ab47815cf9d43f071b4e495ededebd7eb868e62679c28fc8069e26187ba0c496c908dbc74f7f5e98e60cfb03eb5f909a207ed3202950899649b7ef1423bb655021ddf28849cceba9d0a159512c56844e75a15d86392b6ae5c65f77ceab376eb7bde6fbf42e187315bb108fca108063768e3e6ceb58396bc4c5b9c8c254ab34623e4327cc388c7e3b4a1016eee59a09371b782bbcb8d8f3c66bffae83fd84fdef256fbe9456fb53b3ff519dbb676adc643704b1d8f43dacc134f505bfec5f1583a4139c606a68a05feb9a0b6ad5963db9e5d4b118f09e0670c734e3dc56f35435ab9828138e5e248bc01f8bd94f09a7cd4ade3cba838dd2e8057eccdd426bcced2040de4d9a546a3e34af3ec55e468efed1fb0fbbef2357be47bff699bb9153db6c21efe8fefd943d75cab7bf0120e37d38e5b6cbdd8ddf1ec8673a4e4380ba85a09527c6be11e8446a824563b2211949748c8d5c276047fe5d2a5f6c8753fd1c7aecfaf78dceebfea6a5bfee39fb80c90e39db86081f612396904f76643026aba9a89b171f2f92115fd895d0b880f6b4c3dfa68db37c0cbce58b860e727a3d558c1abf927ea7dcaba59bca1a19daa7763459140db2114a1b1d304969e1bae4f4c5ee326c0a1210c6cfbb36b6cddfdf7eb6485974fdc4d71f1acbdf73edbfdc266970bf0924a9faf6762c82bece6e01cc9877c6e12357b0d802e09b2613fad35c21648d8e7c7724c2a6f2d33eec181bdfa58958f7cd5c18fadf5a14cc4bc3fa42f1f574d1a8c477f7ebdedd8b8d10f2d2461018d993ad5165df416eb1b334afd7aa8cab7ccc4b8448b06504d7812c31f8474c2c76e16be53815b101d29c00e70073ed05c04e58e450b48e509ca008e9d5a6e10e555026d0f6cdd86139dcd6e278aaea3fb9acfb935175507d07d343d8acef2e4f990f0a61892da0af2b96e2b1e2d32e65d9b5fd0872aa12ce813471e0a40d3a2a56d32860c9bb96b6ea95c5ceb972db71537deec04f0923bffac336ce64927e970a81b3dc128f3532a34d88e3ee10b047b66f9aa36dada315f64bd7b016420da0de4ea762f7cd1600ea4e6b760cfb66d3813f59399ba176e41fd5c1440eaf1c4a51b27311eaca30cb203e49b315126ecef17721e1164bb837992c5a25dc97b9bb7bd5b91b775198bc618f21d93219ac749d305d4c3e42efbc10ff5997dfdbca277c4083dd73766da54ffc494b2b200a0d2e1003ef9c15c57c78dd0a57d3c2c71b6cf608a2174f4025325a84d707034943aae95e8e45bbb2bc9a7644c965aed48d32122b87cbb71a7ba5d71151c1bed5082a22d4041940ef2693383619b496eee781db4cbf01863e1d683af2133c096646b72dc7bacfdc303b6ec873f2e7b5e169e3ff0d87ff88517aa5f1214c83d948fafe23572265ba9da72cc27a4cc81a887be4ad391a81090dde4a15d6e57b68a0768377585ec77804460cb453c02bf9bd8941705c2d5a0d8f6847502e9da6d7a4fef8a5bad1ac257f1061db6a95a4d5e3bea6edbf3e6f00972d056e63be3e62165d977bf6f1b1e7e587d210677d41b2fb4c987f3193f5e0d394f3a60eb04306889bc03eb9ebc6690f459f65f0aa792abb65ed0da07eb41b2a6d3a048bdbe705a510dc4e518c49000cf3d048610970ced7937843c964e905f827268b3ebb138b9806c94f45962ff1fa0480e11044da58c7c674c51f3d8cffb0a7ffcf7ff28377d34c1d8fa79c7f1880b5f8f6bfbe1d2f5bc7bce59cb422dd6d2842ce5dd9fdbd3e4bb4f46945b054c3a31506f1334e034ca57469dd62a2d80e8d349310f7affe0651eed4697e26a0fa1275e0c509d546c478937c78b662769790a86c79cdd0ed291379a956d962190326524a5e196193f77ff8ffcf8a7f6f46fefd6993f210ff073d805e7e31070acce0f7cacc15433ecb28d462e0e8173e5956a599530ba5e3b423c98758e236c07d86a9769006cea68600c42c9092bc5590559845c06ea18c2870e5e6e9799f5e40f214bdfd9cc9a7ea25d07e5eaa1e5a4d66905c1530186b299c8582923c9882bdf75ddbf6993ddcb6fe26cd9521600b7fed153a7d88bdf7eb11e6bd3353fc7ca0a2fd90514a5ffb97dd5ee2f175e2c29b2c9aa06166370ed42ad20237cd1906ce740bcb401267cc2bdce20850e0a944caf62b3a36bf8a4d640bbfc432d318ea763108e3c37a16cb97dadb7263c2b4d16f53a9c2138e83459b43f4410f2cd98c5f738d49723f749f460f7fff4ddf7d88a9bfcd28f6cf1203befcc336cc13967ebf94311c5a0dd9403210d11d1f6bc7b478f714943f0a4a98014f28dc13b42286a3758938b7e431324ee62f56a914fe906e43f24284f216dcdedd29e48afc5452d5a27d47935f90e668190e5950a8ebd3cc9e243a0fcc26a3b14806a4d2e5be9a705254e020db65592180d6eedfc2c84c7fe2dbcf4439f16b960795ef0d2f7bcdb0e987150dc0a4fe540bdcb368781427dfa67edc77cbe09ded280b12b75e15aa02d103d07180eda82a82145a3c2187dc793fd066086f21e26c1ba5d5214ba44c2f4899d9a43c7a0c472f0289263d74915a80e22bfa4cacb2d3e7c3a6aca14ddc29db2e845367ededc361fea068d3c4d7c8b4c03e241a6e1d8a1fc07784774dd037fc4f1ff3ad0fda92769222e7ecc7cd49bde54f9cd82376e649e32fa709b198ebbc602d220d2192abf37ced51b4624c95287274f92e04b1b6f32e5bd3670cb19d8bd4b37750659faa346e113316d087bf45df6007f06993455ad2127c8946146eae3d05eae2e8f3eafe6f9ad98e3fff68376f697be68e77efdab761e1feaf8ceb5b6e4637faf873868cb3d026ccbb15b96ffe8b7c259e471375de911d4552841da87f5bc17b9fae377be6b1b1f7dac1cfb53e3880b5f6753172dd2c91f15494f7b9a3b1a8bc2f303361dd85828e613881665c5451b2bbfac9e36040f905e38cb639c682d183979b2cd3efd349b7bd61936fb8cd36dd6e9af4039cd0e3e79898d183fae5d83a644e42d15c6e1b6835840aac7e97449a9db1e83005ed149d49a0435b9575a74f1c576e287ffce0e3dff5c9b71dc629b7ce8a1366ec60c1b35691236c9d8cbe83d74d4c0e89164ba1f2282b00fe95a8cb9081817f317a90440c1d6ff022efdf8f52e8d0b4cb2b9f58f9b39d38e7acb9bac879f85d42657f66827285957d7fdbab7cf17c155a886432757544873ad80618ad47562b754a72908043919bba853fef90a3be5cacfdb295ff81ccae7edd42f5d694b3efd7ff508978eaba153a00ca028b036aea335bc92b5568623d2a657d56f4214d8e157a378db96f12be9fb816f655eb875d206695dfc8cbd05da6b1092a54eb451b91fb662c4e8d30e3f087b18bbfe95bffe8dfac9a7fc82b3cfb28316ff45799e20d15800b48105ed97f28eb8c3e72bc63f57e7602300122bd91adc811c815f12c963780c42201b7d72b957e0d7b978cf5e0f1ed40a75a4c73a6c65c5bad6049a013157c927d8a6844c0d01979114da6ea031b9a1cc4957f2632bdfbe61a376bd2c4c3451d37230e1dcf2d9463e7b870d0f3268e1835f0225d4d35bf82b3970b247c88e2f80dd9bb7d8fddfbada76c5a7a0c9e30f38f053bfded1fea99fc87851822ff70b0ab6d6884cf674fa2801b2f1c65aab9dcf8ce5963c141480bf8832c05625d0f66ba740de65b7aec0aef715251b059577c2fd34259a705e6828ae4abb0d8aa34b9fb4ddfdf92bed8677bfc7ae7bcbc576d7e55794efc9a5b622e3d801c54ba89b34c8068372d27456914f7d5d1589ed7dbd83c00de5e9bbefd657c7692be559cf39e5649b76f4d1fac899903fbc2a19514b6157fb2739111741859248a00c058ad30cdf7260ae1bc4a8e41c7481ed0e455cb55d535b1a74a80b1229513a21161c40fff5feff04f450c61ca0be7aa897ffe83afbd95bdf61f75cf9053d23c7873af8f1741bd227e3669785b4a142810386eb72410add416c7465ef5b03af66f87b01cb7ff463dbb66e5dd923d10fbf13381fd7fe3216f6a85e9a72c69e175a0e6dbd032140c18cac237ca2654627379970a6922652d1e518f4fa871eb2db3ef8bfedb6cbfec66efb00ea0ffcaddd8e7ea304fdaefff3492598cfa5fbbd81624def43816e3dfca165848c8fb671a8d249508b0a13bbf6a13fd96f2fff9c6d5eb54a975cbce9c28f59cbf3f20df8581d9e5cf53b85029ae7ac622b67287e6fc0a9c953ae41679c8cebb9fbeed70f37a5bf949b7cd491368c77fdb8d706a8439e4c16c4bc81e8cfed5342167cf25c1a05b4a6628272608059d80824cf244b0e6ae0d7a857fef257f6d42dbfd40f39acfcc5ad5ed84761bd12f4a76efe853dbdf4d7bab9c189519268a0382a0da1d9737470df8e0c12f6955cef09a9bfee8107f48b57fa8268c86b923aa098c3ab3e2943887be01088aae8d050a31f701a0a269fbf7cb6ea8e3be3e64e85911327d8f071e3b8eb08c3b120d3169ba093c643bb4efee48ec2ce6f41305ae0726120f43d6c22eb4a975b0b1f46e4992ebf84503fe1e3e34f5d517ab3cf00a548dbb433741c9ea86a023b4b3a5c3e6ed0726171f29150de314ba4fe4eedde7d32254f94463ba84749df65b35f2d8456909c1cd723cdb7f052c8a42063941d57229d4f3ff349625e72ba37ff118cbe1123d0775db7415d14f419db205eb4873feef6ebabbed9ae4a2b40ab47df2a46cb429d08908ec2274dc8f14386b34a82f1c6a009be7317e6430194800e087a9d5b9bae0664932f36520f09ac8bb349f6206fe36a9c9df2d284ccb1ae04c3599d50412cbc64de49faf8969fe5738b5689b6cb56a0ceeecd9bad7fc7ce46241c87e4e24dc30bc55c50ca33c783bcc6a51e40226b062bad6837dc3aea0e355143c875022f6724cd40d882b1b4e141518ab1783c552b75eade03a0f395a05cd51b0aeeb31348752ff0864ef11879f1adb00534878aac26bf832ce0e3c078b58ba6932e7d71858f69cf39f5642fa79d6a130f59e07c146a2857d873f1cb19bc3b2a5b2d71791ccc256593400e2bcfbfaef9953608690ba3031a084715285e813d1906242f1df69c566ec6d5f5d8849c9fd0049d7d54bc3cd107265ce9fdbee2abb85c967e284b7a1db28637dd70a204632142af236a76ddde7e6429415914f781665c4bd741b7f42cef1983aa76db2ea3a6c6c50d8287ba97beeb9d76deb7be6ee7febfafda2b512eb8ea1b76f4db2e96bf323f54ccf8c34641da44912ce3a8bb97aa8f85f0fd1dff18444ba034e2a85a82ab04953a28d4973ade8a999a5e69521943506038e3ec1b6673ce3ac30e7ff31b6de11b2eb4856fbed016fce5abac77e44897a61ca0a0859a4d806c95dc8741cec7d21994e2ee31ed7a32d1e8a4005b55b23849aeef6f2d904c3e80827608e62def3a2429796f6376d5ee193e42dffb1b8693367e538739d00f3631de9ad3324f51d5419e937d3e344eaab6c852cacfd782a1a43178be540f81601419bc29dd8aaf0ab223148bcbf043247e39e3c8b75f6c277ce26376dcc7ffde8e47fd920f5ea604f0962f9189ea04b2c87589badc7e74609731e40250389dc2663ea2297b72c612a41a44c25be63a653a9925c87659b4b0aa5c9f79cf3a0cc4c9631a22bdc4dd0a88666e330ecd116da91363967edee163bb150d620450e04af52014146a95426fd16310fcd3499c086a275c0f85cc0c9a149d90815ef39720a57e2f408dd0eb84e4f833b835b90e2ac9cfad97bd41b43b883a22c68a3fa4642d4cea7042d96e477508f58672243dbcb5eab00fbe6cd55db3cd4255384d357daa97b9520a43d17709897adb919733942d9324fd76598172acc10e4dc9eaf38036b84491a37dfe65a02de0965c3ead8aaabe30dbd18c53763b88733cb4932cd6593a22e22c7cf98880da40db4dae2fb64ef24ecb31a9e63d10f55a00628481121290574be3443b72a5cc8749bd1334ce5b8ce1ab1d724e5bbe0bf24fa6e8209c75828b0874ca13bff2a4cb7ed41c54dc9f6dc450d89445194a1c3e1527fe144ef5d6116986b53e78a16c0ea40e9028e3e682aff1b5cb92221119c51bfe448bf74e60ae23721741bf9324f7a46945f73318330a3766e9a60da05ceaa98ea0333955ec291e10c3e1b22cecf9aaca9515ca35c4aad3aa6530e0ab1fec16f80a0f1feceb3d6db7206c4896ede877025994cbf4f9b03b2b90971e53af333cc90d2e3b25174df864021a638b5e1d2ee4d1a1ad78b0d755bf033c67518a55afb537a70d9170c2c75a0aeceb048b8ebc4f944ba88e8064da0f11dad26feec95132d98ddd3b07ddd8abb0d1621f6a5a24d2f778f8aaac55202d171beb32964ec244b8728b5ef3e6ca3e7d09a209fe807326ab116107dbdc8b5531330ed0f4e6fc3a2a756f692148b5e224785754cfee65c228e2c6bdd491fa6e4cb24ef18d2eb80ee87673ba9d01168d49012f36d3d890a08ede211bd7a1bdbd3666fa41fa00a4aecdef977320f4c1a44816123dc3fbf4610491f2baab856b7d8f47d6f5723407cc9ea6116c259b76657b68f830795916279ea8f92b5a6aca9f83b14938902d3e325dbf1d4c94e838366fc9d65e3e5d0bb09d32d2add9a56176bbf8ef526a6442bf5c4af9888bef7cec8d3f3ecd6f328b963cdd30f2be283226027b0e3942010d4b2a04931f9b32d5f870474dad424428277288829a779c464f9f6e538e395a32920afb7c8c6b18ae5df3f28d7b02fd1a072ee948a70ce559f8b165fd430b9a27272cb6a385acad6408518221cb5ac8f12653facb05448c18cb9f8c65cbfb7a076fdcec595ac899f43a48610a3d3783d6bf7d87e8b49b21f1e7d2474e9ae8cf2e62e1196a4ee4e829534322e200c6cd9ca11c69ef44827ceeb389871c12bfd5c3bd8ccbf6236f037bfc97439c16f1b1997dea47dcddf90302de45c06a44a2cb5d8d263838fe1488561fca702469f4d4a936fd84e36cf1473e6413171ea649a6294d048cf2a7cca69f787c75274fffaf679f4d3ffe384f440444f03373feb2541e2a7c6cb216a503324eb0656b0831d229aa25196dde55e4ffd5119bba91cc89871e6223274e8a78f7dadedd7b10eb249b7bea29e213e946931b7a69830b6addf2e5a209a09177e01147d821e79c83b6fbe6875eb34f3d554fe3925f0a72c8ff2dc07ffcd0cb5ffd62ee6093fff564e16b5fadbb823a54d310b073d346fdd64163e2093629c33a6344e939fb80719fec85f3bc43163c248553c300f636fed9828c40883f9fc20732e79f7fae1dfa9a57d9617ff53a94bfb4490b177a40900953aab91ac7cf9fa704f211a8919326dbec334fb723df76b18d183fbed865e0cbfeeddf6dddfd0f60f7c8e7dfdc0a77cb935f74a41d7cd24941a778976d5df3ac3d7efd0dfe14b0883005d9b18877ded967f9a126c0c978f467d7db9695ab741c1520cfa763474e986873311ec641bb4cfee803b1b7c2e2e6cfa1f38791f8c836bf26cd2f4b5046e362419b8f76adb8f1266da1ec13cc03fd1c72ee39fa8a356dd32e379c692f7931723a4bff1d63e16b5e6dc7bcf3edfad56de521402b3c7c4e3be618fd83abf173e6e8bf801c7bc97bede0e38e6dc4cafc3efca39fd853b7df5e1d926080512ad628927596755d79d0ec7dc3b0aa866115654224e4a9446206ec9cabbea1ff4c45c50c4e375e3a40131fa0a4a7c1411dfee0107f431e2ef4dfa69894d4217fc39f96d96d975c663bd6adf7f306b9c321051377e81b5e6fc77df4c3bae5297918e13f57f8c5bbde677bb66f933e63ecdfbdcb669d7cb2bde24b57ea3669827b931bfefa5db6f28e3bb0d5f8439494e79730c6ce9861e75f7bb51d988b973cbea1bd63fd06dbb5658b16291704fde61e534985df4731f1b7bcff03fe834d1c1c407d3e0072d6bf7cd10ebbe0bc466e24137202e220bf9cd801356e199bda94a17c14e669d3e38fdb4f2f7e877ef28693aff9c31f65538ffed8ca4becee016c25bbb035eee1ae6d9f1f5778f78bf63530fa9168c019c57129085c09918e2784cf9273d2d82628c389e3bf0a1b73d0416a674228b373c3067bf0ebdfd46fe5ebf7f3a9c3403300d5f594384903651db1c03b46e6724a540d945442f0d2091fe4f994cee655abed0fdfbcaafcda16414db6c74c9f6693b1e5f1a7d7d87fe2b6a5b6f1914795f4828851fe2207dc9868ef9e2f7cd1d63df427e986187c7bce4aee58530f3cea6943a42d803aca2d6458528f600cdc33f189233edecd2f78961b5e80ec479bba7a7a2762d4d7b5687837ce4afb79e223c35af070e26a114351ea5810ac928ac9e6170bd7de77bfddf5f14fdaddfff469fd670ba9874c826d251a7e9e5fb1c2eef9f4e5b6fa36ecb64843c89e48b45818331febe2af518aee7634016cc6082947f05fa354bb3f9f0c264636694b2ff22421dec3d7fdd4eeffd655d6bf7397c71a25c1633fff0fce5d9ffe0c4eaee2d74482afbd141b0c20830048dfb8fc61fd5b9567f8dfaf9058ea708cb2cfb8a2cfc28de549e4e0b1eb6f8c7fc850f1245f2bf4b21ee714b4fdf84db74886503aaa1072889217194cd2ba2e9f3a13b90509bd3e6c01c3b135f6f62171bc1123e9413bf4f5afd3c90f03a35ca48dcca8bdcf84ec5cb741bfb4b5f989276c379f8441401316ccb319a79c6c072e7a917e73d77f6ca94b67f5db9e7edad6f2ffc62cbdc3b6ad5e8dfda44f10c3e3c69b2b9cab7d0a8e93b34ec371995721888b7b07fed78a87bff77d6c617c9ad6b7109e534c386401ce552ed093421a086cf26469f9f77fa05fd5e27983163e8adf15f3eb7d1e86169cf74a1da7c7cf9ea305d4cfdf0e446c4fddbe149372831ea238faad17e950411dcaac5fb6cc96fff0c758217be58b517b567ccbe309e39869d36cfe2bcfb659279ea8f387f2fc3ec0a781373dfaa81e127dece7d7a3bfdb669c70024e044fb609f3e6dba82993ad6f84ff48d59e2d5bf5bf7f9ebbff0fb6e2e69b710eb35ae3915ff0513914043b1c2346a826734126aacba7ccf013727438e1bd30321c41f571abc1426252723753261d556e416ae3e57cda2611b4589d849e71873c2f4d46e0b285273c14e389563f06c2dfeba96f996e8283a8da7a4787b1783b769968ebaa00ceb56b242f6a4e8c4fae27445f45a31fd9e5ae56d21137f8691f85ffb891bf7cc989e58313fc8f9bdca331c9ba4ce598120a027fb9e5c52187360b131dea3016fe2f1c3e6fd7c39b480c0c342e4cfe07523e9f978713c6c243d2f0716395bbfc8a18ffa7119fdde7a51da13d9cc6146311555ef596f3238808a0db75c5d419a0b3c53f4c188cf471010c1f66bd9c0c4ea2cb16504675bc6b0a684284a04630d966d0ea670d1ae9b2ef8a64459b7aa875c12c560175d32e99797c4b5ac5e7e1acc5a762f5c35ad1551b80bbbcd14368c1a3d69d3bd6883377ab4e51a302c7822ae3f034708ca864000dda924fd469dfb550d0662ec207216acaa314fbb4853fffaf5f69d79b8992ffe007d16b58a65f3ffe7313d7cedf5f0308ac9f4fd4a0a6205f899ca8ca4c40749464009adc9467f2b8a251787dea8f41f78aefb145a28a3e88955bc1bb6e33f5388836843d81464b114164d68a0d7f1a612628c05859f2f7f734f1f44966faa57e0b44019db5da90a54edaf7bd14320e9bca010eb1dae3b1c0475dd6015b913bca2816d69c7822ed4233c753100154f3873ae449d366cd46ead031b7009efdefc17188ff9346c1e0cf6552d90b691caaa705057f0a42c81a64d8a0447e54ec32a4546df652a3990087f39cde6940046d71b7eb2468145e68b31f4ed84e7f3443bdea13ca263c0799e062c06bfa0c8ddc77b057620c5de580e7170dfb5808dada9da6f8e5437f15c297781dc06377eec9fc04d9b76eff102874dc81f7d1e670e5850a82f4b9cbc4d68f89d72520160369d48d183c48bc941427f0cddb80cb23151549cd0c5ec1b11d7d81be41a7496e2165a00aae924b9fc9d6af50b153f4699b9c880d105b74359c86975a20919c490992236409b56843157aee2464e3bd66bf0de0f998d54445593f1709720599e7c2201592aa29eb8b2791ed4241bf1c2df926c35e9ca677e9755dc9133e18761adea420b6aa1e5c5ef106508f7635a0e459a3dedc88ab3129a47982355fb485e2f623f1019755c3134939f2b39fb624443078d21d3a31a31fe906076ffe610d691273c0c8e05e2e0a5f8cd4711fde662cfa12636c25c9a37da1f4fd4d3ae1bb3e2641b4709f3180201afb7c539f7a68a34faff15793410d34ec93474830bbde68e45824e752b8b87282fafbf6edb3ff0fb851a74f36455ff80000000049454e44ae426082', 'Computer bello', NULL, 'Franco', 'Linux', 'Arch', 2);


--
-- TOC entry 4890 (class 0 OID 16614)
-- Dependencies: 220
-- Data for Name: asta; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4893 (class 0 OID 16622)
-- Dependencies: 223
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categoria VALUES (1, 'Smartphone');
INSERT INTO public.categoria VALUES (2, 'PC');


--
-- TOC entry 4895 (class 0 OID 16628)
-- Dependencies: 225
-- Data for Name: recensione; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.recensione VALUES (1, 1, 'Molto bello', 'Ciccio');
INSERT INTO public.recensione VALUES (3, 1, 'Me Gusta', 'Donato');


--
-- TOC entry 4898 (class 0 OID 16635)
-- Dependencies: 228
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utente VALUES (true, 'admin', 'admin', '', true);
INSERT INTO public.utente VALUES (true, 'Franco', 'Franco1234.', 'franco126@gmail.com', false);
INSERT INTO public.utente VALUES (false, 'Donato', 'Donato1234.', 'donato@gmail.com', false);
INSERT INTO public.utente VALUES (false, 'Ciccio', 'Ciccio1234.', 'ciccio@gmail.com', false);


--
-- TOC entry 4913 (class 0 OID 0)
-- Dependencies: 218
-- Name: annuncio_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.annuncio_id_categoria_seq', 1, false);


--
-- TOC entry 4914 (class 0 OID 0)
-- Dependencies: 219
-- Name: annuncio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.annuncio_id_seq', 1, true);


--
-- TOC entry 4915 (class 0 OID 0)
-- Dependencies: 221
-- Name: asta_id_annuncio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.asta_id_annuncio_seq', 1, false);


--
-- TOC entry 4916 (class 0 OID 0)
-- Dependencies: 222
-- Name: asta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.asta_id_seq', 1, false);


--
-- TOC entry 4917 (class 0 OID 0)
-- Dependencies: 224
-- Name: categoria_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_id_seq', 2, true);


--
-- TOC entry 4918 (class 0 OID 0)
-- Dependencies: 226
-- Name: recensione_id_annuncio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recensione_id_annuncio_seq', 1, false);


--
-- TOC entry 4919 (class 0 OID 0)
-- Dependencies: 227
-- Name: recensione_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recensione_id_seq', 3, true);


--
-- TOC entry 4727 (class 2606 OID 16649)
-- Name: annuncio annuncio_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio
    ADD CONSTRAINT annuncio_pk PRIMARY KEY (id);


--
-- TOC entry 4729 (class 2606 OID 16651)
-- Name: asta asta_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT asta_pk PRIMARY KEY (id);


--
-- TOC entry 4731 (class 2606 OID 16653)
-- Name: categoria categoria_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pk PRIMARY KEY (id);


--
-- TOC entry 4733 (class 2606 OID 16655)
-- Name: recensione recensione_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_pk PRIMARY KEY (id);


--
-- TOC entry 4735 (class 2606 OID 16657)
-- Name: utente utente_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pk PRIMARY KEY (username);


--
-- TOC entry 4736 (class 2606 OID 16658)
-- Name: annuncio annuncio_categoria_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio
    ADD CONSTRAINT annuncio_categoria_fk FOREIGN KEY (id_categoria) REFERENCES public.categoria(id);


--
-- TOC entry 4737 (class 2606 OID 16663)
-- Name: annuncio annuncio_utente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annuncio
    ADD CONSTRAINT annuncio_utente_fk FOREIGN KEY (venditore) REFERENCES public.utente(username);


--
-- TOC entry 4738 (class 2606 OID 16668)
-- Name: asta asta_annuncio_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT asta_annuncio_fk FOREIGN KEY (id_annuncio) REFERENCES public.annuncio(id);


--
-- TOC entry 4739 (class 2606 OID 16673)
-- Name: asta asta_utente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT asta_utente_fk FOREIGN KEY (acquirente) REFERENCES public.utente(username);


--
-- TOC entry 4740 (class 2606 OID 16678)
-- Name: recensione recensione_annuncio_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_annuncio_fk FOREIGN KEY (id_annuncio) REFERENCES public.annuncio(id);


--
-- TOC entry 4741 (class 2606 OID 16683)
-- Name: recensione recensione_utente_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recensione
    ADD CONSTRAINT recensione_utente_fk FOREIGN KEY (autore) REFERENCES public.utente(username);


-- Completed on 2025-01-26 20:05:20

--
-- PostgreSQL database dump complete
--

