package com.finastra.mediastra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finastra.mediastra.entity.HelpingHand;
@Repository
public interface HelpingHandRepo extends JpaRepository<HelpingHand, Long>{

}
