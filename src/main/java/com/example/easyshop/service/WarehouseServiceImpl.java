package com.example.easyshop.service;

import com.example.easyshop.entity.*;
import com.example.easyshop.repository.CategoryRepository;
import com.example.easyshop.repository.ProductInstanceRepository;
import com.example.easyshop.repository.ProductRepository;
import com.example.easyshop.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ProductInstanceRepository productInstanceRepository;

    private void checkAdmin(LoginToken loginToken)
            throws TechnicalException, InvalidTokenException, AccessDeniedException {

        Token token = userManagementService.validateToken(loginToken);
        for (Role role : token.getUser().getRoles()) {
            if (role.getName().equals("ADMIN")) {
                return;
            }
        }
        throw new AccessDeniedException();
    }

    @Override
    public void addCategory(LoginToken loginToken, CategoryData categoryData)
            throws ValidationFailedException, InvalidTokenException, TechnicalException, AccessDeniedException, CategoryNotFoundException {

        checkAdmin(loginToken);

        Category parentCategory = null;
        if (categoryData.getParentName() != null && categoryData.getParentName().length() != 0) {
            Optional<Category> optionalParentCategory = categoryRepository.findByName(categoryData.getParentName());
            if (!optionalParentCategory.isPresent()) {
                throw new CategoryNotFoundException();
            }
            parentCategory = optionalParentCategory.get();
        }

        try {
            Category category = new Category();
            category.setName(categoryData.getName());
            category.setParent(parentCategory);
            categoryRepository.save(category);
        } catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }
    }

    @Override
    public void deleteCategory(LoginToken loginToken, CategoryData categoryData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException, CategoryNotFoundException {

        checkAdmin(loginToken);

        Optional<Category> category = categoryRepository.findByName(categoryData.getName());
        if (!category.isPresent()){
            throw new CategoryNotFoundException();
        }
        try {
           categoryRepository.delete(category.get());
        } catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }
    }

    @Override
    public void updateCategory(LoginToken loginToken, CategoryData categoryData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException, CategoryNotFoundException {
        checkAdmin(loginToken);

        Optional<Category> category = categoryRepository.findByName(categoryData.getName());
        if (!category.isPresent()){
            throw new CategoryNotFoundException();
        }
        Category parentCategory = null;
        if (categoryData.getParentName() != null && categoryData.getParentName().length() != 0) {
            Optional<Category> optionalParentCategory = categoryRepository.findByName(categoryData.getParentName());
            if (!optionalParentCategory.isPresent()) {
                throw new CategoryNotFoundException();
            }
            parentCategory = optionalParentCategory.get();
        }
        try{
            category.get().setName(categoryData.getName());
            category.get().setParent(parentCategory);
            categoryRepository.save(category.get());
        }catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }
    }

    @Override
    public List<CategoryData> getCategory(LoginToken loginToken, CategoryFilterData categoryFilterData, PagingData pagingData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException {

        checkAdmin(loginToken);

        List<CategoryData> categoryDataArrayList = new ArrayList<>();
        List<Category> categories = categoryRepository.findByNameContains(categoryFilterData.getName());
        for (Category cat : categories){
            categoryDataArrayList.add(getCategoryData(cat));
        }
        return categoryDataArrayList;
    }

    @Override
    public void addProduct(LoginToken loginToken, ProductData productData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException, CategoryNotFoundException {

        checkAdmin(loginToken);

        Optional<Category> category = categoryRepository.findByName(productData.getCategoryData().getName());
        if (!category.isPresent()){
            throw new CategoryNotFoundException();
        }

        try{
            Product product = new Product();
            product.setName(productData.getName());
            product.setRate(productData.getRate());
            product.setPrice(productData.getPrice());
            product.setDescription(productData.getDescription());
            product.setCategory(category.get());
            productRepository.save(product);
        }catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }
    }

    @Override
    public void deleteProduct(LoginToken loginToken, ProductData productData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException, ProductNotFoundException {

        checkAdmin(loginToken);

        Optional<Product> product = productRepository.findById(productData.getId);
        if (!product.isPresent()){
            throw new ProductNotFoundException();
        }

        try {
            productRepository.delete(product.get());
        }catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }

    }

    @Override
    public void updateProduct(LoginToken loginToken, ProductData productData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException, ProductNotFoundException, CategoryNotFoundException {

        checkAdmin(loginToken);

        Optional<Product> product = productRepository.findById(productData.getId);
        if (!product.isPresent()){
            throw new ProductNotFoundException();
        }
        Optional<Category> category = categoryRepository.findByName(productData.getCategoryData().getName());
        if(!category.isPresent()){
            throw  new CategoryNotFoundException();
        }

        try {
            product.get().setName(productData.getName());
            product.get().setRate(productData.getRate());
            product.get().setPrice(productData.getPrice());
            product.get().setDescription(productData.getDescription());
            product.get().setCategory(category.get());
            productRepository.save(product.get());
        }catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }

    }

    @Override
    public List<ProductData> getProducts(LoginToken loginToken, ProductFilterData productFilterData , PagingData pagingData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException {

        checkAdmin(loginToken);

        List<ProductData> productDataList = new ArrayList<>();
        List<Product> products = productRepository.findByNameContains(productFilterData.getName());
        for (Product prod : products){
            productDataList.add(getProductData(prod));
        }
        return productDataList;
    }

    @Override
    public void addProductInstance(LoginToken loginToken, ProductInstanceData productInstanceData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException, ProductNotFoundException {

        checkAdmin(loginToken);

        Optional<Product> product = productRepository.findById(productInstanceData.getProductData().getGetId());
        if(!product.isPresent()){
            throw  new ProductNotFoundException();
        }

        try {
            ProductInstance productInstance = new ProductInstance();
            productInstance.setEnterDate(productInstanceData.getEnterDate());
            productInstance.setExpireDate(productInstanceData.getExpireDate());
            productInstance.setPurchaseDate(productInstanceData.getPurchaseDate());
            productInstance.setProduct(product.get());
            productInstanceRepository.save(productInstance);
        }catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }

    }

    @Override
    public void deleteProductInstance(LoginToken loginToken, ProductInstanceData productInstanceData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException, ProductInstanceNotFoundException {
        checkAdmin(loginToken);

        Optional<ProductInstance> productInstance = productInstanceRepository.findById(productInstanceData.getId());
        if (!productInstance.isPresent()){
            throw new ProductInstanceNotFoundException();
        }
        try{
            productInstanceRepository.delete(productInstance.get());
        }catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }

    }

    @Override
    public void updateProductInstance(LoginToken loginToken, ProductInstanceData productInstanceData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException, ProductInstanceNotFoundException, ProductNotFoundException {
        checkAdmin(loginToken);

        Optional<ProductInstance> productInstance = productInstanceRepository.findById(productInstanceData.getId());
        if (!productInstance.isPresent()){
            throw new ProductInstanceNotFoundException();
        }

        Optional<Product> product = productRepository.findById(productInstanceData.getProductData().getGetId());
        if(!product.isPresent()){
            throw  new ProductNotFoundException();
        }

        try {
            productInstance.get().setEnterDate(productInstanceData.getEnterDate());
            productInstance.get().setExpireDate(productInstanceData.getExpireDate());
            productInstance.get().setPurchaseDate(productInstanceData.getPurchaseDate());
            productInstance.get().setProduct(product.get());
            productInstanceRepository.save(productInstance.get());
        }catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }
    }

    @Override
    public List<ProductInstanceData> getProductInstance(LoginToken loginToken, ProductInstanceFilterData productInstanceFilterData, PagingData pagingData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException {

        checkAdmin(loginToken);

        List<ProductInstanceData> productInstanceDataList = new ArrayList<>();
        List<ProductInstance> productInstances = productInstanceRepository.findByNameContains(productInstanceFilterData.getName());
        for (ProductInstance prodIns : productInstances){
            productInstanceDataList.add(getProductInstanceData(prodIns));
        }

        return productInstanceDataList;
    }

    private CategoryData getCategoryData(Category category){

        CategoryData categoryData = new CategoryData();
        categoryData.setName(category.getName());
        categoryData.setParentName(category.getParent().getName());
        return categoryData;
    }

     private ProductData getProductData(Product product){

         ProductData productData = new ProductData();
         productData.setName(product.getName());
         productData.setPrice(product.getPrice());
         productData.setDescription(product.getDescription());
         productData.setRate(product.getRate());
         productData.setCategoryData(getCategoryData(product.getCategory()));
         return productData;
     }

     private ProductInstanceData getProductInstanceData(ProductInstance productInstance){

        ProductInstanceData productInstanceData = new ProductInstanceData();
        productInstanceData.setEnterDate(productInstance.getEnterDate());
        productInstanceData.setPurchaseDate(productInstance.getPurchaseDate());
        productInstanceData.setExpireDate(productInstance.getExpireDate());
        productInstanceData.setProductData(getProductData(productInstance.getProduct()));
        return  productInstanceData;
     }
}
