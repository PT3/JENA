package chiffrement;

import java.io.*;
import java.math.*;
import java.security.*;

@SuppressWarnings("serial")
public class RSA implements Serializable
{
    
    private final static BigInteger one = BigInteger.ONE;
    private final static SecureRandom random = new SecureRandom();
    private BigInteger p1 = null;
    private BigInteger p2 = null;
    
    
    private static int N = 1024;          // Taille des cles 1024-bit

    private BigInteger phi0;
    
    
    public String[] keyGen(){
        //p1
        p1 = BigInteger.probablePrime(N / 2, random);
        
        //p2
        p2 = BigInteger.probablePrime(N / 2, random);
        
        //M
        phi0 = (p1.subtract(one)).multiply(p2.subtract(one));
        
        //N
        BigInteger modulus = p1.multiply(p2);
        //d
        BigInteger privateKey = setPrivateKey(modulus);
        //e
        BigInteger publicKey = privateKey.modInverse(phi0);
        
        String tab[] = {publicKey + "", modulus + "", privateKey + ""};
        return tab;
        
    }
    

    /**
     * s'assure que privateKey
     * 1. n'a aucun autre diviseur que 1
     * 2. qu'il est plus grand que p1 et p2
     * 3. qu'il est plus petit que p1 * p2
     * */
    private BigInteger setPrivateKey(BigInteger modulus){
        BigInteger privateKey = null;

        do{
            privateKey = BigInteger.probablePrime(N / 2, random);
        }
        while (privateKey.gcd(phi0).intValue() != 1 ||
               privateKey.compareTo(modulus) != -1 ||
               privateKey.compareTo(p1.max(p2)) == -1);

        return privateKey;
    }
    
    
    /*
     Encrypte le message avec les clees public
     pour l'encryption les clees public doivent etre initialisees
     le message doit être divise en paket de N / 8 octects ou bytes
     */
    public String encrypt(BigInteger message, BigInteger publicKey, BigInteger modulus)
    {
        BigInteger rep = null;
        String str_message = new String(message.toByteArray());
        
        if (str_message.length() <= (N / 8))
        {
            if (publicKey != null && modulus != null &&
                message.toByteArray().length < Integer.MAX_VALUE){
                rep = message.modPow(publicKey, modulus);
            }
        }
        
        return "" + rep;
    }
    
    
    //Decrypte le message avec les clees prive
    public BigInteger decrypt(BigInteger encrypted, BigInteger privateKey, BigInteger modulus){
        return encrypted.modPow(privateKey, modulus);
    }
    
    
    public String Crypt(String p, String pb, String md){
        char a;
        BigInteger b;
        String c = "";
        
        for(int i = 0; i < p.length(); i++){
            a = p.charAt(i);
            b = new BigInteger("" + (int)a);
            c += "" + encrypt(b, new BigInteger(pb), new BigInteger(md)) + (char)0;
        }
        
        return c;
    }
    
    
    public String DeCrypt(String p, String pv, String md){
        char a;
        BigInteger b;
        String c = "", d = "";
        
        for(int i = 0; i < p.length(); i++){
            if((int)p.charAt(i) != 0)
                c += p.charAt(i);
            else{
                b = new BigInteger(c);
                a = (char)decrypt(b, new BigInteger(pv), new BigInteger(md)).intValue();
                d += a;
                c = "";
            }
        }
        return d;
    }
}