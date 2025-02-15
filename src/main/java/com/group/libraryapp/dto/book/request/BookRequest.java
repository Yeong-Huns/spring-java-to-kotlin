package com.group.libraryapp.dto.book.request;

import org.jetbrains.annotations.NotNull;

public class BookRequest {
  public BookRequest(@NotNull String name) {
    this.name = name;
  }
  @NotNull
  private String name;

  @NotNull
  public String getName() {
    return name;
  }

}
