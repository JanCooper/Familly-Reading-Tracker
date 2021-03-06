-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER family_reading_tracker_owner
WITH PASSWORD 'finalcapstone';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO family_reading_tracker_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO family_reading_tracker_owner;

CREATE USER family_reading_tracker_appuser
WITH PASSWORD 'finalcapstone';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO family_reading_tracker_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO family_reading_tracker_appuser;
