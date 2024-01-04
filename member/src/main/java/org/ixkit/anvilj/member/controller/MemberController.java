package org.ixkit.anvilj.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.ixkit.anvilj.member.entity.Member;
import org.ixkit.anvilj.member.service.IMemberService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 基础资源 成员表
 * @Author: jeecg-boot
 * @Date:   2024-01-02
 * @Version: V1.0
 */
@Api(tags="基础资源 成员表")
@RestController
//@RequestMapping("/org .ixkit.anvilj.member/member")
@RequestMapping("/api/v1/anvilj/member")
@Slf4j
public class MemberController extends JeecgController<Member, IMemberService> {
	@Autowired
	private IMemberService memberService;
	
	/**
	 * 分页列表查询
	 *
	 * @param member
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "基础资源 成员表-分页列表查询")
	@ApiOperation(value="基础资源 成员表-分页列表查询", notes="基础资源 成员表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Member>> queryPageList(Member member,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Member> queryWrapper = QueryGenerator.initQueryWrapper(member, req.getParameterMap());
		Page<Member> page = new Page<Member>(pageNo, pageSize);
		IPage<Member> pageList = memberService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param member
	 * @return
	 */
	@AutoLog(value = "基础资源 成员表-添加")
	@ApiOperation(value="基础资源 成员表-添加", notes="基础资源 成员表-添加")
	@RequiresPermissions("org.ixkit.anvilj.member:aj_member:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Member member) {
		memberService.save(member);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param member
	 * @return
	 */
	@AutoLog(value = "基础资源 成员表-编辑")
	@ApiOperation(value="基础资源 成员表-编辑", notes="基础资源 成员表-编辑")
	@RequiresPermissions("org.ixkit.anvilj.member:aj_member:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Member member) {
		memberService.updateById(member);
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
	@RequiresPermissions("org.ixkit.anvilj.member:aj_member:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		memberService.removeById(id);
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
	@RequiresPermissions("org.ixkit.anvilj.member:aj_member:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.memberService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<Member> queryById(@RequestParam(name="id",required=true) String id) {
		Member member = memberService.getById(id);
		if(member==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(member);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param member
    */
    @RequiresPermissions("org.ixkit.anvilj.member:aj_member:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Member member) {
        return super.exportXls(request, member, Member.class, "基础资源 成员表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("org.ixkit.anvilj.member:aj_member:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Member.class);
    }

}
