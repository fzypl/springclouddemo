package com.ljj.springcloud.entities;

import lombok.Data;

/**
 * @author ljj
 * @create 2020-03-12 11:47
 */
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class CommonResult<T>{
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult() {
    }

    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
