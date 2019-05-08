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
(4285522462,	'I6077221',	'Ikhsan Fauji',	500000,	'2019-04-26 19:01:59',	'2019-04-26 20:16:33',	NULL,	NULL),
(4536603761,	'I6077221',	'Ikhsan Fauji',	523000,	'2019-04-26 19:01:30',	'2019-04-26 20:16:33',	NULL,	NULL);

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
('I6077221',	'Ikhsan',	'Fauji',	'Cilacap',	'1996-04-11',	'Male',	'Kedungbenda',	'Pengusaha',	'5Jt - 10 Jt',	'0865437723',	'ikhsan@local.com',	'ikhsan',	'ikhsanfauji',	'2019-04-26 18:55:01',	'2019-04-26 18:59:52',	NULL);

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
(196,	'T0001',	4536603761,	NULL,	'2019-04-26 19:01:30',	500000),
(197,	'T0001',	4285522462,	NULL,	'2019-04-26 19:01:59',	600000),
(198,	'T0002',	4285522462,	NULL,	'2019-04-26 19:36:35',	50000),
(199,	'T0003',	NULL,	4285522462,	'2019-04-26 19:46:02',	50000),
(200,	'T0003',	NULL,	4285522462,	'2019-04-26 19:47:04',	10000),
(201,	'T0005',	4285522462,	4285522462,	'2019-04-26 19:59:14',	30000),
(202,	'T0005',	4285522462,	4285522462,	'2019-04-26 20:01:34',	3000),
(203,	'T0004',	4087179413,	4285522462,	'2019-04-26 20:15:02',	50000),
(204,	'T0004',	4536603761,	4285522462,	'2019-04-26 20:16:00',	20000),
(205,	'T0004',	4536603761,	4285522462,	'2019-04-26 20:16:33',	3000);

DROP TABLE IF EXISTS `tb_transaction_type`;
CREATE TABLE `tb_transaction_type` (
  `transaction_code` varchar(10) NOT NULL,
  `transaction_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`transaction_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_transaction_type` (`transaction_code`, `transaction_name`) VALUES
('T0001',	'Opening New Account'),
('T0002',	'Top Up'),
('T0003',	'Top Up By Account'),
('T0004',	'Transfer'),
('T0005',	'Transfer Wallet To Wallet'),
('T0006',	'Transfer Wallet To Account'),
('T0007',	'Cash Withdrawal'),
('T0008',	'Cash Withdrawal Account'),
('T0009',	'Payment');

DROP TABLE IF EXISTS `tb_wallet`;
CREATE TABLE `tb_wallet` (
  `wallet_id` int(11) NOT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `cash_tag` varchar(45) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  `flag_active` int(2) DEFAULT NULL,
  `flag_delete` int(2) DEFAULT 0,
  `active_ballance` bigint(20) DEFAULT 0,
  `place_ob` varchar(45) DEFAULT NULL,
  `birth_date` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `wallet_name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`wallet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_wallet` (`wallet_id`, `full_name`, `cash_tag`, `email`, `password`, `type`, `created_at`, `updated_at`, `flag_active`, `flag_delete`, `active_ballance`, `place_ob`, `birth_date`, `phone_number`, `wallet_name`, `username`) VALUES
(21611,	'Ikhsan Fauji',	'ikhsan.fauji',	'ikhsan@local.com',	'ikhsanfauji',	'E-Payment',	'2019-04-26 19:04:15',	'2019-04-26 20:11:01',	NULL,	NULL,	78212,	'Cilacap',	'1996-04-11',	'0865437723',	'Belanja',	NULL),
(73252,	'Ikhsan Fauji',	'v.ikhsanfauji',	'ikhsan@local.com',	'ikhsan123',	'E-Merchant',	'2019-04-26 19:19:08',	'2019-04-26 20:23:40',	NULL,	NULL,	0,	'Cilacap',	'1996-04-11',	'0865437723',	'V Card',	NULL);

DROP TABLE IF EXISTS `tb_wallet_account`;
CREATE TABLE `tb_wallet_account` (
  `wa_id` int(11) NOT NULL AUTO_INCREMENT,
  `wallet_id` int(11) DEFAULT NULL,
  `account_number` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`wa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_wallet_account` (`wa_id`, `wallet_id`, `account_number`) VALUES
(69,	21611,	4285522462),
(70,	73252,	4536603761);

DROP VIEW IF EXISTS `v_trx`;
CREATE TABLE `v_trx` (`transaction_id` int(11), `acn_credit` bigint(20), `acn_debet` bigint(20), `amount` double, `transaction_date` timestamp, `transaction_code` varchar(45), `customer_number` varchar(45));


DROP VIEW IF EXISTS `v_wallet_account`;
CREATE TABLE `v_wallet_account` (`wallet_id` int(11), `full_name` varchar(50), `place_ob` varchar(45), `birth_date` varchar(45), `phone_number` varchar(45), `cash_tag` varchar(45), `wallet_name` varchar(45), `type` varchar(20), `created_at` timestamp, `account_number` bigint(20), `ballance` bigint(20), `customer_number` varchar(45));


DROP TABLE IF EXISTS `v_trx`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `v_trx` AS select `tt`.`transaction_id` AS `transaction_id`,`tt`.`acn_credit` AS `acn_credit`,`tt`.`acn_debet` AS `acn_debet`,`tt`.`amount` AS `amount`,`tt`.`transaction_date` AS `transaction_date`,`tr`.`transaction_name` AS `transaction_code`,`a`.`customer_number` AS `customer_number` from ((`tb_account` `a` join `tb_transaction` `tt`) join `tb_transaction_type` `tr`) where (`a`.`account_number` = `tt`.`acn_debet` or `a`.`account_number` = `tt`.`acn_credit`) and `tr`.`transaction_code` = `tt`.`transaction_code`;

DROP TABLE IF EXISTS `v_wallet_account`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `v_wallet_account` AS select `w`.`wallet_id` AS `wallet_id`,`w`.`full_name` AS `full_name`,`w`.`place_ob` AS `place_ob`,`w`.`birth_date` AS `birth_date`,`w`.`phone_number` AS `phone_number`,`w`.`cash_tag` AS `cash_tag`,`w`.`wallet_name` AS `wallet_name`,`w`.`type` AS `type`,`w`.`created_at` AS `created_at`,`a`.`account_number` AS `account_number`,`w`.`active_ballance` AS `ballance`,`a`.`customer_number` AS `customer_number` from ((`tb_wallet` `w` join `tb_wallet_account` `wa`) join `tb_account` `a`) where `w`.`wallet_id` = `wa`.`wallet_id` and `a`.`account_number` = `wa`.`account_number` and (`w`.`flag_delete` is null or `w`.`flag_delete` = 0);

-- 2019-04-26 20:44:25