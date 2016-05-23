package com.wanhe.controller.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.magicbeans.pay.Pay;
import cn.magicbeans.pay.instance.PluginInstance;
import cn.magicbeans.pay.plugin.Plugin;

import com.wanhe.controller.BaseController;
import com.wanhe.entity.Order;
import com.wanhe.entity.Product;
import com.wanhe.service.OrderService;
import com.wanhe.service.ProductService;
import com.wanhe.util.SettingUtils;

@Controller("apiOrderController")
@RequestMapping("/api/order")
public class OrderApi extends BaseController {

	@Resource
	private ProductService productService;
	@Resource
	private OrderService orderService;
	
	@RequestMapping(value = "/submit", produces = "application/json; charset=utf-8")
	public @ResponseBody String submit(Long productId, Integer quantity, String paymentMethod) {
		Product product = productService.find(productId);
		// 计算价格
		BigDecimal price = product.getPrice().multiply(new BigDecimal(quantity));
		
		String splitSn = new Date().getTime() + "";
		
		Order order = new Order();
		order.setSn(new Date().getTime() + "");
		order.setSplitSn(splitSn);
		
		Map<Product, Integer> productMap = new HashMap<Product, Integer>();
		productMap.put(product, quantity);
		order.setAmount(price);
		order.setProductMap(productMap);
		orderService.save(order);
		
		JSONObject json = success();
		/** ===============================  订单支付开始   =============================== */
		// 通知回调地址
		String notifyUrl = SettingUtils.get().getSiteUrl() + "/api/order/notify/";
		// 根据支付方式获取支付插件
		Plugin plugin = PluginInstance.getInstance().get(paymentMethod);
		
		order = orderService.findBySn(order.getSn());
		order.setPluginName(paymentMethod);
//		orderService.pay(order, paymentPlugin);
		
		JSONObject data = new JSONObject();
		// 支付宝、微信支付回传参数
		if(plugin != null){
			data = Pay.getAppRequsetData(order, paymentMethod, notifyUrl);
		} else {
			data.put("sn", "");
			data.put("requestUrl", "");
			data.put("paymentMethod", -1);
			data.put("amount", 0);
		}
		/** ===============================  订单支付结束   =============================== */
		json.put("data", data);
		return json.toString();
	}
	
	/**
	 * 支付回调通知
	 */
	@RequestMapping(value = "/notify/{sn}", produces = "application/json; charset=utf-8")
	public String notify(@PathVariable String sn, HttpServletRequest request) {
//		System.out.println(sn);
		// 通过拆单号获取所有订单
		List<Order> orders = orderService.findBySplitSn(sn);
		
		// 验证数据
		for (Order order : orders) {
			Pay.verifyNotify(order.getSn(), order.getPluginName(), order.getAmount(), request);
		}
		
		return "success";
	}
}
