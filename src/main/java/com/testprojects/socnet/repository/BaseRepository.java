package com.testprojects.socnet.repository;

import java.io.Serializable;

import com.testprojects.socnet.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * Provides base repository
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable>
        extends JpaRepository<T, ID> {

    /**
     * Update status isEnabled
     */
    @Query("UPDATE #{#entityName} e SET e.isEnabled = FALSE WHERE e.id = :id")
    void deleteById(ID id);
}
