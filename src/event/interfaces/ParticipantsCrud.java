/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.interfaces;

import event.entites.Participants;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface ParticipantsCrud<T> {
    
    List<T> displayParticipants();
    void addParticipant(T t);

    
}
