package org.ixkit.anvilj.member.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.ixkit.anvilj.member.entity.AnvilMember;
import org.ixkit.anvilj.member.service.IAnvilMemberService;

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

 /**
 * @Description: member
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
@Api(tags="member")
@RestController
@RequestMapping("/org.ixkit.anvilj.member/anvilMember")
@Slf4j
public class AnvilMemberController extends JeecgController<AnvilMember, IAnvilMemberService> {
	@Autowired
	private IAnvilMemberService anvilMemberService;
	
	/**
	 * 分页列表查询
	 *
	 * @param anvilMember
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "member-分页列表查询")
	@ApiOperation(value="member-分页列表查询", notes="member-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AnvilMember>> queryPageList(AnvilMember anvilMember,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AnvilMember> queryWrapper = QueryGenerator.initQueryWrapper(anvilMember, req.getParameterMap());
		Page<AnvilMember> page = new Page<AnvilMember>(pageNo, pageSize);
		IPage<AnvilMember> pageList = anvilMemberService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param anvilMember
	 * @return
	 */
	@AutoLog(value = "member-添加")
	@ApiOperation(value="member-添加", notes="member-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:anvil_member:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AnvilMember anvilMember) {
		anvilMemberService.save(anvilMember);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param anvilMember
	 * @return
	 */
	@AutoLog(value = "member-编辑")
	@ApiOperation(value="member-编辑", notes="member-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:anvil_member:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AnvilMember anvilMember) {
		anvilMemberService.updateById(anvilMember);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "member-通过id删除")
	@ApiOperation(value="member-通过id删除", notes="member-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:anvil_member:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		anvilMemberService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "member-批量删除")
	@ApiOperation(value="member-批量删除", notes="member-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:anvil_member:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.anvilMemberService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "member-通过id查询")
	@ApiOperation(value="member-通过id查询", notes="member-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AnvilMember> queryById(@RequestParam(name="id",required=true) String id) {
		AnvilMember anvilMember = anvilMemberService.getById(id);
		if(anvilMember==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(anvilMember);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param anvilMember
    */
    //@RequiresPermissions("org.jeecg.modules.demo:anvil_member:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AnvilMember anvilMember) {
        return super.exportXls(request, anvilMember, AnvilMember.class, "member");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("anvil_member:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AnvilMember.class);
    }

}
