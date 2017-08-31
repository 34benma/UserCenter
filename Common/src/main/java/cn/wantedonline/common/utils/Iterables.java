package cn.wantedonline.common.utils;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Created by louiswang on 17/8/30.
 * 支持带索引的foreach 循环 Java8 新特性
 */
public class Iterables {
    public static <E> void forEach(Iterable<? extends E> elements, BiConsumer<Integer, ? super E> action) {
        Objects.requireNonNull(elements);
        Objects.requireNonNull(action);

        int index = 0;
        for (E element : elements) {
            action.accept(index++, element);
        }
    }
}
