package co.com.ias.demos.canopus.utils;

import co.com.ias.demos.canopus.domain.Employee;
import co.com.ias.demos.canopus.domain.Store;
import co.com.ias.demos.canopus.domain.Zone;
import com.google.common.collect.ImmutableListMultimap;
import io.reactivex.Single;
import org.jgrapht.UndirectedGraph;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PathUtils {
    public static Single getShortestPath(List<Zone> zones) {
        return Single.create(e -> {

        });
    }

    public static Function<Zone, List<Store>> getStores = Zone::getStores;

    public static UndirectedGraph<Store, Long> getStoresWithDistancesFromEachOther(List<Zone> zones) {
        List<Store> stores = zones
                .stream()
                .map(getStores)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return null;
    }
}
