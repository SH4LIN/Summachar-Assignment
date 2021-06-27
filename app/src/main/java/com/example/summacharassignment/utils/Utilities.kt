package com.example.summacharassignment.utils

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import com.example.summacharassignment.BuildConfig
import com.example.summacharassignment.network.RetrofitInterface
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class Utilities {
    companion object{
        fun create(): Utilities{
            return Utilities()
        }
    }

    fun initWebServiceCall(path:String): RetrofitInterface {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = getOkHttpBuilderWithTlsSupport()
        client!!.readTimeout(1, TimeUnit.MINUTES)
        client.connectTimeout(1, TimeUnit.MINUTES)
        client.writeTimeout(1, TimeUnit.MINUTES)
        client.addInterceptor(httpLoggingInterceptor)
        client.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request =
                chain.request().newBuilder().header("X-Api-Key", BuildConfig.X_API_KEY).build()
            chain.proceed(request)
        })
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(path)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RetrofitInterface::class.java)
    }

    fun changeStatusBarColor(activity: Activity, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }


    private fun getOkHttpBuilderWithTlsSupport(): OkHttpClient.Builder? {
        val builder = OkHttpClient.Builder()
        //tls 1.2
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(
                    TlsVersion.TLS_1_2,
                    TlsVersion.TLS_1_1,
                    TlsVersion.TLS_1_0,
                    TlsVersion.SSL_3_0
                )
                .cipherSuites(
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA,
                    CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384,
                    CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA,
                    CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA,
                    CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA256,
                    CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA256,
                    CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA
                )
                .allEnabledCipherSuites()
                .build()
            builder.connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, spec))
        } else {
            builder.sslSocketFactory(YOSSLSocketFactory(), resolveDefaultTrustManager())
        }
        builder.hostnameVerifier { s: String?, sslSession: SSLSession? -> true }
        return builder
    }


    private fun resolveDefaultTrustManager(): X509TrustManager? {
        var x509TrustManager: X509TrustManager? = null
        try {
            val trustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(null as KeyStore?)
            for (trustManager in trustManagerFactory.trustManagers) {
                if (trustManager is X509TrustManager) {
                    x509TrustManager = trustManager
                    break
                }
            }
        } catch (e: NoSuchAlgorithmException) {
            // move along...
        } catch (e: KeyStoreException) {
        }
        return x509TrustManager
    }
}