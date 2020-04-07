package com.simba.thread.web;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class main {

	public static void main(String[] args) throws IOException {
		//启用socket服务
		ServerSocket serverSocket = new ServerSocket(8899);
		System.out.println("开启端口的监听...");
		while(!Thread.interrupted()){
			//不停的监听客户端服务
			Socket socket = serverSocket.accept();
			//获得输入输出流
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			//读取请求
//			int len =0;
//			byte[] bytes = new byte[1024];
//			if((len =inputStream.read(bytes)) !=-1){
//				System.out.println("获取输入流：\n"+new String(bytes,0,len));
//			}

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = bufferedReader.readLine();
			System.out.println(line);

			//给用户响应
			PrintWriter printWriter = new PrintWriter(outputStream);
			InputStream inputStream1 = new FileInputStream("/Users/lilei/1.html");
			BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1));
			printWriter.println("HTTP/1.1 200 OK");
			printWriter.println("Content-Type:text/html;charset=utf-8");
			printWriter.println("Content-Length:"+inputStream1.available());
			printWriter.println("HTTP/1.1 200 OK");
			printWriter.println("HTTP/1.1 200 OK");


			String c = null;
			while((c=bufferedReader1.readLine())!=null){
				printWriter.print(c);
			}
			printWriter.flush();



		}


	}
}
