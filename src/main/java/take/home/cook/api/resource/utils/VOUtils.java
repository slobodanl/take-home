package take.home.cook.api.resource.utils;

import org.modelmapper.ModelMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class VOUtils {
    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        //mapper.getConfiguration().setPropertyCondition((ctx) -> !(ctx.getSource() instanceof PersistentCollection));
    }

    public static <T,S> T copyProperties ( T  dest , S org) {
        return copyProperties(dest , org , true);
    }

    public static <T,S> T copyProperties ( T  dest , S org , boolean outgoing) {
        if( dest == null || org == null ) {
            return dest;
        }
        mapper.map(org , dest);
        return dest;
    }


    public static <S ,D, E extends Collection<D>> E transform(Collection<S> sourceList, Class<D> destinationType, Supplier<E> destination) {
        if (sourceList == null)
            return null;

        return sourceList.stream().map(s -> {
                    try {
                       return destinationType.getConstructor(s.getClass()).newInstance(s);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        ).filter(Objects::nonNull).collect(Collectors.toCollection(destination));
    }

}