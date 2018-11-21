package model;

import java.io.Serializable;

import javafx.scene.image.Image;

public class Photo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6955723612371190680L;
	
	/**
	 * file name of a photo
	 */
	String photoFileName;
	
	/**
	 * Caption of a photo
	 */
	String caption;
	
	/**
	 * date of photo
	 */
	long dateOfPhoto;
	
	Image imageview;
	
	public Image getImageview(String filename) {
		this.imageview = new Image(filename);
		return imageview;
	}
	
	
	
	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public void setDateOfPhoto(long dateOfPhoto) {
		this.dateOfPhoto = dateOfPhoto;
	}

	/**
	 * Initialize Photo object
	 * @param photoFileName file name of photo
	 * @param caption caption of photo
	 * @param dateOfPhoto date of photo
	 */
	public Photo(String photoFileName, String caption, long dateOfPhoto){
		this.photoFileName = photoFileName;
		this.caption = caption;
		this.dateOfPhoto = dateOfPhoto;
	}
	
	/**
	 * @return date of photo
	 */
	public long getDateOfPhoto(){
		return dateOfPhoto;
	}
}
