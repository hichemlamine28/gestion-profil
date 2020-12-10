package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Service
public class AdminSMImpl implements AdminSM {


    @Autowired
    private ApplicationContext ctx;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void reindexAllES(){
        String[] beanNames = ctx.getBeanDefinitionNames();// récupération de tous les beans créé par Spring
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            if(beanName.endsWith("RepositoryImpl")){ // appliquer la réindexation sur tous les repository
                logger.info("start reindexing : {}", beanName);
                Object bean = ctx.getBean(beanName); // récupérer le bean


                try {
                    Method reindexESMethod
                            = bean.getClass().getMethod("reindexES"); //récupérer la fonction reindex du bean actuel

                    reindexESMethod.invoke(bean);// exécuter la fonction

                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                   // if there is no method of that name we ignore the error. No need to handle.
                    logger.warn("reindex error for {}. normally, this warning should be ignored",beanName,e);
                }
            }
        }

    }
}
