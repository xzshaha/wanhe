package com.wanhe.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wanhe.Page;
import com.wanhe.controller.BaseController;

/**
 * Interceptor - 列表查询
 * 
 * 
 * 
 */
public class ListInterceptor extends HandlerInterceptorAdapter {

	/** 重定向视图名称前缀 */
	private static final String REDIRECT_VIEW_NAME_PREFIX = "redirect:";

	/** 列表查询Cookie名称 */
	private static final String LIST_QUERY_COOKIE_NAME = "listQuery";
	/** "模式"参数名称 */
	private static final String PATTERN_PARAMETER_NAME = "pattern";

	/** "页码"参数名称 */
	private static final String PAGE_NUMBER_PARAMETER_NAME = "pageNumber";

	/** "总页数"参数名称 */
	private static final String TOTAL_PAGES_PARAMETER_NAME = "totalPages";

	/** "中间段页码数"参数名称 */
	private static final String SEGMENT_COUNT_PARAMETER_NAME = "segmentCount";

	/** "模式"变量名称 */
	private static final String PATTERN_VARIABLE_NAME = "pattern";

	/** "页码"变量名称 */
	private static final String PAGE_NUMBER_VARIABLE_NAME = "pageNumber";
	
	/** 总记录条数 */
	private static final String TOTAL_RECORDS_VARIABLE_NAME = "totalRecords";

	/** "总页数"变量名称 */
	private static final String PAGE_COUNT_VARIABLE_NAME = "totalPages";

	/** "中间段页码数"变量名称 */
	private static final String SEGMENT_COUNT_VARIABLE_NAME = "segmentCount";

	/** "是否存在上一页"变量名称 */
	private static final String HAS_PREVIOUS_VARIABLE_NAME = "hasPrevious";

	/** "是否存在下一页"变量名称 */
	private static final String HAS_NEXT_VARIABLE_NAME = "hasNext";

	/** "是否为首页"变量名称 */
	private static final String IS_FIRST_VARIABLE_NAME = "isFirst";

	/** "是否为末页"变量名称 */
	private static final String IS_LAST_VARIABLE_NAME = "isLast";

	/** "上一页页码"变量名称 */
	private static final String PREVIOUS_PAGE_NUMBER_VARIABLE_NAME = "previousPageNumber";

	/** "下一页页码"变量名称 */
	private static final String NEXT_PAGE_NUMBER_VARIABLE_NAME = "nextPageNumber";

	/** "首页页码"变量名称 */
	private static final String FIRST_PAGE_NUMBER_VARIABLE_NAME = "firstPageNumber";

	/** "末页页码"变量名称 */
	private static final String LAST_PAGE_NUMBER_VARIABLE_NAME = "lastPageNumber";

	/** "中间段页码"变量名称 */
	private static final String SEGMENT_VARIABLE_NAME = "segment";
	
	/**
	 * 共多少数据
	 */
	private static final String TOTAL ="total";
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null && modelAndView.isReference()) {
			String viewName = modelAndView.getViewName();
//			if (StringUtils.startsWith(viewName, REDIRECT_VIEW_NAME_PREFIX)) {
//				String listQuery = WebUtils.getCookie(request, LIST_QUERY_COOKIE_NAME);
//				if (StringUtils.isNotEmpty(listQuery)) {
//					if (StringUtils.startsWith(listQuery, "?")) {
//						listQuery = listQuery.substring(1);
//					}
//					if (StringUtils.contains(viewName, "?")) {
//						modelAndView.setViewName(viewName + "&" + listQuery);
//					} else {
//						modelAndView.setViewName(viewName + "?" + listQuery);
//					}
//					WebUtils.removeCookie(request, response, LIST_QUERY_COOKIE_NAME);
//				}
//			}

			Object o = modelAndView.getModel().get(BaseController.PAGE);
			if (null != o) {
				Page<?> page = (Page<?>) o;
//				String pattern = modelAndView.getModelMap().get(PATTERN_PARAMETER_NAME).toString();
				Integer pageNumber = page.getPageNumber();
				Integer totalPages = page.getTotalPages();
				long total = page.getTotal();
				Integer segmentCount = null;

				if (pageNumber == null || pageNumber < 1) {
					pageNumber = 1;
				}
				if (totalPages == null || totalPages < 1) {
					totalPages = 1;
				}
				if (segmentCount == null || segmentCount < 1) {
					segmentCount = 5;
				}
				long totalRecords = page.getTotal();
				boolean hasPrevious = pageNumber > 1;
				boolean hasNext = pageNumber < totalPages;
				boolean isFirst = pageNumber == 1;
				boolean isLast = pageNumber.equals(totalPages);
				int previousPageNumber = pageNumber - 1;
				int nextPageNumber = pageNumber + 1;
				int firstPageNumber = 1;
				int lastPageNumber = totalPages;
				int startSegmentPageNumber = pageNumber - (int) Math.floor((segmentCount - 1) / 2D);
				int endSegmentPageNumber = pageNumber + (int) Math.ceil((segmentCount - 1) / 2D);
				if (startSegmentPageNumber < 1) {
					startSegmentPageNumber = 1;
				}
				if (endSegmentPageNumber > totalPages) {
					endSegmentPageNumber = totalPages;
				}
				List<Integer> segment = new ArrayList<Integer>();
				for (int i = startSegmentPageNumber; i <= endSegmentPageNumber; i++) {
					segment.add(i);
				}

				Map<String, Object> variables = new HashMap<String, Object>();
//				variables.put(PATTERN_VARIABLE_NAME, pattern);
				variables.put(TOTAL_RECORDS_VARIABLE_NAME, totalRecords);
				variables.put(PAGE_NUMBER_VARIABLE_NAME, pageNumber);
				variables.put(PAGE_COUNT_VARIABLE_NAME, totalPages);
				variables.put(SEGMENT_COUNT_VARIABLE_NAME, segmentCount);
				variables.put(HAS_PREVIOUS_VARIABLE_NAME, hasPrevious);
				variables.put(HAS_NEXT_VARIABLE_NAME, hasNext);
				variables.put(IS_FIRST_VARIABLE_NAME, isFirst);
				variables.put(IS_LAST_VARIABLE_NAME, isLast);
				variables.put(PREVIOUS_PAGE_NUMBER_VARIABLE_NAME, previousPageNumber);
				variables.put(NEXT_PAGE_NUMBER_VARIABLE_NAME, nextPageNumber);
				variables.put(FIRST_PAGE_NUMBER_VARIABLE_NAME, firstPageNumber);
				variables.put(LAST_PAGE_NUMBER_VARIABLE_NAME, lastPageNumber);
				variables.put(SEGMENT_VARIABLE_NAME, segment);
				variables.put(TOTAL, total);
				modelAndView.addAllObjects(variables);
			}
		}
	}
}