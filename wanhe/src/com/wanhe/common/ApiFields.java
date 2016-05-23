package com.wanhe.common;

import java.io.Serializable;

/**
 * API返回参数名称定义
 *
 */
public class ApiFields implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7015444090378040094L;


	/** 库存状态 */
	public enum Status {
		/** 正常 */
		normal,
		/** 库存警告 */
		stockAlert,
		/** 无库存 */
		outofstock;
	}
	
	/* ---------- 通用参数定义 ---------- */
	/** 时间格式： 年-月-日 */
	public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
	/** 时间格式：年-月-日 时:分 */
	public static final String DATE_FORMAT_YYYYMMDD_HHMM = "yyyy-MM-dd HH:mm";
	/** 时间格式：年-月-日 时:分:秒 */
	public static final String DATE_FORMAT_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	/** 状态码 */
	public static final String STATUS = "status";
	/** 验证码 */
	public static final String CODE = "code";
	/** 创建时间 */
	public static final String CREATE_DATE = "createDate";
	
	/* ---------- 集合参数定义 ---------- */
	/** 商品分类 */
	public static final String CATEGORYS = "categorys";
	/** 品牌 */
	public static final String BRANDS = "brands";
	/** 分类 */
	public static final String SPECIFICATIONS = "specifications";
	/** 分类值 */
	public static final String SPECIFICATION_VALUES = "specificationValues";
	/** 支付方式 */
	public static final String PAYMENTMETHODS = "paymentMethods";
	/** 配送方式 */
	public static final String SHIPPINGMETHODS = "shippingMethods";
	/** 商家 */
	public static final String MERCHANTS = "merchants";
	/** 评价 */
	public static final String REVIEWS = "reviews";
	/** 购物车项 */
	public static final String CART_ITEMS = "cartItems";
	
	/* ---------- 会员参数定义 ---------- */
	/** ID */
	public static final String MEMBER_ID = "memberId";
	
	/** 账号 */
	public static final String USERNAME = "username";
	
	/** 昵称 */
	public static final String NICKNAME = "nickname";
	
	/** 密码 */
	public static final String PASSWORD = "password";
	
	/** 头像 */
	public static final String AVATAR = "avatar";
	
	/** 手机号 */
	public static final String MOBILE = "mobile";
	
	/** 性别 */
	public static final String GENDER = "gender";
	
	/** 生日 */
	public static final String BIRTHDAY = "birthday";
	
	/** 邮箱 */
	public static final String EMAIL = "email";
	
	/** 等级 */
	public static final String RANK = "rank";
	
	/** 积分 */
	public static final String POINT = "point";
	
	/** 钱包余额 */
	public static final String BALANCE = "balacne";
	
	/** 是否启用 */
	public static final String ENABLE = "enable";
	
	/** 是否设置过支付密码 */
	public static final String HAS_PAY_PASSWORD = "hasPayPassword";
	
	/** 是否设置过邀请码 */
	public static final String HAS_INVITE_CODE = "hasInviteCode";
	
	/** 邀请码 */
	public static final String INVITE_CODE = "inviteCode";
	
	/* ---------- 购物车 ---------- */
	/** ID */
	public static final String CART_ID = "cartId";
	/** 购物车项ID */
	public static final String CART_ITEM_ID = "cartItemId";
	/** 购物车项数量 */
	public static final String CART_ITEM_QUANTITY = "cartItemQuantity";
	/** 数量 */
	public static final String CART_QUANTITY = "cartQuantity";
	/** 价格 */
	public static final String CART_AMOUNT = "cartAmount";
	
	/* ---------- 商贸城 ---------- */
	/** ID */
	public static final String TRADE_CITY_ID = "tradeCityId";
	/** 名称 */
	public static final String TRADE_CITY_NAME = "tradeCityName";
	/** 地址 */
	public static final String TRADE_CITY_ADDRESS = "tradeCityAddress";
	/** 邮箱 */
	public static final String TRADE_CITY_EMAIL = "tradeCityEmail";
	
	/* ---------- 商品分类 ---------- */
	/** ID */
	public static final String CATEGORY_ID = "categoryId";
	/** 子类父ID */
	public static final String CATEGORY_PARENT = "categoryParent";
	/** 子类父名称 */
	public static final String CATEGORY_PARENT_NAME = "categoryParentName";
	/** 分类名称 */
	public static final String CATEGORY_NAME = "categoryName";
	/** 分类图片 */
	public static final String CATEGORY_IMAGE = "categoryImage";
	/** 分类子类 */
	public static final String CATEGORY_CHILDREN = "categoryChildren";
	/** 广告图片 */
	public static final String BANNER_IMAGE = "bannerImage";
	/** 广告链接 */
	public static final String BANNER_URL = "bannerUrl";
	
	/* ---------- 商品 ----------*/
	/** ID */
	public static final String PRODUCT_ID = "productId";
	/** 商品名称 */
	public static final String PRODUCT_NAME = "productName";
	/** 商品图片 */
	public static final String PRODUCT_IMAGE = "productImage";
	/** 商品价格 */
	public static final String PRODCUT_PRICE = "productPrice";
	/** 商品评论数 */
	public static final String PRODUCT_REVIEW_COUNT = "productReviewCount";
	/** 商品销量 */
	public static final String PRODUCT_SALE_COUNT = "productSaleCount";
	/** 商品手机专享价 */
	public static final String PRODUCT_MOBILE_PRICE = "productMobilePrice";
	/** 商品价格变化 */
	public static final String PRODUCT_PRICE_CHANGE = "productPriceChange";
	/** 商品是否上架 */
	public static final String PRODUCT_ISMARKETABLE = "productisMarketable";
	/** 商品库存 */
	public static final String PRODUCT_STOCK = "productStock";
	/** 收藏数 */
	public static final String PRODUCT_FAVORITE_COUNT = "productFavoriteCount";
	/** 浏览次数 */
	public static final String PRODUCT_HITS = "productHits";
	/** 是否已收藏 */
	public static final String PRODUCT_ISFAVORITE = "productisFavorite";
	/** 库存状态 */
	public static final String PRODUCT_STOCK_STATUS = "productStockStatus";
	
	/* ---------- 品牌 ---------- */
	/** ID */
	public static final String BRAND_ID = "brandId";
	/** 品牌名称 */
	public static final String BRAND_NAME = "brandName";
	
	/* ---------- 规格 ---------- */
	/** ID */
	public static final String SPECIFICATION_ID = "specificationId";
	/** 规格名称 */
	public static final String SPECIFICATION_NAME = "specificationName";
	/** ID */
	public static final String SPECIFICATION_VALUE_ID = "specificationValueId";
	/** 规格值名称 */
	public static final String SPECIFICATION_VALUE_NAME = "specificationValueName";
	
	/* ---------- 支付方式 ---------- */
	/** ID */
	public static final String PAYMENT_METHOD_ID = "paymentMethodId";
	/** 支付方式名称 */
	public static final String PAYMENT_METHOD_NAME = "paymentMethodName";
	
	/* ---------- 配送方式 ---------- */
	/** ID */
	public static final String SHIPPING_METHOD_ID = "shippingMethodId";
	/** 配送方式名称 */
	public static final String SHIPPING_METHOD_NAME = "shippingMethodName";
	
	/* ---------- 商家 ---------- */
	/** ID */
	public static final String MERCHANT_ID = "merchantId";
	/** 商家名称 */
	public static final String MERCHANT_NAME = "merchantName";
	/** 商家图片 */
	public static final String MERCHANT_IMAGE = "merchantImage";
	/** 商家地址 */
	public static final String MERCHANT_ADDRESS = "merchantAddress";
	/** 商家联系方式 */
	public static final String MERCHANT_PHONE = "merchantPhone";
	/** 浏览次数 */
	public static final String MERCHANT_HITS = "merchantHits";
	/** 收藏数 */
	public static final String MERCHAT_FAVORITE = "merchantFavorite";
	/** 商品数 */
	public static final String MERCHANT_PRODUCT_COUNT = "merchantProductCount";
	/** 店铺优惠券数 */
	public static final String MERCHANT_COUPON_COUNT = "merchantCouponCount";
	/** 店铺背景图片 */
	public static final String MERCHANT_BACKGROUND = "merchantBackground";
	
	/* ---------- 评价 ---------- */
	/** ID */
	public static final String REVIEW_ID = "reviewId";
	/** 分数 */
	public static final String REVIEW_SCORE = "reviewScore";
	/** 内容 */
	public static final String REVIEW_CONTENT = "reviewContent";
	/** 图片 */
	public static final String REVIEW_IMAGES = "reviewImages";
	/** 图片 */
	public static final String REVIEW_IMAGE = "reviewImage";
	/** 总数 */
	public static final String REVIEW_ALL_COUNT = "reviewAllCount";
	/** 晒图数 */
	public static final String REVIEW_IMAGE_COUNT = "reviewImagelCount";
	/** 是否参加公益 */
	public static final String REVIEW_USEFUL = "reviewUseful";
	
	/* ---------- 优惠券 ---------- */
	/** ID */
	public static final String COUPON_ID = "couponId";
	/** 名称 */
	public static final String COUPON_NAME ="couponName";
	/** 金额 */
	public static final String COUPON_AMOUNT = "couponAmount";
	/** 内容 */
	public static final String COUPON_CONTENT = "couponContent";
	/** 类型 */
	public static final String COUPON_TYPE = "couponType";
	/** 起始时间 */
	public static final String COUPON_START_DATE = "couponStartDate";
	/** 结束时间 */
	public static final String COUPON_END_DATE = "couponEndDate";
	
}
