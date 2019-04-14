-- Adminer 4.7.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `account_number` bigint(20) NOT NULL,
  `customer_number` varchar(45) DEFAULT NULL,
  `account_name` varchar(45) DEFAULT NULL,
  `ballance` double DEFAULT NULL,
  `open_date` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  `flag_active` int(2) DEFAULT 1,
  `flag_delete` int(2) DEFAULT 0,
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_account` (`account_number`, `customer_number`, `account_name`, `ballance`, `open_date`, `updated_at`, `flag_active`, `flag_delete`) VALUES
(4536608772,	'W8822352',	'Widya Sucitra',	550000,	'2019-04-14 09:29:05',	'2019-04-14 09:43:39',	NULL,	NULL),
(7813141353,	'I3482261',	'Ikhsan Fauji',	10000000,	'2019-04-14 09:44:46',	'2019-04-14 09:44:46',	NULL,	NULL),
(8324456111,	'I3482261',	'Ikhsan Fauji',	200000,	'2019-04-14 08:51:39',	'2019-04-14 09:43:39',	NULL,	NULL);

DROP TABLE IF EXISTS `tb_customer`;
CREATE TABLE `tb_customer` (
  `customer_number` varchar(45) NOT NULL,
  `f_name` varchar(45) DEFAULT NULL,
  `l_name` varchar(45) DEFAULT NULL,
  `place_ob` varchar(45) DEFAULT NULL,
  `brith_date` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `job` varchar(50) DEFAULT NULL,
  `range_salary` varchar(45) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  `flag_delete` int(2) DEFAULT 0,
  PRIMARY KEY (`customer_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_customer` (`customer_number`, `f_name`, `l_name`, `place_ob`, `brith_date`, `gender`, `address`, `job`, `range_salary`, `phone_number`, `email`, `username`, `password`, `created_at`, `updated_at`, `flag_delete`) VALUES
('I3482261',	'Ikhsan',	'Fauji',	NULL,	'11-03-1996',	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	'ikhsan',	'234567',	'2019-04-14 08:36:23',	'2019-04-14 09:02:09',	NULL),
('W8822352',	'Widya',	'Sucitra',	NULL,	'14-04-1996',	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	'citra',	'123456',	'2019-04-14 09:27:01',	'2019-04-14 09:27:01',	NULL);

DROP TABLE IF EXISTS `tb_transaction`;
CREATE TABLE `tb_transaction` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_code` varchar(10) DEFAULT NULL,
  `acn_credit` bigint(20) DEFAULT NULL,
  `acn_debet` bigint(20) DEFAULT NULL,
  `transaction_date` timestamp NULL DEFAULT current_timestamp(),
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_transaction` (`transaction_id`, `transaction_code`, `acn_credit`, `acn_debet`, `transaction_date`, `amount`) VALUES
(73,	'T0001',	8324456111,	NULL,	'2019-04-14 08:51:39',	500000),
(74,	'T0002',	8324456111,	NULL,	'2019-04-14 09:14:20',	200000),
(75,	'T0004',	NULL,	8324456111,	'2019-04-14 09:17:56',	150000),
(76,	'T0001',	4536608772,	NULL,	'2019-04-14 09:29:05',	500000),
(79,	'T0003',	4536608772,	8324456111,	'2019-04-14 09:43:39',	50000),
(80,	'T0001',	7813141353,	NULL,	'2019-04-14 09:44:46',	10000000);

DROP TABLE IF EXISTS `tb_transaction_type`;
CREATE TABLE `tb_transaction_type` (
  `transaction_code` varchar(10) NOT NULL,
  `transaction_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`transaction_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_transaction_type` (`transaction_code`, `transaction_name`) VALUES
('T0001',	'Opening New Account'),
('T0002',	'Top Up'),
('T0003',	'Transfer'),
('T0004',	'Cash Withdrawal'),
('T0005',	'Payment');

DROP TABLE IF EXISTS `tb_wallet`;
CREATE TABLE `tb_wallet` (
  `wallet_id` int(11) NOT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `cash_tag` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(8) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  `flag_active` int(2) DEFAULT NULL,
  `flag_delete` int(2) DEFAULT 0,
  PRIMARY KEY (`wallet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_wallet` (`wallet_id`, `full_name`, `cash_tag`, `username`, `password`, `type`, `created_at`, `updated_at`, `flag_active`, `flag_delete`) VALUES
(38802,	'Ikhsan Fauji',	'ikhsan.fauji',	NULL,	NULL,	'Tabungan Belajar',	'2019-04-14 09:48:27',	'2019-04-14 09:48:27',	NULL,	NULL),
(59801,	'Ikhsan Fauji',	'ikhsanfauji',	NULL,	NULL,	'E-Banking',	'2019-04-14 09:08:35',	'2019-04-14 09:52:46',	NULL,	1);

DROP TABLE IF EXISTS `tb_wallet_account`;
CREATE TABLE `tb_wallet_account` (
  `wa_id` int(11) NOT NULL AUTO_INCREMENT,
  `wallet_id` int(11) DEFAULT NULL,
  `account_number` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`wa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_wallet_account` (`wa_id`, `wallet_id`, `account_number`) VALUES
(42,	78791,	8324456111),
(43,	59801,	8324456111),
(44,	59801,	7813141353),
(45,	38802,	7813141353);

DROP VIEW IF EXISTS `v_trx`;
CREATE TABLE `v_trx` (`transaction_id` int(11), `acn_credit` bigint(20), `acn_debet` bigint(20), `amount` double, `transaction_date` timestamp, `transaction_code` varchar(45), `customer_number` varchar(45));


DROP VIEW IF EXISTS `v_wallet_account`;
CREATE TABLE `v_wallet_account` (`wallet_id` int(11), `full_name` varchar(50), `cash_tag` varchar(45), `type` varchar(20), `created_at` timestamp, `account_number` bigint(20), `ballance` double, `customer_number` varchar(45));


DROP TABLE IF EXISTS `v_trx`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `v_trx` AS select `tt`.`transaction_id` AS `transaction_id`,`tt`.`acn_credit` AS `acn_credit`,`tt`.`acn_debet` AS `acn_debet`,`tt`.`amount` AS `amount`,`tt`.`transaction_date` AS `transaction_date`,`tr`.`transaction_name` AS `transaction_code`,`a`.`customer_number` AS `customer_number` from ((`tb_account` `a` join `tb_transaction` `tt`) join `tb_transaction_type` `tr`) where (`a`.`account_number` = `tt`.`acn_debet` or `a`.`account_number` = `tt`.`acn_credit`) and `tr`.`transaction_code` = `tt`.`transaction_code`;

DROP TABLE IF EXISTS `v_wallet_account`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `v_wallet_account` AS select `w`.`wallet_id` AS `wallet_id`,`w`.`full_name` AS `full_name`,`w`.`cash_tag` AS `cash_tag`,`w`.`type` AS `type`,`w`.`created_at` AS `created_at`,`a`.`account_number` AS `account_number`,`a`.`ballance` AS `ballance`,`a`.`customer_number` AS `customer_number` from ((`tb_wallet` `w` join `tb_wallet_account` `wa`) join `tb_account` `a`) where `w`.`wallet_id` = `wa`.`wallet_id` and `a`.`account_number` = `wa`.`account_number` and (`w`.`flag_delete` is null or `w`.`flag_delete` = 0);

-- 2019-04-14 10:22:17