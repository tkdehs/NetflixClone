package com.pnxand.netflixclone.manager

import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class NetworkService {
    private val CONNECT_TIMEOUT: Long = 10
    private val READ_TIMEOUT: Long = 10
    private val WRITE_TIMEOUT: Long = 10

    fun getApiService(): IAPIService {
        return getRetrofitBuilder().create(IAPIService::class.java)
    }

    private fun getRetrofitBuilder(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .client(getOkHttpClient())
        retrofit.addConverterFactory(GsonConverterFactory.create())
        return retrofit.build()
    }

    /**
     * OKHttpClient 설정
     *  @param tag LogCat에 표시 될 TAG
     */
    private fun getOkHttpClient(): OkHttpClient {
        class HeaderInterceptor: Interceptor {
            @Throws(Exception::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest = chain.request().newBuilder()
//                    .addHeader("Authorization", "KakaoAK ca2b5da1cba84dde2a45f81cafbd84b0")
                    .build()
                return chain.proceed(newRequest)
            }
        }

        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.cookieJar(JavaNetCookieJar(cookieManager))
        setUnsafeCert(okHttpBuilder)    // 안전하지 않은 사이트로 이동 처리
        okHttpBuilder.addInterceptor(HeaderInterceptor())

        okHttpBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

        return okHttpBuilder.build()
    }

    /**
     * 안전하지 않은 사이트로 이동 처리
     * @param builder: OkHtppBuilder
     */
    fun setUnsafeCert(builder: OkHttpClient.Builder) {
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier(HostnameVerifier { hostname, session -> true })
    }
}