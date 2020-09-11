package com.glg.okhttptest;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * Description:
 *
 * @package: com.glg.okhttptest
 * @className: HttpsCertificateVerifyHelper
 * @author: gao
 * @date: 2020/8/24 18:52
 */
public class HttpsCertificateVerifyHelper {

    /*------------------------设置信任所有证书 start------------------------*/
    public static OkHttpClient.Builder trustAllCertificate(OkHttpClient.Builder okBuilder) {
        initSSLSocketFactoryAndX509TrustManager();
        //sslSocketFactory 方法: 设置信任所有证书,无需使用CA证书对服务器证书验证
        //hostnameVerifier方法:设置域名验证成功，我没有设置这个，https接口也是可以使用的，可能默认返回的true;
        // 但是我让其返回false，接口出错Hostname  xxxxhost not verified
        okBuilder.sslSocketFactory(sslSocketFactory, trustManager)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
        return okBuilder;

    }

    private static X509TrustManager trustManager;
    private static SSLSocketFactory sslSocketFactory;

    private static void initSSLSocketFactoryAndX509TrustManager() {
        try {
            trustManager = new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*------------------------设置信任所有证书 end------------------------*/


    /*------------------------设置信任内置的服务器证书 start------------------------*/

    /**
     * 指定本地的服务器证书来认证 服务器的证书（服务器证书）
     *
     * @param okBuilder
     * @param context
     * @param assetsFileName 证书在assets中的文件名  .cer 格式或者 .pem格式
     * @return
     */
    public static OkHttpClient.Builder trustSpecificCertificate(OkHttpClient.Builder okBuilder, Context context, String assetsFileName) {
        initSSLSocketFactoryAndX509TrustManager(context, assetsFileName);
        //sslSocketFactory 方法: 设置信任所有证书,无需使用CA证书对服务器证书验证
        //hostnameVerifier方法:设置域名验证成功，我没有设置这个，https接口也是可以使用的，可能默认返回的true;
        // 但是我让其返回false，接口出错Hostname  xxxxhost not verified
        okBuilder.sslSocketFactory(sslSocketFactory, trustManager)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
        return okBuilder;

    }

    public static void initSSLSocketFactoryAndX509TrustManager(final Context context, final String assetsFileName) {
        try {
            trustManager = new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    if (chain == null || chain.length == 0) {
                        throw new CertificateException("checkServerTrusted: X509Certificate array is null");
                    }

                    if (!(null != authType && authType.equals("ECDHE_RSA"))) {
                        throw new CertificateException("checkServerTrusted: AuthType is not ECDHE_RSA");
                    }
                    //判断证书是否是本地信任列表里颁发的证书(系统默认的验证)
                    try {
                        TrustManagerFactory factory = TrustManagerFactory.getInstance("X509");
                        factory.init((KeyStore) null);
                        for (TrustManager trustManager : factory.getTrustManagers()) {
                            ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
                        }
                        return;//用系统的证书验证服务器证书,验证通过就不需要继续验证证书信息；也可以注释掉，继续走自己的服务器证书逻辑
                    } catch (Exception e) {
                        e.printStackTrace();
                        //注意这个地方不能抛异常,用系统的证书验证服务器证书，没通过就用自己的验证规则
//                        throw new CertificateException(e);
                    }

                    //获取本地证书中的信息
                    String clientEncoded = "";//公钥
                    String clientSubject = "";//颁发给
                    String clientIssUser = "";//颁发机构
                    try (InputStream inputStream = getAssetFileInputStream(context, assetsFileName)) {
                        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                        X509Certificate clientCertificate = (X509Certificate) certificateFactory.generateCertificate(inputStream);
                        clientEncoded = new BigInteger(1, clientCertificate.getPublicKey().getEncoded()).toString(16);
                        clientSubject = clientCertificate.getSubjectDN().getName();
                        clientIssUser = clientCertificate.getIssuerDN().getName();

                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new CertificateException(e);
                    }

                    //获取网络中的证书信息
                    X509Certificate certificate = chain[0];
                    PublicKey publicKey = certificate.getPublicKey();
                    String serverEncoded = new BigInteger(1, publicKey.getEncoded()).toString(16);

                    if (!clientEncoded.equals(serverEncoded)) {
                        throw new CertificateException("server's PublicKey is not equals to client's PublicKey");
                    }
                    String subject = certificate.getSubjectDN().getName();
                    if (!clientSubject.equals(subject)) {
                        throw new CertificateException("server's SubjectDN is not equals to client's SubjectDN");
                    }
                    String issuser = certificate.getIssuerDN().getName();
                    if (!clientIssUser.equals(issuser)) {
                        throw new CertificateException("server's IssuerDN is not equals to client's IssuerDN");
                    }
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static InputStream getAssetFileInputStream(Context context, String assetsFileName) {
        try {
            return context.getAssets().open(assetsFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*------------------------设置信任内置的服务器证书 end------------------------*/
}