package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

public class LocalRibbonClientConfiguration {
	
	 @Autowired
		IClientConfig ribbonClientConfig;
		
		@Bean
		public IRule ribbonRule(IClientConfig ribbonClientConfig ) {
			return new AvailabilityFilteringRule();
		}

		@Bean
		public IPing ribbonPing() {
			return new PingUrl();
		}
		
//		 @Bean
//		    public ServerList<Server> ribbonServerList() {
//		        // return new ConfigurationBasedServerList();
//		        StaticServerList<Server> staticServerList = new StaticServerList<>(
//		                new Server("localhost", 9090));
//		        return staticServerList;
//		    }

}
