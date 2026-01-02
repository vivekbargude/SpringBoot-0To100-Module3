package com.module3.module3.repository;

import com.module3.module3.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    //Auto Generated methods by Hibernate based on Naming Conventions
    List<ProductEntity> findByTitle(String title);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime createdAt);

    List<ProductEntity> findByQuantityAndPrice(int quantity, BigDecimal price);

    List<ProductEntity> findByQuantityGreaterThanAndPriceGreaterThan(int quantity, BigDecimal price);
    List<ProductEntity> findByQuantityGreaterThanOrPriceGreaterThan(int quantity, BigDecimal price);

    List<ProductEntity> findByTitleLike(String title);

    List<ProductEntity> findByTitleContaining(String title);


    //Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);




    //Custom Query using JPQL used the java based names in the query i.e used "ProductEntity" instead of "products" which is the table name for DB

//   @Query("select e from ProductEntity e where e.title=:title and e.price=:price")
    @Query("select e from ProductEntity e where e.title=?1 and e.price=?2")
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);


    //Sorting using auto methods : but tightly coupled to the name so have to declare separate methods for each one of them.
    List<ProductEntity> findByTitleOrderByPrice(String title);

    List<ProductEntity> findByOrderByPrice();


    //Using Sort object

    List<ProductEntity> findBy(Sort sort);


    //Pagination
    //Main Interface -> Pageable
    //Concrete impl of that is PageRequest
    //Which gives a Page object -> a subset of whole dataset.

    List<ProductEntity> findByTitleContaining(String title, Pageable pageable);





}
