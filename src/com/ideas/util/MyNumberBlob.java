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

	/**�ǩ`��������byte[]*/
	private byte[] bytes = new byte[0];
	/**�ǩ`��length*/
	private int length = 0;
	
	 /**
     * byte[]�� MyNumberBlob����
     * @param byte[]
     */
    public MyNumberBlob(byte[] _bytes) {
    	bytes=_bytes;
    	length=_bytes.length;
    }
    /**
     * InputStream�� MyNumberBlob����
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
     * Blob�� MyNumberBlob����
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
     * MyNumberBlob��lengthȡ��
     * @param �ʤ�
     * @return long
     */
    @Override
    public long length() throws SQLException{
    	return bytes.length;
    }
	 /**
     * byte[]�� MyNumberBlobȡ��
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
            throw new SQLException("parameter�����Фι�����������ǤϤʤ��Ǥ���");
        }
    }
	 /**
     * InputStream�� MyNumberBlobȡ��
     * @param �ʤ�
     * @return InputStream
     */
    @Override
    public InputStream getBinaryStream () throws SQLException{
    	return new ByteArrayInputStream(bytes);
    }
	 /**
     * long start�����������������byte[]���_ʼpositionȡ��
     * @param byte pattern[], long start
     * @return long
     */
    @Override
    @Deprecated
    public long position(byte pattern[], long start) throws SQLException{
        // ʹ�ä��ʤ�����������
        nonsupport();
    	start--;
         if (start < 0) {
             throw new SQLException("start<0�Ǥ�");
         }
         if (start >bytes.length) {
             throw new SQLException("start >max length");
         }
         if (pattern == null) {
             throw new SQLException("��������byte[]��null�Ǥ�");
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
     * long start�����������������Blob���_ʼpositionȡ��
     * @param pattern
     * @param start
     * @return
     * @throws SQLException
     */
    @Override
    @Deprecated
    public long position(Blob pattern, long start) throws SQLException {
        // ʹ�ä��ʤ�����������
        nonsupport();
        return position(blobToBytes(pattern), start);
    }
  
    /**
     * byte[]��set����
     * 
     * @param pos
     * @param bytes
     * @return
     * @throws SQLException
     */
    @Override
    @Deprecated
    public int setBytes(long pos, byte[] bytes) throws SQLException{
        // ʹ�ä��ʤ�����������
        nonsupport();
        return setBytes(pos, bytes, 0, bytes.length, true);
    }

    /**
     * byte[]��set����
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
        // ʹ�ä��ʤ�����������
        nonsupport();
        return setBytes(pos, bytes, offset, len, true);
    }
    
    /**
     * byte[]��set����
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
        // ʹ�ä��ʤ�����������
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
        //copy data�Έ���
        if (copy) {
            bytes = new byte[size];
            System.arraycopy(_bytes, offset, bytes, 0, size);
        } else { 
            bytes = _bytes;
        }
        return bytes.length;
    }
    
    /**
     * methodʹ�ä��ʤ�����`
     * 
     */
    private void nonsupport() {
        throw new UnsupportedOperationException("����methodʹ�ä��ʤ�����������");
    }
    /**
     * ָ��length�Υǩ`����OutputStream�Ǒ���
     *
     */
   public java.io.OutputStream setBinaryStream(long pos) throws SQLException{
       // ʹ�ä��ʤ�����������
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
    * cutһ���֥ǩ`��
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
    * InputStream�� MyNumberBlobȡ��
    * @param �ʤ�
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