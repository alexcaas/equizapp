--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: bd_ujian; Type: COMMENT; Schema: -; Owner: equiz
--

COMMENT ON DATABASE bd_ujian IS 'Database ujian';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: tanswer; Type: TABLE; Schema: public; Owner: equiz; Tablespace: 
--

CREATE TABLE tanswer (
    answerid bigint NOT NULL,
    answerstring text NOT NULL,
    answercorrect boolean DEFAULT false NOT NULL,
    itemid bigint NOT NULL
);


ALTER TABLE public.tanswer OWNER TO equiz;

--
-- Name: TABLE tanswer; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON TABLE tanswer IS 'answer table';


--
-- Name: COLUMN tanswer.answerid; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tanswer.answerid IS 'answer id (key)';


--
-- Name: COLUMN tanswer.answerstring; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tanswer.answerstring IS 'answer string';


--
-- Name: COLUMN tanswer.answercorrect; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tanswer.answercorrect IS 'this answer is correct?';


--
-- Name: COLUMN tanswer.itemid; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tanswer.itemid IS 'foreign key item id';


--
-- Name: answerid_seq; Type: SEQUENCE; Schema: public; Owner: equiz
--

CREATE SEQUENCE answerid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.answerid_seq OWNER TO equiz;

--
-- Name: answerid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: equiz
--

ALTER SEQUENCE answerid_seq OWNED BY tanswer.answerid;


--
-- Name: SEQUENCE answerid_seq; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON SEQUENCE answerid_seq IS 'answer id sequence';


--
-- Name: tgroup; Type: TABLE; Schema: public; Owner: equiz; Tablespace: 
--

CREATE TABLE tgroup (
    groupcode integer NOT NULL,
    groupname character varying(254) NOT NULL,
    groupitemsnumber smallint DEFAULT 30 NOT NULL,
    groupdatecreation timestamp with time zone NOT NULL,
    groupcodestr character varying(254),
    grouplastmodif timestamp with time zone
);


ALTER TABLE public.tgroup OWNER TO equiz;

--
-- Name: TABLE tgroup; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON TABLE tgroup IS 'group table';


--
-- Name: COLUMN tgroup.groupcode; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tgroup.groupcode IS 'group code (key)';


--
-- Name: COLUMN tgroup.groupname; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tgroup.groupname IS 'group name';


--
-- Name: COLUMN tgroup.groupitemsnumber; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tgroup.groupitemsnumber IS 'test items shown per group';


--
-- Name: COLUMN tgroup.groupdatecreation; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tgroup.groupdatecreation IS 'group date creation';


--
-- Name: COLUMN tgroup.groupcodestr; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tgroup.groupcodestr IS 'group code string';


--
-- Name: COLUMN tgroup.grouplastmodif; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tgroup.grouplastmodif IS 'group last modification';


--
-- Name: groupcode_seq; Type: SEQUENCE; Schema: public; Owner: equiz
--

CREATE SEQUENCE groupcode_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.groupcode_seq OWNER TO equiz;

--
-- Name: groupcode_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: equiz
--

ALTER SEQUENCE groupcode_seq OWNED BY tgroup.groupcode;


--
-- Name: SEQUENCE groupcode_seq; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON SEQUENCE groupcode_seq IS 'groupcode sequence';


--
-- Name: titem; Type: TABLE; Schema: public; Owner: equiz; Tablespace: 
--

CREATE TABLE titem (
    itemid bigint NOT NULL,
    itemstring text NOT NULL,
    itemdifficulty smallint NOT NULL,
    groupcode integer NOT NULL
);


ALTER TABLE public.titem OWNER TO equiz;

--
-- Name: TABLE titem; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON TABLE titem IS 'items table';


--
-- Name: COLUMN titem.itemid; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN titem.itemid IS 'item id';


--
-- Name: COLUMN titem.itemstring; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN titem.itemstring IS 'item string';


--
-- Name: COLUMN titem.itemdifficulty; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN titem.itemdifficulty IS 'item difficulty';


--
-- Name: COLUMN titem.groupcode; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN titem.groupcode IS 'group code';


--
-- Name: itemid_seq; Type: SEQUENCE; Schema: public; Owner: equiz
--

CREATE SEQUENCE itemid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.itemid_seq OWNER TO equiz;

--
-- Name: itemid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: equiz
--

ALTER SEQUENCE itemid_seq OWNED BY titem.itemid;


--
-- Name: SEQUENCE itemid_seq; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON SEQUENCE itemid_seq IS 'item id sequence';


--
-- Name: tuser; Type: TABLE; Schema: public; Owner: equiz; Tablespace: 
--

CREATE TABLE tuser (
    useremail character varying(254) NOT NULL,
    username character varying(254) NOT NULL,
    userlastname character varying(254) NOT NULL,
    userpassword character varying(254) NOT NULL,
    useradmin boolean DEFAULT false NOT NULL,
    userid bigint NOT NULL,
    userlastmodif timestamp with time zone
);


ALTER TABLE public.tuser OWNER TO equiz;

--
-- Name: TABLE tuser; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON TABLE tuser IS 'user table';


--
-- Name: COLUMN tuser.useremail; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tuser.useremail IS 'user email';


--
-- Name: COLUMN tuser.username; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tuser.username IS 'user first name';


--
-- Name: COLUMN tuser.userlastname; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tuser.userlastname IS 'user last name(s)';


--
-- Name: COLUMN tuser.userpassword; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tuser.userpassword IS 'user password';


--
-- Name: COLUMN tuser.useradmin; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tuser.useradmin IS 'user is admin?';


--
-- Name: COLUMN tuser.userid; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tuser.userid IS 'user id';


--
-- Name: COLUMN tuser.userlastmodif; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tuser.userlastmodif IS 'user last modification';


--
-- Name: tusergroup; Type: TABLE; Schema: public; Owner: equiz; Tablespace: 
--

CREATE TABLE tusergroup (
    groupcode integer NOT NULL,
    usertrait smallint,
    datetestdone timestamp with time zone,
    userid bigint NOT NULL,
    usertraitlastmodif timestamp with time zone
);


ALTER TABLE public.tusergroup OWNER TO equiz;

--
-- Name: TABLE tusergroup; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON TABLE tusergroup IS 'user group table';


--
-- Name: COLUMN tusergroup.groupcode; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tusergroup.groupcode IS 'foreign key group code';


--
-- Name: COLUMN tusergroup.usertrait; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tusergroup.usertrait IS 'student trait level';


--
-- Name: COLUMN tusergroup.datetestdone; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tusergroup.datetestdone IS 'test date done';


--
-- Name: COLUMN tusergroup.userid; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tusergroup.userid IS 'user id';


--
-- Name: COLUMN tusergroup.usertraitlastmodif; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON COLUMN tusergroup.usertraitlastmodif IS 'user trait last modification';


--
-- Name: userid_seq; Type: SEQUENCE; Schema: public; Owner: equiz
--

CREATE SEQUENCE userid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.userid_seq OWNER TO equiz;

--
-- Name: userid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: equiz
--

ALTER SEQUENCE userid_seq OWNED BY tuser.userid;


--
-- Name: SEQUENCE userid_seq; Type: COMMENT; Schema: public; Owner: equiz
--

COMMENT ON SEQUENCE userid_seq IS 'userid sequence';


--
-- Name: answerid; Type: DEFAULT; Schema: public; Owner: equiz
--

ALTER TABLE ONLY tanswer ALTER COLUMN answerid SET DEFAULT nextval('answerid_seq'::regclass);


--
-- Name: groupcode; Type: DEFAULT; Schema: public; Owner: equiz
--

ALTER TABLE ONLY tgroup ALTER COLUMN groupcode SET DEFAULT nextval('groupcode_seq'::regclass);


--
-- Name: itemid; Type: DEFAULT; Schema: public; Owner: equiz
--

ALTER TABLE ONLY titem ALTER COLUMN itemid SET DEFAULT nextval('itemid_seq'::regclass);


--
-- Name: userid; Type: DEFAULT; Schema: public; Owner: equiz
--

ALTER TABLE ONLY tuser ALTER COLUMN userid SET DEFAULT nextval('userid_seq'::regclass);


--
-- Name: answerid_seq; Type: SEQUENCE SET; Schema: public; Owner: equiz
--

SELECT pg_catalog.setval('answerid_seq', 278, true);


--
-- Name: groupcode_seq; Type: SEQUENCE SET; Schema: public; Owner: equiz
--

SELECT pg_catalog.setval('groupcode_seq', 45, true);


--
-- Name: itemid_seq; Type: SEQUENCE SET; Schema: public; Owner: equiz
--

SELECT pg_catalog.setval('itemid_seq', 104, true);


--
-- Data for Name: tanswer; Type: TABLE DATA; Schema: public; Owner: equiz
--

INSERT INTO tanswer VALUES (171, 'En una red de computadores, la ejecución de programas concurrentes no es habitual.', false, 69);
INSERT INTO tanswer VALUES (172, 'La comunicación mediante mensajes tiene como consecuencia la existencia de un reloj global.', false, 69);
INSERT INTO tanswer VALUES (173, 'Las afirmaciones anteriores son falsas.', true, 69);
INSERT INTO tanswer VALUES (174, 'Cuando un usuario envía un formulario, el navegador manda una petición HTTP a un servidor web y éste procesa los datos.', true, 70);
INSERT INTO tanswer VALUES (175, 'HTML consta de un conjunto dinámico de estructuras que soportan su extensión.', false, 70);
INSERT INTO tanswer VALUES (176, 'Como arquitectura del sistema, el Web no plantea problemas de escala.', false, 70);
INSERT INTO tanswer VALUES (177, 'Para obtener buenos tiempos de respuesta, los sistemas deben estar compuestos de relativamente pocas capas de software y la cantidad de datos transferida entre el cliente y el servidor debe ser pequeña.', false, 71);
INSERT INTO tanswer VALUES (178, 'Uno de los propósitos de los sistemas distribuidos es permitir que las aplicaciones y los procesos de servicio evolucionen concurrentemente haciendo que compitan por los mismos recursos (balance de carga computacional).', true, 71);
INSERT INTO tanswer VALUES (179, 'Las afirmaciones anteriores son todas ciertas.', false, 71);
INSERT INTO tanswer VALUES (180, 'Se entiende por fallos de envío a la pérdida de mensajes entre el proceso receptor y el búffer del mensaje de salida,', false, 72);
INSERT INTO tanswer VALUES (181, 'Se entiende por fallos por omisión de recepción la pérdida de mensajes entre el búffer de mensajes de salida y el proceso receptor.', false, 72);
INSERT INTO tanswer VALUES (182, 'Se entiende por fallos por omisión del canal a la pérdida de mensajes entre los búfferes.', true, 72);
INSERT INTO tanswer VALUES (183, 'i cierta, ii cierta', false, 73);
INSERT INTO tanswer VALUES (184, 'i cierta, ii falsa', true, 73);
INSERT INTO tanswer VALUES (185, 'i falsa, ii cierta', false, 73);
INSERT INTO tanswer VALUES (186, 'Servicios replicados orientados a la tolerancia a fallos.', false, 74);
INSERT INTO tanswer VALUES (187, 'Búsqueda de servicios en redes espontáneas.', false, 74);
INSERT INTO tanswer VALUES (188, 'Servicios web.', true, 74);
INSERT INTO tanswer VALUES (189, 'Es responsable de activar los servidores registrados bajo demanda.', false, 75);
INSERT INTO tanswer VALUES (190, 'No es necesario que almacenen las ubicaciones de los objetos, ya que las invocaciones a los métodos disponen de esa información para localizar el objeto invocado.', true, 75);
INSERT INTO tanswer VALUES (191, 'Los programas servidores que "alojan" los objetos remotos deben registrar la información de dichos en el propio repositorio.', false, 75);
INSERT INTO tanswer VALUES (192, 'i. cierta, ii. falsa', false, 76);
INSERT INTO tanswer VALUES (193, 'i. falsa, ii. cierta', false, 76);
INSERT INTO tanswer VALUES (194, 'i. falsa, ii. falsa', true, 76);
INSERT INTO tanswer VALUES (195, 'i. cierta, ii. cierta', false, 77);
INSERT INTO tanswer VALUES (196, 'i. cierta, ii. falsa', true, 77);
INSERT INTO tanswer VALUES (197, 'i. falsa, ii. cierta', false, 77);
INSERT INTO tanswer VALUES (198, 'Los cambios en un archivo por un cliente no deben interferir con la operación de otros clientes que acceden o cambian simultáneamente el mismo archivo.', true, 78);
INSERT INTO tanswer VALUES (199, 'Un archivo puede estar representado por varias copias de su contenido en diferentes ubicaciones.', false, 78);
INSERT INTO tanswer VALUES (200, 'Otra cosa distinta de a) y b)', false, 78);
INSERT INTO tanswer VALUES (201, 'Los nombres no puros son patrones de bits sin interpretar.', false, 79);
INSERT INTO tanswer VALUES (202, 'Los nombres puros contienen información acerca del objeto al que nombran.', false, 79);
INSERT INTO tanswer VALUES (203, 'La dirección de un objeto es un valor que identifica la ubicación de un objeto.', true, 79);
INSERT INTO tanswer VALUES (204, '200 segundos.', true, 80);
INSERT INTO tanswer VALUES (205, '8.33 días.', false, 80);
INSERT INTO tanswer VALUES (206, '8.33 horas.', false, 80);
INSERT INTO tanswer VALUES (207, 'participante', true, 81);
INSERT INTO tanswer VALUES (208, 'no participante', false, 81);
INSERT INTO tanswer VALUES (209, 'coordinador', false, 81);
INSERT INTO tanswer VALUES (210, 'Una clave privada.', false, 82);
INSERT INTO tanswer VALUES (211, 'Dos claves (una pública y otra privada).', false, 82);
INSERT INTO tanswer VALUES (212, 'No utilizan claves, se usan para calcular el resumen de un documento.', true, 82);
INSERT INTO tanswer VALUES (213, 'Heterogéneo.', false, 83);
INSERT INTO tanswer VALUES (214, 'Concurrente.', false, 83);
INSERT INTO tanswer VALUES (215, 'Escalable.', true, 83);
INSERT INTO tanswer VALUES (216, 'El repositorio de interfaz.', false, 84);
INSERT INTO tanswer VALUES (217, 'El resguardo/Proxy de cliente.', true, 84);
INSERT INTO tanswer VALUES (218, 'El adaptador de objeto.', false, 84);
INSERT INTO tanswer VALUES (219, 'El lenguaje de programación de la aplicación.', false, 85);
INSERT INTO tanswer VALUES (220, 'Los servicios RMI, RPC y los eventos.', true, 85);
INSERT INTO tanswer VALUES (221, 'La representación externa de los datos del sistema operativo del servidor.', false, 85);
INSERT INTO tanswer VALUES (222, 'Los datos almacenados en los servidores X.500 se organizan en una estructura de árbol en la que los nodos tienen nombres.', false, 86);
INSERT INTO tanswer VALUES (223, 'El árbol de nombres de X.500 se llama Base de Información de Directorio.', true, 86);
INSERT INTO tanswer VALUES (224, 'En X.500 es posible almacenar una gran cantidad de atributos en cada nodo del árbol.', false, 86);
INSERT INTO tanswer VALUES (225, '15', true, 87);
INSERT INTO tanswer VALUES (226, '45', false, 87);
INSERT INTO tanswer VALUES (227, 'Ninguna de las anteriores', false, 87);
INSERT INTO tanswer VALUES (228, '10 servidores.', false, 88);
INSERT INTO tanswer VALUES (229, '21 servidores.', true, 88);
INSERT INTO tanswer VALUES (230, '11 servidores.', false, 88);
INSERT INTO tanswer VALUES (231, 'Necesaria, programada y realizada.', true, 89);
INSERT INTO tanswer VALUES (232, 'Estadística, estratégica y normalizada.', false, 89);
INSERT INTO tanswer VALUES (233, 'Requerida, propuesta e implantada.', false, 89);
INSERT INTO tanswer VALUES (234, 'La calidad percibida.', false, 90);
INSERT INTO tanswer VALUES (235, 'La calidad necesaria.', true, 90);
INSERT INTO tanswer VALUES (236, 'La calidad programada.', false, 90);
INSERT INTO tanswer VALUES (237, 'Programable', false, 91);
INSERT INTO tanswer VALUES (238, 'Posible', false, 91);
INSERT INTO tanswer VALUES (239, 'Percibida', true, 91);
INSERT INTO tanswer VALUES (240, '14', true, 92);
INSERT INTO tanswer VALUES (241, '12', false, 92);
INSERT INTO tanswer VALUES (242, '11', false, 92);
INSERT INTO tanswer VALUES (243, '15', false, 93);
INSERT INTO tanswer VALUES (244, '16', true, 93);
INSERT INTO tanswer VALUES (245, '17', false, 93);
INSERT INTO tanswer VALUES (246, '17', false, 94);
INSERT INTO tanswer VALUES (247, '18', true, 94);
INSERT INTO tanswer VALUES (248, '20', false, 94);
INSERT INTO tanswer VALUES (249, '21', true, 95);
INSERT INTO tanswer VALUES (250, '23', false, 95);
INSERT INTO tanswer VALUES (251, '22', false, 95);
INSERT INTO tanswer VALUES (252, '35', false, 96);
INSERT INTO tanswer VALUES (253, '39', false, 96);
INSERT INTO tanswer VALUES (254, '36', true, 96);
INSERT INTO tanswer VALUES (255, '33', false, 97);
INSERT INTO tanswer VALUES (256, '31', false, 97);
INSERT INTO tanswer VALUES (257, '32', true, 97);
INSERT INTO tanswer VALUES (258, '42', true, 98);
INSERT INTO tanswer VALUES (259, '41', false, 98);
INSERT INTO tanswer VALUES (260, '40', false, 98);
INSERT INTO tanswer VALUES (261, '55', false, 99);
INSERT INTO tanswer VALUES (262, '54', true, 99);
INSERT INTO tanswer VALUES (263, '56', false, 99);
INSERT INTO tanswer VALUES (264, '65', false, 100);
INSERT INTO tanswer VALUES (265, '56', true, 100);
INSERT INTO tanswer VALUES (266, '54', false, 100);
INSERT INTO tanswer VALUES (267, '72', true, 101);
INSERT INTO tanswer VALUES (268, '78', false, 101);
INSERT INTO tanswer VALUES (269, '71', false, 101);
INSERT INTO tanswer VALUES (270, '54', true, 102);
INSERT INTO tanswer VALUES (271, '55', false, 102);
INSERT INTO tanswer VALUES (272, '56', false, 102);
INSERT INTO tanswer VALUES (273, '65', false, 103);
INSERT INTO tanswer VALUES (274, '64', false, 103);
INSERT INTO tanswer VALUES (275, '63', true, 103);
INSERT INTO tanswer VALUES (276, '81', true, 104);
INSERT INTO tanswer VALUES (277, '89', false, 104);
INSERT INTO tanswer VALUES (278, '80', false, 104);


--
-- Data for Name: tgroup; Type: TABLE DATA; Schema: public; Owner: equiz
--

INSERT INTO tgroup VALUES (43, 'Sistemas distribuidos (0155502)', 10, '2014-05-15 17:32:02.056+02', '18554542', '2014-05-15 18:21:54.925+02');
INSERT INTO tgroup VALUES (44, 'Calidad del Software (0155522)', 10, '2014-05-15 18:23:21.363+02', '80736BEF', '2014-05-15 18:25:53.298+02');
INSERT INTO tgroup VALUES (45, 'Matemáticas (2º primaria). Multiplicar', 10, '2014-05-15 18:43:48.096+02', 'A8BC0621', '2014-05-15 18:48:26.497+02');


--
-- Data for Name: titem; Type: TABLE DATA; Schema: public; Owner: equiz
--

INSERT INTO titem VALUES (69, 'En relación con las consecuencias de la definición de sistema distribuido, indicar cual de las siguientes afirmaciones es cierta:', 1, 43);
INSERT INTO titem VALUES (70, 'Indicar cuál de las siguientes afirmaciones es cierta:', 2, 43);
INSERT INTO titem VALUES (71, 'En relación con los requisitos de diseño de arquitecturas distribuidas, indicar cuál de las afirmaciones realizadas en a) y b) es falsa. Si considera que son todas ciertas, marque la opción c).', 3, 43);
INSERT INTO titem VALUES (72, 'En relación con el modelo de fallo, indicar cuál de las afirmaciones es cierta:', 6, 43);
INSERT INTO titem VALUES (73, 'La comunicación síncrona se caracteriza por usar dos operaciones: envía() y recibe(). Decir cuál de las siguientes afirmaciones es cierta:
i. La operación envía() siempre bloquea la ejecución del emisor.
ii. La operación recibe() puede o no bloquear la ejecución del receptor, dependiendo del mecanismo implementado.', 4, 43);
INSERT INTO titem VALUES (74, 'Decir cuál de estas opciones NO se puede considerar como aplicación directa de la multidifusión IP.', 3, 43);
INSERT INTO titem VALUES (75, 'En la arquitectura CORBA existe un elemento denominado "Repositorio de implementación" que se emplea básicamente en las invocaciones dinámicas a objetos remotos CORBA. Indicar cuál de las siguientes afirmaciones es falsa, en cuanto a los usos/responsabilidades del registro de implementación.', 9, 43);
INSERT INTO titem VALUES (76, 'Indicar cuál de las siguientes afirmaciones es cierta:
i. La serialización de objetos en Java emplea la reflexión únicamente en la deserialización de un objeto remoto en el cliente para la creación de una instancia del objeto remoto.
ii. Uno de los campos incluidos en el paquete de datos que se enviará por la red con la información serializada sobre una clase Java es el número de versión de la máquina virtual usada en el proceso de serialización.', 6, 43);
INSERT INTO titem VALUES (77, 'Respecto del paso de parámetros en Java RMI, indicar cuál de estas afirmaciones es cierta:
i. Todos los objetos remotos se pasan por referencia a sus interfaces remotas en los correspondientes argumentos de las invocaciones remotas.
ii. Los objetos locales (no remotos) se deserializan y se pasan por valor en los correspondientes argumentos de las invocaciones remotas.', 4, 43);
INSERT INTO titem VALUES (78, 'En relación con los requisitos del sistema de archivos distribuidos, la actualización concurrente de archivos implica que:', 3, 43);
INSERT INTO titem VALUES (79, 'En relación con los servicios de nombres, indicar cuál de las siguientes afirmaciones es cierta:', 7, 43);
INSERT INTO titem VALUES (80, 'Si un reloj tiene un ritmo de deriva de 0.005 segundos/hora respecto al reloj de referencia, cada cuánto tiempo es necesario hacer una corrección de un segundo sobre el reloj.', 8, 43);
INSERT INTO titem VALUES (81, 'Si se produce la caída del coordinador de un grupo de procesos donde se implementa un algoritmo de elección basado  en anillo, indicar que estado debe tener el proceso que inicie la ejecución del algoritmo de elección.', 9, 43);
INSERT INTO titem VALUES (82, 'Los algoritmos de resumen (HASH) utilizan:', 1, 43);
INSERT INTO titem VALUES (83, 'Un sistema que conserva su efectividad cuando ocurre un incremento significativo en el número de recursos y el número de usuarios, se dice que es:', 2, 43);
INSERT INTO titem VALUES (84, 'Indicar cuál de estos elementos no es fundamental para el funcionamiento de las aplicaciones CORBA, teniendo en cuenta que se emplea invocación dinámica.', 8, 43);
INSERT INTO titem VALUES (85, 'Decir cuál de las siguientes opciones son proporcionadas por las capas middleware de una arquitectura de aplicaciones.', 6, 43);
INSERT INTO titem VALUES (86, 'En relación con el caso del Servicio de Directorio X.500, indicar cuál de las siguientes afirmaciones es falsa:', 9, 43);
INSERT INTO titem VALUES (87, 'En el algoritmo de elección basado en anillo, indicar para el peor caso cuál es el número de mensajes totales que se envían para un grupo de 15 procesos.', 10, 43);
INSERT INTO titem VALUES (88, 'Se pretende conseguir un servicio de alta disponibilidad correcto (replicación de los datos y funcionalidad en los servidores) en un sistema en el que como máximo pueden presentar fallos complejos k servidores. Indicar cuántos servidores como mínimo hacen falta para mantener la disponibilidad correcta del servicio para el caso k=10.', 10, 43);
INSERT INTO titem VALUES (89, 'Se pueden distinguir los siguientes tipos de calidad:', 2, 44);
INSERT INTO titem VALUES (90, 'La calidad que pide el cliente es:', 1, 44);
INSERT INTO titem VALUES (91, '¿Qué tipo de calidad depende de la información que tienen los clientes?', 2, 44);
INSERT INTO titem VALUES (92, '2x7', 1, 45);
INSERT INTO titem VALUES (93, '2x8', 1, 45);
INSERT INTO titem VALUES (94, '3x6', 3, 45);
INSERT INTO titem VALUES (95, '3x7', 3, 45);
INSERT INTO titem VALUES (96, '4x9', 4, 45);
INSERT INTO titem VALUES (97, '4x8', 4, 45);
INSERT INTO titem VALUES (98, '6x7', 5, 45);
INSERT INTO titem VALUES (99, '6x9', 7, 45);
INSERT INTO titem VALUES (100, '7x8', 9, 45);
INSERT INTO titem VALUES (101, '8x9', 8, 45);
INSERT INTO titem VALUES (102, '9x6', 8, 45);
INSERT INTO titem VALUES (103, '9x7', 9, 45);
INSERT INTO titem VALUES (104, '9x9', 7, 45);


--
-- Data for Name: tuser; Type: TABLE DATA; Schema: public; Owner: equiz
--

INSERT INTO tuser VALUES ('alumno1@email.com', 'Jimena', 'Gómez', '1000:3bc9a34d1cf8bd9cc8e2ee4f8eaacf2b44dfa37af0a26532:e846fe2b6958687aaad541eba06278a03b4b4a098fcca5da', false, 67, '2014-05-15 16:01:17.231+02');
INSERT INTO tuser VALUES ('alumno2@email.com', 'Alejandra', 'Gómez', '1000:5cf804db6ba041aa0e0032ae3279b4fcff4a461e9555bf87:cdfd3ef1d827afe408a4c20b70c7457ada11c03dc6d8fac5', false, 68, '2014-05-15 16:02:12.805+02');
INSERT INTO tuser VALUES ('educador1@email.com', 'Luis', 'López', '1000:1ed792de5824b2a263dcb118443d9b7299ff75ca382e104f:8209e36360fc6dc3c70510532bc35bf764d85c5c73cd4d70', true, 66, '2014-05-15 16:01:36.051+02');
INSERT INTO tuser VALUES ('educador2@email.com', 'Mario', 'García', '1000:99603cdf3e65be766aa39fab12a8fb42207e98a913a69421:097f2b78e4d829c4cca64499153642387783f6c46a784d4f', true, 69, '2014-05-15 18:43:11.517+02');


--
-- Data for Name: tusergroup; Type: TABLE DATA; Schema: public; Owner: equiz
--

INSERT INTO tusergroup VALUES (43, 0, NULL, 66, '2014-05-15 17:32:03.699+02');
INSERT INTO tusergroup VALUES (44, 0, NULL, 66, '2014-05-15 18:23:21.571+02');
INSERT INTO tusergroup VALUES (45, 0, NULL, 69, '2014-05-15 18:43:48.143+02');
INSERT INTO tusergroup VALUES (45, 0, NULL, 68, '2014-05-15 18:49:12.343+02');
INSERT INTO tusergroup VALUES (43, 2, NULL, 67, '2014-05-15 18:31:05.29+02');


--
-- Name: userid_seq; Type: SEQUENCE SET; Schema: public; Owner: equiz
--

SELECT pg_catalog.setval('userid_seq', 69, true);


--
-- Name: tanswer_pkey; Type: CONSTRAINT; Schema: public; Owner: equiz; Tablespace: 
--

ALTER TABLE ONLY tanswer
    ADD CONSTRAINT tanswer_pkey PRIMARY KEY (answerid);


--
-- Name: tgroup_pkey; Type: CONSTRAINT; Schema: public; Owner: equiz; Tablespace: 
--

ALTER TABLE ONLY tgroup
    ADD CONSTRAINT tgroup_pkey PRIMARY KEY (groupcode);


--
-- Name: tquestion_pkey; Type: CONSTRAINT; Schema: public; Owner: equiz; Tablespace: 
--

ALTER TABLE ONLY titem
    ADD CONSTRAINT tquestion_pkey PRIMARY KEY (itemid);


--
-- Name: tuser_pkey; Type: CONSTRAINT; Schema: public; Owner: equiz; Tablespace: 
--

ALTER TABLE ONLY tuser
    ADD CONSTRAINT tuser_pkey PRIMARY KEY (userid);


--
-- Name: tusergroup_pkey; Type: CONSTRAINT; Schema: public; Owner: equiz; Tablespace: 
--

ALTER TABLE ONLY tusergroup
    ADD CONSTRAINT tusergroup_pkey PRIMARY KEY (groupcode, userid);


--
-- Name: useremail; Type: CONSTRAINT; Schema: public; Owner: equiz; Tablespace: 
--

ALTER TABLE ONLY tuser
    ADD CONSTRAINT useremail UNIQUE (useremail);


--
-- Name: tanswer_fkey_in_bi_itemid; Type: FK CONSTRAINT; Schema: public; Owner: equiz
--

ALTER TABLE ONLY tanswer
    ADD CONSTRAINT tanswer_fkey_in_bi_itemid FOREIGN KEY (itemid) REFERENCES titem(itemid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: titem_fkey_in_groupcode; Type: FK CONSTRAINT; Schema: public; Owner: equiz
--

ALTER TABLE ONLY titem
    ADD CONSTRAINT titem_fkey_in_groupcode FOREIGN KEY (groupcode) REFERENCES tgroup(groupcode) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: tusergroup_fkey_groupcode; Type: FK CONSTRAINT; Schema: public; Owner: equiz
--

ALTER TABLE ONLY tusergroup
    ADD CONSTRAINT tusergroup_fkey_groupcode FOREIGN KEY (groupcode) REFERENCES tgroup(groupcode) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: tusergroup_fkey_userid; Type: FK CONSTRAINT; Schema: public; Owner: equiz
--

ALTER TABLE ONLY tusergroup
    ADD CONSTRAINT tusergroup_fkey_userid FOREIGN KEY (userid) REFERENCES tuser(userid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: public; Type: ACL; Schema: -; Owner: equiz
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM equiz;
GRANT ALL ON SCHEMA public TO equiz;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

