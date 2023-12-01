package org.ixkit.anvilj.config;

import springfox.documentation.RequestHandler;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;

import java.util.LinkedList;
import java.util.List;


/**
 * @class:SwaggerUtils
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 30/07/2022
 * @version:0.1.0
 * @purpose:
 */
public class SwaggerUtils {

    // 定义分隔符
    public static final String splitor = ";";

    // "a.b.c","x.y.z"
    public static Predicate<RequestHandler> basePackages(final String ... basePackages) {
        int max = basePackages.length;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < max; i++) {
            sb.append(basePackages[i]);
            if (i < max-1){
                sb.append(splitor);
            }
        }
        return parseBasePackage(sb.toString());
    }
    // "a.b.c;x.y.z" RequestHandlerSelectors.basePackage("org.jeecg")
    public static Predicate<RequestHandler> parseBasePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

}
