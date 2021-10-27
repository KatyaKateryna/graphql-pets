package com.softserve.graphqlpets.util;

import com.softserve.graphqlpets.dataloader.DataLoaderInfo;
import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.util.List;

/**
 * This builder is very similar to the default context builder implementation
 * {@link graphql.kickstart.servlet.context.DefaultGraphQLServletContextBuilder}.
 * The only difference is that this one supports Data Loaders.
 */
@Component
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

    public final List<DataLoaderInfo> dataLoaderInfoList;

    public CustomGraphQLContextBuilder(List<DataLoaderInfo> dataLoaderInfoList) {
        this.dataLoaderInfoList = dataLoaderInfoList;
    }

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return DefaultGraphQLServletContext.createServletContext(buildDataLoaderRegistry(), null)
                .with(httpServletRequest)
                .with(httpServletResponse)
                .build();
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext(buildDataLoaderRegistry(), null)
                .with(session)
                .with(handshakeRequest)
                .build();
    }

    @Override
    public GraphQLContext build() {
        return new DefaultGraphQLContext(buildDataLoaderRegistry(), null);
    }

    public DataLoaderRegistry buildDataLoaderRegistry() {
        DataLoaderRegistry registry = new DataLoaderRegistry();
        for (DataLoaderInfo loaderInfo : dataLoaderInfoList) {
            registry.register(loaderInfo.getKey(), loaderInfo.getDataLoader());
        }
        return registry;
    }
}