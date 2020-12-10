package com.arkeup.link_innov.gestion_profil_mcs.commun.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.arkeup.link_innov.gestion_profil_mcs.commun.beanutils.BeanHelper;



@Aspect
@Order(2)
public class LoggerAspect {
	
	/**
	 * 
	 * Aspect de log execute au debut et a la fin de chaque methode declaree dans le grefon @Around
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(public * com.arkeup.link_innov.gestion_profil_mcs.service..*.*(..))")
	public Object logAccesService(ProceedingJoinPoint pjp) throws Throwable {
		long time = System.currentTimeMillis();
		
		org.slf4j.Logger logger=org.slf4j.LoggerFactory.getLogger(pjp.getTarget().getClass());
		Slf4JStopWatch slf4jStopWatch = null;
		ajouterLogin();
		StringBuilder signature = extraireSignatureMethode(pjp,logger);
		StringBuilder methodeName = extraireNameMethode(pjp,logger);
		
		org.slf4j.MDC.put(VariableMCDLogEnum.DEBUT.getVarName(), VariableMCDLogEnum.DEBUT.getValue());
		StringBuilder message = new StringBuilder();		
		message.append("<entree> ");
		message.append(signature);
		logger.info(message.toString());
		
		if(org.slf4j.LoggerFactory.getLogger(Slf4JStopWatch.DEFAULT_LOGGER_NAME).isDebugEnabled()) {
			slf4jStopWatch=new Slf4JStopWatch();
			slf4jStopWatch.lap(methodeName.toString());
		}
		
		Object objet = pjp.proceed();

		org.slf4j.MDC.put(VariableMCDLogEnum.FIN.getVarName(), VariableMCDLogEnum.FIN.getValue());
		if(org.slf4j.LoggerFactory.getLogger(Slf4JStopWatch.DEFAULT_LOGGER_NAME).isDebugEnabled()) {
			slf4jStopWatch.stop(methodeName.toString());
			org.slf4j.MDC.put(VariableMCDLogEnum.FIN.getVarName(), VariableMCDLogEnum.FIN.getValue());
		}
		
		message = new StringBuilder();
		message.append("<sortie> ");
		message.append((System.currentTimeMillis() - time));
		message.append("ms - ");
		message.append(signature);
		
		logger.info(message.toString());

		return objet;
	}
	
	/**
	 * Extraire la signature de la methode appelee
	 * @param pjp
	 * @param logger
	 * @return
	 */
	private StringBuilder extraireSignatureMethode(ProceedingJoinPoint pjp,Logger logger){
		StringBuilder signature = new StringBuilder();
		signature.append(pjp.getSignature().getDeclaringTypeName());
		signature.append(".");
		signature.append(pjp.getSignature().getName());
		signature.append("(");
		if(logger.isDebugEnabled()) {
			try{
				for(Object objet : pjp.getArgs()) {
					signature.append("\n\t");
					BeanHelper.toString(signature, objet);
				}
			}catch(Throwable exception){
				logger.error(exception.getMessage());
			}
		}
		signature.append(")");
		
		return signature;
	}
	

	
	/**
	 * Extraire nom de la methode appelee
	 * @param pjp
	 * @param logger
	 * @return
	 */
	private StringBuilder extraireNameMethode(ProceedingJoinPoint pjp,org.slf4j.Logger logger){
		StringBuilder signature = new StringBuilder();
		signature.append(pjp.getSignature().getDeclaringTypeName());
		signature.append(".");
		signature.append(pjp.getSignature().getName());
		
		return signature;
	}
	
	/**
	 * Extraire la signature de la methode appelee
	 * @param pjp
	 * @param logger
	 * @return
	 */
	private StringBuilder extraireSignatureMethode(ProceedingJoinPoint pjp,org.slf4j.Logger logger){
		StringBuilder signature = new StringBuilder();
		signature.append(pjp.getSignature().getDeclaringTypeName());
		signature.append(".");
		signature.append(pjp.getSignature().getName());
		signature.append("(");
		if(logger.isDebugEnabled()) {
			try{
				for(Object objet : pjp.getArgs()) {
					signature.append("\n\t");
					BeanHelper.toString(signature, objet);
				}
			}catch(Throwable exception){
				logger.error(exception.getMessage());
			}
		}
		signature.append(")");
		
		return signature;
	}
	
	/**
	 * Extraire les donnee en sortie de la methode logger ( Valeur des objets )
	 * @param objet
	 * @param logger
	 * @return
	 */
	private StringBuilder extraireRetourMethode(Object objet,Logger logger){
		StringBuilder retour = new StringBuilder();
		if(logger.isDebugEnabled()) {
			try{
				retour.append("\n\t[retour ");
				BeanHelper.toString(retour, objet);
				retour.append("]");
			}catch(Throwable exception){
				logger.error(exception.getMessage());
			}
		}
		
		return retour;
	}
	
	/**
	 * Extraire les donnee en sortie de la methode logger ( Valeur des objets )
	 * @param objet
	 * @param logger
	 * @return
	 */
	private StringBuilder extraireRetourMethode(Object objet,org.slf4j.Logger logger){
		StringBuilder retour = new StringBuilder();
		if(logger.isDebugEnabled()) {
			try{
				retour.append("\n\t[retour ");
				BeanHelper.toString(retour, objet);
				retour.append("]");
			}catch(Throwable exception){
				logger.error(exception.getMessage());
			}
		}
		
		return retour;
	}
	
	
	/**
	 * Ajout du login connecte
	 * 
	 */
	private void ajouterLogin(){
		
		if ( SecurityContextHolder.getContext()!=null && SecurityContextHolder.getContext().getAuthentication()!=null && SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null ){
			try {
				UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				String loggedUser = user.getUsername();
				MDC.put("IdLogin", loggedUser);
				org.slf4j.MDC.put("IdLogin", loggedUser);
			} catch (Exception e) {
				MDC.put("IdLogin", "XXXXXXXX");
				org.slf4j.MDC.put("IdLogin",  "XXXXXXXX");
			}
		
			
		}
	}
	
	/**
	 * Enumeration a utilise pour Tagger le debut et la fin de l'appel a chaque methode
	 * @author Morad
	 *
	 */
	private static enum VariableMCDLogEnum {
		DEBUT("START_END","S") , FIN("START_END","E");
		
		private String varName;
		private String value;
		
		private VariableMCDLogEnum(String labelLong , String labelShort  ) {
			this.varName = labelLong;
			this.value=labelShort;
		}
		
		/**
		 * @return the labelLong
		 */
		public String getVarName() {
			return varName;
		}

		/**
		 * @return the labelShort
		 */
		public String getValue() {
			return value;
		}

		
	}
}