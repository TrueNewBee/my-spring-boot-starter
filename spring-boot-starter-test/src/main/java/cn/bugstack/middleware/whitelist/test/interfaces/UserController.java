package cn.bugstack.middleware.whitelist.test.interfaces;

import cn.bugstack.middleware.methodext.annotation.DoMethodExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.chentianxiang.middleware.hystrix.annotation.DoHystrix;
import vip.chentianxiang.middleware.ratelimiter.annotation.DoRateLimiter;
import vip.chentianxiang.middleware.whitelist.annotation.DoWhiteList;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 白名单测试
     * 通过：http://localhost:8081/api/queryUserInfo01?userId=aaa
     * 拦截：http://localhost:8081/api/queryUserInfo01?userId=123
     */
    @DoWhiteList(key = "userId", returnJson = "{\"code\":\"1111\",\"info\":\"非白名单可访问用户拦截！\"}")
    @RequestMapping(path = "/api/queryUserInfo01", method = RequestMethod.GET)
    public UserInfo queryUserInfo_01(@RequestParam String userId) {
        logger.info("查询用户信息，userId：{}", userId);
        return new UserInfo("虫虫:" + userId, 19, "天津市东丽区万科赏溪苑14-0000");
    }

    /**
     * 熔断超时测试
         * 测试：http://localhost:8081/api/queryUserInfo02?userId=aaa
     */
    @DoHystrix(timeoutValue = 350, returnJson = "{\"code\":\"1111\",\"info\":\"调用方法超过350毫秒，熔断返回！\"}")
    @RequestMapping(path = "/api/queryUserInfo02", method = RequestMethod.GET)
    public UserInfo queryUserInfo_02(@RequestParam String userId) throws InterruptedException {
        logger.info("查询用户信息，userId：{}", userId);
        Thread.sleep(1000);
        return new UserInfo("虫虫:" + userId, 19, "天津市东丽区万科赏溪苑14-0000");
    }


    /**
     * 调用限流测试:
     * 测试：http://localhost:8081/api/queryUserInfo03?userId=aaa
     */
    @DoRateLimiter(permitsPerSecond = 1, returnJson = "{\"code\":\"1111\",\"info\":\"调用方法超过最大次数，限流返回！\"}")
    @RequestMapping(path = "/api/queryUserInfo03", method = RequestMethod.GET)
    public UserInfo queryUserInfo03(@RequestParam String userId) throws InterruptedException {
        logger.info("查询用户信息，userId：{}", userId);
        return new UserInfo("虫虫:" + userId, 19, "天津市东丽区万科赏溪苑14-0000");
    }
    /**
     * 放行：http://localhost:8081/api/queryUserInfo04?userId=aaa
     * 拦截：http://localhost:8081/api/queryUserInfo04?userId=bbb
     */
    @DoMethodExt(method = "blacklist", returnJson = "{\"code\":\"1111\",\"info\":\"自定义校验方法拦截，不允许访问！\"}")
    @RequestMapping(path = "/api/queryUserInfo04", method = RequestMethod.GET)
    public UserInfo queryUserInfo04(@RequestParam String userId) {
        logger.info("查询用户信息，userId：{}", userId);
        return new UserInfo("虫虫:" + userId, 19, "天津市东丽区万科赏溪苑14-0000");
    }

    /**
     * 自定义黑名单，拦截方法
     */
    public boolean blacklist(@RequestParam String userId) {
        if ("bbb".equals(userId) || "222".equals(userId)) {
            logger.info("拦截自定义黑名单用户 userId：{}", userId);
            return false;
        }
        return true;
    }

}

