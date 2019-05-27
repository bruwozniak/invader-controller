package com.invader.controller.udp

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import kotlinx.coroutines.*


class Invader(ipAddress: String, localPort: Int, private val remotePort: Int) {
    private val udpSocket = DatagramSocket(localPort)
    private var serverAddr: InetAddress = InetAddress.getByName(ipAddress)

    fun send(data: String) = runBlocking {
        launch(Dispatchers.Default) {
            val buf = data.toByteArray()
            val packet = DatagramPacket(buf, buf.size, serverAddr, remotePort)
            udpSocket.send(packet)
        }
    }

    fun changeIP(newAddr: String) {
        serverAddr = InetAddress.getByName(newAddr)
    }
}
