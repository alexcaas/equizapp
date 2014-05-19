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

