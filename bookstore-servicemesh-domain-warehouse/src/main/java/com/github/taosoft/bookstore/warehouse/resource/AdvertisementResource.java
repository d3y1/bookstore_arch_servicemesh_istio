package com.github.taosoft.bookstore.warehouse.resource;

import com.github.taosoft.bookstore.warehouse.domain.Advertisement;
import com.github.taosoft.bookstore.warehouse.domain.AdvertisementRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 广告相关的资源
 **/
@Path("/advertisements")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class AdvertisementResource {

    @Inject
    AdvertisementRepository repository;

    @GET
    @Cacheable("resource.advertisements")
    public Iterable<Advertisement> getAllAdvertisements() {
        return repository.findAll();
    }
}
