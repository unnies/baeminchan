package codesquad.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductIdAndTitle> findTop10ByTitleContaining(String title);
}
