package com.onetuks.csphinxserver.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

  // Global
  INTERNAL_SERVER_ERROR("G001", "Internal Server Error"),
  JSON_PROCESSING_ERROR("G002", "JSON 데이터 처리에 실패했습니다."),
  INVALID_METHOD_ARGUMENT_ERROR("G003", "메소드 인자가 유효하지 않습니다."),
  ILLEGAL_ARGUMENT_ERROR("G004", "유효하지 않은 인자입니다."),
  ILLEGAL_STATE_ERROR("G005", "객체 상태가 유효하지 않습니다."),

  // Web
  UNSUPPORTED_MEDIA_TYPE_ERROR("W001", "지원하지 않는 미디어 타입입니다."),
  INVALID_REQUEST_BODY_ERROR("W002", "요청 바디가 유효하지 않습니다."),
  MISSING_REQUEST_PARAM_ERROR("W003", "요청 파라미터가 유효하지 않습니다."),

  // File
  FILE_SIZE_EXCEEDED_ERROR("F001", "파일 업로드 크기 제한을 초과했습니다."),
  FILE_NOT_FOUND_ERROR("F002", "해당 파일을 찾을 수 없습니다."),

  // Database
  ENTITY_NOT_FOUND_ERROR("D001", "엔티티를 찾을 수 없습니다."),
  UNIQUE_CONSTRAINT_ERROR("D002", "유니크 제약 조건에 위배됩니다."),
  ;

  private final String code;
  private final String message;

  ErrorCode(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
