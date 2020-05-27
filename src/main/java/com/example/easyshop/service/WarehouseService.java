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

    List<CategoryData> getCategory(LoginToken loginToken, FilterData filterData, PagingData pagingData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException;


    // Product CRUD

    void addProduct(LoginToken loginToken,ProductData productData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException;

    void deleteProduct(LoginToken loginToken, ProductData productData)
            throws  InvalidTokenException, AccessDeniedException, TechnicalException;

    void updateProduct(LoginToken loginToken,ProductData productData)
            throws  ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException;

    List<ProductData> getProducts(LoginToken loginToken, FilterData filterData, PagingData pagingData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException;


    // ProductInstance CRUD

    void addProductInstance(LoginToken loginToken,ProductInstanceData productInstanceData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException;

    void deleteProductInstance(LoginToken loginToken, ProductInstanceData productInstanceData)
            throws  InvalidTokenException, AccessDeniedException, TechnicalException;

    void updateProductInstance(LoginToken loginToken,ProductInstanceData productInstanceData)
            throws  ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException;

    List<ProductInstanceData> getProductInstance(LoginToken loginToken, FilterData filterData, PagingData pagingData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException;
}
