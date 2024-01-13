-- 注意：该页面对应的前台目录为views/partner文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值
-- 主菜单
INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2024011302296010888', NULL, '成员管理', '/partner/partnerManagment', 'layouts/RouteView', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2024-01-13 14:29:17', NULL, NULL, 0);
-- 子菜单
INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2024011302296010170', '2024011302296010888', '成员列表', 'partner/partnerList', 'partner/PartnerList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2024-01-13 14:29:17', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024011302296010171', '2024011302296010170', '添加Partner', NULL, NULL, 0, NULL, NULL, 2, 'org.ixkit.anvilj.partner:aj_partner:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-01-13 14:29:17', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024011302296010172', '2024011302296010170', '编辑Partner', NULL, NULL, 0, NULL, NULL, 2, 'org.ixkit.anvilj.partner:aj_partner:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-01-13 14:29:17', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024011302296010173', '2024011302296010170', '删除Partner', NULL, NULL, 0, NULL, NULL, 2, 'org.ixkit.anvilj.partner:aj_partner:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-01-13 14:29:17', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024011302296010174', '2024011302296010170', '批量删除Partner', NULL, NULL, 0, NULL, NULL, 2, 'org.ixkit.anvilj.partner:aj_partner:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-01-13 14:29:17', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024011302296010175', '2024011302296010170', '导出excel_Partner', NULL, NULL, 0, NULL, NULL, 2, 'org.ixkit.anvilj.partner:aj_partner:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-01-13 14:29:17', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024011302296010176', '2024011302296010170', '导入excel_Partner', NULL, NULL, 0, NULL, NULL, 2, 'org.ixkit.anvilj.partner:aj_partner:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-01-13 14:29:17', NULL, NULL, 0, 0, '1', 0);