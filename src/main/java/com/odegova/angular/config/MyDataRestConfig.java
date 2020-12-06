package com.odegova.angular.config;

import com.odegova.angular.entity.Country;
import com.odegova.angular.entity.Product;
import com.odegova.angular.entity.ProductCategory;
import com.odegova.angular.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        disableHttpMethods(config, Product.class, theUnsupportedActions);
        disableHttpMethods(config, ProductCategory.class, theUnsupportedActions);
        disableHttpMethods(config, Country.class, theUnsupportedActions);
        disableHttpMethods(config, State.class, theUnsupportedActions);

        exposeIds(config);
    }

    private void disableHttpMethods(RepositoryRestConfiguration config,
                                    Class className,
                                    HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration().forDomainType(className)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        Class[] domainTypes = entities.stream().map(EntityType::getJavaType).toArray(Class[]::new);
        config.exposeIdsFor(domainTypes);
    }
}

