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
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];
