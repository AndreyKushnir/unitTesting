package com.endava.ap.lotery.dao;

import com.endava.ap.lotery.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantDao extends JpaRepository<Participant, Long> {
}
