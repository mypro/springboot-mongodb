package org.xinhua.gk.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Lang {
    public Lang() {
    }

    public static Type[] getTypeParams(Class<?> klass) {
        if (klass != null && !"java.lang.Object".equals(klass.getName())) {
            Type superclass = klass.getGenericSuperclass();
            if (superclass instanceof ParameterizedType) {
                return ((ParameterizedType) superclass).getActualTypeArguments();
            } else {
                Type[] interfaces = klass.getGenericInterfaces();
                Type[] var3 = interfaces;
                int var4 = interfaces.length;

                for (int var5 = 0; var5 < var4; ++var5) {
                    Type inf = var3[var5];
                    if (inf instanceof ParameterizedType) {
                        return ((ParameterizedType) inf).getActualTypeArguments();
                    }
                }

                return getTypeParams(klass.getSuperclass());
            }
        } else {
            return null;
        }
    }

    public static <T> Class<T> getTypeParam(Class<?> klass, int index) throws Exception {
        Type[] types = getTypeParams(klass);
        if (index >= 0 && index < types.length) {
            Type t = types[index];
            Class<T> clazz = (Class<T>) getTypeClass(t);
            if (clazz == null) {
                throw new Exception("Type '%s' is not a Class" + t.toString());
            } else {
                return clazz;
            }
        } else {
            throw new Exception("Class type param out of range %d/%d" + index + types.length);
        }
    }

    public static Class<?> getTypeClass(Type type) {
        Class<?> clazz = null;
        if (type instanceof Class) {
            clazz = (Class) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            clazz = (Class) pt.getRawType();
        } else {
            if (type instanceof GenericArrayType) {
                GenericArrayType gat = (GenericArrayType) type;
                Class<?> typeClass = getTypeClass(gat.getGenericComponentType());
                return Array.newInstance(typeClass, 0).getClass();
            }

            Type[] tLow;
            if (type instanceof TypeVariable) {
                TypeVariable tv = (TypeVariable) type;
                tLow = tv.getBounds();
                if (tLow != null && tLow.length > 0) {
                    return getTypeClass(tLow[0]);
                }
            } else if (type instanceof WildcardType) {
                WildcardType wt = (WildcardType) type;
                tLow = wt.getLowerBounds();
                if (tLow.length > 0) {
                    return getTypeClass(tLow[0]);
                }

                Type[] tUp = wt.getUpperBounds();
                return getTypeClass(tUp[0]);
            }
        }

        return clazz;
    }

    public static String filterString(String str) throws PatternSyntaxException {
        if (StringUtils.isEmpty(str)) {
            return "";
        } else {
            String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"《》]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            return m.replaceAll("@").trim();
        }
    }

    public static int getAge(Date birthday) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) {
            return 0;
        } else {
            int yearNow = cal.get(1);
            int monthNow = cal.get(2);
            int dayOfMonthNow = cal.get(5);
            cal.setTime(birthday);
            int yearBirth = cal.get(1);
            int monthBirth = cal.get(2);
            int dayOfMonthBirth = cal.get(5);
            int age = yearNow - yearBirth;
            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        --age;
                    }
                } else {
                    --age;
                }
            }

            return age;
        }
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof CharSequence && "".equals(obj)) {
            return true;
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else {
            return obj instanceof Map ? ((Map) obj).isEmpty() : false;
        }
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static <T> List<T> list(T... eles) {
        ArrayList<T> list = new ArrayList(eles.length);
        Object[] var2 = eles;
        int var3 = eles.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            T ele = (T) var2[var4];
            list.add(ele);
        }

        return list;
    }


}
