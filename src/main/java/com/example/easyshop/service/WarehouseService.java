package com.example.easyshop.service;

import java.util.List;

public interface WarehouseService {

    // Category CRUD

    void addCategory(LoginToken loginToken,CategoryData categoryData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException, AccessDeniedException, CategoryNotFoundException;

    void deleteCategory(LoginToken loginToken, CategoryData categoryData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException, CategoryNotFoundException;

    void updateCategory(LoginToken loginToken,CategoryData categoryData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException, CategoryNotFoundException;

    List<CategoryData> getCategory(LoginToken loginToken, CategoryFilterData categoryFilterData, PagingData pagingData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException;


    // Product CRUD

    void addProduct(LoginToken loginToken,ProductData productData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException, CategoryNotFoundException;

    void deleteProduct(LoginToken loginToken, ProductData productData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException, ProductNotFoundException;

    void updateProduct(LoginToken loginToken,ProductData productData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException, ProductNotFoundException, CategoryNotFoundException;

    List<ProductData> getProducts(LoginToken loginToken, FilterData filterData, PagingData pagingData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException;


    // ProductInstance CRUD

    void addProductInstance(LoginToken loginToken,ProductInstanceData productInstanceData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException, ProductNotFoundException;

    void deleteProductInstance(LoginToken loginToken, ProductInstanceData productInstanceData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException, ProductInstanceNotFoundException;

    void updateProductInstance(LoginToken loginToken,ProductInstanceData productInstanceData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException, ProductInstanceNotFoundException, ProductNotFoundException;

    List<ProductInstanceData> getProductInstance(LoginToken loginToken, FilterData filterData, PagingData pagingData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException;
}
