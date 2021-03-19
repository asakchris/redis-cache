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
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "stocks", timeToLive = 120L)
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Stock implements Serializable, CacheType {

  private static final long serialVersionUID = 5370600044242926898L;

  @Id @EqualsAndHashCode.Include private final String stockId = UUID.randomUUID().toString();

  @NotBlank(message = "stockName cannot be blank")
  @Size(min = 3, max = 100, message = "minimum 3 characters and maximum 100 characters in length")
  private String stockName;
}
