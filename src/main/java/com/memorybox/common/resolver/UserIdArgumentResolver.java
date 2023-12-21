package com.memorybox.common.resolver;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {

    public static final String USER_ID_HEADER_KEY = "UserId";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean existAuthUserIdAnnotation = parameter.getParameterAnnotation(CertUserId.class) != null;
        boolean isUserIdClass = long.class.equals(parameter.getParameterType()) || Long.class.equals(parameter.getParameterType());

        return existAuthUserIdAnnotation && isUserIdClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        CertUserId annotation = parameter.getParameterAnnotation(CertUserId.class);
        assert annotation != null;

        String userId = webRequest.getHeader(USER_ID_HEADER_KEY);
        if (StringUtils.isBlank(userId)) {
            if (annotation.required()) {
                throw new IllegalArgumentException("Has no userId Error");
            }
            return 0L;
        }
        return Long.parseLong(userId);
    }
}