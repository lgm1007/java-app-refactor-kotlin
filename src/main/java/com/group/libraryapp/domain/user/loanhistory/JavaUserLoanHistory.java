package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.JavaUser;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class JavaUserLoanHistory {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne
  private JavaUser user;

  private String bookName;

  private boolean isReturn;

  public JavaUserLoanHistory() {

  }

  public JavaUserLoanHistory(JavaUser user, String bookName, boolean isReturn) {
    this.user = user;
    this.bookName = bookName;
    this.isReturn = isReturn;
  }

  @NotNull
  public String getBookName() {
    return this.bookName;
  }

  @NotNull
  public JavaUser getUser() {
    return this.user;
  }

  public boolean isReturn() {
    return isReturn;
  }

  public void doReturn() {
    this.isReturn = true;
  }

}
