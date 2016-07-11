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

CREATE TABLE `apiassets` (
  `item_id` bigint(11) NOT NULL,
  `parent_item_id` bigint(11) NOT NULL,
  `location_id` bigint(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `flag` int(11) NOT NULL,
  `singleton` int(11) NOT NULL,
  `raw_quantity` int(11) DEFAULT NULL,
  `corporation_id` bigint(11) NOT NULL
);

CREATE TABLE `apicontractitems` (
  `contract_id` bigint(11) NOT NULL,
  `record_id` bigint(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `singleton` int(11) NOT NULL,
  `included` int(11) NOT NULL,
  `corporation_id` bigint(11) NOT NULL
);

CREATE TABLE `apicontracts` (
  `contract_id` bigint(11) NOT NULL,
  `issuer_id` bigint(11) NOT NULL,
  `issuer_corp_id` bigint(11) NOT NULL,
  `assignee_id` bigint(11) NOT NULL,
  `acceptor_id` bigint(11) NOT NULL,
  `start_station_id` bigint(11) NOT NULL,
  `end_station_id` bigint(11) NOT NULL,
  `type` varchar(32) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `for_corp` int(11) NOT NULL,
  `availability` varchar(32) DEFAULT NULL,
  `date_issued` datetime NOT NULL,
  `date_expired` datetime NOT NULL,
  `date_accepted` datetime NOT NULL,
  `num_days` int(11) NOT NULL,
  `date_completed` datetime NOT NULL,
  `price` decimal(20,2) NOT NULL,
  `reward` decimal(20,2) NOT NULL,
  `collateral` decimal(20,2) NOT NULL,
  `buyout` decimal(20,2) NOT NULL,
  `volume` decimal(20,2) NOT NULL,
  `corporation_id` bigint(11) NOT NULL
);

CREATE TABLE `apicorpmembers` (
  `character_id` bigint(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_date_time` datetime NOT NULL,
  `base_id` bigint(11) NOT NULL,
  `base` varchar(1024) DEFAULT NULL,
  `title` varchar(1024) DEFAULT NULL,
  `corporation_id` bigint(11) DEFAULT NULL
);

CREATE TABLE `apicorps` (
  `corporation_id` bigint(11) NOT NULL,
  `corporation_name` varchar(255) NOT NULL,
  `character_id` int(11) NOT NULL,
  `character_name` varchar(255) NOT NULL,
  `key_id` varchar(255) NOT NULL
);

CREATE TABLE `apicorpsheet` (
  `corporation_id` bigint(11) NOT NULL,
  `corporation_name` varchar(255) NOT NULL,
  `ticker` varchar(6) NOT NULL,
  `ceo_id` bigint(11) NOT NULL,
  `ceo_name` varchar(255) NOT NULL,
  `station_id` bigint(11) NOT NULL,
  `station_name` varchar(1024) NOT NULL,
  `description` varchar(2048) NOT NULL,
  `url` varchar(255) NOT NULL,
  `alliance_id` bigint(11) NOT NULL,
  `tax_rate` int(11) NOT NULL,
  `member_count` int(11) NOT NULL,
  `member_limit` int(11) NOT NULL,
  `shares` int(11) NOT NULL,
  `graphic_id` int(11) NOT NULL,
  `shape1` int(11) NOT NULL,
  `shape2` int(11) NOT NULL,
  `shape3` int(11) NOT NULL,
  `color1` int(11) NOT NULL,
  `color2` int(11) NOT NULL,
  `color3` int(11) NOT NULL
);

CREATE TABLE `apiindustryjobs` (
  `job_id` bigint(11) NOT NULL,
  `assembly_line_id` bigint(11) NOT NULL,
  `container_id` bigint(11) NOT NULL,
  `installed_item_id` bigint(11) NOT NULL,
  `installed_item_location_id` bigint(11) NOT NULL,
  `installed_item_quantity` bigint(11) NOT NULL,
  `installed_item_productivity_level` bigint(11) NOT NULL,
  `installed_item_material_level` bigint(11) NOT NULL,
  `installed_item_licensed_production_runs_remaining` bigint(11) NOT NULL,
  `output_location_id` bigint(11) NOT NULL,
  `installer_id` bigint(11) NOT NULL,
  `runs` int(11) NOT NULL,
  `licensed_production_runs` int(11) NOT NULL,
  `installed_in_solar_system_id` bigint(11) NOT NULL,
  `container_location_id` bigint(11) NOT NULL,
  `material_multiplier` decimal(16,15) NOT NULL,
  `char_material_multiplier` decimal(16,15) NOT NULL,
  `time_multiplier` decimal(16,15) NOT NULL,
  `char_time_multiplier` decimal(16,15) NOT NULL,
  `installed_item_type_id` int(11) NOT NULL,
  `output_type_id` int(11) NOT NULL,
  `container_type_id` int(11) NOT NULL,
  `installed_item_copy` int(11) NOT NULL,
  `completed` int(11) NOT NULL,
  `completed_successfully` int(11) NOT NULL,
  `successful_runs` int(11) DEFAULT NULL,
  `installed_item_flag` int(11) NOT NULL,
  `output_flag` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `completed_status` int(11) NOT NULL,
  `install_time` datetime NOT NULL,
  `begin_production_time` datetime NOT NULL,
  `end_production_time` datetime NOT NULL,
  `pause_production_time` datetime NOT NULL,
  `corporation_id` bigint(11) DEFAULT NULL
);

CREATE TABLE `apiindustryjobscrius` (
  `job_id` bigint(11) NOT NULL,
  `installer_id` bigint(11) NOT NULL,
  `installer_name` varchar(255) NOT NULL,
  `facility_id` bigint(11) NOT NULL,
  `solar_system_id` int(11) NOT NULL,
  `solar_system_name` varchar(255) NOT NULL,
  `station_id` bigint(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `blueprint_id` bigint(11) NOT NULL,
  `blueprint_type_id` int(11) NOT NULL,
  `blueprint_type_name` varchar(255) NOT NULL,
  `blueprint_location_id` bigint(11) NOT NULL,
  `output_location_id` bigint(11) NOT NULL,
  `runs` int(11) NOT NULL,
  `cost` decimal(20,2) NOT NULL,
  `team_id` bigint(11) NOT NULL,
  `licensed_runs` int(11) NOT NULL,
  `probability` decimal(20,2) NOT NULL,
  `product_type_id` int(11) NOT NULL,
  `product_type_name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  `time_in_seconds` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `pause_date` datetime NOT NULL,
  `completed_date` datetime NOT NULL,
  `completed_character_id` bigint(11) NOT NULL,
  `successful_runs` int(11) DEFAULT NULL,
  `corporation_id` bigint(11) DEFAULT NULL
);

CREATE TABLE `apiwallettransactions` (
  `transaction_date_time` datetime NOT NULL,
  `transaction_id` bigint(11) NOT NULL,
  `quantity` bigint(11) NOT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  `type_id` int(11) NOT NULL,
  `price` decimal(20,2) NOT NULL,
  `client_id` bigint(11) NOT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `character_id` bigint(11) NOT NULL,
  `character_name` varchar(255) DEFAULT NULL,
  `station_id` int(11) NOT NULL,
  `station_name` varchar(255) DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  `transaction_for` varchar(255) DEFAULT NULL,
  `journal_transaction_id` bigint(11) NOT NULL,
  `account_key` int(11) NOT NULL,
  `corporation_id` bigint(11) NOT NULL
);

CREATE TABLE `apiwalletjournal` (
  `date` datetime NOT NULL,
  `ref_id` bigint(11) NOT NULL,
  `ref_type_id` int(11) NOT NULL,
  `owner_name_one` varchar(255) DEFAULT NULL,
  `owner_id_one` int(11) NOT NULL,
  `owner_name_two` varchar(255) DEFAULT NULL,
  `owner_id_two` int(11) NOT NULL,
  `arg_name_one` varchar(255) DEFAULT NULL,
  `arg_id_one` int(11) NOT NULL,
  `amount` decimal(20,2) NOT NULL,
  `balance` decimal(20,2) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `corporation_id` bigint(11) NOT NULL,
  `account_key` int(11) NOT NULL DEFAULT '1000'
);