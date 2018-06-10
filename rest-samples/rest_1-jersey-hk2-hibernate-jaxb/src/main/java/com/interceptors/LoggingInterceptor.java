package com.interceptors;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.annotations.Loggable;

// https://stackoverflow.com/questions/33281393/interceptorbinding-is-not-working
@Loggable
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)		// https://docs.oracle.com/javaee/7/tutorial/cdi-adv006.htm
public class LoggingInterceptor implements Serializable {

	private static final long serialVersionUID = 8747054832609934861L;

	public LoggingInterceptor() {

    }

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext) throws Exception {

        System.out.println("Entering method : "
                + invocationContext.getMethod().getName() + " in class "
                + invocationContext.getMethod().getDeclaringClass().getName());

        return invocationContext.proceed();
    }
    
    //@AroundInvoke
    public Object logMethodExit(InvocationContext invocationContext) throws Exception {

        System.out.println("Exiting method : "
                + invocationContext.getMethod().getName() + " in class "
                + invocationContext.getMethod().getDeclaringClass().getName());

        return invocationContext.proceed();
    }

}