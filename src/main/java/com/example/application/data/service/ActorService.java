package com.example.application.data.service;

import ActorModel.Actor;
import ActorModel.ActorContext;
import ActorModel.ActorProxy;
import ActorModel.Observer.MonitorService;
import com.example.application.data.entity.ActorEntry;

import com.example.application.views.list.ListView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActorService {
    public static Map<String,ActorEntry>entries=new HashMap<>();


    public List<ActorEntry> findAllActors(String filterText){
        for(ActorEntry entry:entries.values()){
//                entry.setProgressValue(5);
            Map<Actor,ArrayList<ActorModel.Messages.Message>>messageMap=MonitorService.getAllReceivedMessages();
            if(entry!=null){
                ArrayList<ActorModel.Messages.Message>messageQueue=messageMap.get(entry.getActor());
                if(messageQueue!=null){
                    entry.setProgressValue(messageQueue.size());
                }
                else{
                    entry.setProgressValue(0);
                }
            }
        }
        if(filterText==null || filterText.isEmpty()){
            return new ArrayList<>(entries.values());
        }
        else{
            List<ActorEntry>list=new ArrayList<>();
            for(ActorEntry entry:entries.values()){
                if(entry.getProxyId().contains(filterText)){
                    list.add(entry);
                }
            }
            return list;
        }
    }

    public long countActors(){
        return entries.size();

    }
    public void deleteActor(ActorEntry contact){
        ActorContext.actorSet.remove(contact.getProxyId());
    }
    public void saveActor(ActorEntry contact){
        if(contact==null){
            System.err.println("Contact is null");
            return;
        }
        entries.put(contact.getProxyId(),contact);
        ActorProxy proxy=ListView.context.spawnActor(contact.getProxyId(),contact.getActor());
        ListView.proxies.put(contact,proxy);

    }


}
