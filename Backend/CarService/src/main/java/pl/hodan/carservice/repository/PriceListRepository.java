package pl.hodan.carservice.repository;

import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hodan.carservice.entity.PriceList;
@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long> {
}
