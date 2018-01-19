package com.hellojd.shopex.service.impl;

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
}
