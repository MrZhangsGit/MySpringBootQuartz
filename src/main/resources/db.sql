DROP TABLE IF EXISTS `quartz_task_info`;
CREATE TABLE `quartz_task_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL COMMENT '版本号：需要乐观锁控制',
  `task_no` varchar(64) NOT NULL COMMENT '任务编号',
  `task_name` varchar(64) NOT NULL COMMENT '任务名称',
  `task_rule` varchar(64) NOT NULL COMMENT '定时规则表达式',
  `task_state` int(2) NOT NULL DEFAULT '1' COMMENT '冻结状态(0:冻结|1:解冻)',
  `frozen_time` datetime DEFAULT NULL COMMENT '冻结时间',
  `un_frozen_time` datetime DEFAULT NULL COMMENT '解冻时间',
  `status` int(2) DEFAULT '1' COMMENT '数据状态(0 删除|1 正常)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;