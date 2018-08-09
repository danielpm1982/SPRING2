package com.danielpm1982.Maven_Web_Spring_JPA_AOP.aop;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class ControllerServiceDAOAOP {
	@Pointcut("execution(* *.*.*.controller.*.*(..))")
	private void controllerPointCut() {}
	@Pointcut("execution(* *.*.*.controller.*.initBinder(..))")
	private void controllerPointCutDoNot() {}
	@Pointcut("execution(* *.*.*.service.*.*(..))")
	private void servicePointCut() {}
	@Pointcut("execution(* *.*.*.dao.*.*(..))")
	private void daoPointCut() {}
	@Pointcut("controllerPointCut() && !controllerPointCutDoNot() || servicePointCut() || daoPointCut()")
	private void GeneralPointCut() {}
	@Before	("GeneralPointCut()")
	public void beforeMethodCall(JoinPoint joinPoint) {
		String matchingMethodSignature =  joinPoint.getSignature().toShortString();
		Object[] matchingArgArray = joinPoint.getArgs();
		List<String> matchingArgList = Arrays.asList(matchingArgArray).stream().map(Object::toString).collect(Collectors.toList());
		String matchingSingleArgString = (!matchingArgList.isEmpty()?matchingArgList.toString():"null arg");
		System.out.println("AOP @Before - signature: "+matchingMethodSignature+" arg:"+matchingSingleArgString);
	}
	@AfterReturning	(pointcut="GeneralPointCut()", returning="result")
	public void afterReturningFromMethodCall(JoinPoint joinPoint, Object result) {
		String matchingMethodSignature =  joinPoint.getSignature().toShortString();
		Object[] matchingArgArray = joinPoint.getArgs();
		List<String> matchingArgList = Arrays.asList(matchingArgArray).stream().map(Object::toString).collect(Collectors.toList());
		String matchingSingleArgString = (!matchingArgList.isEmpty()?matchingArgList.toString():"null arg");
		System.out.println("AOP @AfterReturning - signature: "+matchingMethodSignature+" arg:"+matchingSingleArgString);
		System.out.println("At @Aspect: Return result: "+(result!=null?result:"null result!"));
	}
}
