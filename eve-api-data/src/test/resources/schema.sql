--I know the original schema I passed had different column names but I want to rename them
--because I like my db columns to be underscore seperated and java objects to be camel cased.
--Plus hibernate seems to ignore an explicit mapping of accountKey in a column and instead changes
--that to account_key...  I don't feel like digging through hibernate configurations to remember
--how to set this auto column mapping again... damnit I should have written that down... but I think
--I did... now... where is it?
CREATE TABLE `apiaccountbalance` (
  `account_id` bigint(11) NOT NULL,
  `account_key` int(11) NOT NULL,
  `balance` decimal(20,2) NOT NULL,
  `corporation_id` bigint(11) NOT NULL
);