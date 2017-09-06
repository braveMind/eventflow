/* Copyright 2013-2015 www.snakerflow.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.event.flow.core.cache.memory;



import com.event.flow.core.cache.Cache;
import com.event.flow.core.cache.CacheException;
import com.event.flow.core.cache.CacheManager;
import com.event.flow.core.helper.StringHelper;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class MemoryCacheManager implements CacheManager {
	private final ConcurrentMap<String, Cache> caches;
	
	public MemoryCacheManager() {
		this.caches = new ConcurrentHashMap<String, Cache>();
	}
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		if(StringHelper.isEmpty(name)) {
			throw new IllegalArgumentException("Cache名称不能为空.");
		}
        Cache cache;

        cache = caches.get(name);
        if (cache == null) {
            cache = new MemoryCache<Object, Object>(new ConcurrentHashMap<Object, Object>());
            Cache existing = caches.putIfAbsent(name, cache);
            if (existing != null) {
                cache = existing;
            }
        }
        return cache;
	}

    public void destroy() throws CacheException {
        while (!caches.isEmpty()) {
            caches.clear();
        }
    }
}
