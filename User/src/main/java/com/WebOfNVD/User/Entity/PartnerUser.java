package com.WebOfNVD.User.Entity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.WebOfNVD.Common.Convert.Convert;
import com.WebOfNVD.Common.Entity.EntityBase;
import com.googlecode.jmapper.annotations.JGlobalMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JGlobalMap(excluded = { "MAXSIZE_CODE", "MINSIZE_CODE", "MINSIZE", "MAXSIZE", "isDelete", "userAdd", "dateAdd",
		"modifiedUser", "modifiedAction", "modifiedDate", "MINSIZE_DESCRIPTION", "MAXSIZE_DESCRIPTION" })
@Getter
@Setter
@Entity(name = "PartnerUsers")
@Table(name = "QLBH_partner_users")
@NoArgsConstructor
public class PartnerUser extends EntityBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static int MAXSIZE_CODE = 8;
	private final static int MINSIZE_CODE = 8;
	private final static int MINSIZE = 12;
	private final static int MAXSIZE = 12;
	private final static int MINSIZE_DESCRIPTION = 50;
	private final static int MAXSIZE_DESCRIPTION = 6000;
//dinh dang user he thong : PAR + ma doi tac + ma user  
	// madoi tac la so 4 chu so
	// ma user la so 5 chu so

	@Id
	@Column(name = "Partner_User_Code", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE_CODE, max = MAXSIZE_CODE)
	private String partnerUserCode;

	@Column(name = "Full_Name", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private String fullName;

	@Column(name = "User_Name", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private String userName;

	@Column(name = "Identify_Number", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE, max = MAXSIZE)
	private String identifyNumber;

	@Column(name = "Birth_Date", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private LocalDateTime birthDate;

	@Column(name = "Password", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private String password;

	@Column(name = "Path_Image_User", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private String pathImageUser;

	@Column(name = "Role_Origin_Partner_User", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private String roleOriginPartnerUser;

	@Column(name = "Group_Partner_User", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private String groupPartnerUser;

	@Size(min = MINSIZE_DESCRIPTION, max = MAXSIZE_DESCRIPTION)
	@NotNull
	@Column(name = "description")
	private String description;

	public void setPassword(String password) throws NoSuchAlgorithmException {
		this.password = Convert.ConvertPassword(password);
	}
	// chi admin moi co the tao va chinh sua doi tac
}
