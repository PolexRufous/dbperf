package com.polex.dbperf.configuration;

import com.polex.dbperf.endpoints.Endpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration {

  private final Endpoints endpoints;

  @Bean
  public RouterFunction<ServerResponse> router() {

    return route(GET("/health"), endpoints::health)
        .andRoute(POST("/create/{count}"), endpoints::create)
        .andRoute(GET("/find"), endpoints::find)
        .andRoute(GET("/findByNum"), endpoints::findByNum);
  }

}
