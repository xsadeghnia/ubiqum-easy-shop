package com.example.easyshop.service;

import com.example.easyshop.entity.Category;
import com.example.easyshop.entity.Role;
import com.example.easyshop.entity.Token;
import com.example.easyshop.repository.CategoryRepository;
import com.example.easyshop.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
        Optional<Category> parentCategory = categoryRepository.findByPrentName(categoryData.getParentName());
        if (!parentCategory.isPresent()){
            throw new CategoryNotFoundException();
        }
        try{
            category.get().setName(categoryData.getName());
            category.get().setParent(parentCategory.get().getParent());
        }catch (Throwable throwable) {
            throw new TechnicalException(throwable);
        }
    }

    @Override
    public List<CategoryData> getCategory(LoginToken loginToken, FilterData filterData, PagingData pagingData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException {
        
        return null;
    }

    @Override
    public void addProduct(LoginToken loginToken, ProductData productData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException {

    }

    @Override
    public void deleteProduct(LoginToken loginToken, ProductData productData)
            throws  InvalidTokenException, AccessDeniedException, TechnicalException {

    }

    @Override
    public void updateProduct(LoginToken loginToken, ProductData productData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException {

    }

    @Override
    public List<ProductData> getProducts(LoginToken loginToken, FilterData filterData, PagingData pagingData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException {
        return null;
    }

    @Override
    public void addProductInstance(LoginToken loginToken, ProductInstanceData productInstanceData)
            throws ValidationFailedException, InvalidTokenException, AccessDeniedException, TechnicalException {

    }

    @Override
    public void deleteProductInstance(LoginToken loginToken, ProductInstanceData productInstanceData)
            throws  InvalidTokenException, AccessDeniedException, TechnicalException {

    }

    @Override
    public void updateProductInstance(LoginToken loginToken, ProductInstanceData productInstanceData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException {

    }

    @Override
    public List<ProductInstanceData> getProductInstance(LoginToken loginToken, FilterData filterData, PagingData pagingData)
            throws InvalidTokenException, AccessDeniedException, TechnicalException {
        return null;
    }
}
