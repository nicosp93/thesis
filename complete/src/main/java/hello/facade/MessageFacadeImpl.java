package hello.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import hello.dto.MessageData;
import hello.entity.Message;
import hello.repository.MessageRepository;
import org.dozer.Mapper;


public class MessageFacadeImpl implements MessageFacade{
	
	@Autowired
	MessageRepository messageRepository;
	
	@Override
	public List<MessageData> returnAllMesages(){
		return  convert(messageRepository.findAll());
	}
	
	@Override
	public void saveMesage(MessageData messageData){
		
	}
	
	private List<MessageData> convert(final Iterable<Message> messages){
		List<MessageData> ret = new ArrayList<>();
		for(Message msg:messages) {
			MessageData md= new MessageData("", msg.getSensorId(), msg.getTime(),  msg.getDate());
			ret.add(md);
		}
		return ret;
				
	}

}
