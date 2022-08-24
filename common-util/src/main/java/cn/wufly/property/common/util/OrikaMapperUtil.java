package cn.wufly.property.common.util;

import cn.wufly.property.common.anno.OrikaField;
import cn.wufly.property.common.bo.FieldMapBO;
import cn.wufly.property.common.enums.ConverterEnum;
import cn.wufly.property.common.model.response.PageWrapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;

import java.util.function.Consumer;

public class OrikaMapperUtil {

    private OrikaMapperUtil() {
    }

    private static final MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();
    static {
        ConverterEnum[] converterEnums = ConverterEnum.values();
        for (ConverterEnum converterEnum : converterEnums) {
            MAPPER_FACTORY.getConverterFactory().registerConverter(converterEnum.toString(),
                    converterEnum.buildConverter());
        }
    }

    /**
     * 注册一个类转换映射,@OrikaField 在typeA,双端注册关系
     *
     * @param typeA
     * @param typeB
     */
    public static <A, B> void registerClassMapping(Class<A> typeA, Class<B> typeB) {
        List<FieldMapBO> fieldMapBOList = new ArrayList<>();
        Field[] fields = typeA.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(OrikaField.class)) {
                continue;
            }
            field.setAccessible(true);
            OrikaField annotation = field.getAnnotation(OrikaField.class);
            FieldMapBO fieldMapBO = new FieldMapBO();
            fieldMapBO.setFieldName(field.getName());
            fieldMapBO.setTargetName(annotation.targetName());
            fieldMapBO.setConverter(annotation.converter());
            fieldMapBOList.add(fieldMapBO);
        }

        ClassMapBuilder<A, B> classMapBuilderTo = MAPPER_FACTORY.classMap(typeA, typeB);

        fieldMapBOList.forEach(fieldMapBO -> {
            ConverterEnum converter = fieldMapBO.getConverter();
            classMapBuilderTo.fieldMap(
                    fieldMapBO.getFieldName(),
                    fieldMapBO.getTargetName()
            ).converter(converter.toString()).add();
        });
        classMapBuilderTo.byDefault().register();
    }

    /**
     * @param typeA
     * @param typeB
     * @param consumer 编程式自定义转换逻辑
     */
    public static <A, B> void registerClassMapping(Class<A> typeA, Class<B> typeB,
                                                   Consumer<ClassMapBuilder<A, B>> consumer) {
        ClassMapBuilder<A, B> classMapBuilderTo = MAPPER_FACTORY.classMap(typeA, typeB);
        consumer.accept(classMapBuilderTo);
        classMapBuilderTo.byDefault().register();
    }


    /**
     * 简单的复制出新类型对象.
     * <p>
     * 内部实现是通过source.getClass() 获得源Class
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return MAPPER_FACTORY.getMapperFacade().map(source, destinationClass);
    }

    /**
     * 极致性能的复制出新类型对象.
     * <p>
     * 预先通过{@link OrikaMapperUtil#getType(Class)} 静态获取并缓存Type类型，在此处传入
     */
    public static <S, D> D map(S source, Type<S> sourceType, Type<D> destinationType) {
        return MAPPER_FACTORY.getMapperFacade().map(source, sourceType, destinationType);
    }


    /**
     * 简单的复制出新对象列表到ArrayList
     * <p>
     * 不建议使用{@link MapperFacade#mapAsList(Object[], Class)}} 接口, sourceClass需要在遍历每一个元素的时候反射，实在有点慢
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<S> sourceClass, Class<D> destinationClass) {
        return MAPPER_FACTORY.getMapperFacade().mapAsList(sourceList, TypeFactory.valueOf(sourceClass), TypeFactory.valueOf(destinationClass));
    }

    /**
     * 简单的复制出新对象列表到ArrayList
     * <p>
     * 不建议使用{@link MapperFacade#mapAsList(Object[], Class)}} 接口, sourceClass需要在遍历每一个元素的时候反射，实在有点慢
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<D> destinationClass) {
        return MAPPER_FACTORY.getMapperFacade().mapAsList(sourceList, destinationClass);
    }

    /**
     * 极致性能的复制出新类型对象到ArrayList.
     * <p>
     * 预先通过{@link OrikaMapperUtil#getType(Class)} 静态获取并缓存Type类型，在此处传入
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Type<S> sourceType, Type<D> destinationType) {
        return MAPPER_FACTORY.getMapperFacade().mapAsList(sourceList, sourceType, destinationType);
    }



    /**
     * 简单复制出新对象列表到数组.
     * <p>
     * 内部实现是通过source.getComponentType() 获得源Class
     *
     * @param destination      要复制到的目标数组
     * @param source           待复制的源数据数组
     * @param destinationClass 要复制到的目标数组数据元素Class
     */
    public static <S, D> D[] mapArray(final D[] destination, final S[] source, final Class<D> destinationClass) {
        return MAPPER_FACTORY.getMapperFacade().mapAsArray(destination, source, destinationClass);
    }

    /**
     * 极致性能的复制出新类型对象到数组.
     * <p>
     * 需要预先通过{@link OrikaMapperUtil#getType(Class)} 静态获取并缓存转换所需Type类型，在此处传入
     *
     * @param destination     要复制到的目标数组
     * @param source          待复制的源数据数组
     * @param sourceType      待复制的源数据数组实例类型
     * @param destinationType 要复制到的目标数组类型
     */
    public static <S, D> D[] mapArray(D[] destination, S[] source, Type<S> sourceType, Type<D> destinationType) {
        return MAPPER_FACTORY.getMapperFacade().mapAsArray(destination, source, sourceType, destinationType);
    }

    /**
     * 预先获取orika转换所需要的{@link Type}，避免每次复制都做转换.
     */
    public static <E> Type<E> getType(final Class<E> rawType) {
        return TypeFactory.valueOf(rawType);
    }

    public static <S, D> PageWrapper<D> mapPage(PageWrapper<S> source, Class<D> targetClass) {
        if (ObjectUtils.isEmpty(source)) {
            return PageWrapper.of(new ArrayList<>(), 0, 10, 1);
        }
        PageWrapper<D> dPage = PageWrapper.of(source.getPageSize(), source.getPageIndex());
        dPage.setList(OrikaMapperUtil.mapList(source.getList(), targetClass));
        dPage.setTotalCount(source.getTotalCount());
        return dPage;
    }

    public static <S, D> PageWrapper<D> mapMapperPage(Page<S> source, Class<D> targetClass) {
        if (ObjectUtils.isEmpty(source)) {
            return PageWrapper.of(new ArrayList<>(), 0, 10, 1);
        }
        PageWrapper<D> dPage = PageWrapper.of((int)source.getSize(), (int)source.getCurrent());
        dPage.setList(OrikaMapperUtil.mapList(source.getRecords(), targetClass));
        dPage.setTotalCount(source.getTotal());
        return dPage;
    }
}
