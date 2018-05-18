package alick.server;

import alick.server.proxyserver.MainActivity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class HelloWorld extends HttpServlet {
    private String message;
    private MainActivity mainActivity;

    @Override
    public void init() throws ServletException {
        message = "我爱吃鸡排!!!";
        mainActivity=new MainActivity();

        System.out.println("初始化init()");
        new Thread(){
            @Override
            public void run() {
                try {
                    mainActivity.launch();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        Locale languageType=request.getLocale();//获取用户语言
        String localIp=request.getLocalAddr();//获取本地ip
        int localPort=request.getLocalPort();//获取本地的端口
        String localName=request.getLocalName();//获取本地计算机的名字
        String remoteIp=request.getRemoteAddr();//获取客户端的ip
        int remotePort=request.getRemotePort();//获取客户端的端口号
        String serverName=request.getRemoteHost();//获取远程计算机的名字

        System.out.println("用户语言:"+languageType);
        System.out.println("本地ip:"+localIp);
        System.out.println("本地的端口:"+localPort);
        System.out.println("本地计算机的名字:"+localName);
        System.out.println("客户端的ip:"+remoteIp);
        System.out.println("客户端的端口号:"+remotePort);
        System.out.println("远程计算机的名字:"+serverName);


        //设置响应内容类型
        resp.setContentType("text/html;charset=utf-8");

        //设置逻辑实现
        PrintWriter out = resp.getWriter();
        out.println("<h3>" + message + "</h3>");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}