package com.spring.dto.responseData;

import java.util.ArrayList;
import java.util.List;

public class ResponseData<T> {
   
   private boolean status;

   List<String> message = new ArrayList<String  >();

   private T payLoad;

   public void setMessage(List<String> message) {
      this.message = message;
   }
   public List<String> getMessage() {
      return message;
   }
   public void setPayLoad(T payLoad) {
      this.payLoad = payLoad;
   }
   public T getPayLoad() {
      return payLoad;
   }
   public void setStatus(boolean status) {
      this.status = status;
   }
   public boolean getStatus(){
      return this.status;
   }
}
