package com.hellojd.shopex.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.domain.Auditable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zhaoguoyu on 2018/1/19.
 */
@Aspect
@Component
@Slf4j
public class PersistAop {
  @Pointcut(value = "execution(public * com.hellojd.shopex.repository.*.save*(..))" +
      "||execution(public * com.hellojd.shopex.repository.*.update*(..))"+
      "||execution(public * com.hellojd.shopex.repository.*.insert*(..))"+
      "||execution(public * com.hellojd.shopex.repository.*.add*(..))"+
      "||execution(public * com.hellojd.shopex.repository.*.modify*(..))"
  )
  public void cutService() {
  }
  @Autowired
  AuditingHandler auditingHandler;

  @Before("cutService()")
  public void before(JoinPoint point) throws Throwable {
    Object[] args = point.getArgs();
    if(args.length==0){return;}
    for (Object arg:args){
        if(Auditable.class.isAssignableFrom(arg.getClass())){
          Auditable entity =(Auditable)arg;
          if(entity.isNew()){
            auditingHandler.markCreated(arg);
          }else{
            auditingHandler.markModified(arg);
          }
        }
    }
  }
}
