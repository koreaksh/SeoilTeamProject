package cf.untitled.salend.retrofit

import cf.untitled.salend.model.ProductArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//RetorifitService.kt
interface RetrofitService {

    //GET 예제
    @GET("posts/1")
    fun getProductArray(): Call<ProductArray>

    @GET("posts/{page}")
    fun getProductArrayPage(@Path("page") page: String): Call<ProductArray>

    @GET(".")
    fun getProductArrayPage2(): Call<ProductArray>

    @GET("test")
    fun getProductArrayPage3(): Call<ProductArray>


//    @GET("posts/1")
//    fun getStudent(@Query("school_id") schoolId: Int,
//                   @Query("grade") grade: Int,
//                   @Query("classroom") classroom: Int): Call<ExampleResponse>
//
//
//    //POST 예제
//    @FormUrlEncoded
//    @POST("posts")
//    fun getContactsObject(@Field("idx") idx: String): Call<JsonObject>
}