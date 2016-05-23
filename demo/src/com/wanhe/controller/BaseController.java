package com.wanhe.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wanhe.DateEditor;
import com.wanhe.Message;
import com.wanhe.Pageable;
import com.wanhe.Order.Direction;

/**
 * Controller - 基类
 * 
 * 
 * 
 */
public class BaseController {

	public static final String STATUS = "status";
	public static final String WARN = "warn";
	public static final String SUCCESS = "/admin/common/success";			// 成功页面
	public static final String ERROR = "/admin/common/error";				// 错误页面
	public static final String MESSAGE = "message";
	public static final String CONTENT = "content";
	public static String REDIRECT_URL = "redirectionUrl"; // 跳转页面
	
	/** API成功消息 */
	protected Integer SUCCESS_CODE = 0;
	/** API失败消息 */
	protected Integer ERROR_CODE = 1;
	
	protected String LIST = "list";
	
	/** 错误视图 */
	protected static final String ERROR_VIEW = "/admin/common/error";
	/** 成功视图 */
	protected static final String SUCCESS_VIEW = "/admin/common/success";

	/** 错误消息 */
	protected static final Message ERROR_MESSAGE = Message.error("admin.message.error");

	/** 成功消息 */
	protected static final Message SUCCESS_MESSAGE = Message.success("admin.message.success");

	/** Page参数名称. */
	public static final String PAGE = "page";

	@Resource(name = "validator")
	private Validator validator;
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public CriteriaBuilder getCriteria(){
		return entityManager.getCriteriaBuilder();
	}
	
	/**
	 * 数据绑定
	 * 
	 * @param binder
	 *            WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}
	
	/**
	 * 异常控制
	 * @param runtimeException
	 * @return
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@ExceptionHandler(Exception.class)
	public void runtimeExceptionHandler(HttpServletResponse response,HttpServletRequest request,Exception ex) throws ServletException, IOException {
		ex.printStackTrace();
		request.setAttribute("message", "服务器异常！");
		request.getRequestDispatcher("/WEB-INF/template/admin/common/error.jsp").forward(request, response);
	}
	

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 成功
	 * @return 成功数据包
	 */
	public JSONObject success() {
		JSONObject json = new JSONObject();
		json.put(STATUS, SUCCESS_CODE);
		json.put(MESSAGE, "操作成功");
		return json;
	}
	
	/**
	 * 失败
	 * @param message 失败提示信息
	 * @return 失败数据包
	 */
	public JSONObject error(String message) {
		JSONObject json = new JSONObject();
		json.put(STATUS, ERROR_CODE);
		json.put(MESSAGE, message);
		return json;
	}
	
	

	/**
	 * 向客户端写出JSON数据
	 * @param json
	 * @param response
	 * @throws IOException
	 */
	public void write(String content, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(content);
		out.flush();
		out.close();
	}
	
	public String getRoot(HttpServletRequest request){
		return request.getScheme() + "://" + request.getHeader("host") + request.getContextPath();
	}
	
	
	public void initPageable(HttpServletRequest request,Pageable pageable, String searchProperty){
		String pageNumber = request.getParameter("page");
		String pageCount = request.getParameter("pageCount");
		
		String keyword = request.getParameter("keyword");
		
		if(StringUtils.isNotEmpty(searchProperty)&&StringUtils.isNotEmpty(keyword)){
			pageable.setSearchProperty(searchProperty);
			pageable.setSearchValue(keyword);
		}
		
		if(StringUtils.isNotEmpty(pageCount) && StringUtils.isNumeric(pageCount)){
			pageable.setPageSize(Integer.parseInt(pageCount));

			pageable.setPageNumber(1);
		}
		
		if(StringUtils.isNotEmpty(pageNumber) && StringUtils.isNumeric(pageNumber)){
			pageable.setPageNumber(Integer.parseInt(pageNumber));
		}
		pageable.setOrderDirection(Direction.desc);
		pageable.setOrderProperty("createDate");
	}
}