CREATE TABLE `bill_details` (
  `BILL_ID` int(11) NOT NULL,
  `BILL_DESCRIPTION` varchar(45) NOT NULL,
  `GROUP_ID` int(11) NOT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_ON` date NOT NULL,
  PRIMARY KEY (`BILL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bill_master` (
  `BILL_ID` int(11) NOT NULL,
  `GROUP_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `C_ID` int(10) NOT NULL,
  `ADDED_BY` int(11) NOT NULL,
  `CREATED_DATE` varchar(45) NOT NULL,
  `OWE_PAYED` int(11) NOT NULL,
  `OWE_AMOUNT` int(11) NOT NULL,
  `SETTLED` date NOT NULL,
  `ACTIVE` int(11) NOT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `friend_group` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(45) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `friendrequest` (
  `requester` varchar(20) NOT NULL,
  `requested` varchar(20) NOT NULL,
  PRIMARY KEY (`requester`,`requested`),
  KEY `requested` (`requested`),
  CONSTRAINT `friendrequest_ibfk_1` FOREIGN KEY (`requester`) REFERENCES `user` (`email`),
  CONSTRAINT `friendrequest_ibfk_2` FOREIGN KEY (`requested`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `friends` (
  `user1` varchar(20) NOT NULL,
  `user2` varchar(20) NOT NULL,
  PRIMARY KEY (`user1`,`user2`),
  KEY `user2` (`user2`),
  CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`user1`) REFERENCES `user` (`email`),
  CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`user2`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `friends_bill` (
  `bill_id` int(11) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `created_by_name` varchar(45) NOT NULL,
  `owed_by_name` varchar(45) NOT NULL,
  `bill_description` varchar(45) NOT NULL,
  `bill_active` int(11) NOT NULL,
  `bill_value` int(11) NOT NULL,
  `created_by_emailid` varchar(45) DEFAULT NULL,
  `owed_by_emailid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `group_desc` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(45) NOT NULL,
  `group_desc` varchar(45) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1117 DEFAULT CHARSET=utf8;

CREATE TABLE `group_members_lookup` (
  `c_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `group_member_email` varchar(70) NOT NULL,
  `group_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `key_look_up` (
  `c_id` int(11) NOT NULL,
  `friends_bill` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `temp_user_table` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `email` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
