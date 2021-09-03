package com.demo.truckitin.controller;

import com.demo.truckitin.model.QueryRequest;
import com.demo.truckitin.model.QueryResponse;
import com.demo.truckitin.service.QueryResolverService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class QueryController {

    private final QueryResolverService queryResolverService;

    public QueryController(QueryResolverService queryResolverService) {
        this.queryResolverService = queryResolverService;
    }

    /**
     * Resolves and returns the requested infix notation to postfix notation.
     * Throws an error if query is invalid.
     * @param request The request object containing the infix query.
     * @return {@link ResponseEntity<QueryResponse>} Object containing the postfix notation
     * and calculated result of the notation.
     */
    @PostMapping("/compute")
    public ResponseEntity<QueryResponse> resolveQuery(
            @Validated @RequestBody QueryRequest request
    ) {
        QueryResponse response = queryResolverService.resolve(request.getEquation());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
