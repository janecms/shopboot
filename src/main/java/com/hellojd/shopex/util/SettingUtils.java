package com.hellojd.shopex.util;

import com.hellojd.shopex.common.ShopxxSettings;

/**
 * @author Administrator
 */
public final class SettingUtils {
    public static ShopxxSettings get() {
        return SpringUtils.getBean(ShopxxSettings.class);
    }
}
