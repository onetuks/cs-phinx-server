package com.onetuks.csphinxserver.global.error;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

/** Global Exception Rest Handler 에서 발생한 에러에 대한 응답 처리를 관리 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

  private String code;
  private String message;
  private List<FieldDetailError> fieldDetailErrors;
  private String reason;

  private ErrorResponse(final ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
    this.fieldDetailErrors = Collections.synchronizedList(new ArrayList<>());
  }

  private ErrorResponse(final ErrorCode errorCode, final String reason) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
    this.reason = reason;
  }

  private ErrorResponse(final ErrorCode errorCode, final List<FieldDetailError> fieldDetailErrors) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
    this.fieldDetailErrors = fieldDetailErrors;
  }

  public static ErrorResponse of(final ErrorCode errorCode, final BindingResult bindingResult) {
    return new ErrorResponse(errorCode, FieldDetailError.of(bindingResult));
  }

  public static ErrorResponse of(ErrorCode errorCode) {
    return new ErrorResponse(errorCode);
  }

  public static ErrorResponse of(final ErrorCode errorCode, final String reason) {
    return new ErrorResponse(errorCode, reason);
  }

  @Getter
  public static class FieldDetailError {

    private final String field;
    private final String value;
    private final String reason;

    private FieldDetailError(String field, String value, String reason) {
      this.field = field;
      this.value = value;
      this.reason = reason;
    }

    private static List<FieldDetailError> of(final BindingResult bindingResult) {
      final List<org.springframework.validation.FieldError> fieldErrors =
          bindingResult.getFieldErrors();
      return fieldErrors.stream()
          .map(
              error ->
                  new FieldDetailError(
                      error.getField(),
                      error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                      error.getDefaultMessage()))
          .toList();
    }
  }
}
