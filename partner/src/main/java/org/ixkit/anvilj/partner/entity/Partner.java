package org.ixkit.anvilj.partner.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.ixkit.anvilj.partner.model.Account;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Description: 基础资源 成员表
 * @Author: ixkit
 * @Date:   2024-01-02
 * @Version: V1.0
 */
@Data
@TableName("aj_partner")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="aj_partner对象", description="基础资源 成员表")
public class Partner implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private java.lang.String id;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String name;


    @Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "phone")
    private java.lang.String phone;


    @Excel(name = "EMAIL", width = 15)
    @ApiModelProperty(value = "EMAIL")
    private java.lang.String email;

    @Excel(name = "昵称", width = 15)
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @Excel(name = "实名", width = 15)
    @ApiModelProperty(value = "实名")
    private String legalName;

//    @ApiModelProperty(value = "password")
//    private String password;
//
//    @ApiModelProperty(value = "salt")
//    private String salt;

    @ApiModelProperty(value = "refSysUserId")
    private String refSysUserId;

	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private java.lang.String status;
	/**扩展数据*/
	@Excel(name = "扩展数据", width = 15)
    @ApiModelProperty(value = "扩展数据")
    private java.lang.String extraData;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
    private java.lang.String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private java.util.Date updateTime;
	/**所属部门编码*/
    @ApiModelProperty(value = "所属部门编码")
    private java.lang.String sysOrgCode;
	/**租户id*/
	@Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private java.lang.Integer tenantId;
	/**乐观锁*/
	@Excel(name = "乐观锁", width = 15)
    @ApiModelProperty(value = "乐观锁")
    private java.lang.Integer rawVer;

    @Transient
    @TableField(exist = false)
    private Account account;
}
