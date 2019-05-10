package com.example.myapplication.model;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
* Model层的实现类
* Model层的主要作用是 获取网络数据 并通过接口回调的方式回调给 P层
* */
public class RegisterModel implements IModel {
    public static final String REQUEST_URL = "http://172.17.8.100/small/user/v1/register";
    @Override
    public void requestData(String usetName, String pwd, CallBack callBack) {
        new MyTack(usetName,pwd,callBack).execute(REQUEST_URL);
    }
    class MyTack extends AsyncTask<String,Void,String>{
        private String mUserName;
        private String mPwd;
        private CallBack callBack;

        public MyTack(String mUserName, String mPwd, CallBack callBack) {
            this.mUserName = mUserName;
            this.mPwd = mPwd;
            this.callBack = callBack;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                OutputStream outputStream = connection.getOutputStream();
                String params = "phone="+mUserName+"&pwd="+mPwd;
                //String params = "phone="+mUserName+"&pwd="+mPwd;
                outputStream.write(params.getBytes());
                outputStream.flush();
                outputStream.close();
                if (connection.getResponseCode() == 200){
                    InputStream inputStream = connection.getInputStream();
                    String str = getStr(inputStream);
                    return str;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            callBack.setData(s);
        }
    }

    private String getStr(InputStream inputStream) throws IOException {
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        int leng = 0;
        while ((leng = inputStream.read(bytes))!=-1){
            String info = new String(bytes,0,leng);
            buffer.append(info);
        }
        return buffer.toString();
    }
}
