package com.hellojd.shopex.service;

import com.baomidou.mybatisplus.service.IService;
import com.hellojd.shopex.entity.Log;
import com.hellojd.shopex.entity.MemberRank;

import java.util.List;

/**
 * @author Administrator
 */
public interface MemberRankService extends IService<MemberRank> {
    List<MemberRank> findAll();
}
