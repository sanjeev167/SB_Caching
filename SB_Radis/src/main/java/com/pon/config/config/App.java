/**
 * 
 */
package com.pon.config.config;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RExecutorService;
import org.redisson.api.RLock;
import org.redisson.api.RLockReactive;
import org.redisson.api.RLockRx;
import org.redisson.api.RMap;
import org.redisson.api.RMapReactive;
import org.redisson.api.RMapRx;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.RedissonRxClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Sanjeev
 *
 */
@Configuration
public class App {
 
	// 1. Create config object
	Config config = new Config();
	
	config.useClusterServers().addNodeAddress("redis://127.0.0.1:7181");// // use "rediss://" for SSL connection
	      
	      

	// or read config from file
	config = Config.fromYAML(new File("config-file.yaml")); 
	
	
	// 2. Create Redisson instance
	// Sync and Async API
	RedissonClient redisson = Redisson.create(config);
	// Reactive API
	RedissonReactiveClient redissonReactive = Redisson.createReactive(config);
	// RxJava2 API
	RedissonRxClient redissonRx = Redisson.createRx(config);
 
	
	
	// 3. Get Redis based implementation of java.util.concurrent.ConcurrentMap
	RMap map = redisson.getMap("myMap");
	RMapReactive mapReactive = redissonReactive.getMap("myMap");
	RMapRx mapRx = redissonRx.getMap("myMap");
	
	
	// 4. Get Redis based implementation of java.util.concurrent.locks.Lock
	RLock lock = redisson.getLock("myLock");
	RLockReactive lockReactive = redissonReactive.getLock("myLock");
	RLockRx lockRx = redissonRx.getLock("myLock");
	
	// 4. Get Redis based implementation of java.util.concurrent.ExecutorService
	RExecutorService executor = redisson.getExecutorService("myExecutorService");
	
	// over 50 Redis based Java objects and services ...
}
}
