package com.example.demo.config.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    // Exception Handler
    public ResponseEntity handlerException(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse(Code.SERVER_ERROR));
    }

    // BaseException Handler
    @org.springframework.web.bind.annotation.ExceptionHandler(value = BaseException.class)
    public ResponseEntity handleBaseException(BaseException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse(e.getStatus()));
    }

    // Validation Exception Handler
    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request){
        StringBuilder msg = new StringBuilder();
        for (ObjectError error : ex.getAllErrors()) {
                msg.append(error.getDefaultMessage());
                break;
        }

        log.error(ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse(Code.REQUEST_ERROR, msg.toString()));
    }

    // TODO: Internal Exception (로그가 이상하게 많이 뜸, 확인 필요)
//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
//                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        log.info("At exception handler");
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) request;
//        HttpServletRequest servletRequest = requestAttributes.getRequest();
//
//        String contentType = request.getHeader("Content-Type");
//        log.info("Content-Type : {}", contentType);
//        log.error("발생한 에러의 로그 :", ex);
//        return handleExceptionInternal(ex, status, headers, status, request);
//    }
//
//
//    private ResponseEntity<Object> handleExceptionInternal(Exception e, Code errorCode,
//                                                           WebRequest request) {
//        return handleExceptionInternal(e, errorCode, HttpHeaders.EMPTY, HttpStatus.valueOf(errorCode.getCode()),
//                request);
//    }
//
//    private ResponseEntity<Object> handleExceptionInternal(Exception e, Code errorCode,
//                                                           HttpHeaders headers, HttpStatus status, WebRequest request) {
//        BaseResponse body = new BaseResponse(errorCode);
//        return super.handleExceptionInternal(
//                e,
//                body,
//                headers,
//                status,
//                request
//        );
//    }
//
//    private ResponseEntity<Object> handleExceptionInternalFalse(Exception e, Code errorCode,
//                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {
//        BaseResponse body = new BaseResponse(errorCode);
//        return super.handleExceptionInternal(
//                e,
//                body,
//                headers,
//                status,
//                request
//        );
//    }
}
