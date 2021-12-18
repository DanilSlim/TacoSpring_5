package tacos.data.interfaces.jpa;

import org.springframework.data.repository.CrudRepository;


import tacos.jpa.TacoJPA;

public interface TacoRepositoryJPA extends CrudRepository<TacoJPA, Long> {

}
