package com.pnxand.netflixclone.manager

import com.pnxand.netflixclone.model.RcvTitle
import com.pnxand.netflixclone.model.Title
import retrofit2.http.*


/**
 * Retrofit 카카오 책 검색 API Interface
 */
interface IAPIService {
    @GET("${Constants.API_URL}/3/trending/movie/day?api_key=${Constants.API_KEY}")
    suspend fun getTrendingMovies(): RcvTitle

    @GET("${Constants.API_URL}/3/trending/tv/day?api_key=${Constants.API_KEY}")
    suspend fun getTrendingTv(): RcvTitle

    @GET("${Constants.API_URL}/3/movie/popular?api_key=${Constants.API_KEY}")
    suspend fun getPopular(): RcvTitle

    @GET("${Constants.API_URL}/3/movie/upcoming?api_key=${Constants.API_KEY}&language=ko-KR&page=1")
    suspend fun getUpcomingMovies(): RcvTitle

    @GET("${Constants.API_URL}/3/movie/top_rated?api_key=${Constants.API_KEY}")
    suspend fun getTopRated(): RcvTitle

}