package com.ideas.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;



public  class MyNumberBlob implements Blob{

	/**データ保存用byte[]*/
	private byte[] bytes = new byte[0];
	/**データlength*/
	private int length = 0;
	
	 /**
     * byte[]で MyNumberBlob作成
     * @param byte[]
     */
    public MyNumberBlob(byte[] _bytes) {
    	bytes=_bytes;
    	length=_bytes.length;
    }
    /**
     * InputStreamで MyNumberBlob作成
     * 
     * @param bytes
     */
    public MyNumberBlob(InputStream input) {
		try{
			  int offset = 0;
		      int numRead = 0;
		      while (offset < bytes.length
		               && (numRead=input.read(bytes, offset, bytes.length-offset)) >= 0) {
		            offset += numRead;
		        }
				input.close();
		}catch (Exception e){
			e.printStackTrace();
		}
    }
	 /**
     * Blobで MyNumberBlob作成
     * @param byte[]
     */
    public MyNumberBlob(Blob blob) {
        init(blobToBytes(blob));
    }
    /**
     * init byte[]
     * 
     * @param b
     */
    private void init(byte[] _bytes) {
        bytes = _bytes;
        length = bytes.length;
    }
	 /**
     * MyNumberBlobのlength取得
     * @param なし
     * @return long
     */
    @Override
    public long length() throws SQLException{
    	return bytes.length;
    }
	 /**
     * byte[]で MyNumberBlob取得
     * @param long pos, int length
     * @return byte[]
     */
    @Override
    public byte[] getBytes(long pos, int length) throws SQLException{
        if (pos == 0 && length == length())
            return bytes;
        try {
            byte[] newbytes = new byte[length];
            System.arraycopy(bytes, (int) pos, newbytes, 0, length);
            return newbytes;
        } catch (Exception e) {
            throw new SQLException("parameterの配列の範囲は正しいではないです。");
        }
    }
	 /**
     * InputStreamで MyNumberBlob取得
     * @param なし
     * @return InputStream
     */
    @Override
    public InputStream getBinaryStream () throws SQLException{
    	return new ByteArrayInputStream(bytes);
    }
	 /**
     * long startから検索、検索対象byte[]の開始position取得
     * @param byte pattern[], long start
     * @return long
     */
    @Override
    @Deprecated
    public long position(byte pattern[], long start) throws SQLException{
        // 使用しないください。
        nonsupport();
    	start--;
         if (start < 0) {
             throw new SQLException("start<0です");
         }
         if (start >bytes.length) {
             throw new SQLException("start >max length");
         }
         if (pattern == null) {
             throw new SQLException("検索対象byte[]はnullです");
         }
         if (pattern.length == 0 || bytes.length == 0 || pattern.length > bytes.length) {
             return -1;
         }
         int limit = (int) bytes.length - pattern.length;
         for (int i = (int) start; i <= limit; i++) {
             int p;
             for (p = 0; p < pattern.length && bytes[i + p] == pattern[p]; p++) {
                 if (p == pattern.length) {
                     return i + 1;
                 }
             }
         }
         return -1;
    }
    
    /**
     * long startから検索、検索対象Blobの開始position取得
     * @param pattern
     * @param start
     * @return
     * @throws SQLException
     */
    @Override
    @Deprecated
    public long position(Blob pattern, long start) throws SQLException {
        // 使用しないください。
        nonsupport();
        return position(blobToBytes(pattern), start);
    }
  
    /**
     * byte[]をsetする
     * 
     * @param pos
     * @param bytes
     * @return
     * @throws SQLException
     */
    @Override
    @Deprecated
    public int setBytes(long pos, byte[] bytes) throws SQLException{
        // 使用しないください。
        nonsupport();
        return setBytes(pos, bytes, 0, bytes.length, true);
    }

    /**
     * byte[]をsetする
     * 
     * @param pos
     * @param bytes
     * @param offset
     * @param len
     * @return
     * @throws SQLException
     */
    @Override
    @Deprecated
   public int setBytes(long pos, byte[] bytes, int offset, int len) throws SQLException{
        // 使用しないください。
        nonsupport();
        return setBytes(pos, bytes, offset, len, true);
    }
    
    /**
     * byte[]をsetする
     *
     * @param pos
     * @param bytes
     * @param offset
     * @param size
     * @param copy
     * @return
     * @throws SQLException
     */
    @Deprecated
    private int setBytes(long pos, byte[] _bytes, int offset, int size,
            boolean copy) throws SQLException {
        // 使用しないください。
        nonsupport();
        pos--;
        if (pos < 0) {
            throw new SQLException("pos < 0");
        }
        if (pos > bytes.length) {
            throw new SQLException("pos > max length");
        }
        if (bytes == null) {
            throw new SQLException("bytes == null");
        }
        if (offset < 0 || offset > bytes.length) {
            throw new SQLException("offset < 0 || offset > bytes.length");
        }
        if (size < 0 || pos + size > (long) Integer.MAX_VALUE
                || offset + size > bytes.length) {
            throw new SQLException();
        }
        //copy dataの場合
        if (copy) {
            bytes = new byte[size];
            System.arraycopy(_bytes, offset, bytes, 0, size);
        } else { 
            bytes = _bytes;
        }
        return bytes.length;
    }
    
    /**
     * method使用しないエラー
     * 
     */
    private void nonsupport() {
        throw new UnsupportedOperationException("このmethod使用しないください！");
    }
    /**
     * 指定lengthのデータをOutputStreamで戻る
     *
     */
   public java.io.OutputStream setBinaryStream(long pos) throws SQLException{
       // 使用しないください。
       nonsupport();
       pos--;
       if (pos < 0) {
           throw new SQLException("pos < 0");
       }
       if (pos > bytes.length) {
           throw new SQLException("pos > length");
       }
       // byte[] to ByteArrayInputStream
       ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
       ByteArrayOutputStream os = new ByteArrayOutputStream();
       byte[] bytes = new byte[(int) pos];
       try {
           bytes = new byte[inputStream.available()];
           int read;
           while ((read = inputStream.read(bytes)) >= 0) {
               os.write(bytes, 0, read);
           }

       } catch (IOException e) {
           e.getStackTrace();
       }
       //return OutputStream
       return (OutputStream) os;
    }

   /**
    * cut一部分データ
    *
    */
   public void truncate(long len) throws SQLException{
       if (len < 0) {
           throw new SQLException("len < 0");
       }
       if (len > bytes.length) {
           throw new SQLException("len > max length");
       }
       length = (int) len;
   }
   /**
    * free memory
    *
    */
   public void free() throws SQLException{
       bytes = new byte[0];
       length = 0;
   }
	 /**
    * InputStreamで MyNumberBlob取得
    * @param なし
    * @return InputStream
    */
  public  InputStream getBinaryStream(long pos, long length) throws SQLException{
	  return new ByteArrayInputStream(getBytes(pos, (int) length));
   }
  /**
   * blob to byte[]
   *
   * @param blob
   * @return
   */
  private byte[] blobToBytes(Blob blob) {
      BufferedInputStream is = null;
      try {
          is = new BufferedInputStream(blob.getBinaryStream());
          byte[] bytes = new byte[(int) blob.length()];
          int len = bytes.length;
          int offset = 0;
          int read = 0;
          while (offset < len
                  && (read = is.read(bytes, offset, len - offset)) >= 0) {
              offset += read;
          }
          return bytes;
      } catch (Exception e) {
          return null;
      } finally {
          try {
              is.close();
              is = null;
          } catch (IOException e) {
              return null;
          }
      }
  }


}
//