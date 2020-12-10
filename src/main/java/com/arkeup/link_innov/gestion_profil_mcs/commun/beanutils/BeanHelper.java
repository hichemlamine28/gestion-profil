package com.arkeup.link_innov.gestion_profil_mcs.commun.beanutils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;



public class BeanHelper {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy mm:ss,ZZZ");

	public static String getBeanName(String className) {
		StringBuilder name = new StringBuilder();
		boolean firstCharacterToLowerCase = false;
		if (className != null) {
			for (String split : className.split("[.]")) {
				if (split != null && split.length() > 0) {
					if (firstCharacterToLowerCase) {
						name.append(split.substring(0, 1).toUpperCase());
					} else {
						name.append(split.substring(0, 1).toLowerCase());
						firstCharacterToLowerCase = true;
					}

					if (split != null && split.length() > 1) {
						name.append(split.substring(1));
					}
				}
			}
		}
		return name.toString();
	}

	public static void toString(StringBuilder objetBuilder, Object objet) throws Throwable {
		elementToString(objetBuilder, objet, new HashSet<String>());
	}

	private static void elementToString(StringBuilder objetBuilder, Object objet, Set<String> references) throws Throwable {
		if (objet != null) {
			// gestion reference cyclique
			if (!(isPrimitive(objet) || isDate(objet))) {
				String reference = getReference(objet);
				if (references.contains(reference)) {
					objetBuilder.append("[reference cyclique]");
					return;
				} else {
					references.add(reference);
				}
			}
			objetBuilder.append("[");
			objetBuilder.append(objet.getClass().getName());
			objetBuilder.append("[");
			if (objet instanceof Iterable) {
				iterableToString(objetBuilder, objet, references);
			} else if (objet instanceof Map) {
				mapToString(objetBuilder, objet, references);
			} else {
				objectToString(objetBuilder, objet, references);
			}
			objetBuilder.append("]]");
		}
	}

	private static void iterableToString(StringBuilder objetBuilder, Object objet, Set<String> references) throws Throwable {
		Iterable iterable = (Iterable) objet;
		Iterator iterator = iterable.iterator();
		while (iterator.hasNext()) {
			elementToString(objetBuilder, iterator.next(), references);
		}
	}

	private static void mapToString(StringBuilder objetBuilder, Object objet, Set<String> references) throws Throwable {
		Map map = (Map) objet;
		Object[] mapArray = map.keySet().toArray();
		for (int i = 0; i < mapArray.length; i++) {
			elementToString(objetBuilder, mapArray[i], references);
		}
	}

	private static void objectToString(StringBuilder objetBuilder, Object objet, Set<String> references) throws Throwable {
		Class objetClass = objet.getClass();

		// cas d'un objet primitif
		if (isPrimitive(objet)) {
			objetBuilder.append(objet);
			return;
		}

		// cas d'un objet date
		if (isDate(objet)) {
			objetBuilder.append(dateToString(objet));
			return;
		}

		// cas d'un objet complexe
		Method[] methods = objetClass.getDeclaredMethods();
		for (Method method : methods) {
			if (isGetter(method)) {

				try {
					String champs = getFieldName(method);
					
					objetBuilder.append(" ");
					objetBuilder.append(champs);
					objetBuilder.append(" ");

					Object obj = getFieldValue(objetClass, champs, objet);
					elementToString(objetBuilder, obj, references);

				} catch (Exception e) {
					objetBuilder.append(e.getMessage());
				}
			}
		}
	}

	public static boolean isPrimitive(Object objet) {

		Class objetClass = objet.getClass();

		return isPrimitive(objetClass);
	}

	public static boolean isPrimitive(Class objetClass) {

		return (objetClass.isPrimitive() || objetClass.equals(Boolean.class) || objetClass.equals(Byte.class) || objetClass.equals(Character.class) || objetClass.equals(Short.class)
				|| objetClass.equals(Integer.class) || objetClass.equals(Long.class) || objetClass.equals(Float.class) || objetClass.equals(Double.class) || objetClass.equals(String.class) || objetClass
				.equals(BigDecimal.class));
	}

	private static boolean isDate(Object objet) {

		return ((objet instanceof java.util.Date) || (objet instanceof java.util.Calendar));
	}

	private static String dateToString(Object objet) {
		String date = null;
		if (objet instanceof java.util.Date) {
			date = sdf.format((java.util.Date) objet);
		} else {
			date = sdf.format(((java.util.Calendar) objet).getTime());
		}
		return date;
	}

	public static Object executeChangementList(Object retVal) throws Throwable {

		HashMap<String, Object> listRef = new HashMap<String, Object>();

		Object returnObject = elementToObject(retVal, listRef);
		retVal = returnObject;
		return retVal;
	}

	private static Object iterableToArrayList(Object objet, Map<String, Object> references) throws Throwable {
		
		if (objet != null) {
			List<Object> list = new ArrayList<Object>();
			Iterable iterable = (Iterable) objet;
			Iterator iterator = iterable.iterator();
			while (iterator.hasNext()) {
				list.add(elementToObject(iterator.next(), references));
			}
			return list;
		}else{
			return null;
		}

	}

	private static Object elementToObject(Object objet, Map<String, Object> references) throws Throwable {

		if (objet != null) {
			// gestion reference cyclique
			if (!(isPrimitive(objet) || isDate(objet))) {
				String reference = getReference(objet);
				if (references.containsKey(reference)) {
					return references.get(reference);
				}
			}
			if (objet instanceof Iterable) {
				return iterableToArrayList(objet, references);
			} else if (!(objet instanceof Iterable) && !(isPrimitive(objet) || isDate(objet) || isEnum(objet))) {

				Class objetClass = objet.getClass();

				Object returnObject = objetClass.newInstance();

				String reference = getReference(objet);
				references.put(reference, returnObject);

				//on prend toute les m�thodes de l'objet y compris celle h�rit� extends  et pas uniquement les m�thode de l'objet
				for (Method method : objetClass.getMethods()) {
					
					//isNotGetterExcepted permet d'eviter les methode de la classe Class ou nous ne devons pas utilisez les m�thodes pour faire des getter
					if (isGetter(method) && isNotGetterExcepted(method)) {
						if (method.getReturnType() == List.class || objetClass == Vector.class) {

							// on recupere le nom du champs
							String champs = getFieldName(method);
							
							Object obj = getFieldValue(objetClass, champs, objet);

							Method methodeSet = convertGetterToSetter(objetClass, method);
							methodeSet.invoke(returnObject, iterableToArrayList(obj, references));
							
						} else {

							Object fieldValue = method.invoke(objet, null);
							if (fieldValue != null && fieldValue.getClass().isArray() && isPrimitive(fieldValue.getClass().getComponentType())) {
								// on fait rien
							} else if (fieldValue != null && fieldValue.getClass().isArray()) {
								fieldValue = arrayToArray((Object[]) fieldValue, references);
							} else if (fieldValue != null) {
								fieldValue = elementToObject(fieldValue, references);
							}

							// on ne fait pas de Method set car cela engendre des probl�mes sur les entity.
							// (les m�thodes set avec entity mette des ref�rences sur des champs cach� persistence...)

							// on recupere le nom du champs
							String champs = getFieldName(method);

							setFieldValue(objetClass, champs, returnObject, fieldValue);
						}
					}
				}

				objet = returnObject;

			} else {
				//les type primitif et les dates sont automatiquement retourn�s
			}
				
		} else {
			objet = null;
		}
		return objet;
	}

	
	private static boolean isEnum(Object objet){
		
		return objet.getClass().isEnum();
	}

	
	private static boolean isNotGetterExcepted(Method method){
		
		if(method.getDeclaringClass().equals(Class.class) || method.getDeclaringClass().equals(Object.class)){
			return false;
		}
		else{
			return true;
		}
	}

	private static Object[] arrayToArray(Object[] objets, Map<String, Object> references) throws Throwable {
		List<Object> list = new ArrayList<Object>();
		for (Object element : objets) {
			list.add(elementToObject(element, references));
		}
		return list.toArray();
	}

	private static String getReference(Object objet) {
		return objet.getClass().getName() + '@' + Integer.toHexString(objet.hashCode());
	}
	
	private static boolean isGetter(Method method) {
		String methodName = method.getName();
		return (methodName.startsWith("is") || methodName.startsWith("get")) && (!"get".equals(methodName)) && (!"is".equals(methodName)) && (method.getParameterTypes().length == 0);
	}
	
	private static String getFieldName(Method method) {
		String methodName = method.getName();
		String champs = null;
		if (methodName.startsWith("is")) {
			champs = methodName.substring(2);
		} else {
			champs =methodName.substring(3);
		}
		return champs.substring(0, 1).toLowerCase() + champs.substring(1);
	}
	
	private static Object getFieldValue(Class objetClass, String champs, Object objet) throws Throwable {
		Field field =null;
		try{
			field = objetClass.getDeclaredField(champs);
		}catch(NoSuchFieldException e){
			//si on ne trouve pas le champs alors on va dans la super classe
			objetClass=objetClass.getSuperclass();
			field=objetClass.getDeclaredField(champs);
		}
		
		boolean accessibilite = field.isAccessible();
		field.setAccessible(true);
		Object obj = field.get(objet);
		field.setAccessible(accessibilite);
		return obj;
	}

	private static void setFieldValue(Class objetClass, String champs, Object objet, Object value) throws Throwable {
		
		Field field =null;
		try{
			field = objetClass.getDeclaredField(champs);
		}catch(NoSuchFieldException e){
			//si on ne trouve pas le champs alors on va dans la super classe
			objetClass=objetClass.getSuperclass();
			field=objetClass.getDeclaredField(champs);
		}
		
		boolean accessibilite = field.isAccessible();
		field.setAccessible(true);
		field.set(objet, value);
		field.setAccessible(accessibilite);
	}
	
	private static Method convertGetterToSetter(Class objetClass, Method getter) throws Throwable {
		String setterName = null;
		if(getter.getName().startsWith("get")) {
			setterName = getter.getName().replaceFirst("get", "set");
		}
		if(getter.getName().startsWith("is")) {
			setterName = getter.getName().replaceFirst("is", "set");
		}
		Method method=null;
		try{
			method=objetClass.getMethod(setterName, getter.getReturnType());
		}catch(NoSuchMethodException e){
			//si on ne trouve pas le champs alors on va dans la super classe
			objetClass=objetClass.getSuperclass();
			method=objetClass.getMethod(setterName, getter.getReturnType());
		}
		return method;
	}
}
