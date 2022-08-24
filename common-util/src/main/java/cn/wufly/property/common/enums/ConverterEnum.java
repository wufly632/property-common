package cn.wufly.property.common.enums;

import cn.wufly.property.common.util.OrikaMapperUtil;
import cn.wufly.property.common.util.SearchEnumUtil;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.Converter;

import java.util.Optional;

/**
 * @author Wufly  fei.wu@transsnet.com
 * @project: property-common
 * @description:
 * @date 2022/8/24
 */
public enum ConverterEnum {

    NONE {
        @Override
        public Converter buildConverter() {
            return new BidirectionalConverter<Object, Object>() {
                @Override
                public Object convertTo(Object source, Type<Object> destinationType, MappingContext mappingContext) {
                    return source;
                }

                @Override
                public Object convertFrom(Object source, Type<Object> destinationType, MappingContext mappingContext) {
                    return source;
                }
            };
        }
    },
    /**
     * 嵌套转换
     */
    REF {
        @Override
        public Converter buildConverter() {
            return new BidirectionalConverter<Object, Object>() {
                @Override
                public Object convertTo(Object source, Type<Object> destinationType, MappingContext mappingContext) {
                    return OrikaMapperUtil.map(source, destinationType.getRawType());
                }

                @Override
                public Object convertFrom(Object source, Type<Object> destinationType, MappingContext mappingContext) {
                    return OrikaMapperUtil.map(source, destinationType.getRawType());
                }
            };
        }
    },

    /**
     * 枚举通用转换
     */
    ENUM_CONVERTER {
        @Override
        public Converter buildConverter() {
            return new BidirectionalConverter<Object, Object>() {
                @Override
                public Object convertTo(Object source, Type<Object> destinationType, MappingContext mappingContext) {
                    return enumConverter(source, destinationType);
                }

                @Override
                public Object convertFrom(Object source, Type<Object> destinationType, MappingContext mappingContext) {
                    return enumConverter(source, destinationType);
                }
            };
        }
    },
    ;

    public abstract Converter buildConverter();

    private static Object enumConverter(Object source, Type<Object> destinationType) {
        if (source instanceof SearchEnum) {
            return Optional.ofNullable(source)
                    .map(s -> ((SearchEnum) s).getCode())
                    .orElse(null);
        }
        return SearchEnumUtil.findByClass(destinationType.getRawType(), source);
    }


}

