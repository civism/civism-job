###
#### 这个是项目设计到的表结构，主要是quart的表
####最后一张表是调用记录表 每次的调用结果回放到该表下面
#### 注意  如果新建表失败，麻烦换一下新建表顺序

CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CALENDAR_NAME` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ENTRY_ID` varchar(95) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `INSTANCE_NAME` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `JOB_NAME` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `JOB_GROUP` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `JOB_NAME` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `JOB_GROUP` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IS_DURABLE` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `LOCK_NAME` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `INSTANCE_NAME` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `STR_PROP_1` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `STR_PROP_2` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `STR_PROP_3` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_NAME` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_GROUP` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `JOB_NAME` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `JOB_GROUP` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `tb_job_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(1) NOT NULL,
  `request_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `job_type` int(1) NOT NULL,
  `send_ip` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `accept_ip` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `invoke_type` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `share_id` int(11) DEFAULT NULL,
  `total_share` int(11) DEFAULT NULL,
  `result` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=414 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;