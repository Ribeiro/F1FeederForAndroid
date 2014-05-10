package br.com.androidos.f1feeder.domain;

import java.io.Serializable;

public class Driver implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8172083178869180199L;

	private Long _id;
	private String code;
	private String url;
	private String givenName;
	private String familyName;
	private String dateOfBirth;
	private String nationality;
	private Integer position;
	private Integer points;
	private Integer wins;
	private String driverId;
	private String flagImage;
	private String scuderia;

	public Driver(Long _id, String code, String url, String givenName,
			String familyName, String dateOfBirth, String nationality,
			Integer position, Integer points, Integer wins, String driverId,
			String flagImage, String scuderia) {
		super();
		this._id = _id;
		this.code = code;
		this.url = url;
		this.givenName = givenName;
		this.familyName = familyName;
		this.dateOfBirth = dateOfBirth;
		this.nationality = nationality;
		this.position = position;
		this.points = points;
		this.wins = wins;
		this.driverId = driverId;
		this.flagImage = flagImage;
		this.scuderia = scuderia;
	}

	public Long getId() {
		return _id;
	}

	public String getCode() {
		return code;
	}

	public String getUrl() {
		return url;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getPoints() {
		return points;
	}

	public Integer getWins() {
		return wins;
	}

	public String getDriverId() {
		return driverId;
	}

	public String getFlagImage() {
		return flagImage;
	}
	
	public String getScuderia() {
		return scuderia;
	}

}