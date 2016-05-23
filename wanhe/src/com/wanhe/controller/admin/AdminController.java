package com.wanhe.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wanhe.Filter;
import com.wanhe.Page;
import com.wanhe.Pageable;
import com.wanhe.controller.BaseController;
import com.wanhe.entity.Admin;
import com.wanhe.entity.Admin.AdminType;
import com.wanhe.entity.Admin.Gender;
import com.wanhe.service.AdminService;
import com.wanhe.util.ExcelReader;

@Controller
@RequestMapping("/admin/admin")
public class AdminController extends BaseController {

	@Resource
	private AdminService adminService;

	/**
	 * 获取物业人员信息
	 * @param pageable
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listByJson",  produces = "application/json; charset=utf-8")
	public  @ResponseBody String  listByJson(Integer type,Pageable pageable, ModelMap model,HttpServletRequest request) {
		
		String name = request.getParameter("name");
		String 	username = request.getParameter("username");
		String mobile = request.getParameter("mobile");
		String communityId = request.getParameter("communityId");
		String isEnabled = request.getParameter("isEnabled");

		List<Filter> filters=new ArrayList<Filter>();
		initPageable(request, pageable, null);
		if(StringUtils.isNotEmpty(name)){
			filters.add(Filter.like("name", "%" + name + "%"));
		}
		if(StringUtils.isNotEmpty(username)){
			filters.add(Filter.like("username", "%" + username + "%"));
		}
		if(StringUtils.isNotEmpty(mobile)){
			filters.add(Filter.like("mobile", "%" + mobile + "%"));
		}
		if(StringUtils.isNotEmpty(isEnabled)){
			filters.add(Filter.eq("isEnabled", Integer.parseInt(isEnabled)));
		}
		
		pageable.setFilters(filters);
		Page<Admin> pager = adminService.findPagerByType(AdminType.get(type), pageable);
		
		JSONObject json = success();
		JSONArray array = new JSONArray();
		json.put("pageTotal", pager.getTotal());
		json.put("data", array);
		return json.toString();
	}
	
	/**
	 * 删除物业
	 */
	@RequestMapping(value = "/deletedByJson",  produces = "application/json; charset=utf-8")
	public @ResponseBody String deletedByJson(Long adminId, Pageable pageable){
		if(!adminService.exists(adminId)){
			return error("无法获取物业").toString();
		}
		JSONObject json =null;
		Admin admin =adminService.getCurrent();
		if(admin.getId()==adminId){
			json = error("不能删除当前登陆人！");
		}else{
			try {
				adminService.delete(adminId);
				json = success();
			} catch (Exception e) {
				json = error("有关联关系，不能删除！");
			}
		}
	
		return json.toString();
	}
	
	/**
	 * 禁用启用
	 */
	@RequestMapping(value = "/handleByJson",  produces = "application/json; charset=utf-8")
	public @ResponseBody String handleByJson(Long adminId,HttpServletRequest request,  Pageable pageable){
		if(!adminService.exists(adminId)){
			return error("无法获取物业").toString();
		}
		Admin admin = adminService.find(adminId);
		String isEnabled = request.getParameter("isEnabled");
		admin.setIsEnabled(Boolean.parseBoolean(isEnabled));
		
		admin.setLockedDate(new Date());
		adminService.update(admin);
		JSONObject json = success();
		
		return json.toString();
	}
	
	/**
	 * 保存
	 * @param request
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/saveByJson", produces = "application/json; charset=utf-8")
	public @ResponseBody String saveByJson(HttpServletRequest request, Pageable pageable){
		Admin temp = null;
		String adminId = request.getParameter("adminId");
		String username = request.getParameter("username");
		if(adminId !=null){
			if(!adminService.exists(Long.parseLong(adminId))){
				return error("无法获取物业").toString();
			}
			
			temp = adminService.find(Long.parseLong(adminId));
		} else {
			temp = new Admin();
			temp.setIsEnabled(Boolean.TRUE);
			Admin user = adminService.findByUsername(username);
			if(null!=user){
				return error("名称不能重复").toString();
			}
		}
		
		
		String password = request.getParameter("password");
		Gender gender = Gender.get(Integer.parseInt(request.getParameter("gender")));
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String workTime = request.getParameter("workTime");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		

		temp.setUsername(username);
		temp.setName(name);
		if(!"".equals(password)&&null!=password){
			temp.setPassword(DigestUtils.md5Hex(password));
		}
		
		temp.setLoginFailureCount(0);
		
		adminService.save(temp);
		
		JSONObject json = success();
		return json.toString();
	}

	/**
	 * 获得单个业主
	 */
	@RequestMapping(value = "/getByJson",  produces = "application/json; charset=utf-8")
	public @ResponseBody String getByJson(Long adminId, Pageable pageable){
		if(!adminService.exists(adminId)){
			return error("无法获取物业人员").toString();
		}
		Admin admin =adminService.find(adminId);
		
		JSONObject json = success();
		return json.toString();
	}
	
	/**
	 * 验证名称重复
	 */
	@RequestMapping(value="/checkUserNameByJson",  produces = "application/json; charset=utf-8")
	public  @ResponseBody String checkUserNameByJson(String username,HttpServletRequest request){
		if(adminService.usernameExists(username)){
			return error("用户名不能重复").toString();
		}else{
			JSONObject json = success();
			return  json.toString();
		}
	}
	
	/**
     * 物管信息导入
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartHttpServletRequest request) {
    	Admin current = adminService.getCurrent();
    	
    	Integer success = 0;
    	Integer fail = 0;
        try {
            MultipartFile excel = request.getFile("file");
            ExcelReader reader = new ExcelReader(excel.getInputStream());
            int count = reader.getSheetCount();
            for (int sheetNum = 0; sheetNum < count; sheetNum ++) {
                Map<Integer, List<String>> content = reader.readExcelContent(sheetNum);
                
                Admin admin = null;
                for (Integer rowNum : content.keySet()) {
                    List<String> row = content.get(rowNum);
                    String name = row.get(0); 		// 姓名
                    if(StringUtils.isEmpty(name)){
                    	continue;
                    }
                    String username = row.get(1); 	// 用户名
                    String password = row.get(2); 	// 密码
                    String age = row.get(3); 		// 年龄
                    String gender = row.get(4); 	// 性别
                    String mobile = row.get(5); 	// 联系电话
                    String entryDate = row.get(6);	// 入职时间
                    String workTime = row.get(7); 	// 工作年限
                    String email = row.get(8); 		// 邮箱
                    
                    // 获取业主
                    admin = adminService.findByUsername(username);
                    if(admin != null && admin.getIsEnabled()){
                    	fail++;
                    	continue;
                    }
                    
                    admin = new Admin();
                    admin.setName(name);
                    admin.setUsername(username);
                    admin.setPassword(DigestUtils.md5Hex(password));
                    admin.setEmail(email);
                    admin.setLoginFailureCount(0);
                    adminService.save(admin);
                    
                    success++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return error("操作失败,无法解析Excel文件,请检查后上传!").toString();
        }
        JSONObject json = success();
        
        JSONObject entity = new JSONObject();
        entity.put("successCount", success); // 成功数
        entity.put("failCount", fail); // 失败数
        
        json.put("data", entity);
        return json.toString();
    }
}
