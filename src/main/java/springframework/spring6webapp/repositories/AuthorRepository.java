package springframework.spring6webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.spring6webapp.domain.Author;

public interface AuthorRepository extends CrudRepository<Author,Long> {//generic type addition comes from Repository<T,ID> definition
    //this "declaration" will be detected as Spring compoonent -> Spring data JPA will pick this up and provide us an implementation
}
