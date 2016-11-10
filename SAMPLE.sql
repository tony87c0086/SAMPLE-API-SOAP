/*==================== DB Upgrade Script Version 1.0.1 ====================*/
/*==================== Default SAMPLE DB initialization ====================*/

/*==================== SCHEMA: SAMPLE SCHEMA - CREATESCRIPT ====================*/
CREATE DATABASE IF NOT EXISTS `sample`;
USE `sample`;

/*==================== TABLE: CATALOGUE_TYPE - CREATESCRIPT ====================*/
CREATE TABLE IF NOT EXISTS `catalogue` (
  `catalogue_id` INT(11) NOT NULL AUTO_INCREMENT,
  `catalogue_name` VARCHAR(100) DEFAULT NULL,
  `parent_id` INT(11) DEFAULT NULL,
  `type_id` INT(11) NOT NULL,
  `type_name` VARCHAR(100) DEFAULT NULL,
  `group_id` TINYINT(4) UNSIGNED DEFAULT NULL,
  `group_name` VARCHAR(100) DEFAULT NULL,
  `sort_order` TINYINT(4) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NOW(),
  `active` TINYINT(1) UNSIGNED DEFAULT '1',
  PRIMARY KEY (`catalogue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*==================== DUMPY TABLE DATA ====================*/
DROP procedure IF EXISTS `sampleschemachange`;

DELIMITER $$
CREATE PROCEDURE `sampleschemachange` ()
BEGIN

/*==================== ACCOUNT CATALOGUE ====================*/ 

INSERT INTO `catalogue` (`catalogue_id`,`catalogue_name`,`type_id`,`type_name`,`group_id`,`group_name`,`sort_order`,`active`)
VALUES (954598, 'Customer', 844750, 'Account', 1, 'Personal',1,1)
ON DUPLICATE KEY UPDATE
  `catalogue_name` = VALUES(`catalogue_name`),
  `type_id` = VALUES(`type_id`),
  `type_name` = VALUES(`type_name`),
  `group_id` = VALUES(`group_id`),
  `group_name` = VALUES(`group_name`),
  `sort_order` = VALUES(`sort_order`),
  `active` = VALUES(`active`);
  
INSERT INTO `catalogue` (`catalogue_id`,`catalogue_name`,`type_id`,`type_name`,`group_id`,`group_name`,`sort_order`,`active`)
VALUES (685485, 'Productor', 844750, 'Account', 2, 'Enterprise',1,1)
ON DUPLICATE KEY UPDATE
  `catalogue_name` = VALUES(`catalogue_name`),
  `type_id` = VALUES(`type_id`),
  `type_name` = VALUES(`type_name`),
  `group_id` = VALUES(`group_id`),
  `group_name` = VALUES(`group_name`),
  `sort_order` = VALUES(`sort_order`),
  `active` = VALUES(`active`);


/*==================== COMMUNICATION CATALOGUE ====================*/

INSERT INTO `catalogue` (`catalogue_id`,`catalogue_name`,`type_id`,`type_name`,`group_id`,`group_name`,`sort_order`,`active`)
VALUES (798898, 'Personal', 127371, 'Communication', 1, 'Private',1,1)
ON DUPLICATE KEY UPDATE
  `catalogue_name` = VALUES(`catalogue_name`),
  `type_id` = VALUES(`type_id`),
  `type_name` = VALUES(`type_name`),
  `group_id` = VALUES(`group_id`),
  `group_name` = VALUES(`group_name`),
  `sort_order` = VALUES(`sort_order`),
  `active` = VALUES(`active`);
  
INSERT INTO `catalogue` (`catalogue_id`,`catalogue_name`,`type_id`,`type_name`,`group_id`,`group_name`,`sort_order`,`active`)
VALUES (189639, 'Emergency', 127371, 'Communication', 1, 'Private',2,1)
ON DUPLICATE KEY UPDATE
  `catalogue_name` = VALUES(`catalogue_name`),
  `type_id` = VALUES(`type_id`),
  `type_name` = VALUES(`type_name`),
  `group_id` = VALUES(`group_id`),
  `group_name` = VALUES(`group_name`),
  `sort_order` = VALUES(`sort_order`),
  `active` = VALUES(`active`);
  
INSERT INTO `catalogue` (`catalogue_id`,`catalogue_name`,`type_id`,`type_name`,`group_id`,`group_name`,`sort_order`,`active`)
VALUES (213479, 'Office', 127371, 'Communication', 2, 'Public',1,1)
ON DUPLICATE KEY UPDATE
  `catalogue_name` = VALUES(`catalogue_name`),
  `type_id` = VALUES(`type_id`),
  `type_name` = VALUES(`type_name`),
  `group_id` = VALUES(`group_id`),
  `group_name` = VALUES(`group_name`),
  `sort_order` = VALUES(`sort_order`),
  `active` = VALUES(`active`);
  
INSERT INTO `catalogue` (`catalogue_id`,`catalogue_name`,`type_id`,`type_name`,`group_id`,`group_name`,`sort_order`,`active`)
VALUES (133844, 'Staff', 127371, 'Communication', 2, 'Public',2,1)
ON DUPLICATE KEY UPDATE
  `catalogue_name` = VALUES(`catalogue_name`),
  `type_id` = VALUES(`type_id`),
  `type_name` = VALUES(`type_name`),
  `group_id` = VALUES(`group_id`),
  `group_name` = VALUES(`group_name`),
  `sort_order` = VALUES(`sort_order`),
  `active` = VALUES(`active`);
  
  
/*==================== CONTACT CATALOGUE ====================*/
  
INSERT INTO `catalogue` (`catalogue_id`,`catalogue_name`,`type_id`,`type_name`,`group_id`,`group_name`,`sort_order`,`active`)
VALUES (536858, 'Personal', 171722, 'Contact', 1, 'Private',1,1)
ON DUPLICATE KEY UPDATE
  `catalogue_name` = VALUES(`catalogue_name`),
  `type_id` = VALUES(`type_id`),
  `type_name` = VALUES(`type_name`),
  `group_id` = VALUES(`group_id`),
  `group_name` = VALUES(`group_name`),
  `sort_order` = VALUES(`sort_order`),
  `active` = VALUES(`active`);
  
INSERT INTO `catalogue` (`catalogue_id`,`catalogue_name`,`type_id`,`type_name`,`group_id`,`group_name`,`sort_order`,`active`)
VALUES (174093, 'Emergency', 171722, 'Contact', 1, 'Private',2,1)
ON DUPLICATE KEY UPDATE
  `catalogue_name` = VALUES(`catalogue_name`),
  `type_id` = VALUES(`type_id`),
  `type_name` = VALUES(`type_name`),
  `group_id` = VALUES(`group_id`),
  `group_name` = VALUES(`group_name`),
  `sort_order` = VALUES(`sort_order`),
  `active` = VALUES(`active`);
  
INSERT INTO `catalogue` (`catalogue_id`,`catalogue_name`,`type_id`,`type_name`,`group_id`,`group_name`,`sort_order`,`active`)
VALUES (178966, 'Office', 171722, 'Contact', 2, 'Public',1,1)
ON DUPLICATE KEY UPDATE
  `catalogue_name` = VALUES(`catalogue_name`),
  `type_id` = VALUES(`type_id`),
  `type_name` = VALUES(`type_name`),
  `group_id` = VALUES(`group_id`),
  `group_name` = VALUES(`group_name`),
  `sort_order` = VALUES(`sort_order`),
  `active` = VALUES(`active`);
  
INSERT INTO `catalogue` (`catalogue_id`,`catalogue_name`,`type_id`,`type_name`,`group_id`,`group_name`,`sort_order`,`active`)
VALUES (463446, 'Staff', 171722, 'Contact', 2, 'Public',2,1)
ON DUPLICATE KEY UPDATE
  `catalogue_name` = VALUES(`catalogue_name`),
  `type_id` = VALUES(`type_id`),
  `type_name` = VALUES(`type_name`),
  `group_id` = VALUES(`group_id`),
  `group_name` = VALUES(`group_name`),
  `sort_order` = VALUES(`sort_order`),
  `active` = VALUES(`active`);
  
END
$$

DELIMITER ;

CALL sampleschemachange();

DROP procedure IF EXISTS `sampleschemachange`;
