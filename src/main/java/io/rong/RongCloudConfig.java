package io.rong;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RongCloudConfig {
	
	public RongCloudConfig(String... apiDomains) {
		this.apiDomains = apiDomains;
	}
	public RongCloudConfig(List<String> apiDomainList) {
		if(apiDomainList!=null && apiDomainList.size()>0) {
			this.apiDomains = apiDomainList.toArray(new String[] {});
		}
	}
	
	
	/**
	 * Api 的域名列表
	 */
	public String[] apiDomains = new String[] {};
	
	/**
	 * 访问失败满多少次后进行切换
	 */
	public int errorSwitchingThreshold = 1;
	
	/**
	 * http Tcp链接建立超时时间设置，单位ms
	 */
	public int httpConnectTimeout = 30000;
	
	/**
	 * http获取响应的超时时间设置，单位ms
	 */
	public int httpReadTimeout = 30000;


	/**
	 * 是否维持长连接
	 */
	public boolean connectionKeepAlive = false;
	
	
	
	public AtomicInteger errorCounter = new AtomicInteger(0);
	
	private AtomicInteger index = new AtomicInteger(0);
	public String getDomain() {
		try {
			if(this.errorCounter.get()>=this.errorSwitchingThreshold) {
				this.errorCounter.set(0);
				this.index.incrementAndGet();
			}
			return this.apiDomains[Math.abs(this.index.get())%this.apiDomains.length];
		}catch(Exception e) {
			throw new RuntimeException("Can not get Server API Domain.", e);
		}
	}
}
