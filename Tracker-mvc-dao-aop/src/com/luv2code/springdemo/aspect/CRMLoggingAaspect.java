package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAaspect {

	// setup logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}

	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}

	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
		
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	// @Before Advice
	@Before("forAppFlow()")
	public void doLogging(JoinPoint theJoinPoint){
		
		String method = theJoinPoint.getSignature().toShortString();
		logger.info("===> in @Before: calling method: " + method);
		
		
		// get the arguments
		Object[] args = theJoinPoint.getArgs();
		
		for(Object object : args) {
			logger.info("===> object: "+ object);
		}
	}
	
	// add @AfterReturning advice
	// @Before Advice
	@AfterReturning(pointcut="forAppFlow()", returning="theResult")
	public void afterReturnung(JoinPoint theJoinPoint, Object theResult){
		
		String method = theJoinPoint.getSignature().toShortString();
		logger.info("===> in @AfterReturning: calling method: " + method);
		
		
		// get the result
		logger.info("===> @AfterReturning object: "+ theResult);

	}	
}
