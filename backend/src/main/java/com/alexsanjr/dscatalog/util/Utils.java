package com.alexsanjr.dscatalog.util;

import com.alexsanjr.dscatalog.projections.IdProjection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static <ID> List<? extends IdProjection<ID>> replace(List<? extends IdProjection<ID>> ordered, List<? extends IdProjection<ID>> unordered) {
        Map<ID, IdProjection<ID>> map = new HashMap<>();
        unordered.forEach(obj -> map.put(obj.getId(), obj));

        List< IdProjection<ID>> result = new ArrayList<>();
        ordered.forEach(obj -> result.add(map.get(obj.getId())));

        return result;
    }
}
