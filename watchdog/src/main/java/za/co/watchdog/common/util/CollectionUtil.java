package za.co.watchdog.common.util;

import java.util.List;
import java.util.stream.Stream;

public class CollectionUtil {
    public static <T> Stream<T> safeStream(List<T> list) {
        return list == null ? Stream.empty() : list.stream();
    }
}
