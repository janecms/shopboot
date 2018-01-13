package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.bean.RefBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 */
@Slf4j
public class ShopBaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements IService<T> {

    /**
     * 同步
     * @param requests:提交列表*
     * @param persists 保存列表
     * @param persists 保存列表
     */
    protected  <S> RefResult<S> updateRefResult(Set<S> requests, Set<S> persists ,RefResultUpdater<S> updater){
        Collection<S> saves =null;
        Collection<S> deletes =null;
        Set<S> poSet =new HashSet<>();
        if(CollectionUtils.isNotEmpty(persists)){
            persists.forEach(item ->{
                poSet.add(item);
            });
        }
        if(CollectionUtils.isEmpty(poSet)){
            saves=requests;
        }else{
            if (CollectionUtils.isEmpty(requests)){
                deletes=poSet;
            }else{
                saves=CollectionUtils.removeAll(requests,poSet);
                deletes=CollectionUtils.removeAll(poSet,requests);
            }
        }
        if(CollectionUtils.isEmpty(saves)){
          saves= Collections.emptySet();
        }
        if(CollectionUtils.isEmpty(deletes)){
            deletes=Collections.emptySet();
        }
        final RefResult refResult = new RefResult(saves, deletes);
        if (updater!=null){updater.execute(refResult);}
        return refResult;
    }


    public interface RefResultUpdater<S>{
        void execute(RefResult<S> refResult);
    }

    public static class RefResult<T> {
         RefResult(Collection<T> savingSet, Collection<T> deletingSet) {
            this.savingSet = savingSet;
            this.deletingSet = deletingSet;
        }
        Collection<T> savingSet;
        Collection<T> deletingSet;
        //需要保存的结果
        public Collection<T> getSavingSet() {
            return savingSet;
        }
        //需要删除的结果
        public Collection<T> getDeletingSet() {
            return deletingSet;
        }
    }

}
