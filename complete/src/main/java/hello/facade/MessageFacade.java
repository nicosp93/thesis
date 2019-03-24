package hello.facade;

import java.util.List;

import hello.dto.*;

public interface MessageFacade {

	List<MessageData> returnAllMesages();
	
	void saveMesage(MessageData md);
}
