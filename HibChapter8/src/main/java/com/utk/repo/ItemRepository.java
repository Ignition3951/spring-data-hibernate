package com.utk.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utk.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query("select i from Item i inner join fetch i.images where i.id = :idToSearch")
	Item findItemWithImages(@Param("idToSearch") Long id);

	@Query(value = "SELECT FNAME FROM IMAGES WHERE ITEM_ID = ?1", nativeQuery = true)
	Set<String> findImagesNative(Long id);

}
