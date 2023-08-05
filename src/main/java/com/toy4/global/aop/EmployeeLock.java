package com.toy4.global.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EmployeeLock {
  
  // 회원가입시 이메일 중복 방지를 위한 LOCK 어노테이션
  long tryLockTime() default 5000L;
}
