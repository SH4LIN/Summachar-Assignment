package com.example.summacharassignment.utils

import java.io.IOException
import java.net.InetAddress
import java.net.Socket
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.*

class YOSSLSocketFactory() : SSLSocketFactory() {
    private val kmf: KeyManagerFactory? = null
    private var socketFactory: SSLSocketFactory? = null


    init {
        try {
            val sContext = SSLContext.getInstance("TLSv1.2")
            sContext.init(null, null, null)
            socketFactory = sContext.socketFactory
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    override fun createSocket(s: Socket?, host: String?, port: Int, autoClose: Boolean): Socket? {
        val ss = socketFactory!!.createSocket(s, host, port, autoClose) as SSLSocket
        ss.enabledProtocols = ss.supportedProtocols
        ss.enabledCipherSuites = ss.supportedCipherSuites
        return ss
    }

    override fun getDefaultCipherSuites(): Array<String?>? {
        return socketFactory!!.defaultCipherSuites
    }

    override fun getSupportedCipherSuites(): Array<String?>? {
        return socketFactory!!.supportedCipherSuites
    }

    @Throws(IOException::class)
    override fun createSocket(host: String?, port: Int): Socket? {
        val ss = socketFactory!!.createSocket(host, port) as SSLSocket
        ss.enabledProtocols = ss.supportedProtocols
        ss.enabledCipherSuites = ss.supportedCipherSuites
        return ss
    }

    @Throws(IOException::class)
    override fun createSocket(host: InetAddress?, port: Int): Socket? {
        val ss = socketFactory!!.createSocket(host, port) as SSLSocket
        ss.enabledProtocols = ss.supportedProtocols
        ss.enabledCipherSuites = ss.supportedCipherSuites
        return ss
    }

    @Throws(IOException::class)
    override fun createSocket(host: String?, port: Int, localHost: InetAddress?, localPort: Int): Socket? {
        val ss = socketFactory!!.createSocket(host, port, localHost, localPort) as SSLSocket
        ss.enabledProtocols = ss.supportedProtocols
        ss.enabledCipherSuites = ss.supportedCipherSuites
        return ss
    }

    @Throws(IOException::class)
    override fun createSocket(
        address: InetAddress?, port: Int, localAddress: InetAddress?,
        localPort: Int
    ): Socket? {
        val ss = socketFactory!!.createSocket(address, port, localAddress, localPort) as SSLSocket
        ss.enabledProtocols = ss.supportedProtocols
        ss.enabledCipherSuites = ss.supportedCipherSuites
        return ss
    }
}