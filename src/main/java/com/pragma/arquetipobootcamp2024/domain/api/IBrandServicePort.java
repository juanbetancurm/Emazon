package com.pragma.arquetipobootcamp2024.domain.api;

import com.pragma.arquetipobootcamp2024.domain.model.BrandModel;

import java.util.List;

public interface IBrandServicePort {
    BrandModel createBrand(BrandModel brandModel);
    //List<BrandModel> getCategoriesWithPagination(int page, int size, String sortBy, boolean asc);

}
