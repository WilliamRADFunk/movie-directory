package data.entities;

public class Movie
{
	private int id;
	private String title;
	private String synopsis;
	private double expectedPopularity;
	private double actualPopularity;
	private int optimalSeason;
	private int worstSeason;
	private double costLicense;
	private int licenseLength;
	private String producedBy;
	private String dateCreated;
	private String dateModified;
	
	public Movie(int id, String title, String synopsis, double expectedPopularity, double actualPopularity, int optimalSeason, int worstSeason, double costLicense, int licenseLength, String producedBy, String dateCreated, String dateModified)
	{
		this.id = id;
		this.title = title;
		this.synopsis = synopsis;
		this.expectedPopularity = expectedPopularity;
		this.actualPopularity = actualPopularity;
		this.optimalSeason = optimalSeason;
		this.worstSeason = worstSeason;
		this.costLicense = costLicense;
		this.licenseLength = licenseLength;
		this.producedBy = producedBy;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}
	
	public int getId() { return id; }
	public String getTitle() { return title; }
	public String getSynopsis() { return synopsis; }
	public Double getExpectedPopularity() { return expectedPopularity; }
	public Double getActualPopularity() { return actualPopularity; }
	public int getOptimalSeason() { return optimalSeason; }
	public int getWorstSeason() { return worstSeason; }
	public Double getCostLicense() { return costLicense; }
	public int getLicenseLength() { return licenseLength; }
	public String getProducedBy() { return producedBy; }
	public String getDateCreated() { return dateCreated; }
	public String getDateModified() { return dateModified; }
}
