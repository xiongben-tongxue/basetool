/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  emoji 过滤工具类
 *  @author yangjh
 */
public final class EmojiFilterUtil {

    private static Pattern emoji =
            Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    public static String filterEmoji(String content) {
        if (StringUtils.isEmpty(content)) {
            return "";
        }
        Matcher emojiMatcher = emoji.matcher(content);
        if (emojiMatcher.find()) {
            content = emojiMatcher.replaceAll("");
        }
        return content;
    }

    public static boolean containEmoji(String content) {
        // TODO: 16/06/2017
        Matcher emojiMatcher = emoji.matcher(content);
        return emojiMatcher.find();
    }

//    public static void main(String[] args) {
//        String content = "\\U65e0x\\U2006h\\U2006di\\U2006y\\Ud83d\\Udc45";
//        System.out.print("content:" + filterEmoji(content));
//        System.out.print("containEmoji:" + containEmoji(content));
//    }
}
