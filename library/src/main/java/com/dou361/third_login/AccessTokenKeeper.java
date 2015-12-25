/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dou361.third_login;

import android.content.Context;

import com.dou361.utils.SPUtils;
import com.dou361.utils.UIUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * 该类定义了微博授权时所需要的参数。
 *
 * @author SINA
 * @since 2013-10-07
 */
public class AccessTokenKeeper {

    /**
     * action的两个值
     */
    public static final String QQ_Action = "qq";
    public static final String WeiBo_Action = "weibo";
    /**
     * token的信息标识
     */
    private static final String KEY_UID = "uid";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_EXPIRES_IN = "expires_in";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";

    /**
     * 保存 Token
     */
    public static void writeAccessToken(String action, Oauth2AccessToken token) {
        if (token == null) {
            return;
        }
        Context context = UIUtils.getContext();
        if (context == null) {
            return;
        }
        SPUtils.putData(context, action + KEY_UID, token.getUid());
        SPUtils.putData(context, action + KEY_ACCESS_TOKEN, token.getToken());
        SPUtils.putData(context, action + KEY_REFRESH_TOKEN,
                token.getRefreshToken());
        SPUtils.putData(context, action + KEY_EXPIRES_IN,
                token.getExpiresTime());
    }

    /**
     * 从 SharedPreferences 读取 Token 信息。
     */
    public static Oauth2AccessToken readAccessToken(String action) {
        Context context = UIUtils.getContext();
        if (context == null) {
            return null;
        }
        Oauth2AccessToken token = new Oauth2AccessToken();
        token.setUid((String) SPUtils.getData(context, action + KEY_UID, ""));
        token.setToken((String) SPUtils
                .getData(context, action + KEY_ACCESS_TOKEN, ""));
        token.setRefreshToken((String) SPUtils.getData(context, action
                + KEY_REFRESH_TOKEN, ""));
        token.setExpiresTime((long) SPUtils.getData(context, action + KEY_EXPIRES_IN,
                0));
        return token;
    }

    /**
     * 清空 SharedPreferences 中 Token信息。
     */
    public static void clear(String action) {
        Context context = UIUtils.getContext();
        if (context == null) {
            return;
        }
        SPUtils.remove(context, action + KEY_UID);
        SPUtils.remove(context, action + KEY_ACCESS_TOKEN);
        SPUtils.remove(context, action + KEY_REFRESH_TOKEN);
        SPUtils.remove(context, action + KEY_EXPIRES_IN);
    }
}
