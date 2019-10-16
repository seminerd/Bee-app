package vn.beemart.service.web.rest.service;

import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.hibernate.TypeMismatchException;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.service.ProductService;
import vn.beemart.service.web.rest.model.inventory.InventoryResponse;
import vn.beemart.service.web.rest.model.product.ProductResponse;
import vn.beemart.service.web.rest.model.variant.VariantResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Service
public class ProductFilter {
    private ProductService service;
    private List<ProductResponse> productResponseList;

    public ProductFilter() {

        this.productResponseList = new ArrayList<>();
    }

    public ProductFilter byBrand(String brand) {
        this.productResponseList.removeIf(product -> (!(product
                .getBrand()
                .toLowerCase()
                .contains(brand.toLowerCase())))
                &&
                (!(brand.toLowerCase().contains(product.getBrand().toLowerCase()))));

        return this;


    }

    public ProductFilter byMinPrice(BigDecimal minPrice) {
        try {
            this.productResponseList.forEach(
                    (ProductResponse productRes) -> {
                        productRes.getVariants().removeIf(variant -> variant.getPrice().compareTo(minPrice) < 0);

                    }

            );
            return this;
        } catch (Exception e) {
        }
        ;
        return null;
    }

    public ProductFilter byMaxPrice(BigDecimal maxPrice) {
        this.productResponseList.forEach(
                (ProductResponse productRes) -> {
                    productRes.getVariants().removeIf(variant -> variant.getPrice().compareTo(maxPrice) > 0);
                }
        );
        return this;
    }

    public ProductFilter byLocationId(Integer locationId) {
        val toRemove = new ArrayList<VariantResponse>();
        this.productResponseList.forEach(
                (ProductResponse productRes) -> {
                    for (VariantResponse variantRes : productRes.getVariants()) {
                        boolean available = false;
                        for (InventoryResponse inventoryRes : variantRes.getInventories()) {
                            if (inventoryRes.getLocationId() == locationId) {
                                available = true;
                                break;
                            }
                        }
                        if (available == false) {
                            toRemove.add(variantRes);
                        }


                    }
                    productRes.getVariants().removeAll(toRemove);
                }

        );
        return this;


    }

    public ProductFilter byCategoryId(Integer categoryId) {
        this.productResponseList.removeIf(product -> product.getCategoryId() != categoryId);
        return this;
    }

    public List<ProductResponse> getFilteredList() {
        this.productResponseList.removeIf(product -> product.getVariants().isEmpty());

        return productResponseList;
    }

}
