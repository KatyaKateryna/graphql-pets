package com.softserve.graphqlpets.dataloader;

import com.softserve.graphqlpets.dto.Passport;
import com.softserve.graphqlpets.service.PassportService;
import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Configuration
public class PassportDataLoaderConfig {
    private final PassportService passportService;

    public PassportDataLoaderConfig(PassportService passportService) {
        this.passportService = passportService;
    }

    @Bean
    public DataLoaderInfo ownerDataLoaderInfo() {
        DataLoader<UUID, Passport> dataLoader = DataLoaderFactory.newDataLoader((ids, env) ->
                        CompletableFuture.supplyAsync(() -> passportService.getPassports(ids, isFastLoad(env))),
                DataLoaderOptions.newOptions().setCachingEnabled(false)
        );

        return new DataLoaderInfo(Passport.class.getSimpleName(), dataLoader);
    }

    private boolean isFastLoad(BatchLoaderEnvironment env) {
        return env.getKeyContextsList().stream()
                .filter(Objects::nonNull)
                .findFirst()
                .map(val -> val instanceof Boolean && (boolean) val)
                .orElse(false);
    }
}
