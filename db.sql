-- phpMyAdmin SQL Dump
-- version 2.11.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 01, 2012 at 05:55 AM
-- Server version: 5.0.45
-- PHP Version: 5.2.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `smsmeback`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `id` int(10) NOT NULL auto_increment,
  `admin` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `roleid` int(2) default '0',
  `status` int(1) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admins`
--


-- --------------------------------------------------------

--
-- Table structure for table `admin_user`
--

CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` varchar(50) collate utf8_unicode_ci NOT NULL,
  `pass` varchar(20) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin_user`
--


-- --------------------------------------------------------

--
-- Table structure for table `appconfig`
--

CREATE TABLE `appconfig` (
  `id` int(11) NOT NULL auto_increment,
  `setting` text character set utf8 collate utf8_unicode_ci NOT NULL,
  `value` text character set utf8 collate utf8_unicode_ci,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `appconfig`
--


-- --------------------------------------------------------

--
-- Table structure for table `bulk_sms_csvmsg`
--

CREATE TABLE `bulk_sms_csvmsg` (
  `id` int(11) NOT NULL auto_increment,
  `number` varchar(20) NOT NULL,
  `message` text NOT NULL,
  `user` varchar(20) NOT NULL,
  `status` int(10) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `bulk_sms_csvmsg`
--

INSERT INTO `bulk_sms_csvmsg` (`id`, `number`, `message`, `user`, `status`) VALUES
(1, '8801717272862', 'hello', 'Shopon', 0),
(2, '8801714369600', 'test message', 'Shopon', 0),
(6, '8801714369600', 'test message', 'Shopon', 0),
(5, '8801717272862', 'hello', 'Shopon', 0),
(7, '8801717272862', 'hello', 'Shopon', 1),
(8, '8801714369600', 'test message', 'Shopon', 1);

-- --------------------------------------------------------

--
-- Table structure for table `del_sms_req`
--

CREATE TABLE `del_sms_req` (
  `id` int(11) NOT NULL auto_increment,
  `sender` varchar(20) NOT NULL,
  `receiver` varchar(20) NOT NULL,
  `amount` varchar(11) NOT NULL,
  `sms` varchar(160) default NULL,
  `lock` int(11) NOT NULL default '0',
  `send` int(1) NOT NULL default '0',
  `success` int(1) NOT NULL default '0',
  `reqlogtime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `sendlogtime` timestamp NULL default NULL,
  `successlogtime` timestamp NULL default NULL,
  `transactionid` varchar(200) default NULL,
  `newentry` int(1) NOT NULL default '0',
  `updated` int(1) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `del_sms_req`
--


-- --------------------------------------------------------

--
-- Table structure for table `del_user`
--

CREATE TABLE `del_user` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `user_id` varchar(45) NOT NULL default '',
  `name` varchar(45) NOT NULL default '',
  `phonenumber` varchar(20) default NULL,
  `password` varchar(45) NOT NULL default '',
  `user_type` int(10) unsigned NOT NULL default '0',
  `parent` varchar(45) NOT NULL default '0',
  `status` tinyint(1) NOT NULL default '0',
  `last_login` timestamp NULL default NULL,
  `balance` varchar(20) NOT NULL default '0',
  `email` varchar(200) default NULL,
  `creationdate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `del_user`
--


-- --------------------------------------------------------

--
-- Table structure for table `group_sms`
--

CREATE TABLE `group_sms` (
  `id` int(11) NOT NULL auto_increment,
  `time` varchar(20) collate latin1_general_ci NOT NULL,
  `group_id` varchar(20) collate latin1_general_ci NOT NULL,
  `sender` varchar(20) collate latin1_general_ci NOT NULL,
  `message` varchar(20) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `group_sms`
--

INSERT INTO `group_sms` (`id`, `time`, `group_id`, `sender`, `message`) VALUES
(1, '1325654789', '9', 'Shopon', 'SMS me back test');

-- --------------------------------------------------------

--
-- Table structure for table `int_country_codes`
--

CREATE TABLE `int_country_codes` (
  `Id` int(11) NOT NULL auto_increment,
  `country_name` varchar(200) default NULL,
  `iso_code` varchar(100) default NULL,
  `country_code` varchar(10) default NULL,
  `tariff` float(5,2) default '3.00',
  `active` int(11) NOT NULL default '0',
  PRIMARY KEY  (`Id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=212 ;

--
-- Dumping data for table `int_country_codes`
--


-- --------------------------------------------------------

--
-- Table structure for table `keyword`
--

CREATE TABLE `keyword` (
  `id` int(11) NOT NULL auto_increment,
  `keyword` varchar(50) NOT NULL,
  `discrip` varchar(155) NOT NULL,
  `status` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `keyword` (`keyword`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `keyword`
--

INSERT INTO `keyword` (`id`, `keyword`, `discrip`, `status`) VALUES
(2, 'SNT', 'We are providing: 1.Ring Slab 2. Slab 3.Sanitary Conversion - for all. for more info go to UP Complex. Thanks.', 1),
(3, 'DEC', 'Bijoy masher suvescha...go to UP complex.', 1),
(4, 'RFL', 'Prime Minister give 10 Lac Taka for ''aila'' victim.', 1),
(5, 'DOS', 'Polio vaccine for free from UP shasto complex.', 1),
(6, 'AGR', 'Providing seed, training, fartilizer from District agri office from 12.12.12', 1),
(7, 'AGR TR', ' We provide Agriculteral trainar from our UP Complex', 1),
(8, 'LGSP', 'We already offered LGSP allocation for your UP.Did you recived?', 1),
(9, 'UISC ', 'Did you send Resolutions to upozila chairman & UNO ?', 1),
(10, 'CC ', 'We already send 22 medicine items.Did u open it?', 1),
(11, 'WM', 'Did you Participate/orgnize ward meeting(s) at your Ward/union?', 1),
(12, 'UDDC', 'Did you participate/orgnize last UDDC metting ?', 1),
(13, 'OB', 'Did you participate/orgnize Open Budget meeting?', 1),
(14, 'LOAN', 'Did you get loan informention from UP Complex?', 1),
(15, 'MC ', 'Did you get Micro Credit information from UP or NGOs?', 1),
(16, 'INFO ', 'Get your any information from your UP or UISC', 1),
(17, 'STT', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pull_sms`
--

CREATE TABLE `pull_sms` (
  `id` int(11) NOT NULL auto_increment,
  `mobileno` varchar(20) collate latin1_general_ci NOT NULL,
  `keyword` varchar(160) collate latin1_general_ci NOT NULL,
  `time` varchar(20) collate latin1_general_ci NOT NULL,
  `status` varchar(20) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `pull_sms`
--


-- --------------------------------------------------------

--
-- Table structure for table `sms`
--

CREATE TABLE `sms` (
  `id` int(11) NOT NULL auto_increment,
  `time` varchar(20) collate latin1_general_ci NOT NULL,
  `number` varchar(20) collate latin1_general_ci NOT NULL,
  `sender` varchar(20) collate latin1_general_ci NOT NULL,
  `message` varchar(200) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `sms`
--

INSERT INTO `sms` (`id`, `time`, `number`, `sender`, `message`) VALUES
(5, '1354312938', '8801717272862', 'Shopon', 'hello'),
(4, '1354312935', '8801714369600', 'Shopon', 'test message');

-- --------------------------------------------------------

--
-- Table structure for table `sms_contacts`
--

CREATE TABLE `sms_contacts` (
  `Id` int(11) NOT NULL auto_increment,
  `user_id` varchar(100) default NULL,
  `group_name` varchar(200) default NULL,
  `name` varchar(200) default NULL,
  `phonenumber` varchar(30) default NULL,
  `address` varchar(200) default NULL,
  `country_name` varchar(200) default NULL,
  `logtime` timestamp NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=157 ;

--
-- Dumping data for table `sms_contacts`
--


-- --------------------------------------------------------

--
-- Table structure for table `sms_groups`
--

CREATE TABLE `sms_groups` (
  `Id` int(11) NOT NULL auto_increment,
  `user_id` varchar(200) default NULL,
  `group_name` varchar(200) default NULL,
  `group_type` varchar(100) default NULL,
  `description` varchar(200) default NULL,
  `logtime` timestamp NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `sms_groups`
--


-- --------------------------------------------------------

--
-- Table structure for table `sms_req`
--

CREATE TABLE `sms_req` (
  `id` int(11) NOT NULL auto_increment,
  `sender_id` varchar(50) NOT NULL,
  `sender` varchar(20) NOT NULL,
  `receiver` varchar(20) NOT NULL,
  `amount` float NOT NULL,
  `sms` varchar(180) character set utf8 default NULL,
  `lock` int(11) NOT NULL default '0',
  `send` int(1) NOT NULL default '0',
  `success` int(1) NOT NULL default '0',
  `reqlogtime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `sendlogtime` timestamp NULL default NULL,
  `successlogtime` timestamp NULL default NULL,
  `api` varchar(200) default NULL,
  `newentry` int(1) NOT NULL default '0',
  `updated` int(1) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1455 ;

--
-- Dumping data for table `sms_req`
--


-- --------------------------------------------------------

--
-- Table structure for table `sms_time`
--

CREATE TABLE `sms_time` (
  `id` int(11) NOT NULL auto_increment,
  `sender_id` varchar(20) collate utf8_unicode_ci NOT NULL,
  `recever` varchar(13) collate utf8_unicode_ci NOT NULL,
  `user_id` varchar(20) collate utf8_unicode_ci NOT NULL,
  `sms` varchar(200) collate utf8_unicode_ci NOT NULL,
  `insert_time` varchar(60) collate utf8_unicode_ci NOT NULL,
  `d_time` varchar(100) collate utf8_unicode_ci NOT NULL,
  `req_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `status` int(1) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=23 ;

--
-- Dumping data for table `sms_time`
--


-- --------------------------------------------------------

--
-- Table structure for table `survey`
--

CREATE TABLE `survey` (
  `id` int(11) NOT NULL auto_increment,
  `question` varchar(160) collate latin1_general_ci NOT NULL,
  `time` varchar(20) collate latin1_general_ci NOT NULL,
  `group_id` varchar(20) collate latin1_general_ci NOT NULL,
  `totalsent` varchar(10) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=4 ;

--
-- Dumping data for table `survey`
--

INSERT INTO `survey` (`id`, `question`, `time`, `group_id`, `totalsent`) VALUES
(1, 'Do you take your vaxin ?', '1354317086', '1', '10'),
(2, 'Do+you+take+your+vaxin+%3F', '1354317155', '1', ''),
(3, 'Hello', '1354326770', '9', '2');

-- --------------------------------------------------------

--
-- Table structure for table `survey_ans`
--

CREATE TABLE `survey_ans` (
  `id` int(11) NOT NULL auto_increment,
  `number` varchar(20) collate latin1_general_ci NOT NULL,
  `surveyid` varchar(20) collate latin1_general_ci NOT NULL,
  `ans` varchar(160) collate latin1_general_ci NOT NULL,
  `time` varchar(20) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `survey_ans`
--

INSERT INTO `survey_ans` (`id`, `number`, `surveyid`, `ans`, `time`) VALUES
(1, '01714369600', '1', 'Yes', '132548789'),
(2, '01714369600', '1', 'Yes', '1325684654'),
(3, '01714369600', '1', 'no', '13564654'),
(4, '017168131591', '1', 'YES', '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `user_id` varchar(45) NOT NULL default '',
  `name` varchar(45) NOT NULL default '',
  `phonenumber` varchar(20) default NULL,
  `password` varchar(45) NOT NULL default '',
  `user_type` int(10) unsigned NOT NULL default '0',
  `email` varchar(200) default NULL,
  `nationalid` decimal(20,0) NOT NULL,
  `deg` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `nationalid` (`nationalid`),
  UNIQUE KEY `phonenumber` (`phonenumber`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=137 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `user_id`, `name`, `phonenumber`, `password`, `user_type`, `email`, `nationalid`, `deg`) VALUES
(134, '', 'Md Mazaharul', '01732799579', '12345', 1, 'jewelhuq@gmail.com', 1234, 'Prime Orffice'),
(135, '', 'Shopon', '01714369600', '123456', 9, 'shopno@ymail.com', 1475646, 'Chairman'),
(136, '', 'Ashis', '01717272862', '12345', 9, '', 9874651654, 'Chairman');

-- --------------------------------------------------------

--
-- Table structure for table `user_login`
--

CREATE TABLE `user_login` (
  `id` varchar(60) collate latin1_general_ci NOT NULL,
  `userid` varchar(10) collate latin1_general_ci NOT NULL,
  `ip` varchar(20) collate latin1_general_ci NOT NULL,
  `tm` datetime NOT NULL default '0000-00-00 00:00:00',
  `status` char(3) collate latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `user_login`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_type`
--

CREATE TABLE `user_type` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `type` varchar(45) NOT NULL default '',
  `type_order` int(10) unsigned NOT NULL default '0',
  `type_level` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `user_type`
--

INSERT INTO `user_type` (`id`, `type`, `type_order`, `type_level`) VALUES
(1, 'admin', 0, 0),
(5, 'Dist Comissioner', 0, 0),
(4, 'Division', 0, 0),
(6, 'UNO', 0, 0),
(7, 'UP Complex', 0, 0),
(8, 'WARD', 0, 0),
(9, 'user', 0, 0);
