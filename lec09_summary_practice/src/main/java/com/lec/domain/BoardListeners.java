package com.lec.domain;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/*
EventListener의 종류

@PrePersist : Persist(insert)메서드가 호출되기 전에 실행되는 메서드
@PreUpdate : merge메서드가 호출되기 전에 실행되는 메서드
@PreRemove : Delete메서드가 호출되기 전에 실행되는 메서드
@PostPersist : Persist(insert)메서드가 호출된 이후에 실행되는 메서드
@PostUpdate : merge메서드가 호출된 후에 실행되는 메서드
@PostRemove : Delete메서드가 호출된 후에 실행되는 메서드
@PostLoad : Select조회가 일어난 직후에 실행되는 메서드
 */
public class BoardListeners {
	
//  @PostLoad
//  public void postLoad(Board board) {
//      System.out.println("post load: {}" +  board);
//  }
  
  @PrePersist
  public void prePersist(Board board) {
      System.out.println("pre persist: {}" + board);
  }
  
  @PostPersist
  public void postPersist(Board board) {
      System.out.println("post persist: {}" + board);
  }
  
  @PreUpdate
  public void preUpdate(Board board) {
      System.out.println("pre update: {}" + board);
  }
  
  @PostUpdate
  public void postUpdate(Board board) {
      System.out.println("post update: {}" +  board);
  }
  
  @PreRemove
  public void preRemove(Board board) {
      System.out.println("pre remove: {}" +  board);
  }
  
  @PostRemove
  public void postRemove(Board board) {
      System.out.println("post remove: {}" +  board);
  }
	
}
