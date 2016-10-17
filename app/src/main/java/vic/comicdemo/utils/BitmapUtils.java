package vic.comicdemo.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * ͼƬ������ع�����
 */
public class BitmapUtils {
	
	/**
	 * ���ļ��ж�ȡһ��Bitmap
	 * @param file
	 * @return
	 */
	public static Bitmap loadBitmap(File file){
		if(!file.exists()){ 
			return null;
		}
		Bitmap b = BitmapFactory.decodeFile(file.getAbsolutePath());
		return b;
	}
	
	/**
	 * ��bitmapѹ����jpg��ʽ���浽File�ļ���
	 * @param bitmap
	 * @param file
	 * @throws FileNotFoundException 
	 */
	public static void save(Bitmap bitmap, File file) throws FileNotFoundException{
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs(); //��Ŀ¼�������򴴽� 
		}
		FileOutputStream fos = new FileOutputStream(file);
		bitmap.compress(CompressFormat.JPEG, 100, fos);
	}
	

	/**
	 * ͨ��һ��������  �����û���Ҫ�Ĵ�С����ͼƬѹ���� ����Bitmap����
	 * @param is   ����Դ
	 * @param width   ͼƬ��Ŀ����
	 * @param height   ͼƬ��Ŀ��߶�
	 * @return  ѹ�������Bitmap
	 */
	public static Bitmap loadBitmap(InputStream is, int width, int height) throws IOException {
		//1. ���������� ��ȡ��byte[] 
		//���������е����� д�� �ֽ��������
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[10*1024];
		int length = 0;
		while((length=is.read(buffer)) != -1){
			bos.write(buffer, 0, length);
			bos.flush();
		}
		//��������еõ�byte[]  ����һ������Bitmap����
		byte[] bytes = bos.toByteArray();
		bos.close();
		//2. ����byte[]  ��ȡͼƬ��ԭʼ�����
		Options opts = new Options();
		//��������ͼƬ��bounds����
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
		int w = opts.outWidth / width; //��ȵ�ѹ������
		int h = opts.outHeight / height; //�߶ȵ�ѹ������
		//3. �����û������� �����ѹ������
		int scale = w > h ? w : h;
		//4. �ٴν���byte[] ��ȡѹ�������ͼƬ
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = scale;
		Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
		return bitmap;
	}
	
}
