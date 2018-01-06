package com.hellojd.shopex.util;
import com.hellojd.shopex.bean.CategoryBean;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @author zhaogy
 */
public class TreeGridUtils {
  public static <T extends CategoryBean> Set<T> build(Set<T> nodes){
        if(CollectionUtils.isEmpty(nodes)){
            return Collections.EMPTY_SET;
        }
        Set<T> plainList = new LinkedHashSet<>();
        for (T node:nodes){
            plainList.add(node);
            build(plainList,node.getChildren());
        }
        return plainList;
    }

    private static  <T extends CategoryBean> void   build( Set<T> plainList,Set<T> nodes){
        if(CollectionUtils.isEmpty(nodes)){
            return;
        }
        for (T node:nodes){
            plainList.add(node);
            build(node.getChildren());
        }
    }
}
