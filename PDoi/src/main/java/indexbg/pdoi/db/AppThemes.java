package indexbg.pdoi.db;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.indexbg.system.db.TrackableEntity;

import indexbg.pdoi.system.Constants;

@SqlResultSetMapping(name = "filterByIdApplic", classes = {
		@ConstructorResult(targetClass = AppThemes.class, 
				columns = { 
						@ColumnResult(name = "id", type = Long.class),
						@ColumnResult(name = "theme_value", type = Long.class)					 
						}) 
		})

@Entity
@Table(name = "pdoi_app_themes")
public class AppThemes extends TrackableEntity {	

	/** Основен java Entity клас на тематиките към заявленията
	 * 
	 */
	private static final long serialVersionUID = -1297606679058891585L;

	// properties for object Event	
	@SequenceGenerator(name = "AppThemes", sequenceName = "seq_app_themes", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "AppThemes")
	@Column(name = "id", nullable = false, precision = 10, scale = 0)
	private Long id;
	
	@Column(name="application_id", nullable = false, precision = 10, scale = 0)	
	private Long applicationId; 	
	
	@Column(name="theme_value", nullable = false, precision = 10, scale = 0)	
	private Long themeValue; 	
	
	/** default constructor */
	public AppThemes() {
		
	}

	public AppThemes(Long id, Long themeValue) {		
		this.id = id;		
		this.themeValue = themeValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getThemeValue() {
		return themeValue;
	}

	public void setThemeValue(Long themeValue) {
		this.themeValue = themeValue;
	}

	@Override
	public Long getUserReg() {
		// TODO Auto-generated method stub
		return super.getUserReg();
	}
	
	@Override
	public Date getDateReg() {
		// TODO Auto-generated method stub
		return super.getDateReg();
	}
	
	@Override
	public Long getUserLastMod() {
		// TODO Auto-generated method stub
		return super.getUserLastMod();
	}
	
	@Override
	public Date getDateLastMod() {
		// TODO Auto-generated method stub
		return super.getDateLastMod();
	}
	
	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return Constants.CODE_OBJECT_APP_THEMES;
	}
	
}
