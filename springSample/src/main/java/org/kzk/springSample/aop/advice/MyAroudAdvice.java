package org.kzk.springSample.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAroudAdvice implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("before method");
		Object obj = invocation.proceed();
		System.out.println("after method");
		return obj;
	}
}
