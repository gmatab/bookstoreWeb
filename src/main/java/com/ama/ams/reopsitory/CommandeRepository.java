package com.ama.ams.reopsitory;


import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ama.ams.enteties.Commande;
@Repository
public interface CommandeRepository extends CrudRepository<Commande, Long>,JpaRepository<Commande,Long> {
}