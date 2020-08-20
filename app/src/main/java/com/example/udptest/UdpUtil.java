package com.example.udptest;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

public class UdpUtil {
    private String receiveMessage;
    private DatagramSocket datagramSocket = null;
    String sendIp = "192.168.0.106";
    int sendPort = 2888;
    byte[] sendBuffer = new byte[2048];
    private DatagramPacket sendPacket;

    String receiveIP = "192.168.0.106";
    int receivePort = 2887;
    byte[] receiveBuf = new byte[2048];
    private DatagramPacket receivePacket;

    private ReadThread readThread;
    private boolean isStop = false;
    private static UdpUtil udpUtil;



    public static UdpUtil getInstance() {
        Log.e("calm","版本———14");
        if (udpUtil == null) {
            udpUtil = new UdpUtil();
        }
        return udpUtil;
    }

    private UdpUtil() {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(receivePort);
            datagramSocket = new DatagramSocket(socketAddress);
            readThread = new ReadThread();
            isStop = false;
            readThread.start();

            InetAddress address = InetAddress.getByName(sendIp);
            sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, sendPort);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class ReadThread extends Thread {
        @Override
        public void run() {
//            super.run();

            receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
            while (!isStop) {
                try {
                    datagramSocket.receive(receivePacket);
                    /**读取数据
                     * 指定使用 UTF-8 编码，对于中文乱码问题，遵循对方发送时使用什么编码，则接收时也使用同样的编码的原则*/
                    receiveMessage = new String(receivePacket.getData(), 0, receivePacket.getLength(), Charset.forName("UTF-8"));
                    System.out.println("收到消息:" + receiveMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void send(String message) {
        try {
            System.out.println("send消息:" + message);
            sendPacket.setData(message.getBytes());
            datagramSocket.send(sendPacket);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeUdp() {
        isStop = true;
        if (readThread != null) {
            readThread.interrupt();
        }
        if (datagramSocket != null) {
            datagramSocket.close();
        }
    }
}