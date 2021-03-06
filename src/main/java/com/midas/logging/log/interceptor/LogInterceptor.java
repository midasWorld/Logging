package com.midas.logging.log.interceptor;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class LogInterceptor implements HandlerInterceptor {
	private final ObjectMapper objectMapper;

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler) throws Exception
	{
		String[] content = handler.toString().split("\\.");
		log.warn("{} invoked", (content)[content.length - 1]);
		return true;
	}

	@Override
	public void afterCompletion(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler,
		Exception ex) throws Exception
	{
		ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
		ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;

		String params = request.getParameterMap().entrySet().stream()
			.map(entry -> String.format("{\"%s\":\"%s\"}", entry.getKey(), Arrays.toString(entry.getValue())))
			.collect(Collectors.joining(","));

		log.warn("Params: {}", params);
		log.warn("RequestBody: {}", objectMapper.readTree(cachingRequest.getContentAsByteArray()));
		log.warn("ResponseBody: {}", objectMapper.readTree(cachingResponse.getContentAsByteArray()));
	}
}
