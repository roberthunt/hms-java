INSERT INTO `grp` (`grp_id`, `grp_description`) VALUES
(1, 'Full Access'),
(2, 'Current members'),
(3, 'Snackspace admin'),
(4, 'Gatekeeper admin'),
(5, 'Member Admin Team'),
(6, 'Young Adult Hackers'),
(7, 'Membership Team');

INSERT INTO `group_permissions` (`grp_id`, `permission_code`) VALUES
(1, 'ADD_GROUP       '),
(1, 'ADD_GRP_MEMBER  '),
(1, 'ADD_UPD_PRODUCT '),
(1, 'AMEND_PINS      '),
(1, 'CHG_GRP_PERM    '),
(1, 'DEL_GROUP       '),
(1, 'REC_TRAN        '),
(1, 'REC_TRAN_OWN    '),
(1, 'REM_GRP_MEMBER  '),
(1, 'SET_CREDIT_LIMIT'),
(1, 'SET_PASSWORD    '),
(1, 'UPD_VEND_CONFIG '),
(1, 'VIEW_ACCESS_MEM '),
(1, 'VIEW_BALANCES   '),
(1, 'VIEW_GROUPS     '),
(1, 'VIEW_GRP_MEMBERS'),
(1, 'VIEW_GRP_PERMIS '),
(1, 'VIEW_MEMBERS    '),
(1, 'VIEW_MEMBER_LIST'),
(1, 'VIEW_MEMBER_PINS'),
(1, 'VIEW_MEMBER_RFID'),
(1, 'VIEW_OWN_TRANS  '),
(1, 'VIEW_PRD_DETAIL '),
(1, 'VIEW_PRODUCTS   '),
(1, 'VIEW_SALES      '),
(1, 'VIEW_TRANS      '),
(1, 'VIEW_VEND_CONFIG'),
(1, 'VIEW_VEND_LOG   '),
(1, 'WEB_LOGON       '),
(2, 'REC_TRAN_OWN    '),
(2, 'VIEW_OWN_TRANS  '),
(2, 'VIEW_PRD_DETAIL '),
(2, 'VIEW_PRODUCTS   '),
(2, 'VIEW_VEND_CONFIG'),
(2, 'WEB_LOGON       '),
(3, 'ADD_UPD_PRODUCT '),
(3, 'REC_TRAN        '),
(3, 'REC_TRAN_OWN    '),
(3, 'SET_CREDIT_LIMIT'),
(3, 'UPD_VEND_CONFIG '),
(3, 'VIEW_BALANCES   '),
(3, 'VIEW_OWN_TRANS  '),
(3, 'VIEW_PRD_DETAIL '),
(3, 'VIEW_PRODUCTS   '),
(3, 'VIEW_SALES      '),
(3, 'VIEW_TRANS      '),
(3, 'VIEW_VEND_CONFIG'),
(3, 'VIEW_VEND_LOG   '),
(3, 'WEB_LOGON       '),
(4, 'ADD_MEMBER      '),
(4, 'AMEND_PINS      '),
(4, 'VIEW_ACCESS_MEM '),
(4, 'VIEW_MEMBERS    '),
(4, 'VIEW_MEMBER_LIST'),
(4, 'VIEW_MEMBER_PINS'),
(4, 'VIEW_MEMBER_RFID');

INSERT INTO `permissions` (`permission_code`, `permission_desc`) VALUES
('ADD_GROUP       ', 'Add group'),
('ADD_GRP_MEMBER  ', 'Add member to group'),
('ADD_UPD_PRODUCT ', 'Add / update product'),
('AMEND_PINS      ', 'Add / Cancel PINs'),
('CHG_GRP_PERM    ', 'Change/toggle state of group permissions'),
('DEL_GROUP       ', 'Delete group'),
('REC_TRAN        ', 'Record transaction (against any member)'),
('REC_TRAN_OWN    ', 'Record transaction (against self)'),
('REM_GRP_MEMBER  ', 'Remove member from group'),
('SET_CREDIT_LIMIT', 'Set member credit limit'),
('SET_PASSWORD    ', 'Set any members password'),
('UPD_VEND_CONFIG ', 'Update vending machine config'),
('VIEW_ACCESS_MEM ', 'View Access > Members'),
('VIEW_BALANCES   ', 'View member balances / credit limit'),
('VIEW_GROUPS     ', 'View list of access groups'),
('VIEW_GRP_MEMBERS', 'View group members'),
('VIEW_GRP_PERMIS ', 'View group permissions'),
('VIEW_MEMBERS    ', 'View members list (add member to group listbox - handle+id only)'),
('VIEW_MEMBER_LIST', 'View full members list'),
('VIEW_MEMBER_PINS', 'View entry PINs'),
('VIEW_MEMBER_RFID', 'View registered RFID card details'),
('VIEW_OWN_TRANS  ', 'View own transactions'),
('VIEW_PRD_DETAIL ', 'View product details'),
('VIEW_PRODUCTS   ', 'View products'),
('VIEW_SALES      ', 'View sales list of a product (inc. handle of purchaser)'),
('VIEW_TRANS      ', 'View member transactions'),
('VIEW_VEND_CONFIG', 'View vending machine setup (product in each location)'),
('VIEW_VEND_LOG   ', 'View vending machine log'),
('WEB_LOGON       ', 'Allow logon to nh-web'),
('WIFI_ACCESS     ', 'Connect to Spacenet'),
('WIKI_ACCESS     ', 'Login to Wiki');

INSERT INTO `status` (`status_id`, `title`) VALUES
(1, 'Prospective Member'),
(2, 'Waiting for contact details'),
(3, 'Waiting for Membership Admin to approve contact details'),
(4, 'Waiting for standing order payment'),
(5, 'Current Member'),
(6, 'Ex Member');

INSERT INTO `label_templates` (`template_name`, `template`) VALUES
('member_box', 'N\r\nq792\r\nA40,5,0,4,3,3,N,"MEMBERS BOX"\r\n\r\n;General info\r\nA10,90,0,4,1,1,N,"Member Name:"\r\nA10,130,0,4,2,2,N,":memberName"\r\nA10,230,0,4,1,1,N,"Member Username:"\r\nA10,270,0,4,2,2,N,":username"\r\n\r\n;qrcode and project Id\r\nb10,370,Q,s6,":qrURL"\r\nA220,370,0,4,1,1,N,"Box Id:"\r\nA:idOffset,455,0,4,2,2,N,":memberBoxId"\r\n\r\nP1\r\n'),
('member_project', 'N\r\nq792\r\nA40,5,0,4,3,3,N,"DO NOT HACK"\r\n\r\n;General info\r\nA10,90,0,4,1,1,N,"Project name:"\r\nA10,130,0,4,1,1,N,":projectName"\r\nA10,170,0,4,1,1,N,"Member Name:"\r\nA10,210,0,4,1,1,N,":memberName"\r\nA10,250,0,4,1,1,N,"Member Username:"\r\nA10,290,0,4,1,1,N,":username"\r\nA10,330,0,4,1,1,N,"Start date: :startDate"\r\n\r\n;Worked on box\r\nLO600,5,176,4\r\nLO600,45,176,2\r\nLO600,5,4,563\r\nLO776,5,4,563\r\nLO600,568,176,4\r\nA610,15,0,4,1,1,N,"Worked on"\r\nA610,55,0,3,1,1,N,":lastDate"\r\n\r\n;qrcode and project Id\r\nb10,370,Q,s6,":qrURL"\r\nA220,370,0,4,1,1,N,"Project Id:"\r\nA:idOffset,455,0,4,2,2,N,":memberProjectId"\r\n\r\nP1\r\n');

INSERT INTO `hms_meta` (`name`, `value`) VALUES
('member_box_cost', '-500'),
('member_box_individual_limit', '3'),
('member_box_limit', '129');

INSERT INTO `mv_statuses` (`id`, `status`) VALUES
(1, 'Active'),
(2, 'Planned'),
(3, 'Complete'),
(4, 'Cancelled');