package codesquad.banchan.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BanchanRepository extends JpaRepository<Banchan, Long> {
    List<BanchanInterface> findFirst10ByTitleContaining(String keyword);
}
