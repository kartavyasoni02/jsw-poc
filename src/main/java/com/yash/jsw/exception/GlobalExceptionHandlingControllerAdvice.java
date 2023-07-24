package com.yash.jsw.exception;



import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.yash.jsw.utility.ResponseWrapper;

/**
 * Performs the same exception handling as {@link ExceptionHandlingController}
 * but offers them globally. The exceptions below could be raised by any
 * controller and they would be handled here, if not handled in the controller
 * already.
 * 
 * @author kartavya soni
 */
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandlingControllerAdvice.class);

	public GlobalExceptionHandlingControllerAdvice() {

	}

	/**
	 * Demonstrates how to take total control - setup a model, add useful
	 * information and return the "support" view name. This method explicitly
	 * creates and returns
	 * 
	 * @param req
	 *            Current HTTP request.
	 * @param exception
	 *            The exception thrown - always {@link SupportInfoException}.
	 * @return The model and view used by the DispatcherServlet to generate
	 *         output.
	 * @throws Exception
	 */

	@ExceptionHandler(Exception.class)
	public ResponseWrapper handleError(HttpServletRequest req, Exception exception)
			throws Exception {
		ResponseWrapper response = this.getResponse("Request: " + req.getRequestURI() + " raised " + exception, 500) ;
		response.setException(exception);
		// Rethrow annotated exceptions or they will be processed here instead.
		if (AnnotationUtils.findAnnotation(exception.getClass(),
				ResponseStatus.class) != null)
			throw exception;

		logger.error("Request: " + req.getRequestURI() + " raised " + exception);
		logger.error("Error occured");

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.addObject("timestamp", new Date().toString());
		mav.addObject("status", 500);

		return response;
	}
	private ResponseWrapper getResponse(String message, Integer code) {
		ResponseWrapper wrapper = new ResponseWrapper();
		wrapper.setMessage(message);
		wrapper.setResponseCode(code);
		return wrapper;
	}
}