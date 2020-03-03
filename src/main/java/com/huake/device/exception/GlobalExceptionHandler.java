package com.huake.device.exception;

import com.huake.device.util.MyException;
import com.huake.device.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	private final Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

	/**
	 * 所有异常报错
	 *
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	public Object allExceptionHandler(Exception exception) {
		logger.debug(exception);
		exception.printStackTrace();
		if(exception instanceof MyException) {
			return ((MyException) exception).getObject();
		} else {
			return ResponseUtil.fail(-1, exception.getMessage());
		}
	}

}