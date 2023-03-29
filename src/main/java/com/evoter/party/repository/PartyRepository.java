package com.evoter.party.repository;

import com.evoter.party.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author showunmioludotun
 */
public interface PartyRepository extends JpaRepository<Party, Long> {
}
