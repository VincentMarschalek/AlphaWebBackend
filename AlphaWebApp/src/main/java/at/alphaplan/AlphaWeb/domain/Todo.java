package at.alphaplan.AlphaWeb.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data // getter setter
public class Todo {

  @Id private ObjectId id;

  private String title;

  private boolean completed;

  private Address address;

//   //ctor
//  public Todo(String title, boolean completed, Address address) {
//    this.title = title;
//    this.completed = completed;
//    this.address = address;
//  }

  // ctor Spring Data
  public Todo() {}


  public Todo(String title, boolean completed)
  {
    this.title=title;
    this.completed=completed;
  }


}
