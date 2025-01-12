package com.onetuks.csphinxserver.global.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import java.io.UncheckedIOException;
import java.nio.file.NoSuchFileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class GlobalExceptionRestHandler {

  /** 서버에 정의되지 않은 모든 예외 */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAllException(Exception e) {
    logging(e);

    final ErrorResponse response =
        ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  /** JSON 데이터 처리 문제 */
  @ExceptionHandler(JsonProcessingException.class)
  protected ResponseEntity<ErrorResponse> handleJsonProcessingException(
      JsonProcessingException ex) {
    logging(ex);

    final ErrorResponse response =
        ErrorResponse.of(ErrorCode.JSON_PROCESSING_ERROR, ex.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /** 유효성 검증 문제 (@Valid, @Validated) */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    logging(e);

    final ErrorResponse response =
        ErrorResponse.of(ErrorCode.INVALID_METHOD_ARGUMENT_ERROR, e.getBindingResult());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /** 유효하지 않은 인자 문제 */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleAllException(IllegalArgumentException e) {
    logging(e);

    final ErrorResponse response =
        ErrorResponse.of(ErrorCode.ILLEGAL_ARGUMENT_ERROR, e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /** 유효하지 않은 객체 상태 문제 */
  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<ErrorResponse> handleAllException(IllegalStateException e) {
    logging(e);

    final ErrorResponse response = ErrorResponse.of(ErrorCode.ILLEGAL_STATE_ERROR, e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /** 미지원 ContentType 요청 문제 */
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  protected ResponseEntity<ErrorResponse> handleHttpMediaTypeException(
      HttpMediaTypeNotSupportedException e) {
    logging(e);

    final ErrorResponse response =
        ErrorResponse.of(ErrorCode.UNSUPPORTED_MEDIA_TYPE_ERROR, e.getDetailMessageCode());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /** 요청 매개변수 타입 불일치 문제 */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException e) {
    logging(e);

    final ErrorResponse response =
        ErrorResponse.of(ErrorCode.INVALID_REQUEST_BODY_ERROR, e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /** 요청 객체 바인딩/변환 문제 */
  @ExceptionHandler(BindException.class)
  protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
    logging(e);

    final ErrorResponse response =
        ErrorResponse.of(ErrorCode.INVALID_REQUEST_BODY_ERROR, e.getBindingResult());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /** 요청 파라미터 부재 */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  protected ResponseEntity<ErrorResponse> handleMissingRequestHeaderExceptionException(
      MissingServletRequestParameterException ex) {
    logging(ex);

    final ErrorResponse response =
        ErrorResponse.of(ErrorCode.MISSING_REQUEST_PARAM_ERROR, ex.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /** 파일 업로드 크기 제한 문제 */
  @ExceptionHandler(MaxUploadSizeExceededException.class)
  protected ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException(
      MaxUploadSizeExceededException e) {
    logging(e);

    final ErrorResponse response =
        ErrorResponse.of(ErrorCode.FILE_SIZE_EXCEEDED_ERROR, e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /** S3 객체 부재 문제 */
  @ExceptionHandler(NoSuchFileException.class)
  protected ResponseEntity<ErrorResponse> handleNoSuchKeyException(NoSuchFileException e) {
    logging(e);

    final ErrorResponse response = ErrorResponse.of(ErrorCode.FILE_NOT_FOUND_ERROR, e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /** S3 버킷의 파일을 읽을때 발생하는 IOException Unchecking 문제 */
  @ExceptionHandler(UncheckedIOException.class)
  protected ResponseEntity<ErrorResponse> handleUncheckedIOException(UncheckedIOException e) {
    logging(e);

    final ErrorResponse response = ErrorResponse.of(ErrorCode.FILE_NOT_FOUND_ERROR, e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /** 엔티티 부재 문제 */
  @ExceptionHandler(NoSuchEntityException.class)
  protected ResponseEntity<ErrorResponse> handleNoSuchEntityException(NoSuchEntityException e) {
    logging(e);

    final ErrorResponse response =
        ErrorResponse.of(ErrorCode.ENTITY_NOT_FOUND_ERROR, e.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  /** 유니크 제약조건 문제 */
  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
      DataIntegrityViolationException e) {
    logging(e);

    final ErrorResponse response =
        ErrorResponse.of(ErrorCode.UNIQUE_CONSTRAINT_ERROR, e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  private void logging(Exception e) {
    log.warn("Handle {} : {}", e.getClass(), e.getMessage());
    log.warn("Exception StackTrace: ", e);
  }
}
