package com.softserve.graphqlpets.dataloader;

import lombok.Data;
import org.dataloader.DataLoader;

@Data
public class DataLoaderInfo {
    private final String key;
    private final DataLoader<?, ?> dataLoader;
}
