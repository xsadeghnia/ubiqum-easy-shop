package com.example.easyshop.service;

import com.example.easyshop.entity.Product;

import java.util.List;

public interface WarehouseService {

    // Category CRUD

    void addCategory(LoginToken loginToken,CategoryData categoryData)
            throws ValidationFailedException, InvalidTokenException, OperationDeniedException, TechnicalException;

    void deleteCategory(LoginToken loginToken, CategoryData categoryData)
            throws OperationFailedException, InvalidTokenException, OperationDeniedException, TechnicalException;

    void updateCategory(LoginToken loginToken,CategoryData categoryData)
            throws  InvalidTokenException, OperationDeniedException, TechnicalException;

    List<CategoryData> getCategory(LoginToken loginToken, FilterData filterData, PagingData pagingData)
            throws InvalidTokenException, OperationDeniedException, TechnicalException;


    // Product CRUD

    void addProduct(LoginToken loginToken,ProductData productData)
            throws ValidationFailedException, InvalidTokenException, OperationDeniedException, TechnicalException;

    void deleteProduct(LoginToken loginToken, ProductData productData)
            throws OperationFailedException, InvalidTokenException, OperationDeniedException, TechnicalException;

    void updateProduct(LoginToken loginToken,ProductData productData)
            throws  InvalidTokenException, OperationDeniedException, TechnicalException;

    List<ProductData> getProducts(LoginToken loginToken, FilterData filterData, PagingData pagingData)
            throws InvalidTokenException, OperationDeniedException, TechnicalException;


    //ProductInstance CRUD

    void addProductInstance(LoginToken loginToken,ProductInstanceData productInstanceData)
            throws ValidationFailedException, InvalidTokenException, OperationDeniedException, TechnicalException;

    void deleteProductInstance(LoginToken loginToken, ProductInstanceData productInstanceData)
            throws OperationFailedException, InvalidTokenException, OperationDeniedException, TechnicalException;

    void updateProductInstance(LoginToken loginToken,ProductInstanceData productInstanceData)
            throws  InvalidTokenException, OperationDeniedException, TechnicalException;

    List<ProductInstanceData> getProductInstance(LoginToken loginToken, FilterData filterData, PagingData pagingData)
            throws InvalidTokenException, OperationDeniedException, TechnicalException;




}
