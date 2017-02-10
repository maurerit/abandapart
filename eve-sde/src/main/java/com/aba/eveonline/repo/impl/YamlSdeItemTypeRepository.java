package com.aba.eveonline.repo.impl;

import com.aba.eveonline.model.Type;
import com.aba.eveonline.model.Types;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mm66053 on 2/10/2017.
 */
public class YamlSdeItemTypeRepository extends BaseItemTypeRepository {
    private Types types;

    public YamlSdeItemTypeRepository() {
        Yaml yaml = new Yaml();
        InputStream typeIS = YamlSdeItemTypeRepository.class.getResourceAsStream("/typeIDs.yaml");
        types = yaml.loadAs(typeIS, Types.class);
        types.setTypes(new ConcurrentHashMap<>(types.getTypes()));
    }

    @Override
    public Type findByTypeId(long typeId) {
        return types.getTypes().get(typeId);
    }

    @Override
    public Type findByName(String name) {
        Type foundType = null;

        Optional<Map.Entry<Long, Type>> optional = types.getTypes().entrySet()
                .stream()
                .filter(
                        integerTypeEntry -> integerTypeEntry.getValue().getName().equals(name))
                .findAny();

        if ( optional.isPresent() ) {
            foundType = optional.get().getValue();
        }

        return foundType;
    }
}
