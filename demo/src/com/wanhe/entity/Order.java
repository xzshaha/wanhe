package com.wanhe.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.magicbeans.pay.entity.MOrder;
import cn.magicbeans.pay.instance.PluginInstance;
import cn.magicbeans.pay.plugin.Plugin;


@Entity
@Table(name = "aps_order")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "aps_order_sequence")
public class Order extends MOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2786680015738701016L;

	/** ID */
	private Long id;

	/** 创建日期 */
	private Date createDate;

	/** 修改日期 */
	private Date modifyDate;
	
	/** 订单号 */
	private String sn;
	
	/** 拆单统一订单编号 */
	private String splitSn;
	
	/** 支付插件 */
	private String pluginName;
	
	/** 订单总金额 */
	private BigDecimal amount;

	/** 已付金额 */
	private BigDecimal amountPaid;

	/** 订单状态 */
	private OrderStatus orderStatus = OrderStatus.uncommitted;

	/** 支付状态 */
	private PaymentStatus paymentStatus = PaymentStatus.unpaid;
	
	/** 订单商品 */
	private Map<Product, Integer> productMap = new HashMap<Product, Integer>();
	
	/**
	 * 订单状态
	 */
	public enum OrderStatus {

		/** 未提交. */
		uncommitted,

		/** 已确认. */
		confirmed,

		/** 已完成 */
		completed,

		/** 已取消 */
		cancelled
	}

	/**
	 * 支付状态
	 */
	public enum PaymentStatus {
		/** 未支付 */
		unpaid,

		/** 已支付 */
		paid;

		public static PaymentStatus get(int index) {
			if (index >= values().length || index < 0) {
				return PaymentStatus.unpaid;
			}
			return values()[index];
		}
	}
	
	/**
	 * 获取ID
	 * 
	 * @return ID
	 */
	@JsonProperty
	@DocumentId
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// MySQL/SQLServer: @GeneratedValue(strategy = GenerationType.AUTO)
	// Oracle: @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
	public Long getId() {
		return id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取创建日期
	 * 
	 * @return 创建日期
	 */
	@JsonProperty
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@DateBridge(resolution = Resolution.SECOND)
	@Column(nullable = false, updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置创建日期
	 * 
	 * @param createDate
	 *            创建日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取修改日期
	 * 
	 * @return 修改日期
	 */
	@JsonProperty
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@DateBridge(resolution = Resolution.SECOND)
	@Column(nullable = false)
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * 设置修改日期
	 * 
	 * @param modifyDate
	 *            修改日期
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	@Override
	public String getSn() {
		return sn;
	}

	@Override
	@Transient
	public Plugin getPayPlugin() {
		return PluginInstance.getInstance().get(pluginName);
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getSplitSn() {
		return splitSn;
	}

	public void setSplitSn(String splitSn) {
		this.splitSn = splitSn;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "aps_order_product")
	public Map<Product, Integer> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<Product, Integer> productMap) {
		this.productMap = productMap;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
