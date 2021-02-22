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
public class Country implements Serializable {

  private static final long serialVersionUID = 1452566545714068658L;

  @EqualsAndHashCode.Include private final String countryId = UUID.randomUUID().toString();

  @NotBlank(message = "countryName cannot be blank")
  @Size(min = 3, max = 100, message = "minimum 3 characters and maximum 100 characters in length")
  private String countryName;

  @NotBlank(message = "countryIsoCode cannot be blank")
  @Size(min = 3, max = 10, message = "minimum 3 characters and maximum 10 characters in length")
  private String countryIsoCode;
}
