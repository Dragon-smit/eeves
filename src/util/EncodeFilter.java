package util;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器-专门解决请求中所出现的乱码问题
 */
public class EncodeFilter implements Filter {
    private String encode;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化参数
        String encode = filterConfig.getInitParameter("encode");
        if (this.encode == null){
            this.encode = encode;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 每次拦截请求都会执行doFilter这个方法
        if (null == servletRequest.getCharacterEncoding()){
            servletRequest.setCharacterEncoding(encode);
        }
        // 放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        encode = null;
    }
}
