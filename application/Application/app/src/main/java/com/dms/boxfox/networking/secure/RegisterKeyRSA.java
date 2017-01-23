package com.dms.boxfox.networking.secure;

import android.os.AsyncTask;
import android.util.Base64;


import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.datamodel.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RegisterKeyRSA {
    //해당 커멘드는 서버와 상의후 다시 결정
    public static final int COMMAND_RSA = 7513;
    public static final int COMMAND_AES = 7514;

    //프로토콜에 암호화를 수행하기 위해 스플래쉬 화면에서 해당 메소드 호출
    //Gradle의 dependency 라이브러리 반드시 필요
    public static void register(){
        new RSARegister().execute();
    }

    private static class RSARegister extends AsyncTask {
        @Override
        protected Object doInBackground(Object... params) {
            try {
                Response response = HttpBox.post().setCommand(COMMAND_RSA).putBodyData().push();
                if (response.getCode() == Response.SUCCESS) {
                    JSONObject obj = response.getJsonObject();
                    String key = getResult(obj.getString("PublicKey"), AESKeyGenerator.merge(obj.getString("KeyPice")));
                    JSONObject requestObj = new JSONObject();
                    requestObj.put("AESKey", key);
                    response = HttpBox.post().setCommand(COMMAND_AES).putBodyData(requestObj).push();
                    if(response.getCode() == Response.SUCCESS){
                        new AES256(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException e) {
                e.printStackTrace();
            }
            return null;
        }

        public String getResult(String publicKey, String aesKey) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            byte[] keyBytes = org.apache.commons.codec.binary.Base64.decodeBase64(publicKey.getBytes("utf-8"));
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey key = keyFactory.generatePublic(spec);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] input = aesKey.getBytes();
            byte[] cipherText = cipher.doFinal(input);
            return new String(Base64.encodeToString(cipherText, Base64.DEFAULT));
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
