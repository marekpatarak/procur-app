CREATE TABLE IF NOT EXISTS `notice` (
    `guid` int(11) NOT NULL,
    `description` varchar(255) DEFAULT NULL,
    `link` varchar(255) DEFAULT NULL,
    `pub_date` varchar(255) DEFAULT NULL,
    `title` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`guid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `notice_type` (
    `type` varchar(255) NOT NULL,
    PRIMARY KEY (`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `business_entity` (
    `dtype` varchar(31) NOT NULL,
    `ico` int(11) NOT NULL,
    `email` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    `street` varchar(255) DEFAULT NULL,
    `zip_code` varchar(255) DEFAULT NULL,
    `public_procurer_type` int(11) DEFAULT NULL,
    PRIMARY KEY (`ico`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
