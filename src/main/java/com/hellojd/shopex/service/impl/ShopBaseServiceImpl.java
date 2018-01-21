package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.bean.RefBean;
import com.hellojd.shopex.entity.RefId;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;

import java.io.Serializable;
import java.util.*;

/**
 * @author Administrator
 */
@Slf4j
public class ShopBaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IService<T> {

    /**
     * 同步1（策略增删策略)
     * @param requests:提交列表*
     * @param persists       已保存列表
     */
    protected <S> RefResult<S> updateRefResult(Collection<S> requests, Collection<S> persists, RefResultUpdater<S> updater) {
        Collection<S> saves = null;
        Collection<S> deletes = null;
        Set<S> poSet = new HashSet<>();
        if (CollectionUtils.isNotEmpty(persists)) {
            persists.forEach(item -> {
                poSet.add(item);
            });
        }
        if (CollectionUtils.isEmpty(poSet)) {
            saves = requests;
        } else {
            if (CollectionUtils.isEmpty(requests)) {
                deletes = poSet;
            } else {
                saves = CollectionUtils.removeAll(requests, poSet);

                deletes = CollectionUtils.removeAll(poSet, requests);
            }
        }
        if (CollectionUtils.isEmpty(saves)) {
            saves = Collections.emptySet();
        }
        if (CollectionUtils.isEmpty(deletes)) {
            deletes = Collections.emptySet();
        }
        final RefResult refResult = new RefResult(saves, deletes);
        if (updater != null) {
            updater.execute(refResult);
        }
        return refResult;
    }

    /**
     * 同步2（策略增删改策略，基于PK操作)
     * @param requests
     * @param persists
     * @param updater
     * @param <S>
     * @return
     */
    protected <S extends RefId> RefResult<S> doUpdateRefResult(Collection<S> requests, Collection<S> persists, RefUpdater<S> updater) {
        //已去除重复数据
        final Collection disjunction = CollectionUtils.disjunction(requests, persists);
        final Collection<S> saves = CollectionUtils.intersection(disjunction, requests);
        Collection<Serializable> saveIds = CollectionUtils.collect(saves, new Transformer() {
            @Override
            public Object transform(Object input) {
                S entity = (S) input;
                return entity.getId();
            }
        });
        Collection<Serializable> disjunctionIds = CollectionUtils.collect(disjunction, new Transformer() {
            @Override
            public Object transform(Object input) {
                S entity = (S) input;
                return entity.getId();
            }
        });
        Collection<Serializable> deleteIds=CollectionUtils.removeAll(disjunctionIds,saveIds);
        //删除列表
//        Collection<S> deletes = CollectionUtils.removeAll(disjunction, requests);
        //筛选出真正删除记录
        Collection<S> deletes  = CollectionUtils.select(disjunction, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                S entity = (S) object;
                return deleteIds.contains(entity.getId());
            }
        });
        if (CollectionUtils.isNotEmpty(deletes)) {
            deletes.forEach(entity -> {
                updater.deleteById(entity.getId());
            });
            log.info("删除记录条目{}", deletes.size());
        } else {
            log.info("没有删除记录");
        }
        if (CollectionUtils.isNotEmpty(saves)) {
            int updCount = 0;
            int saveCount = 0;
            for (S entity : saves) {
                if (Objects.isNull(entity.getId())) {
                    updater.insert(entity);
                    saveCount++;
                } else {
                    updater.updateById(entity);
                    updCount++;
                }
            }
            log.info("update item size:{},save item size:{}", updCount, saveCount);
        } else {
            log.info("no item to save!!!");
        }
        final RefResult refResult = new RefResult(saves, deletes);
        return refResult;
    }


    public interface RefResultUpdater<S> {
        void execute(RefResult<S> refResult);
    }

    public interface RefUpdater<S> {
        void deleteById(Serializable id);

        void updateById(S entity);

        void insert(S entity);
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
