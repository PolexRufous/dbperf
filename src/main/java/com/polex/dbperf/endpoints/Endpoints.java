package com.polex.dbperf.endpoints;

import com.polex.dbperf.services.EntityService;
import com.polex.dbperf.services.OperationFindRequest;
import com.polex.dbperf.services.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class Endpoints {

  private final EntityService entities;

  public Mono<ServerResponse> health(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(BodyInserters.fromObject("Server is working!"));
  }

  public Mono<ServerResponse> create(ServerRequest request) {
    int count = Integer.parseInt(request.pathVariable("count"));
    OperationResponse operationResponse;
    try {
      operationResponse = entities.createRandomEntities(count);
    } catch (Exception excep) {
      return ServerResponse.badRequest()
          .contentType(MediaType.TEXT_PLAIN)
          .body(BodyInserters.fromObject("Something went wrong!" + excep.getMessage()));
    }

    return ServerResponse.accepted()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(operationResponse));
  }

  public Mono<ServerResponse> find(ServerRequest request) {
    int numFrom = Integer.parseInt(request.queryParam("numFrom").orElse("0"));
    int numTo = Integer.parseInt(request.queryParam("numTo").orElse("0"));
    int searchCount = Integer.parseInt(request.queryParam("searchCount").orElse("1"));
    OperationFindRequest operationFindRequest = new OperationFindRequest()
        .setNumFrom(numFrom)
        .setNumTo(numTo)
        .setSearchCount(searchCount);
    OperationResponse operationResponse;
    try {
      operationResponse = entities.find(operationFindRequest);
    } catch (Exception excep) {
      return ServerResponse.badRequest()
          .contentType(MediaType.TEXT_PLAIN)
          .body(BodyInserters.fromObject("Something went wrong!" + excep.getMessage()));
    }
    return ServerResponse.accepted()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(operationResponse));
  }

  public Mono<ServerResponse> findByNum(ServerRequest request) {
    int numFrom = Integer.parseInt(request.queryParam("num").orElse("0"));
    int searchCount = Integer.parseInt(request.queryParam("searchCount").orElse("1"));
    OperationFindRequest operationFindRequest = new OperationFindRequest()
        .setNumFrom(numFrom)
        .setSearchCount(searchCount);
    OperationResponse operationResponse;
    try {
      operationResponse = entities.findByNum(operationFindRequest);
    } catch (Exception excep) {
      return ServerResponse.badRequest()
          .contentType(MediaType.TEXT_PLAIN)
          .body(BodyInserters.fromObject("Something went wrong!" + excep.getMessage()));
    }
    return ServerResponse.accepted()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(operationResponse));
  }

}
