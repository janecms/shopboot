package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.entity.MemberRank;
import com.hellojd.shopex.repository.MemberRankRepository;
import com.hellojd.shopex.service.MemberRankService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberRankServiceImpl extends ServiceImpl<MemberRankRepository,MemberRank> implements MemberRankService {

    @Override
    public List<MemberRank> findAll() {
        MemberRank memberRank = new MemberRank();
        Wrapper<MemberRank> ew = new EntityWrapper<>(memberRank);
        return this.baseMapper.selectList(ew);
    }
}
