package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hellojd.shopex.entity.Log;
import com.hellojd.shopex.repository.LogRepository;
import com.hellojd.shopex.service.LogService;
import org.apache.log4j.spi.LoggerRepository;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends ServiceImpl<LogRepository,Log> implements LogService {
}
