package org.ixkit.anvilj.partner.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.ixkit.anvilj.partner.entity.Partner;
import org.ixkit.anvilj.partner.service.IPartnerService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 基础资源 成员表
 * @Author: ixkit
 * @Date:   2024-01-02
 * @Version: V1.0
 */
@Api(tags="基础资源 成员表")
@RestController
//@RequestMapping("/org .ixkit.anvilj.partner/partner")
@RequestMapping("/api/v1/partner")
@Slf4j
public class PartnerController extends JeecgController<Partner, IPartnerService> {
	@Autowired
	private IPartnerService partnerService;
	
	/**
	 * 分页列表查询
	 *
	 * @param partner
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "基础资源 成员表-分页列表查询")
	@ApiOperation(value="基础资源 成员表-分页列表查询", notes="基础资源 成员表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Partner>> queryPageList(Partner partner,
												@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												HttpServletRequest req) {
		QueryWrapper<Partner> queryWrapper = QueryGenerator.initQueryWrapper(partner, req.getParameterMap());
		Page<Partner> page = new Page<Partner>(pageNo, pageSize);
		IPage<Partner> pageList = partnerService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param partner
	 * @return
	 */
	@AutoLog(value = "基础资源 成员表-添加")
	@ApiOperation(value="基础资源 成员表-添加", notes="基础资源 成员表-添加")
	@RequiresPermissions("org.ixkit.anvilj.partner:aj_partner:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Partner partner) {
		partnerService.save(partner);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param partner
	 * @return
	 */
	@AutoLog(value = "基础资源 成员表-编辑")
	@ApiOperation(value="基础资源 成员表-编辑", notes="基础资源 成员表-编辑")
	@RequiresPermissions("org.ixkit.anvilj.partner:aj_partner:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Partner partner) {
		partnerService.updateById(partner);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "基础资源 成员表-通过id删除")
	@ApiOperation(value="基础资源 成员表-通过id删除", notes="基础资源 成员表-通过id删除")
	@RequiresPermissions("org.ixkit.anvilj.partner:aj_partner:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		partnerService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "基础资源 成员表-批量删除")
	@ApiOperation(value="基础资源 成员表-批量删除", notes="基础资源 成员表-批量删除")
	@RequiresPermissions("org.ixkit.anvilj.partner:aj_partner:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.partnerService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "基础资源 成员表-通过id查询")
	@ApiOperation(value="基础资源 成员表-通过id查询", notes="基础资源 成员表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Partner> queryById(@RequestParam(name="id",required=true) String id) {
		Partner partner = partnerService.getById(id);
		if(partner==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(partner);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param partner
    */
    @RequiresPermissions("org.ixkit.anvilj.partner:aj_partner:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Partner partner) {
        return super.exportXls(request, partner, Partner.class, "基础资源 成员表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("org.ixkit.anvilj.partner:aj_partner:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Partner.class);
    }

}
