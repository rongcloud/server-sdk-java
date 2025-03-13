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
	 * List of API domains
	 */
	public String[] apiDomains = new String[] {};
	
	/**
	 * Number of failures required before switching domains
	 */
	public int errorSwitchingThreshold = 1;
	
	/**
	 * HTTP TCP connection timeout in milliseconds
	 */
	public int httpConnectTimeout = 30000;
	
	/**
	 * HTTP response timeout in milliseconds
	 */
	public int httpReadTimeout = 30000;


	/**
	 * Whether to maintain a persistent connection
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
