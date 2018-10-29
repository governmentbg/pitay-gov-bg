package indexbg.pdoi.db;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.indexbg.system.db.PersistentEntity;



@SqlResultSetMapping(name = "filterEgovOrg", classes = {
		@ConstructorResult(targetClass = EgovOrganisations.class, 
				columns = { 
						@ColumnResult(name = "id", type = Long.class),						
						@ColumnResult(name = "guid", type = String.class),
						@ColumnResult(name = "parrent_guid", type = String.class),
						@ColumnResult(name = "administrative_body_name", type = String.class),				
						@ColumnResult(name = "eik", type = String.class)				
						}) 
		})

@Entity
@Table(name = "egov_organisations")
public class EgovOrganisations implements PersistentEntity, Comparable<EgovOrganisations> {

	private static final long serialVersionUID = -3063804870630448946L;
	
	@SequenceGenerator(name = "EgovOrganisations", sequenceName = "seq_egovorganisations", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EgovOrganisations")
	@Column(name = "id", nullable=false, precision = 10, scale = 0)
	private Long id;
	
	@Column(name = "guid", length = 38)
	private String guid;
	
	@Column(name = "parrent_guid", length = 38)
	private String parrentGuid;
	
	@Column(name = "administrative_body_name", length = 250)
	private String AdministrativeBodyName;
	
	@Column(name = "eik", length = 15)
	private String eik;
	
	
	public EgovOrganisations() {
		
	}
	
	public EgovOrganisations(Long id ,String guid ,String parrentGuid , String AdministrativeBodyName ,String eik) {
		this.id = id;
		this.guid = guid;
		this.parrentGuid = parrentGuid;
		this.AdministrativeBodyName = AdministrativeBodyName;
		this.eik = eik;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getGuid() {
		return guid;
	}


	public void setGuid(String guid) {
		this.guid = guid;
	}


	public String getParrentGuid() {
		return parrentGuid;
	}


	public void setParrentGuid(String parrentGuid) {
		this.parrentGuid = parrentGuid;
	}


	public String getAdministrativeBodyName() {
		return AdministrativeBodyName;
	}


	public void setAdministrativeBodyName(String administrativeBodyName) {
		AdministrativeBodyName = administrativeBodyName;
	}
	
	

	public int compareTo(EgovOrganisations o) {

			return this.getAdministrativeBodyName().compareTo(o.getAdministrativeBodyName());

	}


	public String getEik() {
		return eik;
	}


	public void setEik(String eik) {
		this.eik = eik;
	}


	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
