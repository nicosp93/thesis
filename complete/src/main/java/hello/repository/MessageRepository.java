package hello.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.entity.Message;

@Repository
public interface MessageRepository  extends JpaRepository<Message, Integer>{
	
	
}
