package cn.johnyu;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.net.URL;

public class MyApp {
    public static void main(String[] args) throws Exception{
        System.out.println(System.getProperty("java.io.tmpdir"));
        // 1. 创建缓存管理器
        CacheManager cacheManager = CacheManager
                .create(MyApp.class.getClassLoader().getResourceAsStream("ehcache.xml"));
        // 2. 获取缓存对象

        Cache cache = cacheManager.getCache("AppCache");

        // 3. 创建元素
        Element element = new Element("key1", "value1");

        // 4. 将元素添加到缓存
        cache.put(element);
        cache.put(new Element("k2","value2"));

        // 5. 获取缓存
        Element value = cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());

        // 6. 删除元素
//        cache.remove("key1");

        Person p1 = new Person("John",100,"北京");
        Element pelement = new Element("xm", p1);
        cache.put(pelement);
        Element pelement2 = cache.get("xm");
        System.out.println(pelement2.getObjectValue());

        System.out.println(cache.getSize());

        // 7. 刷新缓存
        cache.flush();

        // 8. 关闭缓存管理器
        cacheManager.shutdown();

    }
}
