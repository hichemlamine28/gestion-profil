package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.dynamic_page;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Stéphan R.
 *
 */
public class TagGeneratorDTO {

	private Integer x = 0;
	private Integer y = 0;
	private Integer z = 0;

	private static final Integer LIMIT_NUMBER = 9;

	/**
	 * Default empty constructor
	 */
	public TagGeneratorDTO() {
		super();
	}

	/**
	 * Full constructor
	 *
	 * @param x
	 * @param y
	 * @param z
	 */
	public TagGeneratorDTO(Integer x, Integer y, Integer z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getZ() {
		return z;
	}

	public void setZ(Integer z) {
		this.z = z;
	}

	/**
	 * Increment the tag from z 'til reached the limit number then move to the y and so forth
	 * If the limit of tag is reach (i.e tag of the form 9.9.9) we reset them all to their default values as 0.0.0
	 */
	public void increment() {
		if(this.getZ() < LIMIT_NUMBER) {
			this.setXYZTag(this.x, this.y, ++this.z);
		} else if(this.getY() < LIMIT_NUMBER) {
			this.setXYZTag(this.x, ++this.y, 0);
		} else if(this.getX() < LIMIT_NUMBER) {
			this.setXYZTag(++this.x, 0, 0);
		} else {
			// In case of this form 9.9.9 we reset the tag
			this.setXYZTag(0, 0, 0);
		}
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	private void setXYZTag(Integer x, Integer y, Integer z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}

	/**
	 * Build tag from string
	 *
	 * @param tag
	 *
	 * @return
	 */
	public TagGeneratorDTO buildFromString(String tag) {
		if(StringUtils.isNotBlank(tag)) {
			Pattern pattern = Pattern.compile("([0-9]{1})");
			Matcher matcher = pattern.matcher(tag);
			Integer count = 0;

			while(matcher.find()) {
				count++;

				if(count == 1) {
					this.setX(Integer.valueOf(matcher.group(0)));
				} else if(count == 2) {
					this.setY(Integer.valueOf(matcher.group(0)));
				} else {
					this.setZ(Integer.valueOf(matcher.group(0)));
				}
			}

			return new TagGeneratorDTO(this.getX(), this.getY(), this.getZ());
		}

		return new TagGeneratorDTO();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s.%s.%s", x, y, z);
	}
}
