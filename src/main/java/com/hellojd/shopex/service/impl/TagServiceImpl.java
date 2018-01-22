package com.hellojd.shopex.service.impl;

import com.hellojd.shopex.util.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.entity.Tag;
import com.hellojd.shopex.repository.TagRepository;
import com.hellojd.shopex.service.TagService;

/**
 *
 * @author zhaoguoyu
 * @date 2018/1/19
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagRepository, Tag> implements TagService {
    @Override
    public void save(Tag tag) {
        this.baseMapper.insert(tag);
    }

    @Override
    public void update(Tag tag) {
        final Tag po = this.baseMapper.selectById(tag.getId());
        BeanUtils.copyProperties(tag,po,new String[] { "type", "articles", "products" });
        this.baseMapper.updateById(po);
    }
}
