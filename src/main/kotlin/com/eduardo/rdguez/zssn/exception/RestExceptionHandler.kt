package com.eduardo.rdguez.zssn.exception

import com.eduardo.rdguez.zssn.model.response.ApiErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

  @Override
  @ExceptionHandler(NotFoundException::class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  fun handleEntityNotFound(ex: NotFoundException): ResponseEntity<ApiErrorResponse> {
    return buildResponseEntity(ApiErrorResponse(HttpStatus.NOT_FOUND, ex))
  }

  @Override
  @ExceptionHandler(BadRequestException::class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ApiErrorResponse> {
    return buildResponseEntity(ApiErrorResponse(HttpStatus.BAD_REQUEST, ex))
  }

  @Override
  fun buildResponseEntity(apiErrorResponse: ApiErrorResponse): ResponseEntity<ApiErrorResponse> {
    return ResponseEntity(apiErrorResponse, apiErrorResponse.status)
  }

}