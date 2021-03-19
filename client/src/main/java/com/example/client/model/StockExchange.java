package com.example.client.model;

import java.io.Serializable;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class StockExchange implements Serializable, CacheType {

  private static final long serialVersionUID = 4647739188006325051L;

  @EqualsAndHashCode.Include private final String stockExchangeId = UUID.randomUUID().toString();

  @NotBlank(message = "stockExchangeName cannot be blank")
  @Size(min = 3, max = 100, message = "minimum 3 characters and maximum 100 characters in length")
  private String stockExchangeName;
}
