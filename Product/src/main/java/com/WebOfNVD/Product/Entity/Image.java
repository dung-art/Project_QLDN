package com.WebOfNVD.Product.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.googlecode.jmapper.annotations.JGlobalMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JGlobalMap(excluded = { "MINSIZE" })
@Getter
@Setter
@Entity(name = "Images")
@Table(name = "QLBH_image")
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//		private final static int  MAXSIZE_CODE= 10;
//		private final static int  MINSIZE_CODE = 5;
	private final static int MINSIZE = 1;

	@Id
	@Column(name = "Id", nullable = false, insertable = true)
	@NotNull
//		@GeneratedValue(strategy = GenerationType.IDENTITY)
//		@Size(min = MINSIZE_CODE ,max = MAXSIZE_CODE)
	private String id;

	@Column(name = "Image_Name", insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private String imageName;

	@Column(name = "Image_Data", insertable = true)
	@NotNull
	private byte[] imageData;
}