package org.xinhua.gk.util;

import java.lang.reflect.*;

/**
 * Created by fmy on 2017/8/23.
 */
public class ReflectUtil {

    public static Class getSuperClassGenericType(Class clazz) {
        Class genericType = (Class) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
        return genericType;
    }

    /**
     * 获取一个类的泛型参数数组，如果这个类没有泛型参数，返回 null
     */
    public static Type[] getTypeParams(Class<?> klass) {
        // TODO 这个实现会导致泛型丢失,只能取得申明类型
        if (klass == null || "java.lang.Object".equals(klass.getName())) {
            return null;
        }
        // 看看父类
        Type superclass = klass.getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            return ((ParameterizedType) superclass).getActualTypeArguments();
        }

        // 看看接口
        Type[] interfaces = klass.getGenericInterfaces();
        for (Type inf : interfaces) {
            if (inf instanceof ParameterizedType) {
                return ((ParameterizedType) inf).getActualTypeArguments();
            }
        }
        return getTypeParams(klass.getSuperclass());
    }

    /**
     * 获取一个类的某个一个泛型参数
     *
     * @param klass 类
     * @param index 参数下标 （从 0 开始）
     * @return 泛型参数类型
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getTypeParam(Class<?> klass, int index) throws Exception {
        Type[] types = getTypeParams(klass);
        if (index >= 0 && index < types.length) {
            Type t = types[index];
            Class<T> clazz = (Class<T>) getTypeClass(t);
            if (clazz == null) {
                throw new Exception("Type '%s' is not a Class" + t.toString());
            }
            return clazz;
        }
        throw new Exception("Class type param out of range %d/%d" + index + types.length);
    }

    /**
     * 获取一个 Type 类型实际对应的Class
     *
     * @param type 类型
     * @return 与Type类型实际对应的Class
     */
    @SuppressWarnings("rawtypes")
    public static Class<?> getTypeClass(Type type) {
        Class<?> clazz = null;
        if (type instanceof Class<?>) {
            clazz = (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            clazz = (Class<?>) pt.getRawType();
        } else if (type instanceof GenericArrayType) {
            GenericArrayType gat = (GenericArrayType) type;
            Class<?> typeClass = getTypeClass(gat.getGenericComponentType());
            return Array.newInstance(typeClass, 0).getClass();
        } else if (type instanceof TypeVariable) {
            TypeVariable tv = (TypeVariable) type;
            Type[] ts = tv.getBounds();
            if (ts != null && ts.length > 0) {
                return getTypeClass(ts[0]);
            }
        } else if (type instanceof WildcardType) {
            WildcardType wt = (WildcardType) type;
            Type[] tLow = wt.getLowerBounds();// 取其下界
            if (tLow.length > 0) {
                return getTypeClass(tLow[0]);
            }
            Type[] tUp = wt.getUpperBounds(); // 没有下界?取其上界
            return getTypeClass(tUp[0]);// 最起码有Object作为上界
        }
        return clazz;
    }

}
