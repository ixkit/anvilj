/**
 * Copyright © 2020-2021 The tagsql Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ixkit.land.utils;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * @class:StringUtil
 * @author: RobinZ iRobinZhang@hotamil.com
 * @date: 02/07/2021 
 * @version:0.1.0
 * @purpose:StringUtil
 */
public class StringUtil {

    public static boolean isEmpty(String value){
        return StringUtils.isEmpty(value);
    }


    public static String bytes2Str(byte[] bytes){
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String sha256(String value){
        String sha256hex = Hashing.sha256()
                .hashString(value, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }
    public static boolean equals(final String cs1, final String cs2) {
        //同一对象返回true
        if (cs1 == cs2) {
            return true;
        }
        //一个或两个都为null返回false
        if (cs1 == null || cs2 == null) {
            return false;
        }
        //两个字符串的长度不一样返回false
        if (cs1.length() != cs2.length()) {
            return false;
        }
        //两个都是String实例，则通过equals方法比较，本质是使用==比较两个对象
        //   if (cs1 instanceof String && cs2 instanceof String) {
        return cs1.equals(cs2);
        //   }
        // return false;
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if ("null".equals(object)) {
            return (true);
        }
        return (false);
    }

    public static String getString(String s) {
        return getString(s,"");
    }
    public static String getString(String s, String defval) {
        if (isEmpty(s)) {
            return (defval);
        }
        return (s.trim());
    }
}
