-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'family_reading_tracker';

DROP DATABASE family_reading_tracker;

DROP USER family_reading_tracker_owner;
DROP USER family_reading_tracker_appuser;

-- Re-Create the database
CREATE DATABASE family_reading_tracker;
