/*
  * Test data for database.
*/
SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
/*
CREATE OR REPLACE FUNCTION bytea_import(p_path TEXT, p_result OUT BYTEA)
    LANGUAGE plpgsql AS
$$
DECLARE
    l_oid OID;
BEGIN
    SELECT lo_import(p_path) INTO l_oid;
    SELECT lo_get(l_oid) INTO p_result;
    PERFORM lo_unlink(l_oid);
END;
$$;*/

DO
$BODY$
    DECLARE
        /*
          Post test data
        */
        post_1_uuid  varchar := uuid_generate_v4();
        post_2_uuid  varchar := uuid_generate_v4();
        post_3_uuid  varchar := uuid_generate_v4();
        post_4_uuid  varchar := uuid_generate_v4();
        post_5_uuid  varchar := uuid_generate_v4();
        post_6_uuid  varchar := uuid_generate_v4();
        post_7_uuid  varchar := uuid_generate_v4();
        post_8_uuid  varchar := uuid_generate_v4();
        post_9_uuid  varchar := uuid_generate_v4();
        post_10_uuid varchar := uuid_generate_v4();
        post_11_uuid varchar := uuid_generate_v4();
        post_12_uuid varchar := uuid_generate_v4();
        post_13_uuid varchar := uuid_generate_v4();
        post_14_uuid varchar := uuid_generate_v4();
        post_15_uuid varchar := uuid_generate_v4();
        post_16_uuid varchar := uuid_generate_v4();
        post_17_uuid varchar := uuid_generate_v4();
        post_18_uuid varchar := uuid_generate_v4();
        post_20_uuid varchar := uuid_generate_v4();
        post_21_uuid varchar := uuid_generate_v4();
        post_22_uuid varchar := uuid_generate_v4();
        post_23_uuid varchar := uuid_generate_v4();
        post_24_uuid varchar := uuid_generate_v4();
        post_25_uuid varchar := uuid_generate_v4();
        post_26_uuid varchar := uuid_generate_v4();
        post_27_uuid varchar := uuid_generate_v4();
        post_28_uuid varchar := uuid_generate_v4();
        post_29_uuid varchar := uuid_generate_v4();
        post_30_uuid varchar := uuid_generate_v4();
        post_31_uuid varchar := uuid_generate_v4();
        post_32_uuid varchar := uuid_generate_v4();
        post_33_uuid varchar := uuid_generate_v4();
        post_34_uuid varchar := uuid_generate_v4();
        post_35_uuid varchar := uuid_generate_v4();
        post_36_uuid varchar := uuid_generate_v4();
        post_37_uuid varchar := uuid_generate_v4();
        post_38_uuid varchar := uuid_generate_v4();
        post_39_uuid varchar := uuid_generate_v4();
        post_40_uuid varchar := uuid_generate_v4();
        post_41_uuid varchar := uuid_generate_v4();
        post_42_uuid varchar := uuid_generate_v4();
        post_43_uuid varchar := uuid_generate_v4();
        post_44_uuid varchar := uuid_generate_v4();
        post_45_uuid varchar := uuid_generate_v4();
        post_46_uuid varchar := uuid_generate_v4();
        post_47_uuid varchar := uuid_generate_v4();
        post_48_uuid varchar := uuid_generate_v4();
        post_49_uuid varchar := uuid_generate_v4();
        post_50_uuid varchar := uuid_generate_v4();
        post_51_uuid varchar := uuid_generate_v4();
        post_52_uuid varchar := uuid_generate_v4();
        post_53_uuid varchar := uuid_generate_v4();
        post_54_uuid varchar := uuid_generate_v4();
        post_55_uuid varchar := uuid_generate_v4();
        post_56_uuid varchar := uuid_generate_v4();
        post_57_uuid varchar := uuid_generate_v4();
        post_58_uuid varchar := uuid_generate_v4();
        post_59_uuid varchar := uuid_generate_v4();
        post_60_uuid varchar := uuid_generate_v4();

    BEGIN
        INSERT INTO public.post(id, title, author, link, category, text, likes, dislikes, is_ad, nsfw, is_enabled)
        values (post_1_uuid, 'Post1_is_AD', 'Author1', 'https://socnet.com/link1', 'POLITICS', 'TEST TEXT 1', 30, 20, true, false, true),
               (post_2_uuid, 'Post2_is_AD', 'Author2', 'https://socnet.com/link2', 'TECHNOLOGIES', 'TEST TEXT 2', 10, 100, true, false, true),
               (post_3_uuid, 'Post3_is_AD', 'Author3', 'https://socnet.com/link3', 'POLITICS', 'TEST TEXT 3', 15, 12, true, false, true),
               (post_4_uuid, 'Post4_is_AD', 'Author4', 'https://socnet.com/link4', 'POLITICS', 'TEST TEXT 4', 0, 13, true, true, true),
               (post_5_uuid, 'Post5_is_AD', 'Author5', 'https://socnet.com/link5', 'HISTORY', 'TEST TEXT 5', 0, 0, true, false, true),
               (post_6_uuid, 'Post6_nsfw', 'Author5', 'https://socnet.com/link6', 'HISTORY', 'TEST TEXT 6', 2000, 2, false, true, true),
               (post_7_uuid, 'Post7_nsfw', 'Author6', 'https://socnet.com/link7', 'TECHNOLOGIES', 'TEST TEXT 7', 200, 0, false, true, true),
               (post_8_uuid, 'Post8_nsfw', 'Author7', 'https://socnet.com/link8', 'HISTORY', 'TEST TEXT 8', 3000, 2, false, true, true),
               (post_9_uuid, 'Post9_nsfw', 'Author9', 'https://socnet.com/link9', 'TECHNOLOGIES', 'TEST TEXT 9', 11, 50, false, true, true),
               (post_10_uuid, 'Post10_nsfw', 'Author10', 'https://socnet.com/link10', 'TECHNOLOGIES', 'TEST TEXT 10', 11, 2, false, true, true),
               (post_11_uuid, 'Post_11', 'Author11', 'https://socnet.com/link11', 'TECHNOLOGIES', 'TEST TEXT 11', 100, 2, false, false, true),
               (post_12_uuid, 'Post_12', 'Author12', 'https://socnet.com/link12', 'TECHNOLOGIES', 'TEST TEXT 12', 3, 2, false, false, true),
               (post_13_uuid, 'Post_13', 'Author13', 'https://socnet.com/link13', 'TECHNOLOGIES', 'TEST TEXT 13', 55, 22, false, false, true),
               (post_14_uuid, 'Post_14', 'Author14', 'https://socnet.com/link14', 'TECHNOLOGIES', 'TEST TEXT 14', 40, 0, false, false, true),
               (post_15_uuid, 'Post_15', 'Author15', 'https://socnet.com/link15', 'TECHNOLOGIES', 'TEST TEXT 15', 100, 90, false, false, true),
               (post_16_uuid, 'Post_16', 'Author16', 'https://socnet.com/link16', 'TECHNOLOGIES', 'TEST TEXT 16', 77, 9, false, false, true),
               (post_17_uuid, 'Post_17', 'Author17', 'https://socnet.com/link17', 'TECHNOLOGIES', 'TEST TEXT 17', 65, 13, false, false, true),
               (post_18_uuid, 'Post_18', 'Author18', 'https://socnet.com/link18', 'TECHNOLOGIES', 'TEST TEXT 18', 22, 11, false, false, true),
               (post_20_uuid, 'Post_20', 'Author20', 'https://socnet.com/link20', 'TECHNOLOGIES', 'TEST TEXT 20', 45, 0, false, false, true),
               (post_21_uuid, 'Post_21', 'Author21', 'https://socnet.com/link21', 'TECHNOLOGIES', 'TEST TEXT 21', 121, 12, false, false, true),
               (post_22_uuid, 'Post_22', 'Author22', 'https://socnet.com/link22', 'TECHNOLOGIES', 'TEST TEXT 22', 32, 12, false, false, true),
               (post_23_uuid, 'Post_23', 'Author23', 'https://socnet.com/link23', 'TECHNOLOGIES', 'TEST TEXT 23', 33, 20, false, false, true),
               (post_24_uuid, 'Post_24', 'Author24', 'https://socnet.com/link24', 'TECHNOLOGIES', 'TEST TEXT 24', 44, 0, false, false, true),
               (post_25_uuid, 'Post_25', 'Author25', 'https://socnet.com/link25', 'TECHNOLOGIES', 'TEST TEXT 25', 88, 30, false, false, true),
               (post_26_uuid, 'Post_26', 'Author26', 'https://socnet.com/link26', 'TECHNOLOGIES', 'TEST TEXT 26', 99, 7, false, false, true),
               (post_27_uuid, 'Post_27', 'Author27', 'https://socnet.com/link27', 'TECHNOLOGIES', 'TEST TEXT 27', 11, 7, false, false, true),
               (post_28_uuid, 'Post_28', 'Author28', 'https://socnet.com/link28', 'TECHNOLOGIES', 'TEST TEXT 28', 23, 8, false, false, true),
               (post_29_uuid, 'Post_29', 'Author29', 'https://socnet.com/link29', 'TECHNOLOGIES', 'TEST TEXT 29', 64, 3, false, false, true),
               (post_30_uuid, 'Post_30', 'Author30', 'https://socnet.com/link30', 'TECHNOLOGIES', 'TEST TEXT 30', 89, 20, false, false, true),
               (post_31_uuid, 'Post_31', 'Author31', 'https://socnet.com/link31', 'TECHNOLOGIES', 'TEST TEXT 31', 0, 33, false, false, true),
               (post_32_uuid, 'Post_32', 'Author32', 'https://socnet.com/link32', 'TECHNOLOGIES', 'TEST TEXT 32', 75, 90, false, false, true),
               (post_33_uuid, 'Post_33', 'Author33', 'https://socnet.com/link33', 'TECHNOLOGIES', 'TEST TEXT 33', 23, 8, false, false, true),
               (post_34_uuid, 'Post_34', 'Author34', 'https://socnet.com/link34', 'TECHNOLOGIES', 'TEST TEXT 34', 10, 1, false, false, true),
               (post_35_uuid, 'Post_35', 'Author35', 'https://socnet.com/link35', 'TECHNOLOGIES', 'TEST TEXT 35', 17, 4, false, false, true),
               (post_36_uuid, 'Post_36', 'Author36', 'https://socnet.com/link36', 'TECHNOLOGIES', 'TEST TEXT 36', 99, 17, false, false, true),
               (post_37_uuid, 'Post_37', 'Author37', 'https://socnet.com/link37', 'TECHNOLOGIES', 'TEST TEXT 37', 98, 31, false, false, true),
               (post_38_uuid, 'Post_38', 'Author38', 'https://socnet.com/link38', 'TECHNOLOGIES', 'TEST TEXT 38', 70, 24, false, false, true),
               (post_39_uuid, 'Post_39', 'Author39', 'https://socnet.com/link39', 'TECHNOLOGIES', 'TEST TEXT 39', 60, 0, false, false, true),
               (post_40_uuid, 'Post_40', 'Author40', 'https://socnet.com/link40', 'TECHNOLOGIES', 'TEST TEXT 40', 34, 8, false, false, true),
               (post_41_uuid, 'Post_41', 'Author41', 'https://socnet.com/link41', 'TECHNOLOGIES', 'TEST TEXT 41', 160, 40, false, false, true),
               (post_42_uuid, 'Post_42', 'Author42', 'https://socnet.com/link42', 'TECHNOLOGIES', 'TEST TEXT 42', 300, 0, false, false, true),
               (post_43_uuid, 'Post_43', 'Author43', 'https://socnet.com/link43', 'TECHNOLOGIES', 'TEST TEXT 43', 150, 4, false, false, true),
               (post_44_uuid, 'Post_44', 'Author44', 'https://socnet.com/link44', 'TECHNOLOGIES', 'TEST TEXT 44', 500, 5, false, false, true),
               (post_45_uuid, 'Post_45', 'Author45', 'https://socnet.com/link45', 'TECHNOLOGIES', 'TEST TEXT 45', 220, 8, false, false, true),
               (post_46_uuid, 'Post_46', 'Author46', 'https://socnet.com/link46', 'TECHNOLOGIES', 'TEST TEXT 46', 111, 10, false, false, true),
               (post_47_uuid, 'Post_47', 'Author47', 'https://socnet.com/link47', 'TECHNOLOGIES', 'TEST TEXT 47', 112, 12, false, false, true),
               (post_48_uuid, 'Post_48', 'Author48', 'https://socnet.com/link48', 'TECHNOLOGIES', 'TEST TEXT 48', 113, 33, false, false, true),
               (post_49_uuid, 'Post_49', 'Author49', 'https://socnet.com/link49', 'TECHNOLOGIES', 'TEST TEXT 49', 114, 65, false, false, true),
               (post_50_uuid, 'Post_50', 'Author50', 'https://socnet.com/link50', 'TECHNOLOGIES', 'TEST TEXT 50', 807, 157, false, false, true),
               (post_51_uuid, 'Post_51', 'Author51', 'https://socnet.com/link51', 'TECHNOLOGIES', 'TEST TEXT 51', 980, 234, false, false, true),
               (post_52_uuid, 'Post_52', 'Author52', 'https://socnet.com/link52', 'TECHNOLOGIES', 'TEST TEXT 52', 1134, 50, false, false, true),
               (post_53_uuid, 'Post_53', 'Author53', 'https://socnet.com/link53', 'TECHNOLOGIES', 'TEST TEXT 53', 100, 70, false, false, true),
               (post_54_uuid, 'Post_54', 'Author54', 'https://socnet.com/link54', 'TECHNOLOGIES', 'TEST TEXT 54', 111, 22, false, false, true),
               (post_55_uuid, 'Post_55', 'Author55', 'https://socnet.com/link55', 'TECHNOLOGIES', 'TEST TEXT 55', 1000, 11, false, false, true),
               (post_56_uuid, 'Post_55', 'Author55', 'https://socnet.com/link55', 'TECHNOLOGIES', 'TEST TEXT 55', 1000, 11, false, false, true),
               (post_57_uuid, 'Post_55', 'Author55', 'https://socnet.com/link55', 'TECHNOLOGIES', 'TEST TEXT 55', 1000, 11, false, false, true),
               (post_58_uuid, 'Post_55', 'Author55', 'https://socnet.com/link55', 'TECHNOLOGIES', 'TEST TEXT 55', 1000, 11, false, false, true),
               (post_59_uuid, 'Post_55', 'Author55', 'https://socnet.com/link55', 'TECHNOLOGIES', 'TEST TEXT 55', 1000, 11, false, false, true),
               (post_60_uuid, 'Post_55', 'Author55', 'https://socnet.com/link55', 'TECHNOLOGIES', 'TEST TEXT 55', 1000, 11, false, false, true);
    END;

$BODY$ LANGUAGE plpgsql;