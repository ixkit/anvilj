import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '名称',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: 'EMAIL',
    align: "center",
    dataIndex: 'email'
  },
  {
    title: 'phone',
    align: "center",
    dataIndex: 'phone'
  },
  {
    title: '昵称',
    align: "center",
    dataIndex: 'nickName'
  },
  {
    title: '实名',
    align: "center",
    dataIndex: 'legalName'
  },
  {
    title: '备注',
    align: "center",
    dataIndex: 'remark'
  },
  {
    title: '状态',
    align: "center",
    dataIndex: 'status'
  },
  {
    title: '扩展数据',
    align: "center",
    dataIndex: 'extraData'
  },
  {
    title: '租户id',
    align: "center",
    dataIndex: 'tenantId'
  },
  {
    title: '乐观锁',
    align: "center",
    dataIndex: 'rawVer'
  },
  {
    title: '关联 SysUserId',
    align: "center",
    dataIndex: 'refSysUserId'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '名称',
    field: 'name',
    component: 'Input',
  },
  {
    label: 'EMAIL',
    field: 'email',
    component: 'Input',
  },
  {
    label: 'phone',
    field: 'phone',
    component: 'Input',
  },
  {
    label: '昵称',
    field: 'nickName',
    component: 'Input',
  },
  {
    label: '实名',
    field: 'legalName',
    component: 'Input',
  },
  {
    label: '备注',
    field: 'remark',
    component: 'Input',
  },
  {
    label: '状态',
    field: 'status',
    component: 'Input',
  },
  {
    label: '扩展数据',
    field: 'extraData',
    component: 'Input',
  },
  {
    label: '租户id',
    field: 'tenantId',
    component: 'InputNumber',
  },
  {
    label: '乐观锁',
    field: 'rawVer',
    component: 'InputNumber',
  },
  {
    label: '关联 SysUserId',
    field: 'refSysUserId',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];

// 高级查询数据
export const superQuerySchema = {
  name: {title: '名称',order: 0,view: 'text', type: 'string',},
  email: {title: 'EMAIL',order: 1,view: 'text', type: 'string',},
  phone: {title: 'phone',order: 2,view: 'text', type: 'string',},
  nickName: {title: '昵称',order: 3,view: 'text', type: 'string',},
  legalName: {title: '实名',order: 4,view: 'text', type: 'string',},
  remark: {title: '备注',order: 5,view: 'text', type: 'string',},
  status: {title: '状态',order: 6,view: 'text', type: 'string',},
  extraData: {title: '扩展数据',order: 7,view: 'text', type: 'string',},
  tenantId: {title: '租户id',order: 8,view: 'number', type: 'number',},
  rawVer: {title: '乐观锁',order: 9,view: 'number', type: 'number',},
  refSysUserId: {title: '关联 SysUserId',order: 10,view: 'text', type: 'string',},
};
