-- 注意：该页面对应的前台目录为views/org.ixkit.anvil.member文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2022080111416840290', NULL, 'member', '/org.ixkit.anvil.member/anvilMemberList', 'org.ixkit.anvil.member/AnvilMemberList', NULL, NULL, 0, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2022-08-01 11:41:29', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022080111416850291', '2022080111416840290', '添加member', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:anvil_member:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-08-01 11:41:29', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022080111416860292', '2022080111416840290', '编辑member', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:anvil_member:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-08-01 11:41:29', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022080111416860293', '2022080111416840290', '删除member', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:anvil_member:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-08-01 11:41:29', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022080111416860294', '2022080111416840290', '批量删除member', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:anvil_member:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-08-01 11:41:29', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022080111416860295', '2022080111416840290', '导出excel_member', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:anvil_member:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-08-01 11:41:29', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2022080111416910296', '2022080111416840290', '导入excel_member', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:anvil_member:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2022-08-01 11:41:29', NULL, NULL, 0, 0, '1', 0);