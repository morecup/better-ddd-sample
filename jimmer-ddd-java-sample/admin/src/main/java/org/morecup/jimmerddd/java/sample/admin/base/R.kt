package org.morecup.jimmerddd.kotlin.sample.admin.base

import com.fasterxml.jackson.annotation.JsonIgnore
//import io.swagger.v3.oas.annotations.media.Schema

data class R<T>(
//    @field:Schema(description = "状态码，200成功，500失败", defaultValue = "200")
    /**
     * 状态码（如200成功）
     */
    val code: Int,    // 状态码（如200成功）
//    @field:Schema(description = "提示信息",defaultValue = "操作成功")
    /**
     * 提示消息
     */
    val message: String, // 提示消息
    val data: T?,      // 泛型数据（可为空）
//    @field:Schema(description = "时间戳", defaultValue = "")
    /**
     * 时间戳
     */
    val timestamp: String = System.currentTimeMillis().toString() + ""
) {
    // 快速判断是否成功
    @get:JsonIgnore()
    val isSuccess: Boolean
        get() = code == ResultType.Success.code

    // 伴生对象提供快速构建方法
    companion object {
        fun success(): R<Nothing>{
            return R(ResultType.Success.code, "操作成功", null)
        }

        fun <T> success(data: T): R<T> {
            return R(ResultType.Success.code, "操作成功", data)
        }

        // 错误响应（无数据）
        fun error(): R<Nothing> {
            return R(ResultType.ERROR.code, "操作失败", null)
        }

        fun <T> error(msg: String): R<T?> {
            return R(ResultType.ERROR.code, msg, null)
        }
    }
}