/*
 * 
 * 
 * 
 */
package com.wanhe.controller.admin;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanhe.CommonAttributes;
import com.wanhe.Pageable;
import com.wanhe.Setting;
import com.wanhe.controller.BaseController;
import com.wanhe.service.CacheService;
import com.wanhe.util.SettingUtils;

/**
 * Controller - 系统设置
 * 
 * 
 * 
 */
@Controller("adminstingController")
@RequestMapping("/admin/setting")
public class SettingController extends BaseController {

	@Resource(name = "cacheServiceImpl")
	private CacheService cacheService;


	/**
	 * 获得设置
	 */
	@RequestMapping(value = "/getByJson",  produces = "application/json; charset=utf-8")
	public @ResponseBody String getByJson(Pageable pageable){
		JSONObject json = success();
		Setting set = SettingUtils.get();
		JSONObject object = new JSONObject();
		object.put("sign",set.getSign());
		object.put("signContinuous",set.getSignContinuous());
		
		object.put("sport",set.getSport());
		object.put("sportKilometer",set.getSportKilometer());
		object.put("sportMax",set.getSportMax());
		
		object.put("jump",set.getJump());
		object.put("jumpHundred",set.getJumpHundred());
		object.put("jumpMax",set.getJumpMax());
		
		object.put("dynamic",set.getDynamic());
		object.put("dynamicMax",set.getDynamicMax());
		
		object.put("praise",set.getPraise());
		object.put("praiseMax",set.getPraiseMax());
		
		object.put("comment",set.getComment());
		object.put("commentMax",set.getCommentMax());
		
		object.put("rock",set.getRock());
		object.put("rockMax",set.getRockMax());
		
		object.put("code",set.getCode());
		object.put("codeMax",set.getCodeMax());
		
		object.put("shop",set.getShop());
		
		object.put("payment",set.getPayment());
		
		object.put("invite",set.getInvite());
		
		object.put("dayRankingOne",set.getDayRankingOne());
		object.put("dayRankingTwo",set.getDayRankingTwo());
		object.put("dayRankingThree",set.getDayRankingTwo());
		
		object.put("weekRankingOne",set.getWeekRankingOne());
		object.put("weekRankingTwo",set.getWeekRankingTwo());
		object.put("weekRankingThree",set.getWeekRankingThree());
		
		object.put("monthRankingOne",set.getMonthRankingOne());
		object.put("monthRankingTwo",set.getMonthRankingTwo());
		object.put("monthRankingThree",set.getMonthRankingThree());
		
		json.put("data", object);
		return json.toString();
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", produces = "application/json; charset=utf-8")
	public  @ResponseBody String  update(Setting set,HttpServletRequest request, Pageable pageable) {
		
		Setting s = SettingUtils.get();
	
	
		s.setSign(set.getSign());
		s.setSignContinuous(set.getSignContinuous());
		
		s.setSport(set.getSport());
		s.setSportKilometer(set.getSportKilometer());
		s.setSportMax(set.getSportMax());
		
		s.setJump(set.getJump());
		s.setJumpHundred(set.getJumpHundred());
		s.setJumpMax(set.getJumpMax());
		
		s.setDynamic(set.getDynamic());
		s.setDynamicMax(set.getDynamicMax());
		
		s.setPraise(set.getPraise());
		s.setPraiseMax(set.getPraiseMax());
		
		s.setComment(set.getComment());
		s.setCommentMax(set.getCommentMax());
		
		s.setRock(set.getRock());
		s.setRockMax(set.getRockMax());
		
		s.setCode(set.getCode());
		s.setCodeMax(set.getCodeMax());
		
		s.setShop(set.getShop());
		
		s.setPayment(set.getPayment());
		
		s.setInvite(set.getInvite());
		
		s.setDayRankingOne(set.getDayRankingOne());
		s.setDayRankingTwo(set.getDayRankingTwo());
		s.setDayRankingThree(set.getDayRankingThree());
		
		s.setWeekRankingOne(set.getWeekRankingOne());
		s.setWeekRankingTwo(set.getWeekRankingTwo());
		s.setWeekRankingThree(set.getWeekRankingThree());
		
		s.setMonthRankingOne(set.getMonthRankingOne());
		s.setMonthRankingTwo(set.getMonthRankingTwo());
		s.setMonthRankingThree(set.getMonthRankingThree());
		
		SettingUtils.set(s);
		cacheService.clear();

		OutputStream outputStream = null;
		try {
			org.springframework.core.io.Resource resource = new ClassPathResource(CommonAttributes.wanhe_PROPERTIES_PATH);
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			String templateUpdateDelay = properties.getProperty("template.update_delay");
			String messageCacheSeconds = properties.getProperty("message.cache_seconds");
			if (s.getIsDevelopmentEnabled()) {
				if (!templateUpdateDelay.equals("0") || !messageCacheSeconds.equals("0")) {
					outputStream = new FileOutputStream(resource.getFile());
					properties.setProperty("template.update_delay", "0");
					properties.setProperty("message.cache_seconds", "0");
					properties.store(outputStream, "A+ PROPERTIES");
				}
			} else {
				if (templateUpdateDelay.equals("0") || messageCacheSeconds.equals("0")) {
					outputStream = new FileOutputStream(resource.getFile());
					properties.setProperty("template.update_delay", "3600");
					properties.setProperty("message.cache_seconds", "3600");
					properties.store(outputStream, "A+ PROPERTIES");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
		
		JSONObject json = success();
		return json.toString();
	}

	/**
	 * 获得设置
	 */
	@RequestMapping(value = "/getSystemByJson",  produces = "application/json; charset=utf-8")
	public @ResponseBody String getSystemByJson(Pageable pageable){
		JSONObject json = success();
		Setting set = SettingUtils.get();
		JSONObject object = new JSONObject();
		object.put("water",set.getWater());
		object.put("electric",set.getElectric());
		object.put("gas",set.getGas());
		object.put("property",set.getProperty());
		object.put("park",set.getPark());
		object.put("hygiene",set.getHygiene());
		
		object.put("port",set.getPort());
		
		object.put("music",set.getMusic());
		object.put("text",set.getText());
		object.put("video",set.getVideo());
		object.put("image",set.getImage());
		object.put("bless_port",set.getBless_port());
		
		object.put("sys_phone",set.getSys_phone());
		
		object.put("UDP_Port",set.getUDP_Port());
		
		object.put("visterPrice",set.getVisterPrice());
		object.put("memberPrice",set.getMemberPrice());
		json.put("data", object);
		return json.toString();
	}
	
	
	/**
	 *  更新
	 */
	@RequestMapping(value = "/updateSystem", produces = "application/json; charset=utf-8")
	public  @ResponseBody String  updateSystem(Setting set,HttpServletRequest request, Pageable pageable) {
		
		Setting s = SettingUtils.get();
	
		s.setWater(set.getWater());
		s.setElectric(set.getElectric());
		s.setGas(set.getGas());
		s.setProperty(set.getProperty());
		s.setPark(set.getPark());
		s.setHygiene(set.getHygiene());
		
		s.setPort(set.getPort());
		
		s.setMusic(set.getMusic());
		s.setText(set.getText());
		s.setVideo(set.getVideo());
		s.setImage(set.getImage());
		s.setBless_port(set.getBless_port());
		
		s.setSys_phone(set.getSys_phone());
		
		s.setUDP_Port(set.getUDP_Port());
		
		s.setMemberPrice(set.getMemberPrice());
		s.setVisterPrice(set.getVisterPrice());
		SettingUtils.set(s);
		cacheService.clear();

		OutputStream outputStream = null;
		try {
			org.springframework.core.io.Resource resource = new ClassPathResource(CommonAttributes.wanhe_PROPERTIES_PATH);
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			String templateUpdateDelay = properties.getProperty("template.update_delay");
			String messageCacheSeconds = properties.getProperty("message.cache_seconds");
			if (s.getIsDevelopmentEnabled()) {
				if (!templateUpdateDelay.equals("0") || !messageCacheSeconds.equals("0")) {
					outputStream = new FileOutputStream(resource.getFile());
					properties.setProperty("template.update_delay", "0");
					properties.setProperty("message.cache_seconds", "0");
					properties.store(outputStream, "A+ PROPERTIES");
				}
			} else {
				if (templateUpdateDelay.equals("0") || messageCacheSeconds.equals("0")) {
					outputStream = new FileOutputStream(resource.getFile());
					properties.setProperty("template.update_delay", "3600");
					properties.setProperty("message.cache_seconds", "3600");
					properties.store(outputStream, "A+ PROPERTIES");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
		
		JSONObject json = success();
		return json.toString();
	}
}