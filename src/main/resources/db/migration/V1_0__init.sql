/* Members */
CREATE TABLE IF NOT EXISTS `members` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `join_date` date NOT NULL,
  `unlock_text` varchar(95) DEFAULT NULL,
  `balance` int(11) NOT NULL DEFAULT '0',
  `credit_limit` int(11) NOT NULL DEFAULT '0',
  `member_status` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  `address_1` varchar(100) DEFAULT NULL,
  `address_2` varchar(100) DEFAULT NULL,
  `address_city` varchar(100) DEFAULT NULL,
  `address_postcode` varchar(100) DEFAULT NULL,
  `contact_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `status_updates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `old_status` int(11) NOT NULL,
  `new_status` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `access_log` (
  `access_id` int(11) NOT NULL AUTO_INCREMENT,
  `access_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `rfid_serial` varchar(50) DEFAULT NULL,
  `pin` varchar(50) DEFAULT NULL,
  `access_result` int(11) DEFAULT NULL COMMENT 'denied/granted',
  `member_id` int(11) DEFAULT NULL,
  `door_id` int(11) DEFAULT NULL,
  `denied_reason` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`access_id`),
  KEY `member_id` (`member_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_ref` varchar(18) NOT NULL,
  `natwest_ref` varchar(18) NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `payment_ref` (`payment_ref`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `pins` (
  `pin_id` int(11) NOT NULL AUTO_INCREMENT,
  `pin` varchar(12) DEFAULT NULL,
  `date_added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expiry` timestamp NULL DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pin_id`),
  UNIQUE KEY `pin` (`pin`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `rfid_tags` (
  `member_id` int(11) NOT NULL,
  `rfid_serial` varchar(50) NOT NULL DEFAULT '',
  `state` int(11) NOT NULL DEFAULT '0',
  `last_used` timestamp NULL DEFAULT NULL,
  `friendly_name` varchar(128) NULL DEFAULT NULL,
  PRIMARY KEY (`rfid_serial`,`state`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `state` (
  `state_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `value` varchar(250) NOT NULL,
  PRIMARY KEY (`state_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `members_auth` (
  `member_id` int(11) NOT NULL DEFAULT '0',
  `salt` varchar(16) DEFAULT NULL,
  `passwd` varchar(40) DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `transactions` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `transaction_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` int(11) DEFAULT NULL,
  `transaction_type` varchar(6) NOT NULL COMMENT 'VEND or MANUAL',
  `transaction_status` varchar(8) NOT NULL COMMENT 'PENDING, COMPLETE or ABORTED',
  `transaction_desc` varchar(512) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `recorded_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `invoices` (
  `invoice_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `invoice_from` date DEFAULT NULL,
  `invoice_to` date DEFAULT NULL,
  `invoice_generated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `invoice_status` varchar(16) DEFAULT NULL COMMENT 'GENERATING, GENERATED, FAILED',
  `invoice_amount` int(11) DEFAULT NULL,
  `email_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`invoice_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `hms_emails` (
  `hms_email_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `subject` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hms_email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `emails` (
  `email_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `email_to` varchar(200) DEFAULT NULL,
  `email_cc` varchar(200) DEFAULT NULL,
  `email_bcc` varchar(200) DEFAULT NULL,
  `email_subj` varchar(200) DEFAULT NULL,
  `email_body` text,
  `email_body_alt` text,
  `email_status` varchar(16) DEFAULT NULL COMMENT 'PENDING, SENT, FAILED, BOUNCED [, RECEIVED?]',
  `email_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email_link` int(11) DEFAULT NULL COMMENT 'for recieved emails: original email_id',
  PRIMARY KEY (`email_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

/* Member's Projects & Boxes */
CREATE TABLE IF NOT EXISTS `member_projects` (
  `member_project_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `project_name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `start_date` date NOT NULL,
  `complete_date` date DEFAULT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`member_project_id`)
)ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `member_boxes` (
`member_box_id` int(11) NOT NULL AUTO_INCREMENT,
`member_id` int(11) NOT NULL,
`brought_date` date NOT NULL,
`removed_date` date DEFAULT NULL,
`state` int(11) NOT NULL,
PRIMARY KEY (`member_box_id`)
)ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `label_templates` (
`template_name` varchar(200) NOT NULL,
`template` TEXT DEFAULT NULL,
PRIMARY KEY (`template_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/* Groups */
CREATE TABLE IF NOT EXISTS `grp` (
  `grp_id` int(11) NOT NULL AUTO_INCREMENT,
  `grp_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`grp_id`),
  UNIQUE KEY `grp_desc` (`grp_description`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `group_permissions` (
  `grp_id` int(11) NOT NULL,
  `permission_code` varchar(16) NOT NULL,
  PRIMARY KEY (`grp_id`,`permission_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `permissions` (
  `permission_code` varchar(16) NOT NULL,
  `permission_desc` varchar(200) NOT NULL,
  PRIMARY KEY (`permission_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `member_group` (
  `member_id` int(11) NOT NULL,
  `grp_id` int(11) NOT NULL,
  PRIMARY KEY (`member_id`,`grp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/* Tools */
CREATE TABLE IF NOT EXISTS `tl_tools` (
  `tool_id` int(11) NOT NULL AUTO_INCREMENT,
  `tool_address` varchar(255) DEFAULT NULL,
  `tool_name` varchar(20) DEFAULT NULL,
  `tool_status` varchar(20) DEFAULT NULL COMMENT 'IN_USE, FREE or DISABLED',
  `tool_restrictions` varchar(20) DEFAULT NULL COMMENT 'UNRESTRICTED or RESTRICTED',
  `tool_status_text` varchar(255) DEFAULT NULL COMMENT 'if tool_status=DISABLED, holds the reason why (free text)',
  `tool_pph` int(10) unsigned DEFAULT NULL COMMENT 'cost - pence per hour',
  `tool_booking_length` int(10) unsigned DEFAULT NULL COMMENT 'default booking length for this tool, minutes',
  `tool_length_max` int(10) unsigned DEFAULT NULL COMMENT 'maximum amount of time a booking can be made for, minutes',
  `tool_bookings_max` int(10) unsigned DEFAULT NULL COMMENT 'maximum number of bookings a user can have at any one time',
  `tool_calendar` varchar(255) DEFAULT NULL COMMENT 'id of google calendar',
  `tool_cal_poll_ival` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tool_id`),
  UNIQUE KEY `tool_name` (`tool_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `tl_google` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identity` varchar(255) DEFAULT NULL COMMENT 'google account username',
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `tl_google_notifications` (
  `channel_id` varchar(64) NOT NULL DEFAULT '',
  `tool_id` int(10) unsigned DEFAULT NULL,
  `channel_token` varchar(256) DEFAULT NULL,
  `resource_id` varchar(256) DEFAULT NULL,
  `channel_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `channel_expiration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`channel_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `tl_members_tools` (
  `member_tool_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `tool_id` int(11) DEFAULT NULL,
  `member_id_induct` int(11) DEFAULT NULL COMMENT 'member_id of inductor',
  `mt_date_inducted` datetime DEFAULT NULL,
  `mt_access_level` varchar(20) DEFAULT NULL COMMENT 'USER, INDUCTOR or MAINTAINER',
  PRIMARY KEY (`member_tool_id`),
  UNIQUE KEY `tool_id` (`tool_id`,`member_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `tl_tool_usages` (
  `usage_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `tool_id` int(11) DEFAULT NULL,
  `usage_start` datetime DEFAULT NULL COMMENT 'sign on time',
  `usage_duration` int(11) DEFAULT NULL COMMENT 'use duration, seconds',
  `usage_active_time` int(11) DEFAULT NULL COMMENT 'amount of time tool active for, where applicable (e.g. laser tube time)',
  `usage_status` varchar(20) DEFAULT NULL COMMENT 'IN_PROGRESS, COMPLETE or CHARGED',
  PRIMARY KEY (`usage_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

/* Member Voice */
CREATE TABLE IF NOT EXISTS `mv_ideas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idea` varchar(255) NOT NULL,
  `description` text,
  `votes` int(11) NOT NULL DEFAULT '0',
  `status_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `mv_votes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `idea_id` int(11) NOT NULL,
  `votes` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `mv_statuses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `mv_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idea_id` int(11) NOT NULL,
  `comment` text NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `mv_categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  `parent` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `mv_categories_ideas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idea_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

/* Google Group Stats */
CREATE TABLE IF NOT EXISTS `gg_addresses` (
  `ggaddresses_id` int(11) NOT NULL AUTO_INCREMENT,
  `ggaddress_email` varchar(200) DEFAULT NULL,
  `ggaddress_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ggaddresses_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `gg_email` (
  `ggemail_id` int(11) NOT NULL AUTO_INCREMENT,
  `ggemail_subj` varchar(200) DEFAULT NULL,
  `ggemail_body` varchar(10000) DEFAULT NULL,
  `ggemail_body_wc` varchar(10000) DEFAULT NULL,
  `ggemail_from` varchar(200) DEFAULT NULL,
  `ggemail_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ggemail_reply_to` varchar(200) DEFAULT NULL,
  `ggemail_msg_id` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ggemail_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `gg_summary` (
  `ggemail_id` int(11) NOT NULL,
  `ggaddresses_id` int(11) NOT NULL,
  `ggsum_auto_wc` int(11) DEFAULT NULL,
  `ggsum_manual_wc` int(11) DEFAULT NULL,
  `ggemail_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ggemail_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

/* Vending Machine */
CREATE TABLE IF NOT EXISTS `vmc_details` (
  `vmc_id` int(11) NOT NULL DEFAULT '0',
  `vmc_description` varchar(100) DEFAULT NULL,
  `vmc_type` varchar(10) DEFAULT NULL COMMENT 'VEND or NOTE',
  `vmc_connection` varchar(10) DEFAULT NULL COMMENT 'UDP or MQTT',
  `vmc_address` varchar(100) DEFAULT NULL COMMENT 'IP address:port or MQTT topic',
  PRIMARY KEY (`vmc_id`),
  UNIQUE KEY `vmc_ref_loc` (`vmc_connection`,`vmc_address`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `vmc_ref` (
  `vmc_ref_id` int(11) NOT NULL AUTO_INCREMENT,
  `vmc_id` int(11) DEFAULT NULL COMMENT 'vending machine id - always 1 for now',
  `loc_encoded` varchar(10) DEFAULT NULL,
  `loc_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`vmc_ref_id`),
  UNIQUE KEY `vmc_ref_loc` (`vmc_id`,`loc_encoded`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `vmc_state` (
  `vmc_ref_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`vmc_ref_id`),
  UNIQUE KEY `vmc_state_map` (`vmc_ref_id`,`product_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `vend_log` (
  `vend_tran_id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_id` int(11) DEFAULT NULL,
  `vmc_id` int(11) DEFAULT NULL,
  `rfid_serial` varchar(50) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `enq_datetime` timestamp NULL DEFAULT NULL,
  `req_datetime` timestamp NULL DEFAULT NULL,
  `success_datetime` timestamp NULL DEFAULT NULL,
  `cancelled_datetime` timestamp NULL DEFAULT NULL,
  `failed_datetime` timestamp NULL DEFAULT NULL,
  `amount_scaled` int(11) DEFAULT NULL,
  `position` varchar(10) DEFAULT NULL,
  `denied_reason` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`vend_tran_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `products` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `price` int(11) NOT NULL,
  `barcode` varchar(25) DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '0=no, 1=yes',
  `shortdesc` varchar(25) DEFAULT NULL,
  `longdesc` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_barcode` (`barcode`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `purchase_payment` (
  `transaction_id_purchase` int(11) NOT NULL DEFAULT '0',
  `transaction_id_payment` int(11) NOT NULL DEFAULT '0',
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`transaction_id_purchase`,`transaction_id_payment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/* Doors */
CREATE TABLE IF NOT EXISTS `doors` (
  `door_id` int(11) NOT NULL,
  `door_description` varchar(100) NOT NULL,
  `door_short_name` varchar(10) NOT NULL,
  `door_state` varchar(10) NOT NULL,
  `door_state_change` datetime NOT NULL,
  `permission_code` varchar(16) DEFAULT NULL,
  UNIQUE KEY `door_id` (`door_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `bells` (
  `bell_id` int(11) NOT NULL,
  `bell_description` varchar(100) NOT NULL,
  `bell_topic` varchar(100) NOT NULL,
  `bell_message` varchar(50) NOT NULL,
  `bell_enabled` tinyint(1) NOT NULL DEFAULT '1',
  UNIQUE KEY `bell_id` (`bell_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `door_bells` (
  `door_id` int(11) NOT NULL,
  `bell_id` int(11) NOT NULL,
  UNIQUE KEY `door_id` (`door_id`,`bell_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/* Instrumentation */
CREATE TABLE IF NOT EXISTS `temperature` (
  `name` varchar(100) DEFAULT NULL,
  `dallas_address` varchar(16) NOT NULL,
  `temperature` float DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`dallas_address`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `light_level` (
  `name` varchar(100) DEFAULT NULL,
  `sensor` varchar(30) NOT NULL,
  `reading` int(11) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sensor`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mac_address` varchar(100) DEFAULT NULL,
  `last_seen` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ignore_addr` tinyint(1) NOT NULL DEFAULT '0',
  `comments` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `addr` (`mac_address`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `service_status` (
  `service_name` varchar(256) NOT NULL,
  `status` int(11) NOT NULL,
  `status_str` varchar(1024) DEFAULT NULL,
  `query_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `reply_time` timestamp NULL DEFAULT NULL,
  `restart_time` timestamp NULL DEFAULT NULL,
  `description` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`service_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `events` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `event_type` varchar(25) DEFAULT NULL,
  `event_value` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

/* System */
CREATE TABLE IF NOT EXISTS `forgotpassword` (
  `member_id` int(11) NOT NULL,
  `request_guid` char(36) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expired` tinyint(1) NOT NULL DEFAULT '0',
  UNIQUE KEY `request_guid` (`request_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `hms_meta` (
  `name` varchar(200) NOT NULL,
  `value` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;