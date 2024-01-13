package org.ixkit.anvilj.partner.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.ixkit.anvilj.core.XObject;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/*
    Account => Partner --> SysUser
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="成员账号", description="基础资源 成员账号")
public class Account extends XObject implements Serializable,IAccount {
    private static final long serialVersionUID = 1L;


	/**名称*/
    @ApiModelProperty(value = "帐户名称")
    @NotBlank(message = "帐户名称不能为空")
    @Size(min = 5,max = 20,message = "帐户名称字符长度在 5 -20 之间")
    private String name;

    @Email(message = "EMAIL无效")
    @ApiModelProperty(value = "EMAIL")
    private String email;

    @ApiModelProperty(value = "password")
    @NotBlank(message = "帐户密码不能为空")
    @Size(min = 6,max = 20,message = "密码字符长度在 6 -20 之间")
    private String password;


    @Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "phone")
    private String phone;

    @Excel(name = "昵称", width = 15)
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @Excel(name = "实名", width = 15)
    @ApiModelProperty(value = "实名")
    private String legalName;




    @ApiModelProperty(value = "salt")
    private String salt;

    @ApiModelProperty(value = "enrollType")
    private EnrollType enrollType = EnrollType.email;

    @ApiModelProperty(value = "roles: role ids with comma string")
    private String roleIds;
}
